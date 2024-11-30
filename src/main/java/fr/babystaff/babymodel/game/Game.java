package fr.babystaff.babymodel.game;

import fr.babystaff.babymodel.arena.Arena;
import fr.babystaff.babymodel.game.events.GameCreateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String id;
    private String name;
    private Arena arena;
    private Arena lobbyArena;
    private List<Player> playerList = new ArrayList<>();
    private GameStatus gameStatus;

    public Game(String id, String name, Arena arena, Arena lobbyArena) {
        this.id = id;
        this.name = name;
        this.arena = arena;
        this.lobbyArena = lobbyArena;

        GameCreateEvent event = new GameCreateEvent(this);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void start() {
        if (!gameStatus.equals(GameStatus.LOBBY)) {
            return;
        }

        for (Player p : playerList) {
            p.teleport(arena.getSpawn());
        }

        setGameStatus(GameStatus.STARTING);
    }

    public void stop() {
        if (!gameStatus.equals(GameStatus.RUNNING)) {
            return;
        }

        for (Player p : playerList) {
            p.teleport(lobbyArena.getSpawn());
        }

        setGameStatus(GameStatus.STOP);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void addPlayer(Player player) {
        if (!playerList.contains(player)) {
            playerList.add(player);
        }
    }

    public void removePlayer(Player player) {
        if (playerList.contains(player)) {
            playerList.remove(player);
        }
    }

    public void setLobbyArena(Arena lobbyArena) {
        this.lobbyArena = lobbyArena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Arena getArena() {
        return arena;
    }

    public Arena getLobbyArena() {
        return lobbyArena;
    }
}
