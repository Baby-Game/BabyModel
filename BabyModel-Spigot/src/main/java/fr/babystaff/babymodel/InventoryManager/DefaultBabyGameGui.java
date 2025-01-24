package fr.babystaff.babymodel.InventoryManager;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DefaultBabyGameGui {
    private String name;
    private Inventory inventory;


    public DefaultBabyGameGui(String name) {
        this.name = name;

        this.inventory = Bukkit.createInventory(null, 45, name);
        addBorder();
    }

    public void addBorder() {
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemStack.setItemMeta(itemMeta);

        getInventory().setItem(0, itemStack);
        getInventory().setItem(1, itemStack);
        getInventory().setItem(9, itemStack);

        getInventory().setItem(7, itemStack);
        getInventory().setItem(8, itemStack);
        getInventory().setItem(17, itemStack);

        getInventory().setItem(36, itemStack);
        getInventory().setItem(37, itemStack);
        getInventory().setItem(27, itemStack);

        getInventory().setItem(43, itemStack);
        getInventory().setItem(44, itemStack);
        getInventory().setItem(35, itemStack);
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
