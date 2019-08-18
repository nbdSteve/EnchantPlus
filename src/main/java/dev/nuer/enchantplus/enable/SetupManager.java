package dev.nuer.enchantplus.enable;

import dev.nuer.enchantplus.EnchantPlus;
import dev.nuer.enchantplus.apply.ApplyEnchantmentListener;
import dev.nuer.enchantplus.cmd.CeCmd;
import dev.nuer.enchantplus.enchants.CustomEnchantmentManager;
import dev.nuer.enchantplus.utils.armorequipevent.ArmorListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Class that handles setting up the plugin on start
 */
public class SetupManager {

    /**
     * Loads the files into the file manager
     *
     * @param fileManager FileManager, the plugins file manager
     */
    public static void setupFiles(FileManager fileManager) {
        //General files
        fileManager.add("config", "enchant+.yml");
        fileManager.add("messages", "messages.yml");
        //Load enchantments
        CustomEnchantmentManager.loadEnchantments(fileManager);
    }

    public static void registerCommands(EnchantPlus instance) {
        instance.getCommand("custom-enchantment").setExecutor(new CeCmd());
//        instance.getCommand("tiers").setExecutor(new TiersCmd());
    }

    /**
     * Register all of the events for the plugin
     *
     * @param instance Plugin, the main plugin instance
     */
    public static void registerEvents(Plugin instance) {
        PluginManager pm = instance.getServer().getPluginManager();
        pm.registerEvents(new ArmorListener(FileManager.get("config").getStringList("blocked")), instance);
        pm.registerEvents(new ApplyEnchantmentListener(), instance);
//        pm.registerEvents(new BrewChallengeListener(), instance);
    }
}
