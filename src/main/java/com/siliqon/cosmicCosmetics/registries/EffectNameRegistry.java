package com.siliqon.cosmicCosmetics.registries;

import com.siliqon.cosmicCosmetics.custom.RegistryTemplate;
import com.siliqon.cosmicCosmetics.enums.EffectType;

import java.util.Map;

public class EffectNameRegistry extends RegistryTemplate {
    private static final EffectNameRegistry INSTANCE = new EffectNameRegistry();

    public EffectNameRegistry() {
        registryMap = Map.ofEntries(
                Map.entry(EffectType.SPLASH, plugin.lang.getSplashEffectName()),
                Map.entry(EffectType.ENDER, plugin.lang.getEnderEffectName()),
                Map.entry(EffectType.FLAME, plugin.lang.getFlameEffectName()),
                Map.entry(EffectType.CHARM, plugin.lang.getCharmEffectName()),
                Map.entry(EffectType.LOVE, plugin.lang.getLoveEffectName()),
                Map.entry(EffectType.BLOSSOM, plugin.lang.getBlossomEffectName()),
                Map.entry(EffectType.ENCHANTED, plugin.lang.getEnchantedEffectName()),
                Map.entry(EffectType.TEARS, plugin.lang.getTearsEffectName()),
                Map.entry(EffectType.MUSICAL, plugin.lang.getMusicalEffectName()),
                Map.entry(EffectType.RAINBOW, plugin.lang.getRainbowEffectName()),
                Map.entry(EffectType.CLOUDY, plugin.lang.getCloudEffectName())
        );
    }

    public static EffectNameRegistry getInstance() {
        return INSTANCE;
    }
}
