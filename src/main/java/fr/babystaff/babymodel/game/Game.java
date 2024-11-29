package fr.babystaff.babymodel.game;

import fr.babystaff.babymodel.arena.Arena;
import fr.babystaff.babymodel.game.events.GameCreateEvent;
import fr.babystaff.babymodel.team.events.TeamRemoveEvent;
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

    public Game(String id, String name, Arena arena, Arena lobbyArena) {
        this.id = id;
        this.name = name;
        this.arena = arena;
        this.lobbyArena = lobbyArena;

        GameCreateEvent event = new GameCreateEvent(this);
        Bukkit.getPluginManager().callEvent(event);
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