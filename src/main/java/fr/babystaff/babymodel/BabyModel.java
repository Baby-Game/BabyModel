package fr.babystaff.babymodel;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import fr.babystaff.babymodel.actionBar.ActionBarManager;
import fr.babystaff.babymodel.dataBase.DataBaseManager;
import fr.babystaff.babymodel.hologram.HologramManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class BabyModel extends JavaPlugin {
    private LuckPerms luckPerms;
    private ProtocolManager protocolManager;

    private DataBaseManager dataBaseManager;

    private ActionBarManager actionBarManager;
    private HologramManager hologramManager;

    @Override
    public void onEnable() {
        loadLuckPerms();
        loadProtocolLib();

        this.dataBaseManager = new DataBaseManager();

        this.actionBarManager = new ActionBarManager();

        this.hologramManager = new HologramManager();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadProtocolLib() {
        if (getServer().getPluginManager().getPlugin("ProtocolLib") == null) {
            getLogger().severe("ProtocolLib n'est pas installé !");
            return;
        }

        protocolManager = ProtocolLibrary.getProtocolManager();
        getLogger().info("ProtocolLib est prêt à être utilisé !");
    }

    public void loadLuckPerms() {
        luckPerms = LuckPermsProvider.get();

        if (luckPerms == null) {
            getLogger().severe("LuckPerms API n'a pas pu être initialisé !");
            return;
        }

        getLogger().info("LuckPerms API est prêt à être utilisé !");
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public ActionBarManager getActionBarManager() {
        return actionBarManager;
    }

    public HologramManager getHologramManager() {
        return hologramManager;
    }
}
