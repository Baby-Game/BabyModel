package fr.babystaff.babymodel;

import fr.babystaff.babymodel.InventoryManager.InventoryClickEventOnVoidItem;
import fr.babystaff.babymodel.NametagNMS.NametagNMS;
import fr.babystaff.babymodel.ServerManager.ServerManager;
import fr.babystaff.babymodel.ServerManager.ServerStatus;
import fr.babystaff.babymodel.actionBar.ActionBarManager;
import fr.babystaff.babymodel.arena.ArenaManager;
import fr.babystaff.babymodel.dataBase.DataBaseManager;
import fr.babystaff.babymodel.discord.bot.DiscordBotManager;
import fr.babystaff.babymodel.game.GameManager;
import fr.babystaff.babymodel.hologram.HologramManager;
import fr.babystaff.babymodel.langue.LanguageManager;
import fr.babystaff.babymodel.npc.NPCManager;
import fr.babystaff.babymodel.playerUtils.PlayerUtils;
import fr.babystaff.babymodel.redis.RedisManager;
import fr.babystaff.babymodel.skin.SkinFetcher;
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
    private NametagNMS nametagNMS;
    private NPCManager npcManager;
    private RedisManager redisManager;
    private ServerManager serverManager;
    private SkinFetcher skinFetcher;
    private TeamManager teamManager;
    private WorldManager worldManager;
    private PlayerUtils playerUtils;

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();

        this.serverManager = new ServerManager();

        String langFolderString = getDataFolder() + "/lang";
        File langFolder = new File(langFolderString);

        this.actionBarManager = new ActionBarManager(this);
        this.arenaManager = new ArenaManager();
        this.dataBaseManager = new DataBaseManager();
        this.discordBotManager = new DiscordBotManager();
        this.gameManager = new GameManager();
        this.hologramManager = new HologramManager();
        this.language = new LanguageManager(langFolder);
        this.nametagNMS = new NametagNMS();
        this.npcManager = new NPCManager();
        this.redisManager = new RedisManager();
        this.skinFetcher = new SkinFetcher();
        this.teamManager = new TeamManager();
        this.worldManager = new WorldManager();
        this.playerUtils = new PlayerUtils();

        // inventory event
        getServer().getPluginManager().registerEvents(new InventoryClickEventOnVoidItem(), this);

        serverManager.setServerStatus(ServerStatus.START);

        long endTime = System.currentTimeMillis();
        long durationMs = endTime - startTime;
        double durationSeconds = durationMs / 1000.0;


        String banner =
                "BabyModel by BabyStaff, Louis_292 is enable (" + durationSeconds +  "s)\n  ____        _           __  __           _      _ \n"
                        + " | __ )  __ _| |__  _   _|  \\/  | ___   __| | ___| |\n"
                        + " |  _ \\ / _` | '_ \\| | | | |\\/| |/ _ \\ / _` |/ _ \\ |\n"
                        + " | |_) | (_| | |_) | |_| | |  | | (_) | (_| |  __/ |\n"
                        + " |____/ \\__,_|_.__/ \\__, |_|  |_|\\___/ \\__,_|\\___|_|\n"
                        + "                    |___/                            \n";

        getLogger().info(banner);
    }

    @Override
    public void onDisable() {
        serverManager.setServerStatus(ServerStatus.STOPPING);

        hologramManager.deleteAllHolograms();

        serverManager.setServerStatus(ServerStatus.STOP);
    }

    public SkinFetcher getSkinFetcher() {
        return skinFetcher;
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

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }

    public NametagNMS getNametagNMS() {
        return nametagNMS;
    }

    public PlayerUtils getPlayerUtils() {
        return playerUtils;
    }
}
