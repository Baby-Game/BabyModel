package fr.babystaff.babymodel.dataBase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBase {
    private final DatabaseType databaseType;
    private final String host;
    private final String port;
    private final String user;
    private final String pass;
    private final String name;
    private Connection connection;

    private HikariDataSource dataSource;

    public DataBase(DatabaseType databaseType, String host, String port, String user, String pass, String name) {
        this.databaseType = databaseType;
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.name = name;
    }

    public String toURL() {
        return String.format("%s%s:%s/%s?user=%s&password=%s",
                databaseType.getDriver(), host, port, name, user, pass);
    }

    public void connect() {
        long startTime = System.currentTimeMillis();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(toURL());
        config.setUsername(user);
        config.setPassword(pass);
        config.setDriverClassName(getDatabaseType().getDriver_class());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("[BabyModel] Connexion à la base de données " + getName() + " ouverte en " + duration + " ms.");
        this.dataSource = new HikariDataSource(config);
    }

    public void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }


    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
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
