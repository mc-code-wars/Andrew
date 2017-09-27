package me.andrew.wargames.commands;

import me.andrew.wargames.manager.ChatManager;
import me.andrew.wargames.manager.ConfigManager;
import me.andrew.wargames.misc.MessageType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Andrew on 27/09/2017.
 */
public class ReloadCommand extends SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        if (label.equalsIgnoreCase("reload")) {
            if (!(sender instanceof Player)) {
                new ChatManager().sendDebug(sender, "&cSorry, this command currently cant run from Console");
                return false;
            }
            if (sender.hasPermission("wargames.reload")) {
                if (ConfigManager.getInstance().reloadAll()) {
                    new ChatManager().sendMessage((Player) sender, MessageType.RELOAD_DONE);
                } else {
                    new ChatManager().sendMessage((Player) sender, MessageType.RELOAD_ERROR);
                }
            } else {
                new ChatManager().sendMessage((Player) sender, MessageType.ACCESS_DENIED);
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "reloads the configs";
    }
}
