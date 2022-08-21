package dev.wonkypigs.cosmiccosmetics.handlers.cosmetic_handlers;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public class KillCosmeticsHandler implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    @EventHandler
    public void onDeath(final PlayerDeathEvent event) {
        final Player victim = event.getEntity();
        if (victim.getKiller() != null) {
            final Player killer = victim.getKiller();

            PersistentDataContainer kpdata = killer.getPersistentDataContainer();
            String effect = kpdata.get(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING);

            if(effect.equalsIgnoreCase("BLOOD")) {
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1.0f);

                victim.getWorld().spawnParticle(Particle.REDSTONE, victim.getLocation(), 50, 0.5, 1, 0.5, dustOptions);
                killer.playSound(killer.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 2, 1);
            }
            else if(effect.equalsIgnoreCase("CELEBRATION")) {

                Firework fw2 = victim.getWorld().spawn(victim.getLocation(), Firework.class, fw -> {
                    FireworkMeta fwm = fw.getFireworkMeta();

                    fwm.setPower(1);
                    fwm.addEffect(FireworkEffect.builder().withFade(Color.RED).withColor(Color.LIME, Color.BLUE).flicker(true).trail(true).with(FireworkEffect.Type.BURST).build());

                    fw.setFireworkMeta(fwm);
                });
                fw2.detonate();
            }
            else if(effect.equalsIgnoreCase("SPARKLE")) {
                victim.getWorld().spawnParticle(Particle.END_ROD, victim.getLocation(), 75, 0.5, 0.5, 0.5);
                killer.playSound(killer.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 2, 1);
            }
            else if(effect.equalsIgnoreCase("MUSICAL")) {
                victim.getWorld().spawnParticle(Particle.NOTE, victim.getLocation(), 20, 1, 1, 1);
                killer.playSound(killer.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2, 1);
            }
            else if(effect.equalsIgnoreCase("VOID")) {
                victim.getWorld().spawnParticle(Particle.PORTAL, victim.getLocation(), 100, 0, 0, 0);
                killer.playSound(killer.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 2, 1);
            }
            else if(effect.equalsIgnoreCase("LOVE")) {
                victim.getWorld().spawnParticle(Particle.HEART, victim.getLocation(), 20, 0.5, 0.5, 0.5);
                killer.playSound(killer.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 2, 1);
            }
            else if(effect.equalsIgnoreCase("CLOUD")) {
                victim.getWorld().spawnParticle(Particle.CLOUD, victim.getLocation(), 25, 0, 0, 0);
                killer.playSound(killer.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 2, 1);
            }
            else if(effect.equalsIgnoreCase("BLOSSOM")) {
                victim.getWorld().spawnParticle(Particle.SPORE_BLOSSOM_AIR, victim.getLocation(), 75, 1, 1, 1);
                killer.playSound(killer.getLocation(), Sound.BLOCK_SPORE_BLOSSOM_BREAK, 2, 1);
            }
            else if(effect.equalsIgnoreCase("DRAGON")) {
                victim.getWorld().spawnParticle(Particle.DRAGON_BREATH, victim.getLocation(), 75, 0.5, 0.5, 0.5);
                killer.playSound(killer.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1, 1);
            }
        }
    }
}
