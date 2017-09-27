package me.andrew.wargames.manager;

import me.andrew.wargames.Main;
import me.andrew.wargames.objects.Spawner;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;

/**
 * Created by Andrew on 22/09/2017.
 */

public class SpawnerManager {
    private static SpawnerManager ourInstance = new SpawnerManager();

    public static SpawnerManager getInstance() {
        return ourInstance;
    }
    private ArrayList<Spawner> activeSpawners;
    public boolean CanRun;
    private SpawnerManager() {
        activeSpawners = new ArrayList<Spawner>();
        CanRun = true;
    }

    public void clear() {
        activeSpawners.clear();
    }
    public void addSpawner(Spawner spawner){
        activeSpawners.add(spawner);
    }
    public ArrayList<Spawner> getSpawners(){
        return activeSpawners;
    }

    public void setupSpawners(){
        if(!CanRun){
            new ChatManager().sendDebug("Andrew Has blocked running this more then once");
            return;
        }
        CanRun = false;
        Main main = Main.getInstance();
        main.getServer().getScheduler().runTaskAsynchronously(main, new Runnable() {
            @Override
            public void run() {
                Main main = Main.getInstance();
                int amount = 0;

                World w = main.getServer().getWorld("world");
                for (Chunk c : w.getLoadedChunks()) {
                    int cx = c.getX() << 4;
                    int cz = c.getZ() << 4;
                    for (int x = cx; x < cx + 16; x++) {
                        for (int z = cz; z < cz + 16; z++) {
                            for (int y = 0; y < 128; y++) {
                                if (w.getBlockAt(x, y, z).getType() == Material.DIAMOND_BLOCK) {
                                    SpawnerManager.getInstance().addSpawner(new Spawner(Material.DIAMOND,w.getBlockAt(x, y, z),1));
                                    amount++;
                                }
                                if (w.getBlockAt(x, y, z).getType() == Material.IRON_BLOCK) {
                                    SpawnerManager.getInstance().addSpawner(new Spawner(Material.IRON_INGOT,w.getBlockAt(x, y, z),1));
                                    amount++;
                                }
                                if (w.getBlockAt(x, y, z).getType() == Material.EMERALD_BLOCK) {
                                    SpawnerManager.getInstance().addSpawner(new Spawner(Material.EMERALD,w.getBlockAt(x, y, z),1));
                                    amount++;
                                }
                            }
                        }
                    }
                }
                new ChatManager().sendDebug("There are " + amount + "  Spawners found");
        }
        });

        //THIS IS TEMP CODE
        //TODO: Remove this code
        main.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {
                for(Spawner spawner : SpawnerManager.getInstance().getSpawners()){
                    spawner.spawnItem();
                }
            }
        },100l,100l);
    }

    public void shutdownSpawners() {
        for (Spawner spawner : getSpawners()) {
            spawner.removeHolograms();
        }
    }
}
