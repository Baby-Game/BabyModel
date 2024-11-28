package fr.babystaff.babymodel.arena.events;

import fr.babystaff.babymodel.arena.Arena;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArenaCreateEvent extends Event {
    private Arena arena;

    public ArenaCreateEvent(Arena arena) {
        this.arena = arena;
    }

    public Arena getArena() {
        return arena;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
