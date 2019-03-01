package com.orion31.Lottery.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class ALotteryCommand {
    	public String getName() { return name; }
	public String getUsage() { return usage; }
	public String getDesc() { return desc; }
	
	protected String name;
	protected String usage;
	protected String desc;
	
	public ALotteryCommand(String name, String usage, String desc) {
		this.name = name;
		this.usage = usage;
		this.desc = desc;
	}
	
	public abstract boolean run(CommandSender sender, Command cmd, String label, String[] args) throws CommandNotFoundException, PlayerOnlyException;
}
