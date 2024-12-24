package fr.babystaff.babymodelbungee.dataBase;

import fr.babystaff.babymodel.dataBase.DatabaseType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private DatabaseType databaseType;
    private String host;
    private String port;
    private String user;
    private String pass;
    private String name;
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
        final StringBuilder sb = new StringBuilder();

        sb.append(databaseType.getDriver())
                .append(getHost())
                .append(":")
                .append(getPort())
                .append("/")
                .append(getName())
                .append("?user=")
                .append(getUser())
                .append("&password=")
                .append(getPass());

        return sb.toString();
    }

    public void connect() {
        try {
            Class.forName(databaseType.getDriver_class());
            this.connection = DriverManager.getConnection(toURL());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (this.connection != null) {
                if (!this.connection.isClosed()) {
                    this.connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        if (this.connection != null) {
            if (!this.connection.isClosed()) {
                return this.connection;
            }
        }
        connect();
        return this.connection;
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
