package fr.babystaff.babymodel.hologram;

import fr.babystaff.babymodel.armorStand.ArmorStand;
import fr.babystaff.babymodel.hologram.events.HologramAddLineEvent;
import fr.babystaff.babymodel.hologram.events.HologramCreateEvent;
import fr.babystaff.babymodel.hologram.events.HologramDeleteEvent;
import fr.babystaff.babymodel.hologram.events.HologramRemoveLineEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class Hologram {
    private String id;
    private Location location;
    private List<ArmorStand> armorStands;

    public Hologram(Location location, String uuid) {
        this.id = uuid;// Générer un ID unique
        this.location = location;
        this.armorStands = new ArrayList<>();

        HologramCreateEvent event = new HologramCreateEvent(this);
        Bukkit.getPluginManager().callEvent(event);
    }

    // Retourne l'ID de l'hologramme
    public String getId() {
        return id;
    }


    // Ajouter une ligne de texte à l'hologramme
    public void addLine(String text) {
        // La hauteur de la nouvelle ligne est calculée en ajoutant l'index * 0.25 à la position Y
        Location lineLocation = location.clone().add(0, armorStands.size() * 0.25, 0);
        ArmorStand armorStand = spawnArmorStand(lineLocation, text);
        armorStands.add(armorStand);
        HologramAddLineEvent event = new HologramAddLineEvent(this, text);
        Bukkit.getPluginManager().callEvent(event);
    }

    // Retourne la location de l'hologramme
    public Location getLocation() {
        return location;
    }

    // Supprimer une ligne de l'hologramme
    public void removeLine(int index) {
        if (index < 0 || index >= armorStands.size()) {
            throw new IndexOutOfBoundsException("Invalid line index");
        }
        ArmorStand armorStand = armorStands.get(index);
        armorStand.remove();
        armorStands.remove(index);
        updateLines();
        HologramRemoveLineEvent event = new HologramRemoveLineEvent(this, index);
        Bukkit.getPluginManager().callEvent(event);
    }

    // Met à jour la position des lignes après suppression
    private void updateLines() {
        for (int i = 0; i < armorStands.size(); i++) {
            ArmorStand armorStand = armorStands.get(i);
            Location newLocation = location.clone().add(0, i * 0.25, 0);
            armorStand.setPosition(newLocation);
        }
    }

    // Supprimer l'hologramme
    public void delete() {
        for (ArmorStand armorStand : armorStands) {
            armorStand.remove();
        }
        armorStands.clear();
        HologramDeleteEvent event = new HologramDeleteEvent(this);
        Bukkit.getPluginManager().callEvent(event);
    }

    // Fonction utilitaire pour créer un ArmorStand
    private ArmorStand spawnArmorStand(Location location, String text) {
        World world = location.getWorld();
        ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.getArmorStand().setCustomName(text);
        armorStand.getArmorStand().setCustomNameVisible(true);
        armorStand.getArmorStand().setMarker(true);
        armorStand.getArmorStand().setBasePlate(false);
        armorStand.getArmorStand().setSmall(true);
        return armorStand;
    }

    public List<ArmorStand> getArmorStands() {
        return armorStands;
    }

    public ArmorStand getEntity(int index) {
        if (index < 0 || index >= armorStands.size()) {
            throw new IndexOutOfBoundsException("Invalid line index");
        }
        return armorStands.get(index);
    }

    public ArmorStand getFirstEntity() {
        if (armorStands.isEmpty()) {
            return null; // Ou lancez une exception selon vos besoins
        }
        return armorStands.get(0);
    }

    public ArmorStand getLastEntity() {
        if (armorStands.isEmpty()) {
            return null; // Ou lancez une exception selon vos besoins
        }
        return armorStands.get(armorStands.size() - 1);
    }
}
