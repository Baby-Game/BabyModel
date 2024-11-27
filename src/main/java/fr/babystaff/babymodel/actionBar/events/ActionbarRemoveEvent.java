package fr.babystaff.babymodel.actionBar.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ActionbarRemoveEvent extends Event {
    private Player player;

    public ActionbarRemoveEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
