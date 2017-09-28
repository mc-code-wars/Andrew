package me.andrew.wargames.commands;

import me.andrew.wargames.manager.ChatManager;
import me.andrew.wargames.manager.MenuManager;
import me.andrew.wargames.manager.ShopManager;
import me.andrew.wargames.manager.SpawnerManager;
import me.andrew.wargames.misc.MessageType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Andrew on 27/09/2017.
 */
public class ResetCommand extends SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        if (label.equalsIgnoreCase("reset")) {
            if (sender.hasPermission("wargames.reset")) {
                ShopManager.getInstance().removeShops();
                SpawnerManager.getInstance().shutdownSpawners();

                ShopManager.getInstance().clear();
                MenuManager.getInstance().clear();
                SpawnerManager.getInstance().clear();
                SpawnerManager.getInstance().CanRun = true;
                new ChatManager().sendMessage(sender, "&aAll Spawners and bits have been cleared");
            } else {
                new ChatManager().sendMessage((Player) sender, MessageType.ACCESS_DENIED);
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Remove all active Objects";
    }
}
