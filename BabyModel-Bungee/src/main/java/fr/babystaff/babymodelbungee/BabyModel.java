package fr.babystaff.babymodelbungee;

import fr.babystaff.babymodelbungee.dataBase.DataBaseManager;
import fr.babystaff.babymodelbungee.discord.bot.DiscordBotManager;
import fr.babystaff.babymodelbungee.langue.LanguageManager;
import fr.babystaff.babymodelbungee.redis.RedisManager;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;

public final class BabyModel extends Plugin {
    private DataBaseManager dataBaseManager;
    private DiscordBotManager discordBotManager;
    private LanguageManager languageManager;
    private RedisManager redisManager;

    @Override
    public void onEnable() {
        String langFolderString = getDataFolder() + "/lang";
        File langFolder = new File(langFolderString);

        this.dataBaseManager = new DataBaseManager();
        this.discordBotManager = new DiscordBotManager();
        this.languageManager = new LanguageManager(langFolder);
        this.redisManager = new RedisManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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
}
