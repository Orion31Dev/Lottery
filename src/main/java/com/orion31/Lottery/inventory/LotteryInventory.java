package com.orion31.Lottery.inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class LotteryInventory {
    protected static ArrayList<Inventory> lotteryInventories = new ArrayList<Inventory>();
    
    public static Inventory createInventory(InventoryHolder owner, int size) {
	return createInventory(owner, size, "");
    }

    public static Inventory createInventory(InventoryHolder owner, InventoryType type) {
	return createInventory(owner, type, "");
    }

    public static Inventory createInventory(InventoryHolder owner, int size, String name) {
	Inventory inv = Bukkit.createInventory(owner, size, name);
	addInventory(inv);
	return inv;
    }

    public static Inventory createInventory(InventoryHolder owner, InventoryType type, String name) {
	Inventory inv = Bukkit.createInventory(owner, type, name);
	addInventory(inv);
	return inv;
    }

    public static void addInventory(Inventory inv) {
	lotteryInventories.add(inv);
    }
    
    public static void removeInventory(Inventory inv) {
	lotteryInventories.remove(inv);
    }
    
    public static boolean isLotteryInventory(Inventory inv) {
	return lotteryInventories.contains(inv);
    }
   
}
