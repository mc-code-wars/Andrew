package me.andrew.wargames.manager;

/**
 * Created by Andrew on 22/09/2017.
 */
public class MenuManager {
    private static MenuManager ourInstance = new MenuManager();

    public static MenuManager getInstance() {
        return ourInstance;
    }

    private MenuManager() {
    }
}
