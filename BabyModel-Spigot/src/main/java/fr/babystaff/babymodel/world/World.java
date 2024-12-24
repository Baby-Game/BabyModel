package fr.babystaff.babymodel.world;

import fr.babystaff.babymodel.hologram.events.HologramCreateEvent;
import fr.babystaff.babymodel.world.events.WorldCreateEvent;
import fr.babystaff.babymodel.world.events.WorldDeleteEvent;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;

public class World {
    private String worldName;
    private WorldType worldType;
    private ChunkGenerator generator;

    public World(String worldName, WorldType worldType, ChunkGenerator generator) {
        this.worldName = worldName;
        this.worldType = worldType;
        this.generator = generator;

        createWorld(worldName, worldType, generator);

    }

    public String getWorldName() {
        return worldName;
    }

    public WorldType getWorldType() {
        return worldType;
    }

    public ChunkGenerator getGenerator() {
        return generator;
    }

    public org.bukkit.World getWorld() {
        return Bukkit.getWorld(worldName);
    }

    public World createWorld(String worldName, WorldType worldType, ChunkGenerator generator) {
        try {
            if (Bukkit.getWorld(worldName) != null) {
                System.out.println("Le monde " + worldName + " existe déjà.");
                return null;
            }

            WorldCreator creator = new WorldCreator(worldName)
                    .type(worldType) // Type du monde (NORMAL, NETHER, END)
                    .generator(generator);

            World world = (World) Bukkit.createWorld(creator);

            System.out.println("Le monde " + worldName + " a été créé avec succès.");
            WorldCreateEvent event = new WorldCreateEvent(Bukkit.getWorld(worldName));
            Bukkit.getPluginManager().callEvent(event);
            return world;
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du monde : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteWorld(boolean unloadWorld, boolean deleteWorldFile) {
        try {
            World world = (World) Bukkit.getWorld(worldName);
            if (world == null) {
                System.out.println("Le monde " + worldName + " n'existe pas.");
                return false;
            }

            // Décharge le monde si nécessaire
            if (unloadWorld) {
                Bukkit.unloadWorld((org.bukkit.World) world, false);
                System.out.println("Le monde " + worldName + " a été déchargé.");
            }

            // Supprime le dossier du monde si nécessaire
            if (deleteWorldFile) {
                File worldFolder = new File(Bukkit.getWorldContainer(), worldName);
                if (worldFolder.exists()) {
                    deleteFolder(worldFolder);
                    System.out.println("Le dossier du monde " + worldName + " a été supprimé.");
                    WorldDeleteEvent event = new WorldDeleteEvent(Bukkit.getWorld(worldName));
                    Bukkit.getPluginManager().callEvent(event);
                }
            }

            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression du monde : " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private void deleteFolder(File folder) {
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                deleteFolder(file);
            }
        }
        folder.delete();
    }
}
