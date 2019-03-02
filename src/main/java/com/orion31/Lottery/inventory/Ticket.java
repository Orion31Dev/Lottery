package com.orion31.Lottery.inventory;

import org.bukkit.inventory.ItemStack;

public class Ticket {
    public ItemStack ticket;
    public long id;
    
    public Ticket(ItemStack ticket, long id) {
	this.id = id;
	this.ticket = ticket;
    }
}
