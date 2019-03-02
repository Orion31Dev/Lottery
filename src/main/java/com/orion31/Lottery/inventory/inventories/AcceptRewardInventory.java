package com.orion31.Lottery.inventory.inventories;

import static com.orion31.Lottery.Messenger.color;

import org.bukkit.DyeColor;
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
import com.orion31.Lottery.inventory.TicketManager;

public class AcceptRewardInventory extends LotteryInventory {
    public static String name = "Accept Prize?";
    public static String cl = "&l";
    
    @SuppressWarnings("deprecation")
    public static Inventory get(Reward reward) {
	Inventory inv = createInventory(null, InventoryType.DISPENSER, color(cl + name));
	ItemStack accept = new ItemBuilder().setItem(Material.WOOL, 1).setName("&a&lAccept!").setDurability(DyeColor.LIME.getWoolData()).build();
	ItemStack deny = new ItemBuilder().setItem(Material.WOOL, 1).setName("&4&lSell for " + reward.rarity.tickets + " tickets").setDurability(DyeColor.RED.getWoolData()).build();
	inv.setItem(3, accept);
	inv.setItem(4, reward.item);
	inv.setItem(5, deny);
	return inv;
    }
    	
    public static void onClick(InventoryClickEvent e) {
	if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
	if (e.getSlot() == 3) {
	    e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(4));
	    e.getWhoClicked().closeInventory();
	} else if (e.getSlot() == 5) {
	    TicketManager.giveTicket((Player) e.getWhoClicked(), LootTable.getRarity(e.getInventory().getItem(4)).tickets);
	    e.getWhoClicked().closeInventory();
	}
    }
}