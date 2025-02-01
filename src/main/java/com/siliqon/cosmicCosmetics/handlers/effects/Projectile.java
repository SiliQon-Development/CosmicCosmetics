package com.siliqon.cosmicCosmetics.handlers.effects;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.data.ActiveEffectData;
import com.siliqon.cosmicCosmetics.data.EffectForm;
import com.siliqon.cosmicCosmetics.data.EffectType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static com.siliqon.cosmicCosmetics.utils.general.effects.effectDensity;
import static com.siliqon.cosmicCosmetics.utils.general.effects.effectParticles;

public class Projectile {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    public static void startForPlayer(Player player) {
        ActiveEffectData pdata = plugin.playerActiveEffects.get(player);
        if (pdata == null) return;
        if (pdata.getTaskIds().containsKey(EffectForm.PROJECTILE)) Bukkit.getScheduler().cancelTask(pdata.getTaskIds().get(EffectForm.PROJECTILE));

        EffectType effectType = pdata.getEffects().get(EffectForm.PROJECTILE);
        if (effectType == null) return;


        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            player.getWorld().getEntitiesByClass(org.bukkit.entity.Projectile.class).stream()
                    .filter(projectile -> projectile.getShooter() == player && !projectile.isDead() && projectile.isValid() && !projectile.isOnGround())
                    .forEach(projectile -> {
                        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                            if (otherPlayer != player && (plugin.cosmeticsEnabled.get(otherPlayer) == null || !plugin.cosmeticsEnabled.get(otherPlayer)))
                                continue;
                            otherPlayer.spawnParticle(
                                    effectParticles.get(effectType),
                                    projectile.getLocation(),
                                    effectDensity.get(effectType),
                                    0.1, 0.1, 0.1, 0.075, null, otherPlayer == player
                            );
                        }
                    });
        }, 0L, 3L);

        pdata.addTaskId(EffectForm.PROJECTILE, taskId);
    }
}
