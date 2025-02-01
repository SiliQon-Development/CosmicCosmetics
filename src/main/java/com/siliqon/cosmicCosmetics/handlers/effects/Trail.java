package com.siliqon.cosmicCosmetics.handlers.effects;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.data.ActiveEffectData;
import com.siliqon.cosmicCosmetics.data.EffectForm;
import com.siliqon.cosmicCosmetics.data.EffectType;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import static com.siliqon.cosmicCosmetics.utils.general.effects.effectDensity;
import static com.siliqon.cosmicCosmetics.utils.general.effects.effectParticles;

public class Trail {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    public static void startForPlayer(Player player) {
        ActiveEffectData pdata = plugin.playerActiveEffects.get(player);
        if (pdata == null) return;
        if (pdata.getTaskIds().containsKey(EffectForm.TRAIL)) Bukkit.getScheduler().cancelTask(pdata.getTaskIds().get(EffectForm.TRAIL));

        EffectType effectType = pdata.getEffects().get(EffectForm.TRAIL);
        if (effectType == null) return;

        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (player.isDead() || !player.isValid() || !player.isOnline() || !player.getWorld().getPlayers().contains(player)) // TODO check if player is still
                return;
            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                if (otherPlayer != player && (plugin.cosmeticsEnabled.get(otherPlayer) == null || !plugin.cosmeticsEnabled.get(otherPlayer)))
                    continue;

                if (effectType == EffectType.RAINBOW) {
                    otherPlayer.spawnParticle(
                            effectParticles.get(effectType),
                            player.getLocation().add(0, 0.1, 0),
                            effectDensity.get(effectType),
                            new Particle.DustOptions(Color.fromRGB((int) (Math.random() * 256),
                                    (int) (Math.random() * 256), (int) (Math.random() * 256)), 1f)
                    );
                    continue;
                }
                otherPlayer.spawnParticle(
                        effectParticles.get(effectType),
                        player.getLocation().add(0, 0.1, 0),
                        (int) effectDensity.get(effectType),
                        0.05, 0.05, 0.05, 0.01
                );
            }
        }, 0L, 2L);

        pdata.addTaskId(EffectForm.TRAIL, taskId);
    }
}
