package fr.babystaff.babymodel.arena.events;

import fr.babystaff.babymodel.arena.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArenaRemovePlayerInArenaEvent extends Event {
    private Arena arena;
    private Player player;

    public ArenaRemovePlayerInArenaEvent(Arena arena, Player player) {
        this.arena = arena;
        this.player = player;
    }

    public Arena getArena() {
        return arena;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
