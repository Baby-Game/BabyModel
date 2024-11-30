package fr.babystaff.babymodel.skin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SkinFetcher {

    public String getSkinTexture(Player player) throws Exception {
        JsonObject properties = getPlayerProperties(player.getName());
        if (properties != null) {
            return properties.get("value").getAsString();
        }
        return null;
    }

    public String getSkinSignature(Player player) throws Exception {
        JsonObject properties = getPlayerProperties(player.getName());
        if (properties != null) {
            return properties.get("signature").getAsString();
        }
        return null;
    }



    private static JsonObject getPlayerProperties(String playerName) throws Exception {
        String uuidUrl = "https://api.mojang.com/users/profiles/minecraft/" + playerName;
        HttpURLConnection uuidConnection = (HttpURLConnection) new URL(uuidUrl).openConnection();
        uuidConnection.setRequestMethod("GET");
        InputStreamReader uuidReader = new InputStreamReader(uuidConnection.getInputStream());
        JsonObject uuidResponse = new JsonParser().parse(uuidReader).getAsJsonObject();
        String uuid = uuidResponse.get("id").getAsString();
        uuidReader.close();

        String profileUrl = "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false";
        HttpURLConnection profileConnection = (HttpURLConnection) new URL(profileUrl).openConnection();
        profileConnection.setRequestMethod("GET");
        InputStreamReader profileReader = new InputStreamReader(profileConnection.getInputStream());
        JsonObject profileResponse = new JsonParser().parse(profileReader).getAsJsonObject();
        profileReader.close();

        JsonArray propertiesArray = profileResponse.getAsJsonArray("properties");
        if (propertiesArray.size() > 0) {
            return propertiesArray.get(0).getAsJsonObject();
        }
        return null;
    }
}
