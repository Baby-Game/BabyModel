package fr.babystaff.babymodel.team.events;

import fr.babystaff.babymodel.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TeamRemoveEvent extends Event {
    private Team team;
    private Player player;

    public TeamRemoveEvent(Team team, Player player) {
        this.team = team;
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
