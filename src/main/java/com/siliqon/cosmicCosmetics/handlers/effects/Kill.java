package com.siliqon.cosmicCosmetics.handlers.effects;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.data.ActiveEffectData;
import com.siliqon.cosmicCosmetics.data.EffectForm;
import com.siliqon.cosmicCosmetics.data.EffectType;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static com.siliqon.cosmicCosmetics.utils.general.effects.effectDensity;
import static com.siliqon.cosmicCosmetics.utils.general.effects.effectParticles;

public class Kill implements Listener {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player killer = player.getKiller();
        if (killer == null) return;

        ActiveEffectData pdata = plugin.playerActiveEffects.get(killer);
        if (pdata == null) return;

        EffectType effectType = pdata.getEffects().get(EffectForm.KILL);
        if (effectType == null) return;

        Particle particle = effectParticles.get(effectType);
        int density = effectDensity.get(effectType);

        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
            if (otherPlayer != player && (plugin.cosmeticsEnabled.get(otherPlayer) == null || !plugin.cosmeticsEnabled.get(otherPlayer)))
                continue;
            if (effectType == EffectType.RAINBOW) {
                otherPlayer.spawnParticle(particle, player.getLocation().add(0,1,0), density * 5, 0.3, 0.5, 0.3, 0.05, new Particle.DustOptions(Color.fromRGB((int) (Math.random() * 256),
                        (int) (Math.random() * 256), (int) (Math.random() * 256)), 1f));
                continue;
            }
            otherPlayer.spawnParticle(particle, player.getLocation().add(0,1,0), density * 10, 0.4, 0.75, 0.4, 0.1);
        }
    }
}
