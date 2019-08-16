package dev.nuer.enchantplus.enchants;

import dev.nuer.enchantplus.attributes.Attribute;
import dev.nuer.enchantplus.attributes.AttributeType;
import dev.nuer.enchantplus.utils.LogUtil;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CustomEnchantment {
    private String ID;
    private int maxLevel;
    private int startingLevel;
    private double cost;
    private String lore;
    private List<ItemStack> activeItems;
    private List<Attribute> attributes;
    private YamlConfiguration config;

    public CustomEnchantment(String ID, int maxLevel, int startingLevel, double cost, String lore, List<String> activeItems, YamlConfiguration config) {
        this.ID = ID;
        this.maxLevel = maxLevel;
        this.startingLevel = startingLevel;
        this.cost = cost;
        this.lore = lore;
        this.config = config;
        setActiveItems(activeItems);
        setAttributes();
    }

    public void setActiveItems(List<String> activeItems) {
        this.activeItems = new ArrayList<>();
        for (String item : activeItems) {
            String[] parts = item.split(":");
            this.activeItems.add(new ItemStack(Material.getMaterial(parts[0].toUpperCase()), 1, Short.parseShort(parts[1])));
        }
    }

    public void setAttributes() {
        attributes = new ArrayList<>();
        for (int i = 1; i <= config.getInt("attributes.total"); i++) {
            this.attributes.add(new Attribute(AttributeType.valueOf(config.getString("attributes." + i + ".effect-type").toUpperCase()),
                    config.getString("attributes." + i + ".potion-type"),
                    config.getIntegerList("attributes." + i + ".effect-per-level")));
        }
    }
}