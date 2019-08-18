package dev.nuer.enchantplus.apply;

import dev.nuer.enchantplus.enchants.CustomEnchantment;
import dev.nuer.enchantplus.enchants.CustomEnchantmentManager;
import dev.nuer.enchantplus.utils.nbtapi.NBTItem;
import dev.nuer.enchantplus.utils.nbtapi.NBTList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ApplyEnchantmentListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        //Verify that the cursor item is not null
        if (event.getCursor() == null
                || event.getCursor().getType().equals(Material.AIR)
                || !event.getCursor().hasItemMeta()
                || !event.getCursor().getItemMeta().hasLore()) return;
        //Verify that the clicked item is not null
        if (event.getCurrentItem() == null
            || event.getCurrentItem().getType().equals(Material.AIR)) return;
        //Get attributes of the book
        NBTItem book = new NBTItem(event.getCursor());
        if (!book.getBoolean("enchant+.book")) return;
        String enchantID = book.getString("enchant+.book.enchantment-id");
        int level = book.getInteger("enchant+.book.level");
        //Apply the enchantment to the item
        CustomEnchantment enchantment = CustomEnchantmentManager.getEnchantmentByID(enchantID);
        boolean canApply = false;
        for (ItemStack item : enchantment.getActiveItems()) {
            if (item.getType().equals(event.getCurrentItem().getType())) {
                canApply = true;
                break;
            }
        }
        if (!canApply) return;
        //Get nbtdata of the current item
        NBTItem item = new NBTItem(event.getCurrentItem());
        if (item.getBoolean("enchant+." + enchantID)) return;
        //If the enchant is going to be applied, cancel the click event so that items don't swap
        event.setCancelled(true);
        //Add the new line of lore
        NBTList lore = item.addCompound("display").getStringList("Lore");
        lore.add(enchantment.getLore().replace("{level}", String.valueOf(level)));
        item.setInteger("enchant+." + enchantID, level);
        //Update the players inventory
        event.getCursor().setType(Material.AIR);
        event.getWhoClicked().getInventory().remove(event.getCurrentItem());
        event.getWhoClicked().getInventory().setItem(event.getSlot(), item.getItem());
    }
}