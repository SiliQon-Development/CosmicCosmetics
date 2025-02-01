package com.siliqon.cosmicCosmetics.listeners;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.data.ActiveEffectData;
import com.siliqon.cosmicCosmetics.data.EffectForm;
import com.siliqon.cosmicCosmetics.handlers.effects.Projectile;
import com.siliqon.cosmicCosmetics.handlers.effects.Trail;
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

        if (pdata == null) {
            plugin.cosmeticsEnabled.put(player, true);
            return;
        };
        plugin.cosmeticsEnabled.put(player, pdata.getFirst());

        if (pdata.getSecond() == null) return;
        plugin.playerActiveEffects.put(player, pdata.getSecond());

        // resume any active effect tasks
        ActiveEffectData ped = plugin.playerActiveEffects.get(player);
        for (EffectForm form : ped.getEffects().keySet()) {
            if (ped.getTaskIds().containsKey(form)) continue;
            switch (form) {
                case PROJECTILE: {
                    Projectile.startForPlayer(player);
                }
                case TRAIL: {
                    Trail.startForPlayer(player);
                }
            }
        }
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
