package fr.babystaff.babymodel.dataBase.events;

import fr.babystaff.babymodel.dataBase.DataBase;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DataBaseExecuteSQL extends Event {
    private DataBase dataBase;
    private String sql;

    public DataBaseExecuteSQL(DataBase dataBase, String sql) {
        this.dataBase = dataBase;
        this.sql = sql;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public String getSql() {
        return sql;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
