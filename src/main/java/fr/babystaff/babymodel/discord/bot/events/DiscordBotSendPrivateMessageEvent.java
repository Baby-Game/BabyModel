package fr.babystaff.babymodel.discord.bot.events;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DiscordBotSendPrivateMessageEvent extends Event {
    private JDA jda;
    private User user;
    private String message;

    public DiscordBotSendPrivateMessageEvent(JDA jda, User user, String message) {
        this.jda = jda;
        this.user = user;
        this.message = message;
    }

    public JDA getJda() {
        return jda;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
