package com.orion31.Lottery.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.orion31.Lottery.inventory.inventories.PayTicketInventory;

public class CommandPlay extends ALotteryCommand {
	
	public CommandPlay() {
	    super("play", "/lottery play", "Play the lottery! Requires one ticket (WIP).");
	}

	@Override
	public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws PlayerOnlyException {
	    if (!(sender instanceof Player)) throw new PlayerOnlyException();
	    
	    Player player = (Player) sender;
	    player.openInventory(PayTicketInventory.get(player));
	    return true;
	}

}
