package fr.babystaff.babymodel.discord.bot;

import java.util.HashMap;
import java.util.Map;

public class DiscordBotManager {
    private final Map<String, DiscordBot> discordBotHashMap = new HashMap<>();

    public Map<String, DiscordBot> getDiscordBotHashMap() {
        return discordBotHashMap;
    }

    public void connectBot(DiscordBot discordBot) {
        if (discordBot == null) {
            return;
        }

        if (!discordBotHashMap.containsKey(discordBot.getBotId())) {
            try {
                discordBotHashMap.put(discordBot.getBotId(), discordBot);
                discordBot.connect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeBot(DiscordBot discordBot) {
        if (discordBot == null) {
            return;
        }

        if (discordBotHashMap.containsKey(discordBot.getBotId())) {
            try {
                discordBotHashMap.remove(discordBot.getBotId());
                discordBot.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public DiscordBot getDiscordBot(String id) {
        return discordBotHashMap.get(id);
    }

    public boolean isBotConnected(String botId) {
        return discordBotHashMap.containsKey(botId);
    }

    public String[] getConnectedBotIds() {
        return discordBotHashMap.keySet().toArray(new String[0]);
    }
}
