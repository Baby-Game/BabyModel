package fr.babystaff.babymodel.game;

import fr.babystaff.babymodel.game.events.GameAddPlayerEvent;
import fr.babystaff.babymodel.game.events.GameRemovePlayerEvent;
import fr.babystaff.babymodel.team.events.TeamRemoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GameManager {
    private HashMap<String, Game> gameHashMap = new HashMap<>();
    private HashMap<Player, Game> playerGameHashMap = new HashMap<>();

    public void createGame(Game game) {
        if (!gameHashMap.containsKey(game)) {
            gameHashMap.put(game.getId(), game);
        }
    }

    public void removeGame(Game game) {
        if (gameHashMap.containsKey(game)) {
            gameHashMap.remove(game);
        }
    }

    public HashMap<String, Game> getGameHashMap() {
        return gameHashMap;
    }

    public HashMap<Player, Game> getPlayerGameHashMap() {
        return playerGameHashMap;
    }

    public void addPlayer(Player player, Game game) {
        if (!playerGameHashMap.containsKey(player)) {
            playerGameHashMap.put(player, game);
            GameAddPlayerEvent event = new GameAddPlayerEvent(player, game);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    public void removePlayer(Player player, Game game) {
        if (playerGameHashMap.containsKey(player)) {
            playerGameHashMap.put(player, game);
            GameRemovePlayerEvent event = new GameRemovePlayerEvent(player, game);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    public Game getGame(Player player) {
        return playerGameHashMap.get(player);
    }
}
