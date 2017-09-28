package me.andrew.wargames.objects;

import me.andrew.wargames.Main;
import me.andrew.wargames.manager.ChatManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
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
    ArmorStand MainTitle;
    ArmorStand ExtraData;
    ChatColor txtColor;

    public Spawner(Material mat, Block b, int lvl){
        item = mat;
        block = b;
        spawnLoc = block.getLocation().add(0,5,0);
        level = lvl;
        Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                setHolograms();
            }
        });
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

    public Location getLocation() {
        return block.getLocation();
    }

    public void setHolograms() {
        Location baseLoc = block.getLocation().clone().add(+0.5, 0, +0.5);
        MainTitle = (ArmorStand) spawnLoc.getWorld().spawnEntity(baseLoc.add(0, 0.5, 0), EntityType.ARMOR_STAND);
        ExtraData = (ArmorStand) spawnLoc.getWorld().spawnEntity(baseLoc.add(0, 0.2, 0), EntityType.ARMOR_STAND);
        MainTitle.setGravity(false); //Make sure it doesn't fall
        MainTitle.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
        MainTitle.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
        MainTitle.setVisible(false); //Makes the ArmorStand invisible

        ExtraData.setGravity(false); //Make sure it doesn't fall
        ExtraData.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
        ExtraData.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
        ExtraData.setVisible(false); //Makes the ArmorStand invisible

        ChatColor _txtColor = ChatColor.WHITE;
        switch (item) {
            case IRON_INGOT:
                _txtColor = ChatColor.GRAY;
                break;
            case DIAMOND:
                _txtColor = ChatColor.AQUA;
                break;
            case EMERALD:
                _txtColor = ChatColor.GREEN;
                break;
        }
        String ItemName = item.toString().toLowerCase();
        ItemName = ItemName.replaceAll("_", " ");
        ItemName = ItemName.substring(0, 1).toUpperCase() + ItemName.substring(1);
        txtColor = _txtColor;
        MainTitle.setCustomName(txtColor + ItemName + " Spawner");
        ExtraData.setCustomName(txtColor + "Level: " + level);
        new ChatManager().sendDebug("Setup Holograms for a spawner");
    }

    public void removeHolograms() {
        if (MainTitle != null && !MainTitle.isDead()) {
            MainTitle.remove();
        }
        if (ExtraData != null && !ExtraData.isDead()) {
            ExtraData.remove();
        }
    }
    public int getLevel(){
        return level;
    }
    public void upgrade(){
        level++;
        ExtraData.setCustomName(txtColor + "Level: " + level);
    }

    public void upgrade(int i) {
        level = +i;
        ExtraData.setCustomName(txtColor + "Level: " + level);
    }
}
