package me.andrew.wargames;

import me.andrew.wargames.currencys.CurrencyType;
import me.andrew.wargames.events.InventoryClickEventHandler;
import me.andrew.wargames.events.JoinEvent;
import me.andrew.wargames.events.VillagerClickEvent;
import me.andrew.wargames.manager.*;
import me.andrew.wargames.objects.GamePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

    @Override
    public void onEnable() {
        instance = this;
        registerEvents(new JoinEvent());
        registerEvents(new VillagerClickEvent());
        registerEvents(new InventoryClickEventHandler());
        ShopManager.getInstance().removeShops();
        MenuManager.getInstance();
        ConfigManager.getInstance().getConfig("playershop.yml");
    }

    @Override
    public void onDisable() {
        ShopManager.getInstance().removeShops();
    }

    private void registerEvents(Listener list){
        getServer().getPluginManager().registerEvents(list,this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("setup")){
            sender.sendMessage("Setup starting!");
            SpawnerManager.getInstance().setupSpawners();
            ShopManager.getInstance().spawnShops();
            MenuManager.getInstance().setup();
        }
        if(label.equalsIgnoreCase("playerinfo")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Nope");
                return true;
            }
            sender.sendMessage("Grabbing data");
            Player p = (Player) sender;
            GamePlayer gp = PlayerManager.getInstance().getGamePlayer(p);
            sender.sendMessage("You Have");

            for(CurrencyType c : CurrencyType.values()){
                p.sendMessage(c.name()+": "+gp.getCurrency(c));
            }
        }
        return true;
    }
}
