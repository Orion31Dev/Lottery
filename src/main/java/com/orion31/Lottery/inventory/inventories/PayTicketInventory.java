package com.orion31.Lottery.inventory.inventories;

import static com.orion31.Lottery.Messenger.color;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.orion31.Lottery.inventory.ItemBuilder;
import com.orion31.Lottery.inventory.LotteryInventory;
import com.orion31.Lottery.inventory.TicketManager;

import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("deprecation")
public class PayTicketInventory extends LotteryInventory {
    private final static int PRICE = 3;
    
    public static String name = "Click on " + PRICE + " Tickets";
    public static String cl = "&l";
    
    public static Inventory get() {
	Inventory inv = createInventory(null, InventoryType.DISPENSER, color(cl + name));
	inv.setItem(0, getMissing());
	inv.setItem(1, getMissing());
	inv.setItem(2, getMissing());
	return inv;
    }
    
    private static ItemStack getMissing() {
	return new ItemBuilder().setItem(Material.WOOL, 1).setName("&4&lTicket Needed").setDurability(DyeColor.RED.getWoolData()).build();
    }
    
    private static ItemStack getDeposited() {
	return new ItemBuilder().setItem(Material.WOOL, 1).setName("&a&lTicket Deposited").setDurability(DyeColor.LIME.getWoolData()).build();
    }
    
    public static void onClick(InventoryClickEvent e) {
	if (e.getCurrentItem() == null || e.getCurrentItem().getType() != Material.PAPER || !e.getCurrentItem().hasItemMeta()) return;

	long id = 0;
	try {
	    id = Long.parseLong(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(0)));
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return;
	}
	
	
	if (!TicketManager.isValid(id)) return;
	if (!TicketManager.getPlayer(id).equals(e.getWhoClicked())) return;

	int ticketsDeposited = 0; // Get how many tickets have already been deposited.
	for (ItemStack i : e.getInventory().getContents()) {
	    if (i == null || i.getType() == Material.AIR) continue;
	    if (i.getDurability() == DyeColor.LIME.getWoolData()) ticketsDeposited++;
	}

	
	if (ticketsDeposited == 2) {
	    TicketManager.getPlayer(id).openInventory(PickChestInventory.get());
	    return;
	}
	
	e.getClickedInventory().remove(e.getCurrentItem());
	e.getInventory().setItem(ticketsDeposited, getDeposited());
	
    }
    
}