package fr.babystaff.babymodel;

import fr.babystaff.babymodel.actionBar.ActionBarManager;
import fr.babystaff.babymodel.arena.ArenaManager;
import fr.babystaff.babymodel.dataBase.DataBaseManager;
import fr.babystaff.babymodel.discord.bot.DiscordBotManager;
import fr.babystaff.babymodel.game.GameManager;
import fr.babystaff.babymodel.hologram.HologramManager;
import fr.babystaff.babymodel.langue.Language;
import fr.babystaff.babymodel.langue.LanguageManager;
import fr.babystaff.babymodel.npc.NPCManager;
import fr.babystaff.babymodel.team.TeamManager;
import fr.babystaff.babymodel.world.WorldManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class BabyModel extends JavaPlugin {

    private ActionBarManager actionBarManager;
    private ArenaManager arenaManager;
    private DataBaseManager dataBaseManager;
    private DiscordBotManager discordBotManager;
    private GameManager gameManager;
    private HologramManager hologramManager;
    private LanguageManager language;
    private NPCManager npcManager;
    private TeamManager teamManager;
    private WorldManager worldManager;

    @Override
    public void onEnable() {
        String langFolderString = getDataFolder() + "/lang";
        File langFolder = new File(langFolderString);

        this.actionBarManager = new ActionBarManager();
        this.arenaManager = new ArenaManager();
        this.dataBaseManager = new DataBaseManager();
        this.discordBotManager = new DiscordBotManager();
        this.gameManager = new GameManager();
        this.hologramManager = new HologramManager();
        this.language = new LanguageManager(langFolder);
        this.npcManager = new NPCManager();
        this.teamManager = new TeamManager();
        this.worldManager = new WorldManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ActionBarManager getActionBarManager() {
        return actionBarManager;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public DataBaseManager getDataBaseManager() {
        return dataBaseManager;
    }

    public DiscordBotManager getDiscordBotManager() {
        return discordBotManager;
    }

    public HologramManager getHologramManager() {
        return hologramManager;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }

    public LanguageManager getLanguage() {
        return language;
    }
}
