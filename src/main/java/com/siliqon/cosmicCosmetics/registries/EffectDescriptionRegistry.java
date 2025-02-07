package com.siliqon.cosmicCosmetics.registries;

import com.siliqon.cosmicCosmetics.custom.RegistryTemplate;
import com.siliqon.cosmicCosmetics.enums.EffectType;

import java.util.Map;

public class EffectDescriptionRegistry extends RegistryTemplate {
    private static final EffectDescriptionRegistry INSTANCE = new EffectDescriptionRegistry();

    public EffectDescriptionRegistry() {
        registryMap = Map.ofEntries(
                Map.entry(EffectType.SPLASH, plugin.lang.getSplashEffectDescription()),
                Map.entry(EffectType.ENDER, plugin.lang.getEnderEffectDescription()),
                Map.entry(EffectType.FLAME, plugin.lang.getFlameEffectDescription()),
                Map.entry(EffectType.CHARM, plugin.lang.getCharmEffectDescription()),
                Map.entry(EffectType.LOVE, plugin.lang.getLoveEffectDescription()),
                Map.entry(EffectType.BLOSSOM, plugin.lang.getBlossomEffectDescription()),
                Map.entry(EffectType.ENCHANTED, plugin.lang.getEnchantedEffectDescription()),
                Map.entry(EffectType.TEARS, plugin.lang.getTearsEffectDescription()),
                Map.entry(EffectType.MUSICAL, plugin.lang.getMusicalEffectDescription()),
                Map.entry(EffectType.RAINBOW, plugin.lang.getRainbowEffectDescription()),
                Map.entry(EffectType.CLOUDY, plugin.lang.getCloudEffectDescription())
        );
    }

    public static EffectDescriptionRegistry getInstance() {
        return INSTANCE;
    }
}
