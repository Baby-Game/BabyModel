package fr.babystaff.babymodel.NametagNMS;

import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Collections;

public class NametagNMS {
    public void setNametag(Player player, String prefix, String suffix) {
        try {
            PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam();

            setField(packet, "a", player.getName()); // Team Name
            setField(packet, "b", player.getName()); // Display Name
            setField(packet, "c", prefix); // Prefix
            setField(packet, "d", suffix); // Suffix
            setField(packet, "e", "always"); // Name visibility
            setField(packet, "h", Collections.singletonList(player.getName())); // Players
            setField(packet, "i", 0); // Mode (0 = create, 1 = remove, 2 = update, etc.)

            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setField(Object packet, String fieldName, Object value) throws Exception {
        Field field = packet.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(packet, value);
    }
}
