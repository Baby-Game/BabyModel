package fr.babystaff.babymodel.actionBar;

import fr.babystaff.babymodel.BabyModel;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionBarManager {
    private final List<Player> activeActionBar = new ArrayList<>();
    private final Map<Player, String> actionBarMap = new HashMap<>();

    private final BabyModel babyModel;

    public ActionBarManager(BabyModel babyModel) {
        this.babyModel = babyModel;
    }

    public void fillActionBar(Player player, String text) {
        actionBarMap.put(player, text);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (actionBarMap.containsKey(player)) {
                    sendActionBar(player, text);
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(babyModel, 0L, 20L);
    }

    public void fillActionBar(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (actionBarMap.containsKey(player)) {
                    sendActionBar(player, actionBarMap.get(player));
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(babyModel, 0L, 20L);
    }

    public void stopFillActionBar(Player player) {
        actionBarMap.remove(player);
    }

    public void updateFillActionBar(Player player, String text) {
        if (actionBarMap.containsKey(player)) {
            actionBarMap.remove(player);
            actionBarMap.put(player, text);
        } else {
            actionBarMap.put(player, text);
        }
    }

    public void sendActionBar(Player player, String text) {
        ActionBar actionBar = new ActionBar(player, text);
        actionBar.sendActionBar();
    }

    public Map<Player, String> getActionBarMap() {
        return actionBarMap;
    }

    public List<Player> getActiveActionBar() {
        return activeActionBar;
    }

    public BabyModel getBabyModel() {
        return babyModel;
    }
}
