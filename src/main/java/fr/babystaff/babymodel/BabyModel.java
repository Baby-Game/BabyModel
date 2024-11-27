package fr.babystaff.babymodel;

import fr.babystaff.babymodel.actionBar.ActionBarManager;
import fr.babystaff.babymodel.dataBase.DataBaseManager;
import fr.babystaff.babymodel.discord.bot.DiscordBotManager;
import fr.babystaff.babymodel.hologram.HologramManager;
import fr.babystaff.babymodel.world.World;
import fr.babystaff.babymodel.world.WorldManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BabyModel extends JavaPlugin {

    private DataBaseManager dataBaseManager;

    private DiscordBotManager discordBotManager;
    private WorldManager worldManager;
    private ActionBarManager actionBarManager;
    private HologramManager hologramManager;

    @Override
    public void onEnable() {

        this.dataBaseManager = new DataBaseManager();

        this.worldManager = new WorldManager();

        this.actionBarManager = new ActionBarManager();

        this.hologramManager = new HologramManager();

        this.discordBotManager = new DiscordBotManager();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ActionBarManager getActionBarManager() {
        return actionBarManager;
    }

    public HologramManager getHologramManager() {
        return hologramManager;
    }

    public DataBaseManager getDataBaseManager() {
        return dataBaseManager;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public DiscordBotManager getDiscordBotManager() {
        return discordBotManager;
    }
}
