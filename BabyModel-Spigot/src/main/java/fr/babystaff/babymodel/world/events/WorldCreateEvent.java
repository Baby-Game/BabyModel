package fr.babystaff.babymodel.world.events;


import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WorldCreateEvent extends Event {
    private World world;

    public World getWorld() {
        return world;
    }

    public WorldCreateEvent(World world) {
        this.world = world;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
