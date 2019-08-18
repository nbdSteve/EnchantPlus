package dev.nuer.enchantplus.apply;

import dev.nuer.enchantplus.attributes.Attribute;
import dev.nuer.enchantplus.enchants.CustomEnchantment;
import dev.nuer.enchantplus.enchants.CustomEnchantmentManager;
import dev.nuer.enchantplus.utils.LogUtil;
import dev.nuer.enchantplus.utils.armorequipevent.ArmorEquipEvent;
import dev.nuer.enchantplus.utils.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.time.Period;
import java.util.HashMap;
import java.util.Map;

public class ApplyEffectsListener implements Listener {

    @EventHandler
    public void applyEffects(ArmorEquipEvent event) {
        if (event.isCancelled()) return;
        if (event.getNewArmorPiece() == null
                || event.getNewArmorPiece().getType().equals(Material.AIR)
                || !event.getNewArmorPiece().hasItemMeta()
                || !event.getNewArmorPiece().getItemMeta().hasLore()) return;
        NBTItem item = new NBTItem(event.getNewArmorPiece());
        if (item.getObject("enchant+.custom-enchantments", HashMap.class) == null) return;
        HashMap enchantments = item.getObject("enchant+.custom-enchantments", HashMap.class);
        for (Object enchantID : enchantments.keySet()) {
            CustomEnchantment enchantment = CustomEnchantmentManager.getEnchantmentByID((String) enchantID);
            for (Attribute effect : enchantment.getAttributes()) {
                double d = (double) enchantments.get(enchantID);
                LogUtil.info(""+(int)d);
                effect.applyPotions(event.getPlayer(), (int) d);
            }
        }
    }
}
