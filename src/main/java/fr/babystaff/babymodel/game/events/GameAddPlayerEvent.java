package fr.babystaff.babymodel.game.events;

import fr.babystaff.babymodel.game.Game;
import fr.babystaff.babymodel.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameAddPlayerEvent extends Event {
    private Player player;
    private Game game;

    public GameAddPlayerEvent(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
