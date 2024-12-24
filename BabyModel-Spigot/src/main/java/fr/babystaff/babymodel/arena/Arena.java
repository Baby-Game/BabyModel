package fr.babystaff.babymodel.arena;

import fr.babystaff.babymodel.arena.events.ArenaAddPlayerInArenaEvent;
import fr.babystaff.babymodel.arena.events.ArenaCreateEvent;
import fr.babystaff.babymodel.arena.events.ArenaRemovePlayerInArenaEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private String id;
    private World world;
    private Location spawn;
    private List<Player> playerList = new ArrayList<>();

    public Arena(String id, World world, Location spawn) {
        this.id = id;
        this.world = world;
        this.spawn = spawn;

        ArenaCreateEvent event = new ArenaCreateEvent(this);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void addPlayerInArena(Player player) {
        if (playerList.contains(player)) {
            return;
        } else {
            playerList.add(player);
            player.teleport(spawn);
            ArenaAddPlayerInArenaEvent event = new ArenaAddPlayerInArenaEvent(this, player);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    public void removePlayerInArena(Player player) {
        if (playerList.contains(player)) {
            playerList.remove(player);
            player.teleport(spawn);
            ArenaRemovePlayerInArenaEvent event = new ArenaRemovePlayerInArenaEvent(this, player);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public String getId() {
        return id;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }
}
