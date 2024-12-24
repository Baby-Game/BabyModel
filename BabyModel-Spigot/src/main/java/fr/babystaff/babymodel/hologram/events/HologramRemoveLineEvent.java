package fr.babystaff.babymodel.hologram.events;

import fr.babystaff.babymodel.hologram.Hologram;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HologramRemoveLineEvent extends Event {
    private Hologram hologram;
    private int index;

    public Hologram getHologram() {
        return hologram;
    }

    public int getIndex() {
        return index;
    }

    public HologramRemoveLineEvent(Hologram hologram, int index) {
        this.hologram = hologram;
        this.index = index;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
