package fr.babystaff.babymodel.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.babystaff.babymodel.hologram.Hologram;
import fr.babystaff.babymodel.hologram.HologramManager;
import fr.babystaff.babymodel.npc.events.NPCCreateEvent;
import fr.babystaff.babymodel.npc.events.NPCDeleteEvent;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPC {
    private Location location;
    private String name;
    private String skinTexture;
    private String skinSignature;
    private EntityPlayer entityPlayer;
    private Hologram hologram;

    private HologramManager hologramManager;

    public NPC(Location location, String name, String skinTexture, String skinSignature, HologramManager hologramManager) {
        this.location = location;
        this.name = ChatColor.translateAlternateColorCodes('&', name); // Convertit le nom avec les couleurs
        this.skinTexture = skinTexture;
        this.skinSignature = skinSignature;
        this.hologramManager = hologramManager;
        spawn();
        NPCCreateEvent event = new NPCCreateEvent(this);
        Bukkit.getPluginManager().callEvent(event);
    }

    private void spawn() {
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), ""); // Nom vide ou invisible

        gameProfile.getProperties().put("textures", new Property("textures", skinTexture, skinSignature));

        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();

        entityPlayer = new EntityPlayer(server, worldServer, gameProfile, new PlayerInteractManager(worldServer));
        entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), entityPlayer.yaw, entityPlayer.pitch);

        entityPlayer.getDataWatcher().watch(10, (byte) 0x20);

        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            craftPlayer.getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer));
            craftPlayer.getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));
        }

        Location hologramLocation = location.clone().add(0, 2.2, 0);
        hologram = hologramManager.createHologram(hologramLocation, name);
    }

    public void delete() {
        if (hologram != null) {
            hologram.delete();
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            craftPlayer.getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer));
        }
        NPCDeleteEvent event = new NPCDeleteEvent(this);
        Bukkit.getPluginManager().callEvent(event);
        entityPlayer.getWorld().removeEntity(entityPlayer);
    }

    public void setName(String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        if (hologram != null) {
            hologram.delete();
            Location hologramLocation = location.clone().add(0, 2.2, 0);  // Ajustez la hauteur selon vos besoins
            hologram = hologramManager.createHologram(hologramLocation, this.name);
        }
    }

    public void setSkin(String skinTexture, String skinSignature) {
        this.skinTexture = skinTexture;
        this.skinSignature = skinSignature;
        delete();
        spawn();
    }

    public void setLocation(Location location) {
        this.location = location;
        entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), entityPlayer.yaw, entityPlayer.pitch);
        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            craftPlayer.getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));
        }
    }

    public org.bukkit.inventory.PlayerInventory getInventory() {
        return entityPlayer.getBukkitEntity().getInventory();
    }

    public void setMainHandItem(org.bukkit.inventory.ItemStack item) {
        org.bukkit.inventory.PlayerInventory inventory = getInventory();
        inventory.setItemInHand(item);
    }

    public void setArmor(org.bukkit.inventory.ItemStack helmet, org.bukkit.inventory.ItemStack chestplate, org.bukkit.inventory.ItemStack leggings, org.bukkit.inventory.ItemStack boots) {
        org.bukkit.inventory.PlayerInventory inventory = getInventory();
        inventory.setHelmet(helmet);
        inventory.setChestplate(chestplate);
        inventory.setLeggings(leggings);
        inventory.setBoots(boots);
    }

    public void setItemInSlot(int slot, org.bukkit.inventory.ItemStack item) {
        org.bukkit.inventory.PlayerInventory inventory = getInventory();
        inventory.setItem(slot, item);
    }

    public void setNMSItemInSlot(int slot, net.minecraft.server.v1_8_R3.ItemStack nmsItem) {
        org.bukkit.inventory.ItemStack bukkitItem = CraftItemStack.asBukkitCopy(nmsItem);
        setItemInSlot(slot, bukkitItem);
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public EntityPlayer getEntityPlayer() {
        return entityPlayer;
    }

    public Hologram getHologram() {
        return hologram;
    }

    public int getEntityId() {
        return entityPlayer.getId();
    }
}
