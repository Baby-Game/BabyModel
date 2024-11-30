package fr.babystaff.babymodel.hologram;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.*;

public class HologramManager {
    private Map<String, Hologram> holograms = new HashMap<>();

    public Map<String, Hologram> getHolograms() {
        return holograms;
    }

    // Méthode pour créer un hologramme
    public Hologram createHologram(Location location, String uuid) {
        Hologram hologram = new Hologram(location, uuid);
        holograms.put(hologram.getId(), hologram);
        return hologram;
    }

    // Méthode pour obtenir un hologramme par ID
    public Hologram getHologram(String id) {
        return holograms.get(id);
    }

    // Méthode pour supprimer un hologramme par ID
    public void deleteHologram(UUID id) {
        Hologram hologram = holograms.remove(id);
        if (hologram != null) {
            hologram.delete();
        }
    }

    // Méthode pour obtenir tous les IDs des hologrammes
    public Set<String> getAllHologramIds() {
        return new HashSet<>(holograms.keySet());
    }
    // Méthode pour obtenir tous les hologrammes
    public Collection<Hologram> getAllHolograms() {
        return holograms.values();
    }
    // Méthode pour supprimer tous les hologrammes
    public void deleteAllHolograms() {
        for (Hologram hologram : holograms.values()) {
            hologram.delete();
        }
        holograms.clear(); // Efface le map après avoir supprimé les hologrammes
    }

    // Méthode pour mettre à jour le nom d'un hologramme pour un joueur
    public void updateHologramNameForPlayer(ArmorStand armorStand, Player player, String name) {
        // Implémentez cette méthode selon vos besoins
    }
}
