package fr.babystaff.babymodel.langue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.babystaff.babymodel.hologram.events.HologramAddLineEvent;
import fr.babystaff.babymodel.langue.events.PlayerLanguageChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LanguageManager {
    private final HashMap<Player, Language> playerLanguageMap = new HashMap<>();
    private final HashMap<Language, Map<String, String>> translations = new HashMap<>();
    private final File translationFolder;

    public LanguageManager(File pluginFolder) {
        this.translationFolder = new File(pluginFolder, "translations");
        if (!translationFolder.exists()) {
            translationFolder.mkdirs();
        }
        loadAllTranslations();
    }

    public void setPlayerLanguage(Player player, Language language) {
        Language old = playerLanguageMap.get(player);
        playerLanguageMap.put(player, language);
        PlayerLanguageChangeEvent event = new PlayerLanguageChangeEvent(player, language, old);
        Bukkit.getPluginManager().callEvent(event);
    }

    public Language getPlayerLanguage(Player player) {
        return playerLanguageMap.getOrDefault(player, Language.ENGLISH);
    }

    public String translate(Player player, String key) {
        Language language = getPlayerLanguage(player);
        return getTranslation(language, key);
    }

    public String getTranslation(Language language, String key) {
        Map<String, String> langTranslations = translations.get(language);
        if (langTranslations != null) {
            return langTranslations.getOrDefault(key, "Translation not found: " + key);
        }
        return "Translation not found: " + key;
    }

    private void loadAllTranslations() {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();

        for (Language language : Language.values()) {
            File langFile = new File(translationFolder, language.getCode() + ".json");
            if (langFile.exists()) {
                try (FileReader reader = new FileReader(langFile)) {
                    Map<String, String> langMap = gson.fromJson(reader, type);
                    translations.put(language, langMap);
                } catch (IOException e) {
                    System.err.println("Failed to load translation file: " + langFile.getName());
                    e.printStackTrace();
                }
            } else {
                System.out.println("Translation file not found for language: " + language.getCode());
            }
        }
    }
}
