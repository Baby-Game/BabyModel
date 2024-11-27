package fr.babystaff.babymodel.dataBase;

import fr.babystaff.babymodel.dataBase.events.*;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseManager {

    private final Map<String, DataBase> dataBaseHashMap = new HashMap<>();

    public void openConnectionDataBase(DataBase dataBase) {
        if (dataBase == null) {
            System.err.println("La base de données est nulle. Impossible d'ouvrir la connexion.");
            return;
        }

        synchronized (dataBaseHashMap) {
            if (!dataBaseHashMap.containsKey(dataBase.getName())) {
                dataBase.connect(); // Assure-toi que la méthode connect() existe dans DataBase
                dataBaseHashMap.put(dataBase.getName(), dataBase);
                System.out.println("Connexion à la base de données " + dataBase.getName() + " ouverte.");
            } else {
                System.err.println("La connexion à la base de données " + dataBase.getName() + " est déjà ouverte.");
            }
        }
    }

    public void closeConnectionDataBase(DataBase dataBase) {
        if (dataBase == null) {
            System.err.println("La base de données est nulle. Impossible de fermer la connexion.");
            return;
        }

        synchronized (dataBaseHashMap) {
            if (dataBaseHashMap.containsKey(dataBase.getName())) {
                dataBase.close(); // Assure-toi que la méthode close() existe dans DataBase
                dataBaseHashMap.remove(dataBase.getName());
                System.out.println("Connexion à la base de données " + dataBase.getName() + " fermée.");
            } else {
                System.err.println("Aucune connexion ouverte pour la base de données " + dataBase.getName() + ".");
            }
        }
    }

    public DataBase getDataBase(String dataBaseName) {
        if (dataBaseName == null || dataBaseName.isEmpty()) {
            System.err.println("Le nom de la base de données est invalide.");
            return null;
        }

        synchronized (dataBaseHashMap) {
            return dataBaseHashMap.getOrDefault(dataBaseName, null);
        }
    }

    public boolean tableExists(DataBase dataBase, String tableName) {
        try (Connection connection = dataBase.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet resultSet = metaData.getTables(null, null, tableName, null)) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean columnExists(DataBase dataBase, String tableName, String columnName) {
        try (Connection connection = dataBase.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet resultSet = metaData.getColumns(null, null, tableName, columnName)) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createTable(DataBase dataBase, String tableName, String columnsDefinition) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + columnsDefinition + ")";
        DataBaseCreateTableEvent event = new DataBaseCreateTableEvent(dataBase, tableName, columnsDefinition);
        Bukkit.getPluginManager().callEvent(event);
        return executeSQL(dataBase, sql);
    }

    public boolean createColumn(DataBase dataBase, String tableName, String columnDefinition) {
        String sql = "ALTER TABLE " + tableName + " ADD COLUMN " + columnDefinition;
        DataBaseCreateColumnsEvent event = new DataBaseCreateColumnsEvent(dataBase, tableName, columnDefinition);
        Bukkit.getPluginManager().callEvent(event);
        return executeSQL(dataBase, sql);
    }

    public boolean deleteTable(DataBase dataBase, String tableName) {
        String sql = "DROP TABLE IF EXISTS " + tableName;
        DataBaseDeleteTableEvent event = new DataBaseDeleteTableEvent(dataBase, tableName);
        Bukkit.getPluginManager().callEvent(event);
        return executeSQL(dataBase, sql);
    }

    public boolean deleteColumn(DataBase dataBase, String tableName, String columnName) {
        String sql = "ALTER TABLE " + tableName + " DROP COLUMN " + columnName;
        DataBaseDeleteColumnEvent event = new DataBaseDeleteColumnEvent(dataBase, tableName, columnName);
        Bukkit.getPluginManager().callEvent(event);
        return executeSQL(dataBase, sql);
    }

    public boolean writeToColumn(DataBase dataBase, String tableName, String columnName, String value) {
        String sql = "INSERT INTO " + tableName + " (" + columnName + ") VALUES (?)";

        DataBaseWriteToColumnEvent event = new DataBaseWriteToColumnEvent(dataBase, tableName, columnName, value);
        Bukkit.getPluginManager().callEvent(event);

        try (Connection connection = dataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, value);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean executeSQL(DataBase dataBase, String sql) {
        try (Connection connection = dataBase.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            DataBaseExecuteSQL event = new DataBaseExecuteSQL(dataBase, sql);
            Bukkit.getPluginManager().callEvent(event);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
