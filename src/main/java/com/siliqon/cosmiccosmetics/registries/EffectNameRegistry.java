package com.siliqon.cosmiccosmetics.registries;

import com.siliqon.cosmiccosmetics.custom.Registry;
import com.siliqon.cosmiccosmetics.enums.EffectType;
import com.siliqon.cosmiccosmetics.files.LangFile;

public class EffectNameRegistry extends Registry<EffectType, String> {
    private static final EffectNameRegistry INSTANCE = new EffectNameRegistry();

    public void populate() {
        LangFile lang = plugin.getLang();
        this.register(EffectType.SPLASH, lang.getSplashEffectName());
        this.register(EffectType.ENDER, lang.getEnderEffectName());
        this.register(EffectType.FLAME, lang.getFlameEffectName());
        this.register(EffectType.CHARM, lang.getCharmEffectName());
        this.register(EffectType.LOVE, lang.getLoveEffectName());
        this.register(EffectType.BLOSSOM, lang.getBlossomEffectName());
        this.register(EffectType.ENCHANTED, lang.getEnchantedEffectName());
        this.register(EffectType.TEARS, lang.getTearsEffectName());
        this.register(EffectType.MUSICAL, lang.getMusicalEffectName());
        this.register(EffectType.RAINBOW, lang.getRainbowEffectName());
        this.register(EffectType.CLOUDY, lang.getCloudEffectName());
    }

    public static EffectNameRegistry getInstance() {
        return INSTANCE;
    }
}
