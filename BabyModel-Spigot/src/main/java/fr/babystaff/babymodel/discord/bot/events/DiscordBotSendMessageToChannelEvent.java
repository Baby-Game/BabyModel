package fr.babystaff.babymodel.discord.bot.events;

import net.dv8tion.jda.api.JDA;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DiscordBotSendMessageToChannelEvent extends Event {
    private JDA jda;
    private String channelId;
    private String message;

    public DiscordBotSendMessageToChannelEvent(JDA jda, String channelId, String message) {
        this.jda = jda;
        this.channelId = channelId;
        this.message = message;
    }

    public JDA getJda() {
        return jda;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
