package com.siliqon.cosmicCosmetics.registries;

import com.siliqon.cosmicCosmetics.custom.RegistryTemplate;
import com.siliqon.cosmicCosmetics.enums.EffectType;
import org.bukkit.Particle;

import java.util.Map;

public class EffectParticleRegistry extends RegistryTemplate {
    private static final EffectParticleRegistry INSTANCE = new EffectParticleRegistry();

    public EffectParticleRegistry() {
        registryMap = Map.ofEntries(
                Map.entry(EffectType.SPLASH, Particle.SPLASH),
                Map.entry(EffectType.ENDER, Particle.DRAGON_BREATH),
                Map.entry(EffectType.FLAME, Particle.FLAME),
                Map.entry(EffectType.CHARM, Particle.HAPPY_VILLAGER),
                Map.entry(EffectType.LOVE, Particle.HEART),
                Map.entry(EffectType.BLOSSOM, Particle.CHERRY_LEAVES),
                Map.entry(EffectType.ENCHANTED, Particle.ENCHANT),
                Map.entry(EffectType.TEARS, Particle.FALLING_WATER),
                Map.entry(EffectType.MUSICAL, Particle.NOTE),
                Map.entry(EffectType.RAINBOW, Particle.DUST),
                Map.entry(EffectType.CLOUDY, Particle.EFFECT)
        );
    }

    public static EffectParticleRegistry getInstance() {
        return INSTANCE;
    }
}
