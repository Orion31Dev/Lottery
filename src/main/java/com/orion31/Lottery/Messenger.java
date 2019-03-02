package com.orion31.Lottery;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.orion31.Lottery.commands.ALotteryCommand;
import com.orion31.Lottery.commands.CommandManager;
import com.orion31.Lottery.inventory.LootTable.Reward;

public class Messenger {
	
	public static final char colorChar = '&';
	public static String tag = "&c&lLottery >> &r";
	public static String consoleTag = "[Lottery]: ";
	
	public static void msg(String msg, CommandSender sender, Object... objects) {
		msg = String.format(msg, objects);
		msg(msg, sender);
	}
	
	public static void msg(String msg, CommandSender sender) {
		msg = color(tag + msg);
		sender.sendMessage(msg);
	}
	
	
	public static void ghost(String msg, CommandSender sender, Object... objects) {
		msg = String.format(msg, objects);
		msg(msg, sender);
	}
	
	public static void ghost(String msg, CommandSender sender) {
		msg = color(msg);
		sender.sendMessage(msg);
	}
	
	public static void console(String msg, Object... objects) {
		msg = String.format(msg, objects);
		console(msg);
	}
	
	public static void console(String msg) {
		Lottery.getConsoleSender().sendMessage(consoleTag + msg);
	}
	
	public static void broadcast(String msg) {
	    Bukkit.broadcastMessage(color(tag + msg));
	}
	
	public static void broadcastReward(Reward reward, Player pl) {
	    broadcast(pl.getDisplayName() + "&r won " + reward.toString());
	    for (Player player : Bukkit.getOnlinePlayers()) {
		player.playSound(player.getLocation(), reward.rarity.sound, 1F, 1F);
	    }
	}
	
	public static String color(String color) {
	    return ChatColor.translateAlternateColorCodes(colorChar, color);
	}
	
	
	public static void help(CommandSender sender) {
	    msg("Lottery Commands:", sender);
	    for (ALotteryCommand command : CommandManager.getCommands().values()) 
		ghost(command.getUsage() + ": " + command.getDesc(), sender);
	}
	
	public static void help(ALotteryCommand command, CommandSender sender) {
	    msg(command.getUsage() + ": " + command.getDesc(), sender);
	}
}
