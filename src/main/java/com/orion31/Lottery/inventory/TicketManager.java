package com.orion31.Lottery.inventory;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapelessRecipe;

public class TicketManager {
    
    private static Random idGenerator = new Random();
    private static HashMap<Long, Player> registeredIds = new HashMap<Long, Player>();
    private static long sessionDefaultId;
    
    public static void init(NamespacedKey nKey) {
	sessionDefaultId = idGenerator.nextLong();
	ShapelessRecipe recipe = new ShapelessRecipe(nKey, getTicket(3).ticket);
	Bukkit.addRecipe(recipe.addIngredient(2, Material.DIAMOND).addIngredient(Material.PAPER));
    }
    
    public static Ticket getTicket(int quantity) {
	return new Ticket(new ItemBuilder().setItem(Material.PAPER, quantity)
		.setName("&c&lUnregistered Lottery Ticket")
		.setLore(String.valueOf(sessionDefaultId), "Register with /lottery register", "Valid until Server Reset")
		.build(), sessionDefaultId);
    }
    
    public static Ticket getRegisteredTicket(Player player) {
	long id = getId(player);
	return new Ticket(new ItemBuilder().setItem(Material.PAPER, 1)
		.setName("&a&lLottery Ticket")
		.setLore(String.valueOf(id), "Redeemable only by " + player.getDisplayName(), "Valid until Server Reset").build(), id);
    }
    
    public static long getDefaultId() {
	return sessionDefaultId;
    }
    
    public static long getId(Player player) {
	long id;
	do {
	     id = idGenerator.nextLong();
	} while(id == sessionDefaultId || registeredIds.containsKey(id));
	registeredIds.put(id, player);
	return id;
    }
    
    public static boolean isValid(long id) {
	return registeredIds.containsKey(id);
    }
    
    public static Player getPlayer(long id) {
	return registeredIds.get(id);
    }
}
