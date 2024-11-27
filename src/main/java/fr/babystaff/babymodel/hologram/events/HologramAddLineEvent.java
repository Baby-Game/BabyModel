package fr.babystaff.babymodel.hologram.events;

import fr.babystaff.babymodel.hologram.Hologram;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HologramAddLineEvent extends Event {
    private Hologram hologram;
    private String text;

    public Hologram getHologram() {
        return hologram;
    }

    public String getText() {
        return text;
    }

    public HologramAddLineEvent(Hologram hologram, String text) {
        this.hologram = hologram;
        this.text = text;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
