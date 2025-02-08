package com.siliqon.cosmiccosmetics.registries;

import com.siliqon.cosmiccosmetics.custom.Registry;
import com.siliqon.cosmiccosmetics.enums.EffectType;

public class EffectDensityRegistry extends Registry<EffectType, Integer> {
    private static final EffectDensityRegistry INSTANCE = new EffectDensityRegistry();

    public void populate() {
        this.register(EffectType.SPLASH, 7);
        this.register(EffectType.ENDER, 7);
        this.register(EffectType.FLAME, 7);
        this.register(EffectType.CHARM, 7);
        this.register(EffectType.LOVE, 2);
        this.register(EffectType.BLOSSOM, 7);
        this.register(EffectType.ENCHANTED, 15);
        this.register(EffectType.TEARS, 7);
        this.register(EffectType.MUSICAL, 4);
        this.register(EffectType.RAINBOW, 7);
        this.register(EffectType.CLOUDY, 5);

    }

    public static EffectDensityRegistry getInstance() {
        return INSTANCE;
    }
}
