package me.andrew.wargames.objects;

import me.andrew.wargames.currencys.CurrencyType;
import me.andrew.wargames.manager.MenuManager;
import me.andrew.wargames.misc.ButtonAction;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

/**
 * Created by Andrew on 22/09/2017.
 */
public class ShopItem {
    String name;
    ItemStack is;
    ButtonAction ba;
    HashMap<CurrencyType, Integer> cost;
    String toOpen;
    ConfigurationSection cs;

    public ShopItem(String _name,Material mat, int amt,short data, ConfigurationSection _cs){
        ItemStack _is = new ItemStack(mat,amt);
        ItemMeta im = _is.getItemMeta();
        cost = new HashMap<CurrencyType, Integer>();
        name = ChatColor.translateAlternateColorCodes('&',_name);
        im.setDisplayName(name);
        _is.setDurability(data);
        _is.setItemMeta(im);
        is = _is;
        ba = ButtonAction.valueOf(_cs.getString("action"));
        cs = _cs;
        if(ba == ButtonAction.OPEN){
            toOpen = _cs.getString("open");
        }else if(ba == ButtonAction.BUY){
            for(String s : _cs.getConfigurationSection("cost").getKeys(false)){
                CurrencyType ct = CurrencyType.valueOf(s);
                int val = _cs.getInt("cost." + s);
                cost.put(ct,val);
            }
        }
    }
    public ItemStack getItem(){
        return is;
    }
    public String getName(){
        return name;
    }
    public void runAction(GamePlayer gp){
        if(ba == ButtonAction.OPEN){
            gp.getPlayer().openInventory(MenuManager.getInstance().getMenu(toOpen).getInv());
        }
        else if(ba == ButtonAction.BUY){
            boolean canAfford = false;
            for(CurrencyType ct : cost.keySet()){
                //gp.getPlayer().sendMessage(ct.toString() + ": "+cost.get(ct));
                canAfford = gp.canAfford(ct, cost.get(ct));
            }
            if(!canAfford){
                gp.getPlayer().sendMessage("Cant Afford");
                return;
            }

            for(CurrencyType ct : cost.keySet()){
                 gp.removeCurrency(ct, cost.get(ct));
            }

            ItemStack newIs = new ItemStack(getItem().getType(), getItem().getAmount());
            newIs.setDurability(getItem().getDurability());
            gp.getPlayer().getInventory().addItem(newIs);
        }
    }
}
