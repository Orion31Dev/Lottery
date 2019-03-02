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
import com.orion31.Lottery.inventory.LotteryInventory;
import com.orion31.Lottery.inventory.TicketManager;

@SuppressWarnings("deprecation")
public class PayTicketInventory extends LotteryInventory {
    private final static int PRICE = 3;

    public static String name = "Click on " + PRICE + " Tickets";
    public static String cl = "&l";

    public static Inventory get(Player player) {
	Inventory inv = createInventory(null, InventoryType.DISPENSER, color(cl + name));
	inv.setItem(0, getMissing());
	inv.setItem(1, getMissing());
	inv.setItem(2, getMissing());
	inv.setItem(8, TicketManager.getTicketsItem(player));

	return inv;
    }

    private static ItemStack getMissing() {
	return new ItemBuilder().setItem(Material.WOOL, 1).setName("&4&lTicket Needed")
		.setDurability(DyeColor.RED.getWoolData()).build();
    }

    private static ItemStack getDeposited() {
	return new ItemBuilder().setItem(Material.WOOL, 1).setName("&a&lTicket Deposited")
		.setLore("&4Click to Return Ticket").setDurability(DyeColor.LIME.getWoolData()).build();
    }

    public static void onClick(InventoryClickEvent e) {
	if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta()
		|| (e.getCurrentItem().getType() != Material.PAPER && e.getCurrentItem().getType() != Material.WOOL))
	    return;

	int ticketsDeposited = 0;
	for (ItemStack i : e.getInventory().getContents()) {
	    if (i == null || i.getType() == Material.AIR)
		continue;
	    if (i.getDurability() == DyeColor.LIME.getWoolData())
		ticketsDeposited++;
	}

	if (e.getCurrentItem().getType() == Material.WOOL) {
	    if (e.getCurrentItem().getDurability() == DyeColor.LIME.getWoolData()) {
		TicketManager.giveTicket((Player) e.getWhoClicked(), 1);
		e.getInventory().setItem(ticketsDeposited - 1, getMissing());
		e.getInventory().setItem(8, TicketManager.getTicketsItem((Player) e.getWhoClicked()));
	    }
	    return;
	}

	if (TicketManager.removeTickets((Player) e.getWhoClicked(), 1)) {
	    e.getInventory().setItem(8, TicketManager.getTicketsItem((Player) e.getWhoClicked()));
	    e.getInventory().setItem(ticketsDeposited++, getDeposited());
	}

	if (ticketsDeposited == 3) {
	    e.getWhoClicked().openInventory(PickChestInventory.get());
	    return;
	}
    }

}