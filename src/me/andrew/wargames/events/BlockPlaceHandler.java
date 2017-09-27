package me.andrew.wargames.events;

import me.andrew.wargames.Main;
import me.andrew.wargames.manager.PlayerManager;
import me.andrew.wargames.objects.GamePlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Andrew on 24/09/2017.
 */
@SuppressWarnings("deprecated")
public class BlockPlaceHandler implements Listener {

    HashMap<UUID, GamePlayer> thrownBlocks;

    public BlockPlaceHandler() {
        thrownBlocks = new HashMap<UUID, GamePlayer>();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent e) {
        //Main.getInstance().getServer().broadcastMessage("Placed block: "+e.getPlayer());
        if (e.getBlock().getType() == Material.LIGHT_BLUE_GLAZED_TERRACOTTA) {
            e.getPlayer().sendMessage("Sticky bomb armed!");
        }
    }

    //TODO: Place the following events in there own class - only here for testing
    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {

        if (e.getEntity() instanceof FallingBlock) {

            if (thrownBlocks.containsKey(e.getEntity().getUniqueId())) {

                GamePlayer gp = thrownBlocks.get(e.getEntity().getUniqueId());
                if (e.getTo() == Material.LIGHT_BLUE_GLAZED_TERRACOTTA) {
                    e.setCancelled(true);
                    Block under = e.getBlock().getRelative(0, -1, 0);
                    Block blockBefore = e.getBlock();
                    blockBefore.setType(Material.AIR);
                    e.getBlock().setType(e.getTo());
                    BlockPlaceEvent bpe = new BlockPlaceEvent(e.getBlock(), blockBefore.getState(), under, gp.getPlayer().getInventory().getItemInMainHand(), gp.getPlayer(), true);
                    thrownBlocks.remove(e.getEntity().getUniqueId());
                    Main.getInstance().getServer().getPluginManager().callEvent(bpe);

                }
            }
        }
    }

    @EventHandler
    public void onRightClickAir(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getPlayer().getItemInHand().getType() == Material.LIGHT_BLUE_GLAZED_TERRACOTTA) {
                FallingBlock fb = e.getPlayer().getWorld().spawnFallingBlock(e.getPlayer().getLocation(), e.getPlayer().getItemInHand().getData());
                fb.setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(2));
                thrownBlocks.put(fb.getUniqueId(), PlayerManager.getInstance().getGamePlayer(e.getPlayer()));
            }
        }
    }

}
