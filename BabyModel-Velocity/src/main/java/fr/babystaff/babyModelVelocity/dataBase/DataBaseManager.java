package fr.babystaff.babyModelVelocity.dataBase;
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

    public <T> T executeQuery(DataBase dataBase,String query, SQLFunction<ResultSet, T> handler, Object... params) throws SQLException {
        try (Connection connection = dataBase.getConnection(); // Obtenez une connexion à la base de données
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Paramétrer les valeurs de la requête
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                return handler.apply(resultSet); // Passe le ResultSet à la fonction pour traitement
            }
        }
    }

    // Interface fonctionnelle pour le traitement du ResultSet
    @FunctionalInterface
    public interface SQLFunction<T, R> {
        R apply(T t) throws SQLException;
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

    public String getStringFromColumn(DataBase dataBase, String tableName, String columnName, String whereCondition) {
        String sql = "SELECT " + columnName + " FROM " + tableName + " WHERE " + whereCondition;

        try (Connection connection = dataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getString(columnName); // Retourne la valeur du champ 'columnName'
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retourne null si aucune valeur trouvée
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
        return executeSQL(dataBase, sql);
    }

    public boolean createColumn(DataBase dataBase, String tableName, String columnDefinition) {
        String sql = "ALTER TABLE " + tableName + " ADD COLUMN " + columnDefinition;
        return executeSQL(dataBase, sql);
    }

    public boolean deleteTable(DataBase dataBase, String tableName) {
        String sql = "DROP TABLE IF EXISTS " + tableName;
        return executeSQL(dataBase, sql);
    }

    public boolean deleteColumn(DataBase dataBase, String tableName, String columnName) {
        String sql = "ALTER TABLE " + tableName + " DROP COLUMN " + columnName;
        return executeSQL(dataBase, sql);
    }

    public boolean writeToColumn(DataBase dataBase, String tableName, String columnName, String value) {
        String sql = "INSERT INTO " + tableName + " (" + columnName + ") VALUES (?)";

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

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
