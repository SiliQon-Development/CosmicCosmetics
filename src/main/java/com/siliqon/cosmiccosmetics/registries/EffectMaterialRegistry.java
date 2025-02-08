package com.siliqon.cosmiccosmetics.registries;

import com.siliqon.cosmiccosmetics.custom.Registry;
import com.siliqon.cosmiccosmetics.enums.EffectType;
import org.bukkit.Material;

public class EffectMaterialRegistry extends Registry<EffectType, Material> {
    private static final EffectMaterialRegistry INSTANCE = new EffectMaterialRegistry();

    public void populate() {
        this.register(EffectType.SPLASH, Material.WATER_BUCKET);
        this.register(EffectType.ENDER, Material.DRAGON_EGG);
        this.register(EffectType.FLAME, Material.FLINT_AND_STEEL);
        this.register(EffectType.CHARM, Material.EMERALD);
        this.register(EffectType.LOVE, Material.RED_DYE);
        this.register(EffectType.BLOSSOM, Material.CHERRY_LEAVES);
        this.register(EffectType.ENCHANTED, Material.ENCHANTED_BOOK);
        this.register(EffectType.TEARS, Material.CRYING_OBSIDIAN);
        this.register(EffectType.MUSICAL, Material.NOTE_BLOCK);
        this.register(EffectType.RAINBOW, Material.MAGENTA_DYE);
        this.register(EffectType.CLOUDY, Material.SNOW_BLOCK);
    }

    public static EffectMaterialRegistry getInstance() {
        return INSTANCE;
    }
}
