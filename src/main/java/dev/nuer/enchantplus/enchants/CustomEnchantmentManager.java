package dev.nuer.enchantplus.enchants;

import dev.nuer.enchantplus.enable.FileManager;
import dev.nuer.enchantplus.utils.LogUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CustomEnchantmentManager {
    private static Map<String, CustomEnchantment> loadedEnchantments;

    public static void loadEnchantments(FileManager fileManager) {
        loadedEnchantments = new HashMap<>();
        for (String enchantment : FileManager.get("config").getStringList("enchantment-list")) {
            String[] parts = enchantment.split(":");
            fileManager.add(parts[0], "enchantments" + File.separator + parts[1]);
            YamlConfiguration config = FileManager.get(parts[0]);
            try {
                loadedEnchantments.put(parts[0], new CustomEnchantment(parts[0],
                        config.getInt("max-level"),
                        config.getInt("starting-level"),
                        config.getDouble("cost"),
                        config.getString("enchantment-lore"),
                        config.getStringList("active-items"),
                        config));
                LogUtil.info("Successfully loaded enchantment: " + parts[0]);
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.warning("Error loading enchantment: " + parts[0] + ", please check " + parts[1] + " for any formatting errors.");
            }
        }
    }

    public static CustomEnchantment getEnchantmentByID(String enchantID) {
        return loadedEnchantments.get(enchantID);
    }

    public static void giveItem(String enchantID, Player player, int level) {
        CustomEnchantment enchantment = loadedEnchantments.get(enchantID);
        enchantment.getItemBuilder().addName(enchantment.getConfig().getString("item.name"), "{level}", String.valueOf(level));
        enchantment.getItemBuilder().addBookNBT(enchantID, level);
        enchantment.getItemBuilder().give(player);
    }
}