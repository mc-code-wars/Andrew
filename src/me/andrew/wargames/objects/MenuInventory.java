package me.andrew.wargames.objects;

import me.andrew.wargames.Main;
import me.andrew.wargames.manager.ConfigManager;
import me.andrew.wargames.misc.ShopType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andrew on 22/09/2017.
 */
public class MenuInventory {
    String name;
    String displayName;
    Inventory inv;
    ShopType st;
    HashMap<String, ShopItem> items;


    public MenuInventory(String _name,ShopType _st){
        name = _name;
        st = _st;
        items = new HashMap<String, ShopItem>();
        if(st == ShopType.PLAYER){
            FileConfiguration confFile = ConfigManager.getInstance().getConfig("playershop.yml");
            ConfigurationSection conf = confFile.getConfigurationSection(_name);
            displayName = ChatColor.translateAlternateColorCodes('&',confFile.getString(_name+".name"));
            ConfigurationSection itemConf = confFile.getConfigurationSection(_name+".items");
            for(String s : itemConf.getKeys(false)){

                items.put(itemConf.getString(s+".name"),makeItem(itemConf.getString(s+".name"), Material.valueOf(itemConf.getString(s+".item")), itemConf.getInt(s+".amount"),(byte)itemConf.getInt(s+".data"),confFile.getConfigurationSection(_name+".items."+s)));
            }
            inv = Main.getInstance().getServer().createInventory(null,setRows(items.size(),false), displayName);
            for(ShopItem is : items.values()){
                inv.addItem(is.getItem());
            }

        }
    }
    public ShopItem getShopItem(String itemName){
        ShopItem status = null;
        for(String s : items.keySet()){
            if(items.get(s).getName().equalsIgnoreCase(itemName)){
                status = items.get(s);
            }
        }
        return status;
    }
    public Inventory getInv(){
        return inv;
    }
    private int setRows(int items, boolean bigChest) {
        int maxSize = bigChest ? 54 : 27;
        int amount = (((items / 9) + ((items % 9 == 0) ? 0 : 1)) * 9);
        return amount >= maxSize ? maxSize : amount;

    }
    private ShopItem makeItem(String name,Material mat,int amt,short data,ConfigurationSection cs){
        return new ShopItem(name,mat,amt,data,cs);
    }

}
