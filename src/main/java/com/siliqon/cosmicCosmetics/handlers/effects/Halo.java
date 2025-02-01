package com.siliqon.cosmicCosmetics.handlers.effects;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.data.ActiveEffectData;
import com.siliqon.cosmicCosmetics.data.EffectForm;
import com.siliqon.cosmicCosmetics.data.EffectType;
import org.bukkit.Bukkit;

import java.lang.Math;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import static com.siliqon.cosmicCosmetics.utils.general.effects.effectParticles;

public class Halo {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    public static void startForPlayer(Player player) {
        ActiveEffectData pdata = plugin.playerActiveEffects.get(player);
        if (pdata == null) return;
        if (pdata.getTaskIds().containsKey(EffectForm.HALO)) Bukkit.getScheduler().cancelTask(pdata.getTaskIds().get(EffectForm.HALO));

        EffectType effectType = pdata.getEffects().get(EffectForm.HALO);
        if (effectType == null) return;

        double radius = .4;
        double fullCircle = 2 * Math.PI;
        int particles = 15;
        long[] tickCounter = {0};
        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            int i = (int) (tickCounter[0] % particles);
            double angle = i * fullCircle / particles + (tickCounter[0] * 0.05);
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);
            double y = 1.95;

            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                if (otherPlayer != player && (plugin.cosmeticsEnabled.get(otherPlayer) == null || !plugin.cosmeticsEnabled.get(otherPlayer)))
                    continue;

                if (effectType == EffectType.RAINBOW) {
                    otherPlayer.spawnParticle(
                            effectParticles.get(effectType),
                            player.getLocation().add(x, y, z),
                            1, 0, 0, 0, 0,
                            new Particle.DustOptions(Color.fromRGB((int) (Math.random() * 256),
                                    (int) (Math.random() * 256), (int) (Math.random() * 256)), 1f)
                    );
                    continue;
                }
                otherPlayer.spawnParticle(
                        effectParticles.get(effectType),
                        player.getLocation().add(x, y, z),
                        1,
                        0, 0, 0, 0
                );
            }

            tickCounter[0]++;
        }, 0L, 1L);

        pdata.addTaskId(EffectForm.HALO, taskId);
    }
}
