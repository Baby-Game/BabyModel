package fr.babystaff.babymodel.actionBar;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {
    private Player player;
    private String text;

    public ActionBar(Player player, String text) {
        this.player = player;
        this.text = text;
    }

    public void sendActionBar(Player player, String text) {
        IChatBaseComponent chatComponent = new ChatComponentText(text);
        PacketPlayOutChat packet = new PacketPlayOutChat(chatComponent, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void sendActionBar() {
        sendActionBar(player, text);
    }

    public void sendActionBar(Player player) {
        sendActionBar(player, text);
    }

    public Player getPlayer() {
        return player;
    }

    public String getText() {
        return text;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setText(String text) {
        this.text = text;
    }
}
