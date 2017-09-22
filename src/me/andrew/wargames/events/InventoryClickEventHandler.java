package me.andrew.wargames.events;

import me.andrew.wargames.manager.MenuManager;
import me.andrew.wargames.manager.PlayerManager;
import me.andrew.wargames.objects.MenuInventory;
import me.andrew.wargames.objects.ShopItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

/**
 * Created by Andrew on 22/09/2017.
 */
public class InventoryClickEventHandler implements Listener {
    @EventHandler
    public void onInventroyClick(InventoryClickEvent e){

        if(MenuManager.getInstance().isMenu(e.getClickedInventory().getName())){
            e.setCancelled(true);
            MenuInventory mi = MenuManager.getInstance().getMenu2(e.getClickedInventory().getName());
            ShopItem si = mi.getShopItem(e.getCurrentItem().getItemMeta().getDisplayName());
si.runAction(PlayerManager.getInstance().getGamePlayer((Player) e.getWhoClicked()));
        }
    }

    @EventHandler
    public void onInventroyDrag(InventoryMoveItemEvent e){
        if(MenuManager.getInstance().isMenu( e.getDestination().getName())){
            e.setCancelled(true);
        }
    }
}
