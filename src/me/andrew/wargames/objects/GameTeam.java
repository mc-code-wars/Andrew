package me.andrew.wargames.objects;

import me.andrew.wargames.misc.InventoryHandle;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;

/**
 * Created by Andrew on 22/09/2017.
 */
public class GameTeam {
    String name;
    ChatColor color;
    Spawner teamSpawner;
    ArrayList<GamePlayer> TeamPlayers;

    public void prepareTeam(){
        for(GamePlayer gp : TeamPlayers){
            PlayerInventory inv = (PlayerInventory) gp.getInventory();
            inv.setHelmet(getArmor(Material.LEATHER_HELMET));
            inv.setChestplate(getArmor(Material.LEATHER_CHESTPLATE));
            inv.setLeggings(getArmor(Material.LEATHER_LEGGINGS));
            inv.setBoots(getArmor(Material.LEATHER_BOOTS));
        }
    }
    private ItemStack getArmor(Material item){
        ItemStack is = new ItemStack(item);
        is.setAmount(1);
        LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
        InventoryHandle ih = new InventoryHandle();
        meta.setColor(ih.translateChatToColor(color));
        is.setItemMeta(meta);
        return is;
    }
}
