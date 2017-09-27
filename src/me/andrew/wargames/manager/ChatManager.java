package me.andrew.wargames.manager;

import me.andrew.wargames.Main;
import me.andrew.wargames.commands.CommandCaller;
import me.andrew.wargames.misc.MessageType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * Created by Andrew on 25/09/2017.
 */
public class ChatManager {
    Main main;
    FileConfiguration config;
    String prefix;

    public ChatManager() {
        main = Main.getInstance();
        config = ConfigManager.getInstance().getConfig("messages.yml");
        prefix = cc(config.getString("prefix"));
    }

    public void sendHelp(CommandSender sender) {
        sender.sendMessage(cc(CommandCaller.getInstance().makeHelp()));
    }

    public void sendMessage(Player p, MessageType mt) {
        p.sendMessage(prefix + cc(config.getString(mt.toString().toLowerCase())));
    }

    public String cc(String txt) {
        return ChatColor.translateAlternateColorCodes('&', txt);
    }

    public void sendDebug(Player p, String str) {
        if (Main.getInstance().isDebug) {
            p.sendMessage(prefix + cc("&c[debug] " + str));
        }
    }

    public void sendDebug(CommandSender p, String str) {
        if (Main.getInstance().isDebug) {
            p.sendMessage(prefix + cc("&c[debug] " + str));
        }
    }
}
