package me.andrew.wargames.events;

import me.andrew.wargames.commands.CommandCaller;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

/**
 * Created by Andrew on 28/09/2017.
 */
public class TabCompleteEventHandler implements Listener {
    @EventHandler
    public void onTabCompleate(TabCompleteEvent e) {
        if (e.getBuffer().toLowerCase().startsWith("/wargames ")) {
            e.setCompletions(CommandCaller.getInstance().getCommands());
        }
    }
}
