package com.orion31.Lottery.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import static com.orion31.Lottery.Messenger.*;

public class CommandHelp extends ALotteryCommand {
	
	public CommandHelp() {
		super("help", "/lottery help", "Displays plugin help");
	}

	@Override
	public boolean run(CommandSender sender, Command cmd, String label, String[] args) {
	    if (args.length > 1) {
		ALotteryCommand command;
		try {
		    command = CommandManager.getCommand(args[1]);
		} catch (CommandNotFoundException e) {
		    msg(e.getMessage(), sender);
		    return false;
		}
		help(command, sender);
	    } else {
		help(sender);
	    }
	    return true;
	}
	
	
}
