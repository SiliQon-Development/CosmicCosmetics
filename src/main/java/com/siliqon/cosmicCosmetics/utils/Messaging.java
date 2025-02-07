package com.siliqon.cosmicCosmetics.utils;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messaging {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    public static void log(String message) {
        plugin.getLogger().info(message);
    }
    public static void logError(String message) {
        plugin.getLogger().severe(message);
    }

    public static void sendMessage(Player player, String message, Boolean prefixed) {
        if (prefixed) player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.PREFIX + message));
        else player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
