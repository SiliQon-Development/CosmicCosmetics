package com.siliqon.cosmicCosmetics.listeners;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.data.ActiveEffectData;
import com.siliqon.cosmicCosmetics.utils.general.storage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import redempt.crunch.data.Pair;

public class PlayerListener implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Pair<Boolean, ActiveEffectData> pdata = storage.getPlayerData(player.getUniqueId());

        plugin.setupPlayerData(player, pdata);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        ActiveEffectData pdata = plugin.playerActiveEffects.get(player);
        Boolean enabled = plugin.cosmeticsEnabled.get(player);
        if (enabled == null) enabled = true;

        // stop any particle tasks for player
        if (pdata != null) for (Integer taskId : pdata.getTaskIds().values()) {
            plugin.getServer().getScheduler().cancelTask(taskId);
        }

        // save data and let go of cache
        storage.savePlayerData(player.getUniqueId(), enabled, pdata);
        plugin.playerActiveEffects.remove(player);
        plugin.cosmeticsEnabled.remove(player);
    }
}
