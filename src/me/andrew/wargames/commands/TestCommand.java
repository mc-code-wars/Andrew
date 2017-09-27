package me.andrew.wargames.commands;

import org.bukkit.command.CommandSender;

/**
 * Created by Andrew on 25/09/2017.
 */
public class TestCommand extends SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        if (label.equalsIgnoreCase("test")) {
            sender.sendMessage("Here are the arguments i got: ");
            String argsString = "";
            for (int i = 0; i < args.length; i++) {
                argsString += args[i] + " ";

            }
            sender.sendMessage(argsString);
        }
        return false;
    }
}