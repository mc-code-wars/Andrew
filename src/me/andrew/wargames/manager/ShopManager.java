package me.andrew.wargames.manager;

import me.andrew.wargames.Main;
import me.andrew.wargames.misc.ShopType;
import me.andrew.wargames.objects.Shop;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Villager;

import java.util.HashMap;

/**
 * Created by Andrew on 22/09/2017.
 */
public class ShopManager {
    private static ShopManager ourInstance = new ShopManager();

    public static ShopManager getInstance() {
        return ourInstance;
    }

    private HashMap<Shop, ShopType> shopMapping;
    private ShopManager() {
        shopMapping = new HashMap<Shop, ShopType>();
    }
    public void addShop(Shop s, ShopType st){
        shopMapping.put(s,st);
    }

public void removeShops(){
    for(Shop s : shopMapping.keySet()){
        s.getVillager().remove();
    }
}
    public void spawnShops(){
     //removeShops();
        Main main = Main.getInstance();
        main.getServer().getScheduler().runTaskAsynchronously(main, new Runnable() {
            @Override
            public void run() {
                Main main = Main.getInstance();
                FileConfiguration conf = ConfigManager.getInstance().getConfig("npc.yml");
                int amount = 0;

                World w = main.getServer().getWorld("world");
                for (Chunk c : w.getLoadedChunks()) {
                    int cx = c.getX() << 4;
                    int cz = c.getZ() << 4;
                    for (int x = cx; x < cx + 16; x++) {
                        for (int z = cz; z < cz + 16; z++) {
                            for (int y = 0; y < 128; y++) {
                                if (w.getBlockAt(x, y, z).getType() == Material.valueOf(conf.getString("player.spawn_block"))) {
                                    ShopManager.getInstance().addShop(new Shop(ShopType.PLAYER,w.getBlockAt(x, y, z)),ShopType.PLAYER);
                                    amount++;
                                }
                                if (w.getBlockAt(x, y, z).getType() == Material.valueOf(conf.getString("team.spawn_block"))) {
                                    ShopManager.getInstance().addShop(new Shop(ShopType.TEAM,w.getBlockAt(x, y, z)),ShopType.TEAM);
                                    amount++;
                                }

                            }
                        }
                    }
                }
                main.getServer().broadcastMessage("There are "+ amount + "  Shop found");
            }
        });
        for(Shop s : shopMapping.keySet()){
            s.spawnShop();
            Main.getInstance().getServer().broadcastMessage("Spawned!!!");
        }
    }
}
