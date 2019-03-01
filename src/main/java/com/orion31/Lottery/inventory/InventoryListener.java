package com.orion31.Lottery.inventory;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.orion31.Lottery.inventory.inventories.AcceptRewardInventory;
import com.orion31.Lottery.inventory.inventories.PickChestInventory;

public class InventoryListener implements Listener {
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
	if (!LotteryInventory.isLotteryInventory(e.getInventory())) return;
	if (nameEqual(e, PickChestInventory.name)) PickChestInventory.onClick(e);
	else if (nameEqual(e, AcceptRewardInventory.name)) AcceptRewardInventory.onClick(e);
	e.setCancelled(true);
    }
    	
    private boolean nameEqual(InventoryClickEvent e, String name2) {
	return ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase(ChatColor.stripColor(name2));
    }
}
