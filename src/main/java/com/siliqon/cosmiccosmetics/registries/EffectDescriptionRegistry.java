package com.siliqon.cosmiccosmetics.registries;

import com.siliqon.cosmiccosmetics.custom.Registry;
import com.siliqon.cosmiccosmetics.enums.EffectType;
import com.siliqon.cosmiccosmetics.files.LangFile;

import java.util.List;

public class EffectDescriptionRegistry extends Registry<EffectType, List<String>> {
    private static final EffectDescriptionRegistry INSTANCE = new EffectDescriptionRegistry();

    public void populate() {
        LangFile lang = plugin.getLang();
        this.register(EffectType.SPLASH, lang.getSplashEffectDescription());
        this.register(EffectType.ENDER, lang.getEnderEffectDescription());
        this.register(EffectType.FLAME, lang.getFlameEffectDescription());
        this.register(EffectType.CHARM, lang.getCharmEffectDescription());
        this.register(EffectType.LOVE, lang.getLoveEffectDescription());
        this.register(EffectType.BLOSSOM, lang.getBlossomEffectDescription());
        this.register(EffectType.ENCHANTED, lang.getEnchantedEffectDescription());
        this.register(EffectType.TEARS, lang.getTearsEffectDescription());
        this.register(EffectType.MUSICAL, lang.getMusicalEffectDescription());
        this.register(EffectType.RAINBOW, lang.getRainbowEffectDescription());
        this.register(EffectType.CLOUDY, lang.getCloudEffectDescription());
    }

    public static EffectDescriptionRegistry getInstance() {
        return INSTANCE;
    }
}
