package dev.wonkypigs.cosmiccosmetics.handlers.cosmetic_handlers;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class TrailCosmeticsHandler implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    @EventHandler
    public void onPlayerMoves(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        PersistentDataContainer pdata = player.getPersistentDataContainer();
        String effect = pdata.get(new NamespacedKey(plugin, "trail_effect"), PersistentDataType.STRING);
        if(effect.equalsIgnoreCase("NONE")){
            return;
        }else{
            int amount = 10;
            if(effect.equalsIgnoreCase("HEART") || effect.equalsIgnoreCase("REVERSE_PORTAL") || effect.equalsIgnoreCase("CRIT")) {
                amount = 5;
            }
            player.getWorld().spawnParticle(Particle.valueOf(effect), player.getLocation(), amount, 0, 0, 0);
        }
    }
}
