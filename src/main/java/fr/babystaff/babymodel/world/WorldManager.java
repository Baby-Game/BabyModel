package fr.babystaff.babymodel.world;

import org.bukkit.WorldType;
import org.bukkit.generator.ChunkGenerator;

import java.util.HashMap;

public class WorldManager {
    private final HashMap<String, World> worldHashMap = new HashMap<>();

    public void createWorld(String worldName, WorldType worldType, ChunkGenerator generator) {
        if (!worldHashMap.containsKey(worldName)) {
            World world = new World(worldName, worldType, generator);
            worldHashMap.put(worldName, world);
        }
    }

    public HashMap<String, World> getWorldHashMap() {
        return worldHashMap;
    }

    public void removeWorld(World world) {
        if (worldHashMap.containsKey(world.getWorldName())) {
            worldHashMap.remove(world.getWorldName());
            world.deleteWorld(true, true);
        }
    }

    public World getWorld(String worldName) {
        return worldHashMap.get(worldName);
    }
}
