package fr.babystaff.babymodel.langue.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLanguageChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final String newLanguage;
    private final String oldLanguage;

    public PlayerLanguageChangeEvent(Player player, String newLanguage, String oldLanguage) {
        this.player = player;
        this.newLanguage = newLanguage;
        this.oldLanguage = oldLanguage;
    }

    public Player getPlayer() {
        return player;
    }

    public String getNewLanguage() {
        return newLanguage;
    }

    public String getOldLanguage() {
        return oldLanguage;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
