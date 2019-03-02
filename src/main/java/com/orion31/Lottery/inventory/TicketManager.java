package com.orion31.Lottery.inventory;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import static com.orion31.Lottery.Messenger.*;

public class TicketManager {

    private static Random idGenerator = new Random();
    private static HashMap<Player, Integer> playerTickets = new HashMap<Player, Integer>();
    private static long sessionDefaultId;

    public static void init(NamespacedKey nKey) {
	sessionDefaultId = idGenerator.nextLong();
	ShapelessRecipe recipe = new ShapelessRecipe(nKey, getUnregTicket(3));
	Bukkit.addRecipe(recipe.addIngredient(2, Material.DIAMOND).addIngredient(Material.PAPER));
    }

    public static ItemStack getUnregTicket(int quantity) {
	return new ItemBuilder().setItem(Material.PAPER, quantity).setName("&c&lUnregistered Lottery Ticket")
		.setLore(String.valueOf(sessionDefaultId), "&aClick to Register").glow().build();
    }

    public static long getDefaultId() {
	return sessionDefaultId;
    }

    public static int giveTicket(Player player, int tickets) {
	msg("Given &a" + tickets + "&r " + ((tickets == 1) ? "ticket." : "tickets."), player);
	if (!playerTickets.containsKey(player)) {
	    playerTickets.put(player, tickets);
	    return tickets;
	}
	int newTickets = playerTickets.get(player) + tickets;
	playerTickets.put(player, newTickets);
	return newTickets;
    }

    public static boolean removeTickets(Player player, int tickets) {
	if (!playerTickets.containsKey(player))
	    return false;
	int newCount = playerTickets.get(player) - tickets;
	if (newCount < 0) return false; // Check to see if the player can afford the transaction.
	playerTickets.put(player, newCount);
	msg("Charged &c" + tickets + "&r " + ((tickets == 1) ? "ticket." : "tickets."), player);
	return true;
    }

    public static int getTickets(Player player) {
	if (!playerTickets.containsKey(player)) return 0;
	return playerTickets.get(player);
    }
    
    public static ItemStack getTicketsItem(Player player) {
	return new ItemBuilder()
		.setItem(Material.PAPER, (getTickets(player) > 64) ? 64 : getTickets(player))
		.setName("&6&l" + getTickets(player) + " Tickets")
		.setLore("&aClick to Spend")
		.glow().build();
    }
}
