package dev.nuer.enchantplus.attributes;

import dev.nuer.enchantplus.utils.LogUtil;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Attribute {
    AttributeType type;
    List<PotionEffect> potions;

    public Attribute(AttributeType type, String effect, List<Integer> effectPerLevel) {
        this.type = type;
        potions = new ArrayList<>();
        switch (this.type) {
            case POTION:
                for (int i = 0; i < effectPerLevel.size(); i++) {
                    potions.add(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), 999999, effectPerLevel.get(i) - 1));
                }
                break;
        }
    }

    public void applyPotions(Player player, int level) {
        if (player.hasPotionEffect(potions.get(level - 1).getType())) {
            player.removePotionEffect(potions.get(level - 1).getType());
        }
        player.addPotionEffect(potions.get(level - 1));
    }
}