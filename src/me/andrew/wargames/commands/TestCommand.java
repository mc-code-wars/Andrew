package me.andrew.wargames.commands;

import me.andrew.wargames.manager.ChatManager;
import me.andrew.wargames.manager.SpawnerManager;
import me.andrew.wargames.objects.Spawner;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Andrew on 25/09/2017.
 */
public class TestCommand extends SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        if (label.equalsIgnoreCase("test")) {
            if (!(sender instanceof Player)) {
                new ChatManager().sendDebug(sender, "Unable to get location of console");
                return false;
            }
            Player p = (Player) sender;
            Spawner selectedSpawner = null;
            for (Spawner sp : SpawnerManager.getInstance().getSpawners()) {
                if (sp.getLocation().distance(p.getLocation()) < 2) {
                    selectedSpawner = sp;
                }
            }
            if (selectedSpawner == null) {
                new ChatManager().sendMessage(p, "&cYour not on/near a spawner");
                return false;
            }
            selectedSpawner.upgrade();
            new ChatManager().sendMessage(p, "&aSpawner is now level: " + selectedSpawner.getLevel());
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Test command for upgrading spawners";
    }
}