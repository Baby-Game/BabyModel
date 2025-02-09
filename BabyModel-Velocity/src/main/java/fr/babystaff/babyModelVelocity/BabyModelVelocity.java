package fr.babystaff.babyModelVelocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import fr.babystaff.babyModelVelocity.dataBase.DataBaseManager;
import fr.babystaff.babyModelVelocity.discord.bot.DiscordBotManager;
import fr.babystaff.babyModelVelocity.langue.LanguageManager;
import fr.babystaff.babyModelVelocity.redis.RedisManager;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;

@Plugin(id = "babymodel-velocity", name = "BabyModel-Velocity", version = "1.0.0")
public class BabyModelVelocity {
    private final Path dataDirectory;

    private DataBaseManager dataBaseManager;
    private DiscordBotManager discordBotManager;
    private LanguageManager languageManager;
    private RedisManager redisManager;

    @Inject
    public BabyModelVelocity(@DataDirectory Path dataDirectory) {
        this.dataDirectory = dataDirectory;
    }

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        String langFolderString = getPluginFolder() + "/lang";
        File langFolder = new File(langFolderString);

        if (!langFolder.exists()) {
            if (langFolder.mkdirs()) {
                logger.info("Dossier 'lang' créé avec succès dans " + langFolderString);
            } else {
                logger.error("Impossible de créer le dossier 'lang' dans " + langFolderString);
            }
        }

        // dataBaseManager load
        this.dataBaseManager = new DataBaseManager();

        // discordBotManager load
        this.discordBotManager = new DiscordBotManager();

        // languageManager load
        this.languageManager = new LanguageManager(langFolder);

        // redisManager load
        this.redisManager = new RedisManager();
    }

    public Path getPluginFolder() {
        return dataDirectory;
    }

    public Path getDataDirectory() {
        return dataDirectory;
    }

    public DataBaseManager getDataBaseManager() {
        return dataBaseManager;
    }

    public DiscordBotManager getDiscordBotManager() {
        return discordBotManager;
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public Logger getLogger() {
        return logger;
    }
}
