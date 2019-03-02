package com.orion31.Lottery.commands;

import static com.orion31.Lottery.commands.CommandManager.getCommand;
import static com.orion31.Lottery.Messenger.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandLottery extends ALotteryCommand implements CommandExecutor {
	
	public CommandLottery() {
	    super("lottery", "/lottery", "Base command, displays help");
	}
		
	@Override
	public boolean run(CommandSender sender, Command cmd, String label, String[] args) throws CommandNotFoundException, PlayerOnlyException {
	    if (args.length == 0) return run("help", sender, cmd, label, args);
	    else return run(args[0], sender, cmd, label, args);
		
	}
	
	public boolean run(String cmdName, CommandSender sender, Command cmd, String label, String[] args) throws CommandNotFoundException, PlayerOnlyException {
	    return getCommand(cmdName).run(sender, cmd, label, args);
	}
	
	public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
		try {
		    return run(sender, cmd, label, args);
		} catch (CommandNotFoundException e) {
		    msg(e.getMessage(), sender);
		} catch (PlayerOnlyException e) {
		    msg(e.getMessage(), sender);
		}
		return false;
	}
	
}
