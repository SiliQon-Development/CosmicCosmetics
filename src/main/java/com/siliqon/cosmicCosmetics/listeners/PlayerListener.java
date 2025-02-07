package com.siliqon.cosmicCosmetics.listeners;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.custom.ActiveEffectData;
import com.siliqon.cosmicCosmetics.enums.EffectForm;
import com.siliqon.cosmicCosmetics.utils.Storage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import redempt.crunch.data.Pair;

import static com.siliqon.cosmicCosmetics.utils.Effects.getEffectsEnabled;
import static com.siliqon.cosmicCosmetics.utils.Effects.getPlayerActiveEffectData;

public class PlayerListener implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Pair<Boolean, ActiveEffectData> pdata = Storage.getPlayerData(player.getUniqueId());

        plugin.setupPlayerData(player, pdata);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        ActiveEffectData pdata = getPlayerActiveEffectData(player);
        boolean enabled = getEffectsEnabled(player);

        // stop any particle tasks for player
        if (pdata != null) {
            for (EffectForm form : pdata.getTaskIds().keySet()) {
                for (int taskId : pdata.getTaskIds().get(form)) {
                    plugin.getServer().getScheduler().cancelTask(taskId);
                }
            }
        }

        // save data and let go of cache
        Storage.savePlayerData(player.getUniqueId(), enabled, pdata);
        plugin.playerActiveEffects.remove(player.getUniqueId());
        plugin.cosmeticsEnabled.remove(player.getUniqueId());
    }
}
