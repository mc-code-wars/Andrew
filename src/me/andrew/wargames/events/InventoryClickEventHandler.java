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

        //we need to make sure that the inventory that was clicked was one of ours
        if(MenuManager.getInstance().isMenu(e.getClickedInventory().getName())){
            //Gr8! Its ours (well ofc coz we rule)
            e.setCancelled(true);  //Cancel the event (no block stealing)
            MenuInventory mi = MenuManager.getInstance().getMenu2(e.getClickedInventory().getName()); //Get the Menu from the inventory
            ShopItem si = mi.getShopItem(e.getCurrentItem().getItemMeta().getDisplayName()); //Get the Shop item from the Menu
            si.runAction(PlayerManager.getInstance().getGamePlayer((Player) e.getWhoClicked())); //Run the Action on the player
        }
    }

    @EventHandler
    public void onInventroyMove(InventoryMoveItemEvent e) {
        if(MenuManager.getInstance().isMenu( e.getDestination().getName())){
            e.setCancelled(true);
        }
    }
}
