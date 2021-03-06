package me.andrew.wargames.events;

import me.andrew.wargames.manager.ConfigManager;
import me.andrew.wargames.manager.MenuManager;
import me.andrew.wargames.objects.MenuInventory;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Created by Andrew on 22/09/2017.
 */
public class VillagerClickEvent implements Listener{
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e){


        FileConfiguration conf = ConfigManager.getInstance().getConfig("npc.yml");


        Entity entity = e.getRightClicked();
        if (!(entity instanceof Villager))
            return;
        if (ChatColor.stripColor(e.getRightClicked().getCustomName()).equalsIgnoreCase(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', conf.getString("player.npc_name"))))) {
                e.setCancelled(true);
                MenuInventory mi = MenuManager.getInstance().getMenu("start");
                e.getPlayer().openInventory(mi.getInv());

            }
            if(ChatColor.stripColor(e.getRightClicked().getCustomName()).equalsIgnoreCase(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&',conf.getString("team.npc_name"))))){
                e.setCancelled(true);

            }
        }
    }
