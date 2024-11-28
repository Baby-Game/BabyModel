package fr.babystaff.babymodel.team.events;

import fr.babystaff.babymodel.team.Team;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TeamCreateEvent extends Event {
    private Team team;

    public Team getTeam() {
        return team;
    }

    public TeamCreateEvent(Team team) {
        this.team = team;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
