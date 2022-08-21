package dev.wonkypigs.cosmiccosmetics.handlers.cosmetic_handlers;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;


public class BowCosmeticsHandler implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    private void EffectLoop(final Arrow arrow, final String effect, final Integer amount) {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Show effect at arrow till
                if(arrow.isOnGround()){
                    this.cancel();
                }else if(arrow.isValid()) {
                    arrow.getWorld().spawnParticle(Particle.valueOf(effect), arrow.getLocation(), amount, 0.05, 0.05, 0.05);
                }
            }
        }.runTaskTimer(plugin, 1, 1);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        if(e.getEntity().getShooter() instanceof Player) {
            if(e.getEntityType() == EntityType.ARROW) {
                Player player = (Player) e.getEntity().getShooter();
                PersistentDataContainer pdata = player.getPersistentDataContainer();
                String effect = pdata.get(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING);
                if(effect.equalsIgnoreCase("NONE")){
                    return;
                }else{
                    int amt = 10;
                    if(effect.equalsIgnoreCase("HEART") || effect.equalsIgnoreCase("REVERSE_PORTAL") || effect.equalsIgnoreCase("CRIT")) {
                        amt = 5;
                    }
                    if(effect.equalsIgnoreCase("ASH")) {
                        amt = 20;
                    }
                    EffectLoop((Arrow) e.getEntity(), effect, amt);
                }
            }
        }
    }
}
