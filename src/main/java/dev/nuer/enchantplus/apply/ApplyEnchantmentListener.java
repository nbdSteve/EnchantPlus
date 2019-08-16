package dev.nuer.enchantplus.apply;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ApplyEnchantmentListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (event.getCursor() == null
                || event.getCursor().getType().equals(Material.AIR)
                || !event.getCursor().hasItemMeta()
                || !event.getCursor().getItemMeta().hasLore()) return;
    }
}
