package dev.nuer.enchantplus.utils;

import dev.nuer.enchantplus.EnchantPlus;

public class LogUtil {

    public static void info(String message) {
        EnchantPlus.getLog().info(message);
    }

    public static void warning(String message) {
        EnchantPlus.getLog().warning(message);
    }
}
