package com.orion31.Lottery.inventory.inventories;

import static com.orion31.Lottery.Messenger.color;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

import com.orion31.Lottery.inventory.ItemBuilder;
import com.orion31.Lottery.inventory.LootTable.Reward;
import com.orion31.Lottery.inventory.LotteryInventory;

public class AcceptRewardInventory extends LotteryInventory {
    public static String name = "Accept Prize?";
    public static String cl = "&l";
    
    public static Inventory get(Reward reward) {
	Inventory inv = createInventory(null, InventoryType.DISPENSER, color(cl + name));
	
	Wool acceptWool = new Wool();
	Wool denyWool = new Wool();
	acceptWool.setColor(DyeColor.LIME);
	denyWool.setColor(DyeColor.RED);
	ItemStack accept = new ItemBuilder().setName(color("&a&lAccept!")).setData(acceptWool).build();
	ItemStack deny = new ItemBuilder().setName(color("&4&lSell for " + reward.rarity.tickets + " tickets")).setData(denyWool).build();
	inv.setItem(3, accept);
	inv.setItem(4, reward.item);
	inv.setItem(5, deny);
	return inv;
    }
    	
    public static void onClick(InventoryClickEvent e) {
	if (e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem() == null) return;
	if (e.getSlot() == 3) {
	    e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(4));
	    e.getWhoClicked().closeInventory();
	} else if (e.getSlot() == 5) {
	    e.getWhoClicked().closeInventory();
	}
    }
}