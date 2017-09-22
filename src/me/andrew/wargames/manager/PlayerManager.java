package me.andrew.wargames.manager;

import me.andrew.wargames.objects.GamePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Andrew on 22/09/2017.
 */
public class PlayerManager {
    private static PlayerManager ourInstance = new PlayerManager();

    public static PlayerManager getInstance() {
        return ourInstance;
    }

    private PlayerManager() {
playerMaping = new HashMap<Player, GamePlayer>();
    }
private HashMap<Player, GamePlayer> playerMaping;

public GamePlayer getGamePlayer(Player p){
        if(!playerMaping.containsKey(p)){
            playerMaping.put(p,new GamePlayer(p));
        }
        return(playerMaping.get(p));
}

}
