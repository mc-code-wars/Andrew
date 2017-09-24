package me.andrew.wargames.objects;

import me.andrew.wargames.currencys.CurrencyHandler;
import me.andrew.wargames.currencys.CurrencyType;
import me.andrew.wargames.misc.InventoryHandle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created by Andrew on 22/09/2017.
 */
public class GamePlayer {
    Player player;
    String nickname;
    HashMap<CurrencyType, Integer> currency;
    PlayerClass pClass;

    public GamePlayer(Player sPlayer){
        currency = new  HashMap<CurrencyType, Integer>();
        player = sPlayer;
        nickname = player.getCustomName();
        //Set 0 for a new GamePlayer
        for (CurrencyType c: CurrencyType.values()) {
            currency.put(c,0);
        }
    }

    public Inventory getInventory(){return player.getInventory();}
    public boolean canAfford(CurrencyType type, int amt){
        return CurrencyHandler.getInstance().canAfford(this,type,amt);
    }
    public int getCurrency(CurrencyType type){
        InventoryHandle ih = new InventoryHandle();
        int amtBlocks = ih.getAmount(this,CurrencyHandler.getInstance().currencyItem(type),(short)-1);
        setCurrency(type,amtBlocks);
        return currency.get(type);
    }
    public void addCurrency(CurrencyType type,int amt){
        currency.put(type,currency.get(type)+amt);
    }

    public void removeCurrency(CurrencyType type,int amt){
        int left = amt;
        for(ItemStack item : getPlayer().getInventory().getContents()) {
            if (item != null && item.getType() == CurrencyHandler.getInstance().currencyItem(type)) {
               if(item.getAmount() < left){
                   player.getInventory().remove(item);
                   left = left - item.getAmount();

               }
               if(item.getAmount() == left){
                   player.getInventory().remove(item);
                   left = left - item.getAmount();
                }
                if (item.getAmount() > left){
                    player.getInventory().remove(item);
                    player.getInventory().addItem(new ItemStack(item.getType(),item.getAmount() - left));
                    left = 0;
                }
            }
            player.updateInventory();
        }
        currency.put(type,currency.get(type)+amt);
    }

    public void setCurrency(CurrencyType type,int amt){
        currency.put(type,amt);
    }
    public Player getPlayer(){
        return player;
    }

}
