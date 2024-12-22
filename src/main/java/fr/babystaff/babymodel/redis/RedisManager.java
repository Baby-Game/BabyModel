package fr.babystaff.babymodel.redis;

import fr.babystaff.babymodel.redis.events.RedisCloseEvent;
import fr.babystaff.babymodel.redis.events.RedisConnectEvent;
import fr.babystaff.babymodel.redis.events.RedisGetKeyEvent;
import fr.babystaff.babymodel.redis.events.RedisSetKeyEvent;
import org.bukkit.Bukkit;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class RedisManager {
    private Map<String, Redis> redisHashMap = new HashMap<>();
    private Map<String, Jedis> jedisConnections = new HashMap<>();

    public void addRedisInstance(Redis redis) {
        String instanceName = generateInstanceName(redis);
        redisHashMap.put(instanceName, redis);
    }

    private String generateInstanceName(Redis redis) {
        return redis.getHost() + ":" + redis.getPort() + "/" + redis.getUser();
    }

    public Jedis connect(String instanceName) {
        Redis redis = redisHashMap.get(instanceName);
        if (redis == null) {
            throw new IllegalArgumentException("Instance Redis non trouvée : " + instanceName);
        }

        if (!jedisConnections.containsKey(instanceName) || !jedisConnections.get(instanceName).isConnected()) {
            Jedis jedis = redis.connect();
            jedisConnections.put(instanceName, jedis);
            RedisConnectEvent event = new RedisConnectEvent(jedis, redis);
            Bukkit.getPluginManager().callEvent(event);
        }

        return jedisConnections.get(instanceName);
    }

    public void disconnect(String instanceName) {
        Jedis jedis = jedisConnections.get(instanceName);
        if (jedis != null) {
            jedis.close();
            jedisConnections.remove(instanceName);
            RedisCloseEvent event = new RedisCloseEvent(jedis);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    public Jedis getConnection(String instanceName) {
        return connect(instanceName);
    }

    public void setKey(String instanceName, String key, String value) {
        Jedis jedis = getConnection(instanceName);
        jedis.set(key, value);
        RedisSetKeyEvent event = new RedisSetKeyEvent(jedis, key, value);
        Bukkit.getPluginManager().callEvent(event);
    }

    public String getKey(String instanceName, String key) {
        Jedis jedis = getConnection(instanceName);
        RedisGetKeyEvent event = new RedisGetKeyEvent(jedis, key);
        Bukkit.getPluginManager().callEvent(event);
        return jedis.get(key);
    }

    public Map<String, Redis> getRedisHashMap() {
        return redisHashMap;
    }
}
