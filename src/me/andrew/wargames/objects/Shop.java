package me.andrew.wargames.objects;

import me.andrew.wargames.Main;
import me.andrew.wargames.manager.ConfigManager;
import me.andrew.wargames.misc.ShopType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

/**
 * Created by Andrew on 22/09/2017.
 */
public class Shop {
    String name;
    ShopType type;
    Location spawnLoc;
    Block block;
    Villager v;

    public Shop(ShopType st, Block b){
        FileConfiguration conf = ConfigManager.getInstance().getConfig("npc.yml");

        type= st;
        block = b;
        spawnLoc = block.getLocation().add(+0.5, +1, +0.5);
        if(type == ShopType.PLAYER){
            name = ChatColor.translateAlternateColorCodes('&',conf.getString("player.npc_name"));
        }else if(type == ShopType.TEAM){
            name = ChatColor.translateAlternateColorCodes('&',conf.getString("team.npc_name"));
        }
        spawnShop();
    }
    public void spawnShop(){
        Main main = Main.getInstance();
        main.getServer().getScheduler().runTaskLater(main, new Runnable() {
            @Override
            public void run() {
                v = (Villager) spawnLoc.getWorld().spawnEntity(spawnLoc, EntityType.VILLAGER);
                v.setAI(false);
                v.setCustomName(name);
                v.setCustomNameVisible(true);
                v.setCanPickupItems(false);
                v.setCollidable(false);
                v.setInvulnerable(true);
            }
        },20l);
    }

    public Villager getVillager() {
        return v;
    }
}
