package fr.babystaff.babymodel.world.events;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WorldDeleteEvent extends Event {
    private World world;

    public WorldDeleteEvent(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
