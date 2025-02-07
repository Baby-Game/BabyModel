package fr.babystaff.babymodel.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.resps.StreamEntry;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                System.err.println("[BabyModel] Erreur lors de l'authentification : " + e.getMessage());
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

    public Boolean ifKeyExist(String key) {
        return getKey(key) != null;
    }

    public void publishToChannel(String channel, String message) {
        try {
            jedis.publish(channel, message);
            System.out.println("[BabyModel] Message publié sur le channel " + channel + " : " + message);
        } catch (Exception e) {
            System.err.println("[BabyModel] Erreur lors de la publication : " + e.getMessage());
        }
    }

    public void subscribeToChannel(String channel, JedisPubSub listener) {
        new Thread(() -> {
            try (Jedis subscriberJedis = new Jedis(host, port)) {
                subscriberJedis.auth(user, password);
                System.out.println("[BabyModel] Abonné au channel : " + channel);
                subscriberJedis.subscribe(listener, channel);
            } catch (Exception e) {
                System.err.println("[BabyModel] Erreur lors de l'abonnement : " + e.getMessage());
            }
        }).start();
    }

    public void addToStream(String streamName, Map<String, String> data) {
        jedis.xadd(streamName, StreamEntryID.NEW_ENTRY, data);
    }

    public List<StreamEntry> readFromStream(String streamName, String startID, String endID) {
        if (startID == null) startID = "-";
        if (endID == null) endID = "+";
        return jedis.xrange(streamName, startID, endID);
    }

    public void addToSet(String setName, String value) {
        jedis.sadd(setName, value);
    }

    public boolean isInSet(String setName, String value) {
        return jedis.sismember(setName, value);
    }

    public Set<String> getSetMembers(String setName) {
        return jedis.smembers(setName);
    }

    public void removeFromSet(String setName, String value) {
        jedis.srem(setName, value);
    }

    public void addToSortedSet(String sortedSetName, String player, double score) {
        jedis.zadd(sortedSetName, score, player);
    }

    public Set<String> getTopPlayers(String sortedSetName, int start, int end) {
        return new HashSet<>(jedis.zrevrange(sortedSetName, start, end));
    }

    public Double getPlayerScore(String sortedSetName, String player) {
        return jedis.zscore(sortedSetName, player);
    }
    public void setHash(String hashName, Map<String, String> data) {
        jedis.hset(hashName, data);
    }

    public String getFromHash(String hashName, String field) {
        return jedis.hget(hashName, field);
    }

    public Map<String, String> getAllFromHash(String hashName) {
        return jedis.hgetAll(hashName);
    }

    public void deleteFromHash(String hashName, String field) {
        jedis.hdel(hashName, field);
    }

    public void setExpiration(String key, int seconds) {
        jedis.expire(key, seconds);
    }

    public long getTimeToLive(String key) {
        return jedis.ttl(key);
    }

    public void executePipeline(List<Runnable> commands) {
        try (Pipeline pipeline = jedis.pipelined()) {
            for (Runnable command : commands) {
                command.run();
            }
            pipeline.sync();
        }
    }
}