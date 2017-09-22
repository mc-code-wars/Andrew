package me.andrew.wargames.currencys;

import me.andrew.wargames.misc.InventoryHandle;
import me.andrew.wargames.objects.GamePlayer;
import org.bukkit.Material;

import java.util.HashMap;

/**
 * Created by Andrew on 22/09/2017.
 */
public class CurrencyHandler {
    private static CurrencyHandler ourInstance = new CurrencyHandler();
    public static CurrencyHandler getInstance() {
        return ourInstance;
    }

    private HashMap<CurrencyType,Material> currencyMapping;
    public CurrencyHandler(){
        currencyMapping = new HashMap<CurrencyType,Material>();

        currencyMapping.put(CurrencyType.IRON,Material.IRON_INGOT);
        currencyMapping.put(CurrencyType.DIAMOND,Material.DIAMOND);
        currencyMapping.put(CurrencyType.EMERALD,Material.EMERALD);
    }
    public Material currencyItem(CurrencyType type){
        return currencyMapping.get(type);
    }
    public boolean canAfford(GamePlayer gameplayer, CurrencyType type, int amt){
        InventoryHandle invHandle = new InventoryHandle();
        return(gameplayer.getCurrency(type) >= amt);
    }
}
