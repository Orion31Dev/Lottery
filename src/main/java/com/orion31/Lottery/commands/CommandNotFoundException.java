package com.orion31.Lottery.commands;

public class CommandNotFoundException extends Exception {
    
    private static final long serialVersionUID = 5791922783125164158L;

    public CommandNotFoundException() {
	super("Command not found");
    }
    
    public CommandNotFoundException(String cmd) {
	super("Command not found: " + cmd);
    }
}
