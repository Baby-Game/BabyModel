package fr.babystaff.babyModelVelocity.redis;

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

    public Jedis connect(Redis redis) {
        if (redis == null) {
            throw new IllegalArgumentException("Instance Redis non trouvée : " + redis.generateInstanceName());
        }

        if (!jedisConnections.containsKey(redis.generateInstanceName()) || !jedisConnections.get(redis.generateInstanceName()).isConnected()) {
            Jedis jedis = redis.connect();
            jedisConnections.put(redis.generateInstanceName(), jedis);
            //RedisConnectEvent event = new RedisConnectEvent(jedis, redis);
            //Bukkit.getPluginManager().callEvent(event);
        }

        return jedisConnections.get(redis.generateInstanceName());
    }

    public Jedis connect(String instanceName) {
        Redis redis = redisHashMap.get(instanceName);
        if (redis == null) {
            throw new IllegalArgumentException("Instance Redis non trouvée : " + instanceName);
        }

        if (!jedisConnections.containsKey(instanceName) || !jedisConnections.get(instanceName).isConnected()) {
            Jedis jedis = redis.connect();
            jedisConnections.put(instanceName, jedis);
        }

        return jedisConnections.get(instanceName);
    }

    public void disconnect(String instanceName) {
        Jedis jedis = jedisConnections.get(instanceName);
        if (jedis != null) {
            jedis.close();
            jedisConnections.remove(instanceName);
        }
    }

    public Jedis getConnection(String instanceName) {
        return connect(instanceName);
    }

    public void setKey(String instanceName, String key, String value) {
        Jedis jedis = getConnection(instanceName);
        jedis.set(key, value);
    }

    public String getKey(String instanceName, String key) {
        Jedis jedis = getConnection(instanceName);
        return jedis.get(key);
    }

    public Map<String, Redis> getRedisHashMap() {
        return redisHashMap;
    }
}
