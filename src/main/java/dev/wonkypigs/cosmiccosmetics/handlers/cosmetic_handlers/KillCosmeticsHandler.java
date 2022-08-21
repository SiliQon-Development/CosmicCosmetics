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
                victim.getWorld().spawnParticle(Particle.FALLING_LAVA, victim.getLocation(), 50, 0.5, 1, 0.5);
                victim.getWorld().playSound(victim.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1, 1);
            }
            else if(effect.equalsIgnoreCase("CELEBRATION")) {
                // Make list with colors
                Color[] colors = {Color.GREEN, Color.AQUA, Color.BLUE, Color.LIME, Color.MAROON, Color.ORANGE, Color.RED, Color.PURPLE};

                // Choose random color
                int rnd = new Random().nextInt(colors.length);
                Color color = colors[rnd];

                // Set firework meta
                Firework fw = (Firework) victim.getWorld().spawnEntity(victim.getLocation(), EntityType.FIREWORK);
                FireworkMeta fwm = fw.getFireworkMeta();

                // Ta-da!
                fwm.setPower(2);
                fwm.addEffect(FireworkEffect.builder().withColor(color).flicker(true).build());
                fw.setFireworkMeta(fwm);
            }
            else if(effect.equalsIgnoreCase("SPARKLE")) {
                victim.getWorld().spawnParticle(Particle.END_ROD, victim.getLocation(), 50, 0.5, 0.5, 0.5);
                victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);
            }
            else if(effect.equalsIgnoreCase("MUSICAL")) {
                victim.getWorld().spawnParticle(Particle.NOTE, victim.getLocation(), 25, 1, 1, 1);
                victim.getWorld().playSound(victim.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);
            }
            else if(effect.equalsIgnoreCase("VOID")) {
                victim.getWorld().spawnParticle(Particle.PORTAL, victim.getLocation(), 75, 0.5, 0.5, 0.5);
                victim.getWorld().playSound(victim.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 1, 1);
            }
        }
    }
}
