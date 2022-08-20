package dev.wonkypigs.cosmiccosmetics.listeners;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class PlayerJoinListener implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PersistentDataContainer pdata = player.getPersistentDataContainer();
        if(!pdata.has(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING)) {
            pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "NONE");
        }
        if(!pdata.has(new NamespacedKey(plugin, "trail_effect"), PersistentDataType.STRING)){
            pdata.set(new NamespacedKey(plugin, "trail_effect"), PersistentDataType.STRING, "NONE");
        }
    }
}
