package dev.nuer.enchantplus.utils;

import dev.nuer.enchantplus.EnchantPlus;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public class CustomEventUtil {

    public static void fire(Event event) {
        Bukkit.getPluginManager().callEvent(event);
    }

    public static void fireSync(Event event) {
        Bukkit.getScheduler().runTask(EnchantPlus.getInstance(), () -> {
            Bukkit.getPluginManager().callEvent(event);
        });
    }
}