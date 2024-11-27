package fr.babystaff.babymodel;

import fr.babystaff.babymodel.actionBar.ActionBarManager;
import fr.babystaff.babymodel.dataBase.DataBaseManager;
import fr.babystaff.babymodel.hologram.HologramManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BabyModel extends JavaPlugin {

    private DataBaseManager dataBaseManager;

    private ActionBarManager actionBarManager;
    private HologramManager hologramManager;

    @Override
    public void onEnable() {

        this.dataBaseManager = new DataBaseManager();

        this.actionBarManager = new ActionBarManager();

        this.hologramManager = new HologramManager();

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
}
