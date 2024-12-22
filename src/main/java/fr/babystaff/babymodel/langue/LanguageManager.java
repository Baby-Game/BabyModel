package fr.babystaff.babymodel.langue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.babystaff.babymodel.langue.events.PlayerLanguageChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class LanguageManager {
    private final HashMap<Player, Language> playerLanguageMap = new HashMap<>();
    private final HashMap<Language, Map<String, String>> translations = new HashMap<>();
    private final File translationFolder;

    public HashMap<Player, Language> getPlayerLanguageMap() {
        return playerLanguageMap;
    }

    public HashMap<Language, Map<String, String>> getTranslations() {
        return translations;
    }

    public File getTranslationFolder() {
        return translationFolder;
    }

    public LanguageManager(File pluginFolder) {
        this.translationFolder = new File(pluginFolder, "");
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
        return playerLanguageMap.getOrDefault(player, Language.FRENCH);
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

        // Copier les fichiers de langue par défaut, si nécessaire
        copyDefaultLangFiles();

        // Charger les traductions depuis les fichiers JSON
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
                System.out.println("Translation file not found for language: (" + language.getCode() + ") " + langFile.getPath());
            }
        }
    }

    private void copyDefaultLangFiles() {
        for (Language language : Language.values()) {
            String resourcePath = "/lang/" + language.getCode() + ".json";
            File targetFile = new File(translationFolder, language.getCode() + ".json");

            if (!targetFile.exists()) {
                try (InputStream in = getClass().getResourceAsStream(resourcePath)) {
                    if (in == null) {
                        System.err.println("Resource not found: " + resourcePath);
                        continue;
                    }

                    // Copier le fichier depuis les ressources vers le dossier des traductions
                    Files.copy(in, targetFile.toPath());
                    System.out.println("Copied default language file: " + targetFile.getPath());
                } catch (IOException e) {
                    System.err.println("Failed to copy language file: " + resourcePath);
                    e.printStackTrace();
                }
            }
        }
    }
}
