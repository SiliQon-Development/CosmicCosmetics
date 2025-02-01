package com.siliqon.cosmicCosmetics.utils.general;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.data.*;
import com.siliqon.cosmicCosmetics.handlers.effects.*;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.siliqon.cosmicCosmetics.utils.general.misc.toDisplayCase;

public class effects {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    private static final List<EffectType> projectileEffectTypes = List.of(EffectType.SPLASH, EffectType.ENDER, EffectType.FLAME, EffectType.CHARM, EffectType.LOVE, EffectType.BLOSSOM, EffectType.ENCHANTED, EffectType.TEARS, EffectType.MUSICAL);
    private static final List<EffectType> trailEffectTypes = List.of(EffectType.BLOSSOM, EffectType.RAINBOW, EffectType.CHARM, EffectType.LOVE, EffectType.MUSICAL, EffectType.CLOUDY, EffectType.ENDER);
    private static final List<EffectType> killEffectTypes = List.of(EffectType.ENDER, EffectType.FLAME, EffectType.CHARM, EffectType.LOVE, EffectType.BLOSSOM, EffectType.ENCHANTED, EffectType.TEARS, EffectType.RAINBOW, EffectType.CLOUDY);
    private static final List<EffectType> haloEffectTypes = List.of(EffectType.SPLASH, EffectType.ENDER, EffectType.FLAME, EffectType.CHARM, EffectType.LOVE, EffectType.BLOSSOM, EffectType.ENCHANTED, EffectType.TEARS, EffectType.RAINBOW);

    public static Map<EffectType, String> effectDisplayNames = Map.ofEntries(
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
    public static Map<EffectType, List<String>> effectDescriptions = Map.ofEntries(
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
    public static Map<EffectType, Material> effectDisplayMaterials = Map.ofEntries(
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
    public static Map<EffectType, Particle> effectParticles = Map.ofEntries(
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
    public static Map<EffectType, Integer> effectDensity = Map.ofEntries(
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

    public static String getEffectDisplayName(EffectType type) {
        if (type == null || !effectDisplayNames.containsKey(type)) return "NONE";
        return effectDisplayNames.get(type);
    }
    public static List<String> getEffectDescription(EffectType type) {
        if (type == null || !effectDescriptions.containsKey(type)) return new ArrayList<>();
        return effectDescriptions.get(type);
    }
    public static Material getEffectDisplayMaterial(EffectType type) {
        if (type == null || !effectDisplayMaterials.containsKey(type)) return Material.AIR;
        return effectDisplayMaterials.get(type);
    }

    public static EffectType getActiveEffect(Player player, EffectForm form) {
        ActiveEffectData playerData = plugin.playerActiveEffects.get(player);
        if (playerData == null) return null;

        return playerData.getEffects().get(form);
    }
    public static String getActiveEffectName(Player player, EffectForm form) {
        ActiveEffectData playerData = plugin.playerActiveEffects.get(player);
        if (playerData == null) return "NONE";

        EffectType type = playerData.getEffects().get(form);
        if (type == null) return "NONE";

        return toDisplayCase(type.toString());
    }
    public static Map<EffectForm, EffectType> getActiveEffects(Player player) {
        ActiveEffectData playerData = plugin.playerActiveEffects.get(player);
        if (playerData == null) return new HashMap<>();
        return playerData.getEffects();
    }

    public static void setActiveEffect(Player player, EffectForm form, EffectType type) {
        ActiveEffectData playerData = plugin.playerActiveEffects.get(player);
        if (playerData == null) playerData = new ActiveEffectData(player.getUniqueId(), new HashMap<>(), new HashMap<>());

        if (type == null) {
            playerData.removeEffect(form);
            return;
        }

        playerData.addEffect(form, type);
        if (!plugin.playerActiveEffects.containsKey(player)) plugin.playerActiveEffects.put(player, playerData);
        switch (form) {
            case EffectForm.PROJECTILE: {
                Projectile.startForPlayer(player);
            }
            case EffectForm.TRAIL: {
                Trail.startForPlayer(player);
            }
            case EffectForm.HALO: {
                Halo.startForPlayer(player);
            }
            // kill is event based
        }
    }

    public static void removeActiveEffect(Player player, EffectForm form) {
        ActiveEffectData playerData = plugin.playerActiveEffects.get(player);
        if (playerData == null) return;

        playerData.removeEffect(form);
    }
    public static void removeAllActiveEffects(Player player) {
        ActiveEffectData playerData = plugin.playerActiveEffects.get(player);
        if (playerData == null) return;

        Map<EffectForm, EffectType> current = new HashMap<>(playerData.getEffects());
        for (EffectForm form : current.keySet()) {
            playerData.removeEffect(form);
        }
    }

    public static List<EffectType> getFormEffects(EffectForm form) {
        if (form == EffectForm.PROJECTILE) {
            return projectileEffectTypes;
        } else if (form == EffectForm.TRAIL) {
            return trailEffectTypes;
        } else if (form == EffectForm.KILL) {
            return killEffectTypes;
        } else if (form == EffectForm.HALO) {
            return haloEffectTypes;
        }

        return new ArrayList<>();
    }
}
