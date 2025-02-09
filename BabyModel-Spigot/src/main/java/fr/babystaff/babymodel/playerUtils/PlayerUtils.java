package fr.babystaff.babymodel.playerUtils;

import fr.babystaff.babymodel.armorStand.ArmorStand;
import fr.babystaff.babymodel.hologram.Hologram;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerUtils {

    // Méthode pour obtenir le ping d'un joueur
    public int getPing(Player player) {
        return ((CraftPlayer) player).getHandle().ping;
    }

    // Méthode pour associer un hologramme avec un joueur (ajouter un ArmorStand en passager)
    public void partnerHologramWithPlayer(Player player, Hologram hologram) {
        // Vérifie que l'hologramme a des ArmorStands
        if (!hologram.getArmorStands().isEmpty()) {
            // Récupère le premier ArmorStand de l'hologramme
            ArmorStand firstArmorStand = hologram.getFirstEntity();
            if (firstArmorStand != null) {
                // Ajoute l'ArmorStand comme passager du joueur
                player.setPassenger(firstArmorStand.getArmorStand());
            }
        }
    }
}
