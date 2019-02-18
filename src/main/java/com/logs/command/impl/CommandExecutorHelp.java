package com.logs.command.impl;

import com.logs.command.CommandExecutor;

public class CommandExecutorHelp implements CommandExecutor {

    @Override
    public void execute(String[] args) {
        System.out.println("Usage: ");
        System.out.println("    To print out top \"n\" just pass a path to a file and a number (ie java -jar app.jar timing.log 10)");
        System.out.println("    To draw a histogram of hourly number of requests pass a path to a file and the parameter \"-histo\"");
        System.out.println("    -h Print this help message");
    }

}
