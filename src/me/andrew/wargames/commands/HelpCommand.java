package me.andrew.wargames.commands;

import me.andrew.wargames.manager.ChatManager;
import org.bukkit.command.CommandSender;

/**
 * Created by Andrew on 25/09/2017.
 */
public class HelpCommand extends SubCommand {


    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        if (label.equalsIgnoreCase("help")) {
            new ChatManager().sendHelp(sender);
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Lists the help page";
    }
}
