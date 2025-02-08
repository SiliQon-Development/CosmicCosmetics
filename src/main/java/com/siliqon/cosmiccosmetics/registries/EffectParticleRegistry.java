package com.siliqon.cosmiccosmetics.registries;

import com.siliqon.cosmiccosmetics.custom.Registry;
import com.siliqon.cosmiccosmetics.enums.EffectType;
import org.bukkit.Particle;

public class EffectParticleRegistry extends Registry<EffectType, Particle> {
    private static final EffectParticleRegistry INSTANCE = new EffectParticleRegistry();

    public void populate() {
        this.register(EffectType.SPLASH, Particle.SPLASH);
        this.register(EffectType.ENDER, Particle.DRAGON_BREATH);
        this.register(EffectType.FLAME, Particle.FLAME);
        this.register(EffectType.CHARM, Particle.HAPPY_VILLAGER);
        this.register(EffectType.LOVE, Particle.HEART);
        this.register(EffectType.BLOSSOM, Particle.CHERRY_LEAVES);
        this.register(EffectType.ENCHANTED, Particle.ENCHANT);
        this.register(EffectType.TEARS, Particle.FALLING_WATER);
        this.register(EffectType.MUSICAL, Particle.NOTE);
        this.register(EffectType.RAINBOW, Particle.DUST);
        this.register(EffectType.CLOUDY, Particle.EFFECT);
    }

    public static EffectParticleRegistry getInstance() {
        return INSTANCE;
    }
}
