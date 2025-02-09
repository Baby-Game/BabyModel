package fr.babystaff.babyModelVelocity.langue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.velocitypowered.api.proxy.Player;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;

public class LanguageManager {
    private final HashMap<Player, String> playerLanguageMap = new HashMap<>(); // Utilisation de String pour le code de la langue
    private final HashMap<String, Map<String, String>> translations = new HashMap<>(); // Utilisation de String pour le code de la langue
    private final File translationFolder;

    public HashMap<Player, String> getPlayerLanguageMap() {
        return playerLanguageMap;
    }

    public HashMap<String, Map<String, String>> getTranslations() {
        return translations;
    }

    public File getTranslationFolder() {
        return translationFolder;
    }

    private Logger logger;

    public LanguageManager(Logger logger, File pluginFolder) {
        this.logger = logger;
        this.translationFolder = new File(pluginFolder, "");
        if (!translationFolder.exists()) {
            translationFolder.mkdirs();
        }
        loadAllTranslations();
    }

    public void setPlayerLanguage(Player player, String languageCode) {
        String old = playerLanguageMap.get(player);
        playerLanguageMap.put(player, languageCode);
    }

    public String getPlayerLanguage(Player player) {
        return playerLanguageMap.getOrDefault(player, Language.FRENCH.getCode()); // Utilisation du code de la langue par défaut
    }

    public String translate(Player player, String key) {
        String languageCode = getPlayerLanguage(player);
        return getTranslation(languageCode, key);
    }

    public String getTranslation(String languageCode, String key) {
        Map<String, String> langTranslations = translations.get(languageCode);
        if (langTranslations != null) {
            return langTranslations.getOrDefault(key, "Translation not found: " + key);
        }
        return "Translation not found: " + key;
    }

    public String getTranslation(Player player, String key) {
        Map<String, String> langTranslations = translations.get(getPlayerLanguage(player));
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
            String languageCode = language.getCode(); // Utilisation du code de la langue
            File langFile = new File(translationFolder, languageCode + ".json");
            if (langFile.exists()) {
                try (FileReader reader = new FileReader(langFile)) {
                    Map<String, String> langMap = gson.fromJson(reader, type);
                    translations.put(languageCode, langMap); // Utilisation du code de la langue
                } catch (IOException e) {
                    getLogger().error("Failed to load translation file: " + langFile.getName());
                    e.printStackTrace();
                }
            } else {
                getLogger().info("Translation file not found for language: (" + languageCode + ") " + langFile.getPath());
            }
        }
    }

    public void copyDefaultLangFiles() {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();

        for (Language language : Language.values()) {
            String languageCode = language.getCode(); // Utilisation du code de la langue
            String resourcePath = "/lang/" + languageCode + ".json";
            File targetFile = new File(translationFolder, languageCode + ".json");

            try (InputStream in = getClass().getResourceAsStream(resourcePath)) {
                if (in == null) {
                    getLogger().error("Resource not found: " + resourcePath);
                    continue;
                }

                // Charger les traductions depuis les ressources
                Map<String, String> resourceTranslations = gson.fromJson(new InputStreamReader(in), type);

                // Charger les traductions existantes (s'il y en a)
                Map<String, String> existingTranslations = new HashMap<>();
                if (targetFile.exists()) {
                    try (FileReader reader = new FileReader(targetFile)) {
                        existingTranslations = gson.fromJson(reader, type);
                    } catch (IOException e) {
                        getLogger().error("Failed to read existing translation file: " + targetFile.getName());
                        e.printStackTrace();
                    }
                }

                // Fusionner les traductions (préserver les existantes et ajouter les nouvelles)
                Map<String, String> finalExistingTranslations = existingTranslations;
                resourceTranslations.forEach((key, value) ->
                        finalExistingTranslations.putIfAbsent(key, value)
                );

                // Écrire les traductions mises à jour dans le fichier
                try (FileWriter writer = new FileWriter(targetFile)) {
                    gson.toJson(existingTranslations, writer);
                    getLogger().info("Updated language file: " + targetFile.getPath());
                } catch (IOException e) {
                    getLogger().error("Failed to write updated translation file: " + targetFile.getName());
                    e.printStackTrace();
                }
            } catch (IOException e) {
                getLogger().error("Failed to process language file: " + resourcePath);
                e.printStackTrace();
            }
        }
    }

    public Logger getLogger() {
        return logger;
    }
}
