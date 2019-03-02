package com.orion31.Lottery.commands;

import static com.orion31.Lottery.Messenger.console;

import java.util.HashMap;

public class CommandManager {
	
	private static HashMap<String, ALotteryCommand> registeredCommands = new HashMap<String, ALotteryCommand>(); 
	
	public static void registerCommand(ALotteryCommand command) {
		registeredCommands.put(command.getName(), command);
		console("Registered Command " + command.getName());
	}
	
	public static ALotteryCommand getCommand(String cmd) throws CommandNotFoundException {
	    if (registeredCommands.containsKey(cmd)) return registeredCommands.get(cmd);
	    else throw new CommandNotFoundException(cmd);
	}
	
	public static HashMap<String, ALotteryCommand> getCommands() {
	    return registeredCommands;
	}
}
