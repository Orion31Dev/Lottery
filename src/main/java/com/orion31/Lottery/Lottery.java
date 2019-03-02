package com.orion31.Lottery;

import static com.orion31.Lottery.Messenger.console;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.orion31.Lottery.commands.CommandHelp;
import com.orion31.Lottery.commands.CommandLottery;
import com.orion31.Lottery.commands.CommandManager;
import com.orion31.Lottery.commands.CommandNotFoundException;
import com.orion31.Lottery.commands.CommandPlay;
import com.orion31.Lottery.commands.CommandRegister;
import com.orion31.Lottery.inventory.InventoryListener;
import com.orion31.Lottery.inventory.ItemBuilder;
import com.orion31.Lottery.inventory.LootTable;
import com.orion31.Lottery.inventory.LootTable.Reward;
import com.orion31.Lottery.inventory.TicketManager;

public final class Lottery extends JavaPlugin {

    private static Lottery _instance;

    public Lottery() {
	_instance = this;
    }

    @Override
    public void onEnable() {
	CommandManager.registerCommand(new CommandLottery());
	CommandManager.registerCommand(new CommandHelp());
	CommandManager.registerCommand(new CommandPlay());
	CommandManager.registerCommand(new CommandRegister());

	try {
	    getServer().getPluginCommand("lottery").setExecutor((CommandExecutor) CommandManager.getCommand("lottery"));
	} catch (CommandNotFoundException e) {
	    e.printStackTrace();
	}

	getServer().getPluginManager().registerEvents(new InventoryListener(), this);
	registerLoot();

	TicketManager.init(new NamespacedKey(this, getDescription().getName()));

	console("[Lottery] Plugin Active.");

    }

    public static void registerLoot() {
	LootTable.clearLoot();
	LootTable.addLoot(
		new Reward(new ItemBuilder().setItem(Material.COOKED_BEEF, 64).setName("&6&lMeat Package")
			.setLore(LootTable.Rarity.LEGENDARY.name).build(), LootTable.Rarity.LEGENDARY));

	console("Loot registered");

    }

    public static ConsoleCommandSender getConsoleSender() {
	return Bukkit.getConsoleSender();
    }

    public static Lottery getInstance() {
	return _instance;
    }

}