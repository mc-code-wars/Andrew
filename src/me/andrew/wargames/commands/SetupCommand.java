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
public class SetupCommand extends SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        if (label.equalsIgnoreCase("setup")) {
            if (sender.hasPermission("wargames.setup")) {

                new ChatManager().sendDebug("Starting Setup");
                SpawnerManager.getInstance().setupSpawners();
                ShopManager.getInstance().spawnShops();
                MenuManager.getInstance().setup();
            } else {
                new ChatManager().sendMessage((Player) sender, MessageType.ACCESS_DENIED);
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Sets up Objects";
    }
}
