package fr.babystaff.babymodel.dataBase.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DataBaseCloseEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final String databaseName;

    public DataBaseCloseEvent(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}