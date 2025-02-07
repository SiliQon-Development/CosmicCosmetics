package com.siliqon.cosmicCosmetics.registries;

import com.siliqon.cosmicCosmetics.custom.RegistryTemplate;
import com.siliqon.cosmicCosmetics.enums.EffectType;

import java.util.Map;

public class EffectDensityRegistry extends RegistryTemplate {
    private static final EffectDensityRegistry INSTANCE = new EffectDensityRegistry();

    public EffectDensityRegistry() {
        registryMap = Map.ofEntries(
                Map.entry(EffectType.SPLASH, 7),
                Map.entry(EffectType.ENDER, 7),
                Map.entry(EffectType.FLAME, 7),
                Map.entry(EffectType.CHARM, 7),
                Map.entry(EffectType.LOVE, 2),
                Map.entry(EffectType.BLOSSOM, 7),
                Map.entry(EffectType.ENCHANTED, 15),
                Map.entry(EffectType.TEARS, 7),
                Map.entry(EffectType.MUSICAL, 4),
                Map.entry(EffectType.RAINBOW, 7),
                Map.entry(EffectType.CLOUDY, 5)
        );
    }

    public static EffectDensityRegistry getInstance() {
        return INSTANCE;
    }
}
