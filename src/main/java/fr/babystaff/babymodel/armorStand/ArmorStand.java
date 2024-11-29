package fr.babystaff.babymodel.armorStand;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class ArmorStand {

    private org.bukkit.entity.ArmorStand armorStand;

    public ArmorStand(Location location, World world) {
        this.armorStand = world.spawn(location, org.bukkit.entity.ArmorStand.class);
    }

    public void setPosition(Location location) {
        armorStand.teleport(location);
    }

    public void setName(String name) {
        armorStand.setCustomName(name);
        armorStand.setCustomNameVisible(true);
    }

    public void setNameVisible(boolean visible) {
        armorStand.setCustomNameVisible(visible);
    }

    public void setVisible(boolean visible) {
        armorStand.setVisible(visible);
    }

    public void setGravity(boolean gravity) {
        armorStand.setGravity(gravity);
    }

    public void setInteractable(boolean interactable) {
        armorStand.setMarker(!interactable);
    }

    public void setBodyPose(EulerAngle angle) {
        armorStand.setBodyPose(angle);
    }

    public void setLeftArmPose(EulerAngle angle) {
        armorStand.setLeftArmPose(angle);
    }

    public void setRightArmPose(EulerAngle angle) {
        armorStand.setRightArmPose(angle);
    }

    public void setLeftLegPose(EulerAngle angle) {
        armorStand.setLeftLegPose(angle);
    }

    public void setRightLegPose(EulerAngle angle) {
        armorStand.setRightLegPose(angle);
    }

    public void setHeadPose(EulerAngle angle) {
        armorStand.setHeadPose(angle);
    }

    public void setHelmet(ItemStack item) {
        armorStand.getEquipment().setHelmet(item);
    }

    public void setChestplate(ItemStack item) {
        armorStand.getEquipment().setChestplate(item);
    }

    public void setLeggings(ItemStack item) {
        armorStand.getEquipment().setLeggings(item);
    }

    public void setBoots(ItemStack item) {
        armorStand.getEquipment().setBoots(item);
    }

    public void setItemInHand(ItemStack item) {
        armorStand.getEquipment().setItemInHand(item);
    }

    public void remove() {
        armorStand.remove();
    }

    public org.bukkit.entity.ArmorStand getArmorStand() {
        return armorStand;
    }

}
