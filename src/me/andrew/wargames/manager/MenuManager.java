package me.andrew.wargames.manager;

import me.andrew.wargames.Main;
import me.andrew.wargames.misc.ShopType;
import me.andrew.wargames.objects.MenuInventory;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andrew on 22/09/2017.
 */
public class MenuManager {
    private static MenuManager ourInstance = new MenuManager();

    public static MenuManager getInstance() {
        return ourInstance;
    }
private HashMap<String, MenuInventory> menuMapping;
    private MenuManager() {
        menuMapping = new HashMap<String, MenuInventory>();
    }
    public void addMenu(String name, MenuInventory mi){
        menuMapping.put(name,mi);
    }
    public MenuInventory getMenu(String name){
        return menuMapping.get(name);
    }
    public boolean isMenu(String invName){
        boolean status = false;
        for(String s : menuMapping.keySet()){
            if(menuMapping.get(s).getInv().getName().equalsIgnoreCase(invName)){
                status = true;
            }
        }
        return status;
    }

    public void clear() {
        menuMapping.clear();
    }
    public MenuInventory getMenu2(String invName){
        MenuInventory status = null;
        for(String s : menuMapping.keySet()){
            if(menuMapping.get(s).getInv().getName().equalsIgnoreCase(invName)){
               status = menuMapping.get(s);
            }
        }
        return status;
    }
    public void setup(){
        Main main = Main.getInstance();
        main.getServer().getScheduler().runTaskAsynchronously(main, new Runnable() {
            @Override
            public void run() {
                Main main = Main.getInstance();
                FileConfiguration conf = ConfigManager.getInstance().getConfig("playershop.yml");
                int amount = 0;
                ArrayList<String> temp = new ArrayList<String>();
                temp.add("start");
                for(String s : conf.getKeys(false)){
                    new ChatManager().sendDebug("Made: " + s);
                    MenuManager.getInstance().addMenu(s,new MenuInventory(s, ShopType.PLAYER));
                    amount++;
                }
                new ChatManager().sendDebug("There are " + amount + "  Shop inventroys found");
            }
        });
    }
}
