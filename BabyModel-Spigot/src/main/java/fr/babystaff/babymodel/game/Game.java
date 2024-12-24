package fr.babystaff.babymodel.game;

import fr.babystaff.babymodel.arena.Arena;
import fr.babystaff.babymodel.game.events.GameCreateEvent;
import fr.babystaff.babymodel.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Game {
    private final String id;
    private String name;
    private Arena arena;
    private Arena lobbyArena;
    private final List<Player> playerList = new ArrayList<>();
    private GameStatus gameStatus = GameStatus.LOBBY;
    private final HashMap<ChatColor, Team> teamHashMap = new HashMap<>();

    public Game(String id, String name, Arena arena, Arena lobbyArena) {
        this.id = id;
        this.name = name;
        this.arena = arena;
        this.lobbyArena = lobbyArena;

        // Appel de l'événement GameCreateEvent
        GameCreateEvent event = new GameCreateEvent(this);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void addTeamInGame(Team team) {
        if (!teamHashMap.containsKey(team.getChatColor())) {
            teamHashMap.put(team.getChatColor(), team);
            Bukkit.getLogger().info("Team added: " + team.getName() + " in game: " + id);
        }
    }

    public void removeTeamInGame(Team team) {
        if (teamHashMap.remove(team.getChatColor()) != null) {
            Bukkit.getLogger().info("Team removed: " + team.getName() + " from game: " + id);
        }
    }

    public Team getTeam(ChatColor color) {
        return teamHashMap.get(color); // Retourne null si la couleur n'existe pas
    }

    public void start() {
        if (gameStatus != GameStatus.LOBBY) {
            Bukkit.getLogger().warning("Game cannot start because it is not in LOBBY state.");
            return;
        }

        for (Player p : playerList) {
            p.teleport(arena.getSpawn());
        }

        for (Team team : teamHashMap.values()) {
            for (Player p : team.getPlayerList()) {
                p.teleport(team.getSpawn());
            }
        }

        setGameStatus(GameStatus.STARTING);
        Bukkit.getLogger().info("Game " + id + " is starting.");
    }

    public void stop() {
        if (gameStatus != GameStatus.RUNNING) {
            Bukkit.getLogger().warning("Game cannot stop because it is not in RUNNING state.");
            return;
        }

        for (Player p : playerList) {
            p.teleport(lobbyArena.getSpawn());
        }

        setGameStatus(GameStatus.STOP);
        Bukkit.getLogger().info("Game " + id + " has stopped.");
    }

    public List<Player> getPlayerList() {
        return Collections.unmodifiableList(playerList); // Renvoie une liste immuable
    }

    public void addPlayer(Player player) {
        if (!playerList.contains(player)) {
            playerList.add(player);
            Bukkit.getLogger().info("Player " + player.getName() + " added to game: " + id);
        }
    }

    public void removePlayer(Player player) {
        if (playerList.remove(player)) {
            Bukkit.getLogger().info("Player " + player.getName() + " removed from game: " + id);
        }
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        Bukkit.getLogger().info("Game " + id + " status changed to: " + gameStatus);
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
