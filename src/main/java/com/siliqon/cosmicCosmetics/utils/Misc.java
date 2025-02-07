package com.siliqon.cosmicCosmetics.utils;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import org.bukkit.entity.Player;

public class Misc {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    public static Boolean checkPlayerPermission(Player player, String permission) {
        if (plugin.vaultEnabled) return plugin.vaultPerms.has(player, permission);
        return player.hasPermission(permission);
    }

    public static String toDisplayCase(String s) {

        final String ACTIONABLE_DELIMITERS = " '-/"; // these cause the character following
        // to be capitalized

        StringBuilder sb = new StringBuilder();
        boolean capNext = true;

        for (char c : s.toCharArray()) {
            c = (capNext)
                    ? Character.toUpperCase(c)
                    : Character.toLowerCase(c);
            sb.append(c);
            capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); // explicit cast not needed
        }
        return sb.toString();
    }
}
