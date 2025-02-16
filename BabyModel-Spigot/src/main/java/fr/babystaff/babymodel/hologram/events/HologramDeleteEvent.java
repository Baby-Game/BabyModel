package fr.babystaff.babymodel.hologram.events;

import fr.babystaff.babymodel.hologram.Hologram;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HologramDeleteEvent extends Event {


    private Hologram hologram;

    public HologramDeleteEvent(Hologram hologram) {
        this.hologram = hologram;
    }

    public Hologram getHologram() {
        return hologram;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
