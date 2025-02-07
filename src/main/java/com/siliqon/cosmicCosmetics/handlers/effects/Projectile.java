package com.siliqon.cosmicCosmetics.handlers.effects;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.custom.ActiveEffectData;
import com.siliqon.cosmicCosmetics.enums.EffectForm;
import com.siliqon.cosmicCosmetics.enums.EffectType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import static com.siliqon.cosmicCosmetics.utils.Effects.*;

public class Projectile {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    public static void startForPlayer(Player player) {
        EffectType effectType = getActiveEffect(player, EffectForm.PROJECTILE);
        if (effectType == null) return;

        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            player.getWorld().getEntitiesByClass(org.bukkit.entity.Projectile.class).stream()
                    .filter(projectile -> projectile.getShooter() == player && !projectile.isDead() && projectile.isValid() && !projectile.isOnGround())
                    .forEach(projectile -> {
                        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                            if (otherPlayer != player && !getEffectsEnabled(otherPlayer))
                                continue;

                            Particle particle = getEffectParticle(effectType);
                            Location loc = projectile.getLocation();
                            int density = getEffectDensity(effectType);
                            double xOffset = .1, yOffset = .1, zOffset = .1, speed = .075;

                            otherPlayer.spawnParticle(
                                    particle, loc, density,
                                    xOffset, yOffset, zOffset, speed,
                                    null, otherPlayer == player
                            );
                        }
                    });
        }, 0L, 3L);

        ActiveEffectData pdata = getPlayerActiveEffectData(player);
        pdata.addTaskId(EffectForm.PROJECTILE, taskId);
    }
}
