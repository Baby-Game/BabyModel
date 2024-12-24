package fr.babystaff.babymodel.dataBase.events;

import fr.babystaff.babymodel.dataBase.DataBase;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DataBaseCreateTableEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final DataBase dataBase;
    private final String tableName;
    private final String columnsDefinition;

    public DataBaseCreateTableEvent(DataBase dataBase, String tableName, String columnsDefinition) {
        this.dataBase = dataBase;
        this.tableName = tableName;
        this.columnsDefinition = columnsDefinition;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnsDefinition() {
        return columnsDefinition;
    }
}
