package fr.babystaff.babymodel.game.events;

import fr.babystaff.babymodel.game.Game;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameCreateEvent extends Event {
    private Game game;

    public Game getGame() {
        return game;
    }

    public GameCreateEvent(Game game) {
        this.game = game;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
