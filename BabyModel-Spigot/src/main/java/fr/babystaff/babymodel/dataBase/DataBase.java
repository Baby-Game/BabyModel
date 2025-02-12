package fr.babystaff.babymodel.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private final DatabaseType databaseType;
    private final String host;
    private final String port;
    private final String user;
    private final String pass;
    private final String name;
    private Connection connection;

    public DataBase(DatabaseType databaseType, String host, String port, String user, String pass, String name) {
        this.databaseType = databaseType;
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.name = name;
    }

    public String toURL() {
        return String.format("%s%s:%s/%s",
                databaseType.getDriver(), host, port, name);
    }

    public void connect() {
        long startTime = System.currentTimeMillis();

        try {
            // Vérifie si le driver est bien disponible
            System.out.println("Chargement du driver : " + databaseType.getDriver_class());
            Class.forName(databaseType.getDriver_class());
            System.out.println("Driver chargé avec succès !");

            // Connexion à la base de données
            connection = DriverManager.getConnection(toURL(), user, pass);
            System.out.println("[BabyModel] Connexion réussie à la base de données " + name);

        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Driver JDBC introuvable !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données !");
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("[BabyModel] Connexion à la base de données " + name + " ouverte en " + duration + " ms.");
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("[BabyModel] Connexion à la base de données fermée.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion !");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }
}
