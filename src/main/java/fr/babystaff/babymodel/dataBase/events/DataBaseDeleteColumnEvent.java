package fr.babystaff.babymodel.dataBase.events;

import fr.babystaff.babymodel.dataBase.DataBase;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DataBaseDeleteColumnEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final DataBase dataBase;
    private final String tableName;
    private final String columnName;

    public DataBaseDeleteColumnEvent(DataBase dataBase, String tableName, String columnName) {
        this.dataBase = dataBase;
        this.tableName = tableName;
        this.columnName = columnName;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
