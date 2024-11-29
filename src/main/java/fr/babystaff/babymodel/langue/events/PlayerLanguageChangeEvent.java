package fr.babystaff.babymodel.langue.events;

import fr.babystaff.babymodel.langue.Language;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLanguageChangeEvent extends Event {
    private Player player;
    private Language newLanguage;
    private Language oldLanguage;

    public PlayerLanguageChangeEvent(Player player, Language newLanguage, Language oldLanguage) {
        this.player = player;
        this.newLanguage = newLanguage;
        this.oldLanguage = oldLanguage;
    }

    public Player getPlayer() {
        return player;
    }

    public Language getNewLanguage() {
        return newLanguage;
    }

    public Language getOldLanguage() {
        return oldLanguage;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
