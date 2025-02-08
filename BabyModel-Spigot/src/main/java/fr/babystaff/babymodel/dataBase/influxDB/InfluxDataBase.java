package fr.babystaff.babymodel.dataBase.influxDB;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

import java.util.concurrent.TimeUnit;

public class InfluxDataBase {
    private String host;
    private String user;
    private String pass;
    private int port;
    private String dataBaseName;
    private InfluxDB influxDb;

    public InfluxDataBase(String host, String user, String pass, int port, String dataBaseName) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.port = port;
        this.dataBaseName = dataBaseName;
    }

    public void connect() {
        influxDb = InfluxDBFactory.connect(getUrl(), user, pass);
        if (influxDb.ping() == null) {
            System.out.println("[BabyModel] influxDB ping is null connection closing");
            close();
        }
    }

    public void close() {
        influxDb.close();
    }

    public void addVariable(String measurement, String serverName, String valueName, String value) {
        Point point = Point.measurement(measurement)
                .tag("server", serverName)
                .addField(valueName, value)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .build();

        getInfluxDb().write(point);
    }

    public void addVariable(String measurement, String serverName, String valueName, double value) {
        Point point = Point.measurement(measurement)
                .tag("server", serverName)
                .addField(valueName, value)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .build();

        getInfluxDb().write(point);
    }

    public void addVariable(String measurement, String serverName, String valueName, int value) {
        Point point = Point.measurement(measurement)
                .tag("server", serverName)
                .addField(valueName, value)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .build();

        getInfluxDb().write(point);
    }

    public void addVariable(String measurement, String serverName, String valueName, long value) {
        Point point = Point.measurement(measurement)
                .tag("server", serverName)
                .addField(valueName, value)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .build();

        getInfluxDb().write(point);
    }

    public void addVariable(String measurement, String serverName, String valueName, boolean value) {
        Point point = Point.measurement(measurement)
                .tag("server", serverName)
                .addField(valueName, value)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .build();

        getInfluxDb().write(point);
    }

    public String getUrl() {
        return "http://" + host +":" + port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public int getPort() {
        return port;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public InfluxDB getInfluxDb() {
        return influxDb;
    }


}
