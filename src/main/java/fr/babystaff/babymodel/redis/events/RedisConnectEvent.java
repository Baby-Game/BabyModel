package fr.babystaff.babymodel.redis.events;

import fr.babystaff.babymodel.redis.Redis;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import redis.clients.jedis.Jedis;

public class RedisConnectEvent extends Event {
    private Jedis jedis;
    private Redis redis;

    public RedisConnectEvent(Jedis jedis, Redis redis) {
        this.jedis = jedis;
        this.redis = redis;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
