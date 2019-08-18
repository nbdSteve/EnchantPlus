package dev.nuer.enchantplus.enchants;

import dev.nuer.enchantplus.attributes.Attribute;
import dev.nuer.enchantplus.attributes.AttributeType;
import dev.nuer.enchantplus.utils.ColorUtil;
import dev.nuer.enchantplus.utils.ItemBuilderUtil;
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
    private ItemBuilderUtil itemBuilder;

    public CustomEnchantment(String ID, int maxLevel, int startingLevel, double cost, String lore, List<String> activeItems, YamlConfiguration config) {
        this.ID = ID;
        this.maxLevel = maxLevel;
        this.startingLevel = startingLevel;
        this.cost = cost;
        this.lore = lore;
        this.config = config;
        setActiveItems(activeItems);
        setAttributes();
        this.itemBuilder = createItem();
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

    public ItemBuilderUtil createItem() {
        ItemBuilderUtil ibu = new ItemBuilderUtil(config.getString("item.material"),
                config.getString("item.data-value"));
        ibu.addName(config.getString("item.name"));
        ibu.addLore(config.getStringList("item.lore"));
        ibu.addEnchantments(config.getStringList("item.enchantments"));
        ibu.addItemFlags(config.getStringList("item.item-flags"));
        return ibu;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getStartingLevel() {
        return startingLevel;
    }

    public void setStartingLevel(int startingLevel) {
        this.startingLevel = startingLevel;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getLore() {
        return ColorUtil.colorize(lore);
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public List<ItemStack> getActiveItems() {
        return activeItems;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void setConfig(YamlConfiguration config) {
        this.config = config;
    }

    public ItemBuilderUtil getItemBuilder() {
        return itemBuilder;
    }

    public void setItemBuilder(ItemBuilderUtil itemBuilder) {
        this.itemBuilder = itemBuilder;
    }
}