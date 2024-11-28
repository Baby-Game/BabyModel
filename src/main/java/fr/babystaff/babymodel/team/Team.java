package fr.babystaff.babymodel.team;

import fr.babystaff.babymodel.hologram.events.HologramAddLineEvent;
import fr.babystaff.babymodel.team.events.TeamAddPlayerEvent;
import fr.babystaff.babymodel.team.events.TeamCreateEvent;
import fr.babystaff.babymodel.team.events.TeamRemoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private ChatColor chatColor;
    private String id;
    private String name;
    private int maxPlayerPerTeam;
    private final List<Player> playerList = new ArrayList<>();

    // Constructeur
    public Team(ChatColor chatColor, String id, String name, int maxPlayerPerTeam) {
        this.chatColor = chatColor;
        this.id = id;
        this.name = name;
        this.maxPlayerPerTeam = maxPlayerPerTeam;

        TeamCreateEvent event = new TeamCreateEvent(this);
        Bukkit.getPluginManager().callEvent(event);
    }

    public boolean addPlayer(Player player) {
        if (playerList.contains(player)) {
            return false;
        }
        if (playerList.size() >= maxPlayerPerTeam) {
            return false;
        }
        playerList.add(player);
        TeamAddPlayerEvent event = new TeamAddPlayerEvent(this, player);
        Bukkit.getPluginManager().callEvent(event);
        return true;
    }

    public boolean removePlayer(Player player) {
        TeamRemoveEvent event = new TeamRemoveEvent(this, player);
        Bukkit.getPluginManager().callEvent(event);
        return playerList.remove(player);
    }

    public void clearTeam() {
        playerList.clear();
    }

    public boolean hasPlayer(Player player) {
        return playerList.contains(player);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(playerList);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxPlayerPerTeam(int maxPlayerPerTeam) {
        this.maxPlayerPerTeam = maxPlayerPerTeam;
    }

    public void setChatColor(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxPlayerPerTeam() {
        return maxPlayerPerTeam;
    }

    public int getCurrentPlayerCount() {
        return playerList.size();
    }

    public boolean isFull() {
        return playerList.size() >= maxPlayerPerTeam;
    }

    @Override
    public String toString() {
        return chatColor + "[" + name + "] (ID: " + id + ", Joueurs: " + playerList.size() + "/" + maxPlayerPerTeam + ")";
    }
}
