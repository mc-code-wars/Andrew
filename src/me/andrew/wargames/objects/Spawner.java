package me.andrew.wargames.objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Created by Andrew on 22/09/2017.
 */
public class Spawner {
    Material item;
    Block block;
    Location spawnLoc;
    int level;
    Item itemDrop;

    public Spawner(Material mat, Block b, int lvl){
        item = mat;
        block = b;
        spawnLoc = block.getLocation().add(0,5,0);
        level = lvl;
    }

    public void spawnItem(){
        itemDrop = block.getLocation().getWorld().dropItem(block.getLocation().add(+0.5, +1, +0.5), getItemStack(level));
        itemDrop.setVelocity(new Vector(0,0,0));

    }
    private ItemStack getItemStack(int amt){
        ItemStack is = new ItemStack(item);
        is.setAmount(amt);
        return is;
    }
    public int getLevel(){
        return level;
    }
    public void upgrade(){
        level++;
    }
}
