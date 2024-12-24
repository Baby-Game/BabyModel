package fr.babystaff.babymodel.discord.bot.events;

import net.dv8tion.jda.api.JDA;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DiscordBotDisconnectEvent extends Event {
    private JDA jda;

    public DiscordBotDisconnectEvent(JDA jda) {
        this.jda = jda;
    }

    public JDA getJda() {
        return jda;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
