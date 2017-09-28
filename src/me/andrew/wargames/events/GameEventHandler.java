package me.andrew.wargames.events;

import me.andrew.wargames.Main;

/**
 * Created by Andrew on 27/09/2017.
 */
public class GameEventHandler {

    public void setupListeners() {
        Main.getInstance().registerEvents(new JoinEvent());
        Main.getInstance().registerEvents(new VillagerClickEvent());
        Main.getInstance().registerEvents(new InventoryClickEventHandler());
        Main.getInstance().registerEvents(new BlockPlaceHandler());
        Main.getInstance().registerEvents(new TabCompleteEventHandler());
    }
}
