package com.siliqon.cosmicCosmetics.registries;

import com.siliqon.cosmicCosmetics.custom.RegistryTemplate;
import com.siliqon.cosmicCosmetics.enums.EffectType;
import org.bukkit.Material;

import java.util.Map;

public class EffectMaterialRegistry extends RegistryTemplate {
    private static final EffectMaterialRegistry INSTANCE = new EffectMaterialRegistry();

    public EffectMaterialRegistry() {
        registryMap = Map.ofEntries(
                Map.entry(EffectType.SPLASH, Material.WATER_BUCKET),
                Map.entry(EffectType.ENDER, Material.DRAGON_EGG),
                Map.entry(EffectType.FLAME, Material.FLINT_AND_STEEL),
                Map.entry(EffectType.CHARM, Material.EMERALD),
                Map.entry(EffectType.LOVE, Material.RED_DYE),
                Map.entry(EffectType.BLOSSOM, Material.CHERRY_LEAVES),
                Map.entry(EffectType.ENCHANTED, Material.ENCHANTED_BOOK),
                Map.entry(EffectType.TEARS, Material.CRYING_OBSIDIAN),
                Map.entry(EffectType.MUSICAL, Material.NOTE_BLOCK),
                Map.entry(EffectType.RAINBOW, Material.MAGENTA_DYE),
                Map.entry(EffectType.CLOUDY, Material.SNOW_BLOCK)
        );
    }

    public static EffectMaterialRegistry getInstance() {
        return INSTANCE;
    }
}
