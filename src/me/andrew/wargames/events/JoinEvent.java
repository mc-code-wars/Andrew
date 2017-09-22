package me.andrew.wargames.events;

import me.andrew.wargames.manager.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Andrew on 22/09/2017.
 */
public class JoinEvent implements Listener{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        PlayerManager.getInstance().getGamePlayer(e.getPlayer());
    }


    @EventHandler
    public void onPlayerJoin2(InventoryEvent e){
    }
}
