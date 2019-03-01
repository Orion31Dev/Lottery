package com.orion31.Lottery;

import static com.orion31.Lottery.Messenger.color;
import static com.orion31.Lottery.Messenger.console;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.orion31.Lottery.commands.CommandHandler;
import com.orion31.Lottery.commands.CommandHelp;
import com.orion31.Lottery.commands.CommandLottery;
import com.orion31.Lottery.commands.CommandNotFoundException;
import com.orion31.Lottery.commands.CommandPlay;
import com.orion31.Lottery.inventory.InventoryListener;
import com.orion31.Lottery.inventory.ItemBuilder;
import com.orion31.Lottery.inventory.LootTable;
import com.orion31.Lottery.inventory.LootTable.Reward;

public final class Lottery extends JavaPlugin {

    	private static Lottery _instance;
    	
    	public Lottery() {
    	    _instance = this;
    	}
    	
	@Override
	public void onEnable() {
		console("[Lottery] Plugin Active.");
		
		CommandHandler.registerCommand(new CommandLottery());
		CommandHandler.registerCommand(new CommandHelp());
		CommandHandler.registerCommand(new CommandPlay());
		
		try {
		    getServer().getPluginCommand("lottery").setExecutor((CommandExecutor) CommandHandler.getCommand("lottery"));
		} catch (CommandNotFoundException e) {
		    e.printStackTrace();
		}
		
		getServer().getPluginManager().registerEvents(new InventoryListener(), this);
		
		registerLoot();

	}
	
	public static void registerLoot() {
	    LootTable.clearLoot();
	    LootTable.addLoot(new Reward(new ItemBuilder()
		    .setItem(Material.COOKED_BEEF, 64)
		    .setName(color("&6&lMeat Package"))
		    .setLore(color(LootTable.Rarity.LEGENDARY.name)).build(), LootTable.Rarity.LEGENDARY));
	    
	    console("Loot registered");
	    
	}
	
	public static ConsoleCommandSender getConsoleSender() {
		return Bukkit.getConsoleSender();
	}
	
	public static Lottery getInstance() {
	    return _instance;
	}
	
}