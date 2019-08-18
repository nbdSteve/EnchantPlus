package dev.nuer.enchantplus;

import dev.nuer.enchantplus.enable.FileManager;
import dev.nuer.enchantplus.enable.SetupManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class EnchantPlus extends JavaPlugin {
    private static EnchantPlus instance;
    private static Logger logger;

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();
        SetupManager.setupFiles(new FileManager(this));
        SetupManager.registerCommands(this);
        SetupManager.registerEvents(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static EnchantPlus getInstance() {
        return instance;
    }

    public static Logger getLog() {
        return logger;
    }
}
