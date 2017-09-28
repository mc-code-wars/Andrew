package me.andrew.wargames;

import me.andrew.wargames.commands.CommandCaller;
import me.andrew.wargames.events.GameEventHandler;
import me.andrew.wargames.manager.ConfigManager;
import me.andrew.wargames.manager.MenuManager;
import me.andrew.wargames.manager.ShopManager;
import me.andrew.wargames.manager.SpawnerManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Andrew on 22/09/2017.
 */
public class Main extends JavaPlugin{
    private static Main instance;
    public static Main getInstance(){
        return instance;
    }

    public boolean isDebug;
    @Override
    public void onEnable() {
        instance = this;
        getCommand("wargames").setExecutor(new CommandCaller());
        ShopManager.getInstance().removeShops();
        MenuManager.getInstance();
        ConfigManager.getInstance().getConfig("playershop.yml");
        ConfigManager.getInstance().getConfig("playershop.yml");
        isDebug = true;
        new GameEventHandler().setupListeners();
    }

    @Override
    public void onDisable() {
        ShopManager.getInstance().removeShops();
        SpawnerManager.getInstance().shutdownSpawners();
    }

    public void registerEvents(Listener list) {
        getServer().getPluginManager().registerEvents(list,this);
    }
}
