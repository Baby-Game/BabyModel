package fr.babystaff.babymodel.actionBar;

import fr.babystaff.babymodel.actionBar.events.ActionbarRemoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ActionBarManager {
    private final Map<Player, ActionBar> activeActionBars = new HashMap<>();

    public void addActionBar(Player player, String content) {
        if (activeActionBars.containsKey(player)) {
            updateActionBar(player, content);
        } else {
            ActionBar actionBar = new ActionBar(player, content);
            activeActionBars.put(player, actionBar);
        }
    }

    public Map<Player, ActionBar> getActiveActionBars() {
        return activeActionBars;
    }

    public void removeActionBar(Player player) {
        activeActionBars.remove(player);
        ActionbarRemoveEvent event = new ActionbarRemoveEvent(player);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void updateActionBar(Player player, String newContent) {
        ActionBar actionBar = activeActionBars.get(player);
        if (actionBar != null) {
            actionBar.setContent(newContent);
        } else {
            addActionBar(player, newContent);
        }
    }

    public boolean hasActionBar(Player player) {
        return activeActionBars.containsKey(player);
    }

    public void sendActionBars() {
        for (ActionBar actionBar : activeActionBars.values()) {
            actionBar.send();
        }
    }

    public void startAutoFillTask(long intervalTicks) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("BabyModel"), this::sendActionBars, 0, intervalTicks);
    }
}
