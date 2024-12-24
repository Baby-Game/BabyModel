package fr.babystaff.babymodel.actionBar.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ActionBarSendEvent extends Event {
    private Player player;
    private String content;

    public Player getPlayer() {
        return player;
    }

    public String getContent() {
        return content;
    }

    public ActionBarSendEvent(Player player, String content) {
        this.player = player;
        this.content = content;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
