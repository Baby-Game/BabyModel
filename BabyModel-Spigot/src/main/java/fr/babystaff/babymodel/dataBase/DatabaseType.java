package fr.babystaff.babymodel.dataBase;

public enum DatabaseType {
    MYSQL("jdbc:mysql://", "org.mysql.jdbc.Driver"),
    MARIADB("jdbc:mariadb://", "org.mariadb.jdbc.Driver");

    private final String driver;
    private final String driver_class;

    DatabaseType(String driver, String driver_class) {
        this.driver = driver;
        this.driver_class = driver_class;
    }

    public String getDriver() {
        return driver;
    }

    public String getDriver_class() {
        return driver_class;
    }
}
