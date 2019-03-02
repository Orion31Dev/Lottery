package com.orion31.Lottery.inventory.inventories;
import static com.orion31.Lottery.Messenger.broadcastReward;
import static com.orion31.Lottery.Messenger.color;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.orion31.Lottery.inventory.ItemBuilder;
import com.orion31.Lottery.inventory.LootTable;
import com.orion31.Lottery.inventory.LootTable.Reward;
import com.orion31.Lottery.inventory.LotteryInventory;

public class PickChestInventory extends LotteryInventory {
    public static String name = "Pick a Chest.";
    public static String cl = "&l";
    
    public static Inventory get() {
	Inventory inv = createInventory(null, InventoryType.DISPENSER, color(cl + name));
	ItemStack chest = new ItemBuilder().setItem(Material.CHEST, 1).setName("&6&lOpen Me!").build();
	inv.setItem(3, chest);
	inv.setItem(4, chest);
	inv.setItem(5, chest);
	return inv;
    }
    
    public static void onClick(InventoryClickEvent e) {
	if (e.getCurrentItem().getType() != Material.CHEST) return;
	Reward reward = LootTable.getReward();
	e.getWhoClicked().openInventory(AcceptRewardInventory.get(reward));
	broadcastReward(reward, (Player) e.getWhoClicked()); 
    }
}