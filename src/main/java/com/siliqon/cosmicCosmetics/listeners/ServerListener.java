package com.siliqon.cosmicCosmetics.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

import static com.siliqon.cosmicCosmetics.utils.general.messaging.logError;
import static com.siliqon.cosmicCosmetics.utils.general.messaging.sendMessage;

public class ServerListener implements Listener {

    @EventHandler
    public void onServerLoad(ServerLoadEvent e) {
        if (e.getType() == ServerLoadEvent.LoadType.RELOAD) {
            logError("Server reload detected! No support will be provided for issues that arise from server reloads.");
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isOp()) {
                sendMessage(player, "&cServer reload detected! No support will be provided for issues that arise from server reloads.", true);
            }
        }
    }
}
