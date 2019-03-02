package com.orion31.Lottery.inventory;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import com.orion31.Lottery.inventory.inventories.AcceptRewardInventory;
import com.orion31.Lottery.inventory.inventories.PayTicketInventory;
import com.orion31.Lottery.inventory.inventories.PickChestInventory;

public class InventoryListener implements Listener {
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
	if (!LotteryInventory.isLotteryInventory(e.getInventory())) return;
	if (nameEqual(e.getClickedInventory(), PickChestInventory.name)) PickChestInventory.onClick(e);
	else if (nameEqual(e.getClickedInventory(), AcceptRewardInventory.name)) AcceptRewardInventory.onClick(e);
	else if (nameEqual(e.getClickedInventory(), PayTicketInventory.name)) PayTicketInventory.onClick(e);;
	e.setCancelled(true);
    }
    	
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
	if (LotteryInventory.isLotteryInventory(e.getInventory())) LotteryInventory.lotteryInventories.remove(e.getInventory());
    } 
    
    private boolean nameEqual(Inventory inv, String name2) {
	return ChatColor.stripColor(inv.getName()).equalsIgnoreCase(ChatColor.stripColor(name2));
    }
}
