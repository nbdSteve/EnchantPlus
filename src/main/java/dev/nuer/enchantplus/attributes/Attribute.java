package dev.nuer.enchantplus.attributes;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Attribute {
    AttributeType type;
    List<PotionEffect> potions;

    public Attribute(AttributeType type, String effect, List<Integer> effectPerLevel) {
        this.type = type;
        switch (this.type) {
            case POTION:
                for (int i = 0; i < effectPerLevel.size(); i++) {
                    potions.add(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), 999999, effectPerLevel.get(i) - 1));
                }
                break;
        }
    }
}