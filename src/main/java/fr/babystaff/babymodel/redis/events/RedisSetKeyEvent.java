package fr.babystaff.babymodel.redis.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import redis.clients.jedis.Jedis;

public class RedisSetKeyEvent extends Event {
    private Jedis jedis;
    private String key;
    private String value;

    public RedisSetKeyEvent(Jedis jedis, String key, String value) {
        this.jedis = jedis;
        this.key = key;
        this.value = value;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
