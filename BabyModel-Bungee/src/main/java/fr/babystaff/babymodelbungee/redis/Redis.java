package fr.babystaff.babymodelbungee.redis;

import redis.clients.jedis.Jedis;

public class Redis {
    private String host;
    private int port;
    private String user;
    private String password;
    private Jedis jedis;

    public Redis(String host, int port, String user, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public String generateInstanceName() {
        return getHost() + ":" + getPort() + "/" + getUser();
    }

    public boolean isConnected() {
        return jedis != null && jedis.isConnected();
    }

    public Jedis getJedis() {
        if (jedis == null || !jedis.isConnected()) {
            connect();
        }
        return jedis;
    }

    public Jedis connect() {
        if (jedis == null || !jedis.isConnected()) {
            jedis = new Jedis(host, port);
            try {
                jedis.auth(user, password);
            } catch (Exception e) {
                System.err.println("Erreur lors de l'authentification : " + e.getMessage());
                close();
            }
        }
        return jedis;
    }

    public Jedis getConnection() {
        if (jedis == null || !jedis.isConnected()) {
            return connect();
        }
        return jedis;
    }

    public void setKey(String key, String value) {
        jedis.set(key, value);
    }

    public String getKey(String key) {
        return jedis.get(key);
    }

    public void deleteKey(String key) {
        if (jedis == null || !jedis.isConnected()) {
            connect();
        }
        jedis.del(key);
    }

    public void close() {
        if (jedis != null) {
            jedis.close();
            jedis = null;
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
