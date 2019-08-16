package dev.nuer.enchantplus.utils;

import dev.nuer.enchantplus.EnchantPlus;
import dev.nuer.enchantplus.enable.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandUtil {

    public static void execute(String directory, String path, Player player) {
        for (String command : FileManager.get(directory).getStringList(path)) {
            Bukkit.getScheduler().runTask(EnchantPlus.getInstance(), () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName()));
            });
        }
    }
}