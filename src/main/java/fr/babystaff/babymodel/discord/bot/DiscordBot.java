package fr.babystaff.babymodel.discord.bot;

import fr.babystaff.babymodel.discord.bot.events.DiscordBotConnectEvent;
import fr.babystaff.babymodel.discord.bot.events.DiscordBotDisconnectEvent;
import fr.babystaff.babymodel.discord.bot.events.DiscordBotSendMessageToChannelEvent;
import fr.babystaff.babymodel.discord.bot.events.DiscordBotSendPrivateMessageEvent;
import fr.babystaff.babymodel.world.events.WorldCreateEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;

import java.util.List;

public class DiscordBot {
    private String token;
    private net.dv8tion.jda.api.JDA jda;

    public DiscordBot(String token) {
        this.token = token;
    }

    public void connect() {
        try {
            jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.GUILD_MESSAGES)
                    .addEventListeners(this)
                    .build();

            DiscordBotConnectEvent event = new DiscordBotConnectEvent(jda);
            Bukkit.getPluginManager().callEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JDA getJDA() {
        return jda;
    }

    public void disconnect() {
        if (jda != null) {
            jda.shutdownNow();
            DiscordBotDisconnectEvent event = new DiscordBotDisconnectEvent(jda);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    public void sendMessageToChannel(String channelId, String message) {
        if (jda != null) {
            TextChannel channel = jda.getTextChannelById(channelId);
            if (channel != null) {
                channel.sendMessage(message).queue();
                DiscordBotSendMessageToChannelEvent event = new DiscordBotSendMessageToChannelEvent(getJDA(), channelId, message);
                Bukkit.getPluginManager().callEvent(event);
            } else {
                System.out.println("Salon introuvable.");
            }
        }
    }

    public String getBotId() {
        if (jda != null && jda.getSelfUser() != null) {
            User selfUser = jda.getSelfUser();
            return selfUser.getId();
        }
        return null;
    }

    public void sendPrivateMessage(String userId, String message) {
        if (jda != null) {
            User user = jda.getUserById(userId);
            if (user != null) {
                user.openPrivateChannel().queue(privateChannel -> {
                    privateChannel.sendMessage(message).queue();
                    DiscordBotSendPrivateMessageEvent event = new DiscordBotSendPrivateMessageEvent(getJDA(), user, message);
                    Bukkit.getPluginManager().callEvent(event);
                });
            }
        }
    }

    public String getChannelIdByName(String guildId, String channelName) {
        if (jda != null) {
            Guild guild = jda.getGuildById(guildId);
            if (guild != null) {
                for (TextChannel channel : guild.getTextChannels()) {
                    if (channel.getName().equalsIgnoreCase(channelName)) {
                        return channel.getId();
                    }
                }
            }
        }
        return null;
    }

    public String getUserIdByUsername(String username) {
        if (jda != null) {
            for (User user : jda.getUsers()) {
                if (user.getName().equalsIgnoreCase(username)) {
                    return user.getId();
                }
            }
        }
        return null;
    }

    public Guild getGuildById(String guildId) {
        if (jda != null) {
            Guild guild = jda.getGuildById(guildId);
            return guild;
        }
        return null;
    }

    public List<Guild> getAllGuilds() {
        if (jda != null) {
            return jda.getGuilds();
        }
        return null;
    }
}
