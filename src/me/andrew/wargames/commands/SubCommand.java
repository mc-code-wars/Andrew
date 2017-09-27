package me.andrew.wargames.commands;

import org.bukkit.command.CommandSender;

/**
 * Created by Andrew on 25/09/2017.
 */
public class SubCommand {
    public boolean onCommand(CommandSender sender, String label, String[] args) {

        return false;
    }

    public String getDescription() {
        return "A wargames subcommand";
    }
}
