package com.siliqon.cosmiccosmetics.listeners;

import com.siliqon.cosmiccosmetics.CosmicCosmetics;
import com.siliqon.cosmiccosmetics.custom.ActiveEffectData;
import com.siliqon.cosmiccosmetics.enums.EffectForm;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import redempt.crunch.data.Pair;

import static com.siliqon.cosmiccosmetics.CosmicCosmetics.log;
import static com.siliqon.cosmiccosmetics.utils.Effects.getEffectsEnabled;
import static com.siliqon.cosmiccosmetics.utils.Effects.getPlayerActiveEffectData;

public class PlayerListener implements Listener {

    private final CosmicCosmetics plugin;
    public PlayerListener(CosmicCosmetics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Pair<Boolean, ActiveEffectData> pdata = plugin.getStorage().getPlayerData(player.getUniqueId());

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
        if (plugin.debugLevel >= 2) log("Stopped ongoing tasks for " + player.getName());

        // save data and let go of cache
        plugin.getStorage().savePlayerData(player.getUniqueId(), enabled, pdata);
        plugin.playerActiveEffects.remove(player.getUniqueId());
        plugin.cosmeticsEnabled.remove(player.getUniqueId());
    }
}
