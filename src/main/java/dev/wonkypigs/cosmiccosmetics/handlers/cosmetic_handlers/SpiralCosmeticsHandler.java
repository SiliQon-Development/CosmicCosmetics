package dev.wonkypigs.cosmiccosmetics.handlers.cosmetic_handlers;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class SpiralCosmeticsHandler implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    public class WhateverSpiralRenderer implements Runnable {

        private int frame;
        private final UUID playerId;

        public WhateverSpiralRenderer(Player player){
            this.playerId = player.getUniqueId();
        }

        @Override
        public void run() {
            Player player = Bukkit.getPlayer(playerId);
            if(player == null) {
                return;
            }
            PersistentDataContainer pdata = player.getPersistentDataContainer();
            String effect = pdata.get(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING);
            if(!effect.equalsIgnoreCase("NONE")) {
                Location loc = player.getLocation();

                // Ah yes, maths.
                frame++;
                if (frame > 37) {
                    frame = 0;
                }
                frame %= 50;
                double y = frame * 1.8 / 37;
                int angle = frame * 12; // 12 deg offset per frame
                float radius = 0.9F;
                double radian = Math.toRadians(angle);
                double z = radius * Math.cos(radian);
                double x = radius * Math.sin(radian);

                // Down to up
                player.getWorld().spawnParticle(Particle.valueOf(effect),
                        (float) (loc.getX() - x), (float) (loc.getY() + y), (float) (loc.getZ() - z),
                        1, 0, 0, 0, 0.01);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Bukkit.getScheduler().runTaskTimer(plugin, new WhateverSpiralRenderer(player), 1, 1);

    }
}
