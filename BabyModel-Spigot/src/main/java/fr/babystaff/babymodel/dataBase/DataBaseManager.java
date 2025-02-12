package fr.babystaff.babymodel.dataBase;

import fr.babystaff.babymodel.dataBase.events.*;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseManager {

    private final Map<String, DataBase> dataBaseHashMap = new HashMap<>();

    public Map<String, DataBase> getDataBaseHashMap() {
        return dataBaseHashMap;
    }

    public void openConnectionDataBase(DataBase dataBase) {
        if (dataBase == null) {
            System.err.println("[BabyModel] La base de données est nulle. Impossible d'ouvrir la connexion.");
            return;
        }

        synchronized (dataBaseHashMap) {
            if (!dataBaseHashMap.containsKey(dataBase.getName())) {
                dataBase.connect();
                dataBaseHashMap.put(dataBase.getName(), dataBase);
            } else {
                System.err.println("[BabyModel] La connexion à la base de données " + dataBase.getName() + " est déjà ouverte.");
            }
        }
    }

    public <T> T executeQuery(DataBase dataBase, String query, SQLFunction<ResultSet, T> handler, Object... params) throws SQLException {
        if (dataBase == null) {
            throw new IllegalArgumentException("La base de données est nulle.");
        }

        long startTime = System.currentTimeMillis();

        try (Connection connection = dataBase.getConnection();
             PreparedStatement statement = prepareStatement(connection, query, params);
             ResultSet resultSet = statement.executeQuery()) {

            T result = handler.apply(resultSet);

            long endTime = System.currentTimeMillis();
            System.out.println("[BabyModel] Requête exécutée en " + (endTime - startTime) + " ms : " + query);
            return result;
        }
    }

    private PreparedStatement prepareStatement(Connection connection, String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        return statement;
    }

    public boolean executeSQL(DataBase dataBase, String sql) {
        if (dataBase == null) {
            throw new IllegalArgumentException("La base de données est nulle.");
        }

        long startTime = System.currentTimeMillis();

        try (Connection connection = dataBase.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

            long endTime = System.currentTimeMillis();
            Bukkit.getPluginManager().callEvent(new DataBaseExecuteSQL(dataBase, sql));
            System.out.println("[BabyModel] SQL exécuté en " + (endTime - startTime) + " ms : " + sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean tableExists(DataBase dataBase, String tableName) {
        if (dataBase == null) {
            throw new IllegalArgumentException("La base de données est nulle.");
        }

        long startTime = System.currentTimeMillis();

        try (Connection connection = dataBase.getConnection();
             ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, null)) {

            boolean exists = resultSet.next();
            long endTime = System.currentTimeMillis();

            System.out.println("[BabyModel] Vérification de l'existence de la table '" + tableName + "' exécutée en " + (endTime - startTime) + " ms.");
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean columnExists(DataBase dataBase, String tableName, String columnName) {
        if (dataBase == null) {
            throw new IllegalArgumentException("La base de données est nulle.");
        }

        long startTime = System.currentTimeMillis();

        try (Connection connection = dataBase.getConnection();
             ResultSet resultSet = connection.getMetaData().getColumns(null, null, tableName, columnName)) {

            boolean exists = resultSet.next();
            long endTime = System.currentTimeMillis();

            System.out.println("[BabyModel] Vérification de l'existence de la colonne '" + columnName + "' dans la table '" + tableName + "' exécutée en " + (endTime - startTime) + " ms.");
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnectionDataBase(DataBase dataBase) {
        if (dataBase == null) {
            System.err.println("La base de données est nulle. Impossible de fermer la connexion.");
            return;
        }

        synchronized (dataBaseHashMap) {
            if (dataBaseHashMap.containsKey(dataBase.getName())) {
                long startTime = System.currentTimeMillis();

                dataBase.close();

                long endTime = System.currentTimeMillis();
                System.out.println("[BabyModel] Connexion à la base de données " + dataBase.getName() + " fermée en " + (endTime - startTime) + " ms.");

                dataBaseHashMap.remove(dataBase.getName());
            } else {
                System.err.println("Aucune connexion ouverte pour la base de données " + dataBase.getName() + ".");
            }
        }
    }

    @FunctionalInterface
    public interface SQLFunction<T, R> {
        R apply(T t) throws SQLException;
    }
}
