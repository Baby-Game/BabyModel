package fr.babystaff.babymodel.arena;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class ArenaManager {
    private HashMap<String, Arena> arenaHashMap = new HashMap<>();
    private HashMap<Player, Arena> playerArenaHashMap = new HashMap<>();

    public void addPlayerInArena(Player player, Arena arena) {
        if (!playerArenaHashMap.containsKey(player)) {
            arena.addPlayerInArena(player);
            playerArenaHashMap.put(player, arena);
        }
    }

    public void removePlayerInArena(Player player, Arena arena) {
        if (playerArenaHashMap.containsKey(player)) {
            arena.removePlayerInArena(player);
            playerArenaHashMap.remove(player);
        }
    }

    public HashMap<String, Arena> getArenaHashMap() {
        return arenaHashMap;
    }

    public HashMap<Player, Arena> getPlayerArenaHashMap() {
        return playerArenaHashMap;
    }

    public void createArena(Arena arena) {
        if (arenaHashMap.containsKey(arena.getId())) {
            return;
        } else {
            arenaHashMap.put(arena.getId(), arena);
        }
    }

    public void removeArena(Arena arena) {
        if (arenaHashMap.containsKey(arena.getId())) {
            arenaHashMap.remove(arena);
        }
    }

    public Arena getArena(String id) {
        return arenaHashMap.get(id);
    }
}
