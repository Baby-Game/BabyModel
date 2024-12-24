package fr.babystaff.babymodel.dataBase.events;

import fr.babystaff.babymodel.dataBase.DataBase;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DataBaseDeleteTableEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final DataBase dataBase;
    private final String tableName;

    public DataBaseDeleteTableEvent(DataBase dataBase, String tableName) {
        this.dataBase = dataBase;
        this.tableName = tableName;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
