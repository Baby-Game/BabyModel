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

        String banner =
                "BabyModel by BabyStaff, Louis_292 is enable\n  ____        _           __  __           _      _ \n"
                        + " | __ )  __ _| |__  _   _|  \\/  | ___   __| | ___| |\n"
                        + " |  _ \\ / _` | '_ \\| | | | |\\/| |/ _ \\ / _` |/ _ \\ |\n"
                        + " | |_) | (_| | |_) | |_| | |  | | (_) | (_| |  __/ |\n"
                        + " |____/ \\__,_|_.__/ \\__, |_|  |_|\\___/ \\__,_|\\___|_|\n"
                        + "                    |___/                            \n";

        getLogger().info(banner);
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
