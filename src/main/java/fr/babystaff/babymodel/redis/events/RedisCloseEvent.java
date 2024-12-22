package fr.babystaff.babymodel.redis.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import redis.clients.jedis.Jedis;

public class RedisCloseEvent extends Event {
    private Jedis jedis;

    public RedisCloseEvent(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
