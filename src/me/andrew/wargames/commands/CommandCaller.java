package me.andrew.wargames.commands;

import me.andrew.wargames.manager.ChatManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andrew on 25/09/2017.
 */
public class CommandCaller implements CommandExecutor {
    private static CommandCaller ourInstance;

    public static CommandCaller getInstance() {
        return ourInstance;
    }

    HashMap<String, SubCommand> commandMapping;

    public CommandCaller() {
        ourInstance = this;
        commandMapping = new HashMap<String, SubCommand>();
        commandMapping.put("help", new HelpCommand());
        commandMapping.put("test", new TestCommand());
        commandMapping.put("reload", new ReloadCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("wargames")) {
            if (args.length == 0) {
                new ChatManager().sendHelp(sender);
                return false;
            }
            if (commandMapping.containsKey(args[0].toLowerCase())) {
                // sender.sendMessage("Running!");
                String subCommand = args[0];
                ArrayList<String> argsNew = new ArrayList<String>();
                for (int i = 1; i < args.length; i++) {
                    argsNew.add(args[i]);
                }
                String[] argsNewArray = argsNew.toArray(new String[0]);
                return commandMapping.get(args[0]).onCommand(sender, args[0], argsNewArray);
            } else {
                new ChatManager().sendHelp(sender);
                return false;
            }
        }
        return false;
    }

    public String makeHelp() {
        String str = "&3--[&bWarGames&3]--" + System.lineSeparator();
        for (String cmdName : commandMapping.keySet()) {
            SubCommand sub = commandMapping.get(cmdName);
            str += cmdName + " | " + sub.getDescription() + System.lineSeparator();
        }
        return str;
    }
}
