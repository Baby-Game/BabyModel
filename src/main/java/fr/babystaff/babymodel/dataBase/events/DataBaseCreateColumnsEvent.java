package fr.babystaff.babymodel.dataBase.events;

import fr.babystaff.babymodel.dataBase.DataBase;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DataBaseCreateColumnsEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final DataBase dataBase;
    private final String tableName;
    private final String columnDefinition;

    public DataBaseCreateColumnsEvent(DataBase dataBase, String tableName, String columnDefinition) {
        this.dataBase = dataBase;
        this.tableName = tableName;
        this.columnDefinition = columnDefinition;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnDefinition() {
        return columnDefinition;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
