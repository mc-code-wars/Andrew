package me.andrew.wargames.manager;

import me.andrew.wargames.objects.Config;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

/**
 * Created by Andrew on 22/09/2017.
 */
public class ConfigManager {
    private static ConfigManager ourInstance = new ConfigManager();

    public static ConfigManager getInstance() {
        return ourInstance;
    }
    private HashMap<String, Config> configMapping;

    private ConfigManager() {
        configMapping = new HashMap<String, Config>();
    }
    public FileConfiguration getConfig(String name){
        if(!configMapping.containsKey(name)){
            configMapping.put(name,new Config(name));
        }
        return configMapping.get(name).getConfig();
    }

}
