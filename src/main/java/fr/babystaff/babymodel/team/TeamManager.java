package fr.babystaff.babymodel.team;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeamManager {
    private final HashMap<String, Team> teamHashMap = new HashMap<>();
    private final HashMap<Player, Team> playerTeamHashMap = new HashMap<>();

    public HashMap<String, Team> getTeamHashMap() {
        return teamHashMap;
    }

    public HashMap<Player, Team> getPlayerTeamHashMap() {
        return playerTeamHashMap;
    }

    public boolean createTeam(Team team) {
        if (teamHashMap.containsKey(team.getId())) {
            return false;
        }
        teamHashMap.put(team.getId(), team);
        return true;
    }

    public boolean removeTeam(String teamId) {
        Team team = teamHashMap.remove(teamId);
        if (team != null) {
            for (Player player : playerTeamHashMap.keySet()) {
                if (playerTeamHashMap.get(player).equals(team)) {
                    playerTeamHashMap.remove(player);
                }
            }
            return true;
        }
        return false;
    }

    public boolean addPlayerToTeam(Player player, String teamId) {
        Team team = teamHashMap.get(teamId);
        if (team == null || playerTeamHashMap.containsKey(player) || team.isFull()) {
            return false;
        }
        playerTeamHashMap.put(player, team);
        team.addPlayer(player);
        return true;
    }

    public boolean removePlayerFromTeam(Player player) {
        Team team = playerTeamHashMap.remove(player);
        if (team != null) {
            team.removePlayer(player);
            return true;
        }
        return false;
    }

    public Team getPlayerTeam(Player player) {
        return playerTeamHashMap.get(player);
    }

    public Team getTeamById(String teamId) {
        return teamHashMap.get(teamId);
    }

    public boolean teamExists(String teamId) {
        return teamHashMap.containsKey(teamId);
    }

    public boolean isPlayerInTeam(Player player) {
        return playerTeamHashMap.containsKey(player);
    }

    public int getTeamCount() {
        return teamHashMap.size();
    }

    public int getTotalPlayersInTeams() {
        return playerTeamHashMap.size();
    }
}
