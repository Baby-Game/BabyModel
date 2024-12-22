package fr.babystaff.babymodel.redis.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import redis.clients.jedis.Jedis;

public class RedisGetKeyEvent extends Event {
    private Jedis jedis;
    private String key;

    public RedisGetKeyEvent(Jedis jedis, String key) {
        this.jedis = jedis;
        this.key = key;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
