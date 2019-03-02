package com.orion31.Lottery.inventory;

import static com.orion31.Lottery.Messenger.color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;


public class LootTable {
    private static ArrayList<Reward> drops = new ArrayList<Reward>();
    private static HashMap<ItemStack, Rarity> dropRewards = new HashMap<ItemStack, Rarity>();
    public static Reward getReward() {
	Random rnd = new Random();
	return drops.get(rnd.nextInt(drops.size()));
    }
    
    public static void addLoot(ItemStack loot, Rarity rarity) {
	Reward reward = new Reward(loot, rarity);
	addLoot(reward);
	
    }
    
    public static void addLoot(Reward reward) {
	for (int i = 0; i < reward.rarity.timesAdded; i++)
	    drops.add(reward);
	dropRewards.put(reward.item, reward.rarity);
    }
    
    public static Rarity getRarity(ItemStack item) {
	return dropRewards.get(item);
    }
    
    public static void clearLoot() {
	drops.clear();
    }
    
    public enum Rarity {
	COMMON(7, 1, "&7Common"), UNCOMMON(5, 2, "&bUncommon"), RARE(3, 3, "&1&lRare"), EPIC(2, 6, "&5&lEpic", Sound.ENTITY_GHAST_AMBIENT), LEGENDARY(1, 12, "&6&lLEGENDARY", Sound.ENTITY_ENDERDRAGON_AMBIENT);
	
	public int timesAdded;
	public int tickets;
	public String name;
	public Sound sound;
	
	Rarity(int timesAdded, int tickets, String name, Sound sound) {
	    this.timesAdded = timesAdded;
	    this.tickets = tickets;
	    this.name = name;
	    this.sound = sound;
	}
	
	Rarity(int timesAdded, int tickets, String name) {
	    this.timesAdded = timesAdded;
	    this.tickets = tickets;
	    this.name = name;
	    this.sound = Sound.ENTITY_PLAYER_LEVELUP;
	}
    }
    
    public static class Reward {
	public ItemStack item;
	public Rarity rarity;
	
	public Reward(ItemStack item, Rarity rarity) {
	    this.item = item;
	    this.rarity = rarity;
	}
	
	@Override
	public String toString() {
	    return color(item.getItemMeta().getDisplayName() + "&r of type " + rarity.name);
	}
    }
}
