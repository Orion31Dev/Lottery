package com.orion31.Lottery.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.orion31.Lottery.inventory.Ticket;
import com.orion31.Lottery.inventory.TicketManager;

import static com.orion31.Lottery.Messenger.*;


public class CommandRegister extends ALotteryCommand {

    public CommandRegister() {
	super("register", "/lottery register", "Registers held tickets");
    }

    @Override
    public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws PlayerOnlyException {
	if (!(sender instanceof Player)) throw new PlayerOnlyException();
	Player player = (Player) sender;
	if (player.getInventory().getItemInMainHand().getType() != Material.PAPER ||
		!ChatColor.stripColor(player.getInventory().getItemInMainHand().getItemMeta().getLore().get(0)).equals(String.valueOf(TicketManager.getDefaultId()))) {
	    msg("You must be holding an Unregistered Lottery Ticket to perform this command.", player);
	    return true;
	}
	
	int quantity = player.getInventory().getItemInMainHand().getAmount();
	for (int i = 0; i < quantity; i++) {
	    Ticket t = TicketManager.getRegisteredTicket(player);
	    player.getInventory().addItem(t.ticket);
	    msg("Given Ticket " + t.id, player);
	}
	player.getInventory().remove(player.getInventory().getItemInMainHand());
	
	return true;
    }

}
