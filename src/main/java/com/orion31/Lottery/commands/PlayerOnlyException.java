package com.orion31.Lottery.commands;

public class PlayerOnlyException extends Exception {
    private static final long serialVersionUID = 5791922783125164158L;

    public PlayerOnlyException() {
	super("Only Players may execute this command.");
    }
}
