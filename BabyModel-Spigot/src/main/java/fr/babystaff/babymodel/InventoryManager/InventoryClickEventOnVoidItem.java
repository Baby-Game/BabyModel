package fr.babystaff.babymodel.InventoryManager;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickEventOnVoidItem implements Listener {

    @EventHandler
    public void interact(InventoryClickEvent event) {
        ItemStack currentItem = event.getCurrentItem();

        if (currentItem == null || !currentItem.hasItemMeta()) {
            return;
        }

        if (currentItem.getType() != Material.STAINED_GLASS_PANE) {
            return;
        }

        if (" ".equals(currentItem.getItemMeta().getDisplayName())) {
            event.setCancelled(true);
        }
    }
}
