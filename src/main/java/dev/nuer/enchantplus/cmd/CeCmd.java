package dev.nuer.enchantplus.cmd;

import dev.nuer.enchantplus.enchants.CustomEnchantmentManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CeCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //ce give player gear 1
        CustomEnchantmentManager.giveItem(args[2], Bukkit.getPlayer(args[1]), Integer.parseInt(args[3]));
        return true;
    }
}
