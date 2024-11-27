package fr.babystaff.babymodel.actionBar;

import fr.babystaff.babymodel.actionBar.events.ActionBarSendEvent;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {
    private final Player player;
    private String content;

    public ActionBar(Player player, String content) {
        this.player = player;
        this.content = content;
    }

    public Player getPlayer() {
        return player;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void send() {
        if (player == null || !player.isOnline()) return;
        ChatComponentText chatComponent = new ChatComponentText(content);
        PacketPlayOutChat packet = new PacketPlayOutChat(chatComponent, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        ActionBarSendEvent event = new ActionBarSendEvent(player, content);
        Bukkit.getPluginManager().callEvent(event);
    }
}
