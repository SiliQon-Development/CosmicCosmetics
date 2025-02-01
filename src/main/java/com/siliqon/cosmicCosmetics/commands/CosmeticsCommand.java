package com.siliqon.cosmicCosmetics.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.guis.MainWindow;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.siliqon.cosmicCosmetics.utils.general.messaging.*;

@CommandAlias("cosmetics|cc")
public class CosmeticsCommand extends BaseCommand {
    private final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    @Default
    @CommandPermission("cosmetics.use")
    public void onCommand(Player player) {
        MainWindow mainWindow = new MainWindow();
        plugin.guiManager.openGUI(mainWindow, player);
    }

    @Subcommand("toggle")
    @CommandPermission("cosmetics.toggle")
    public void toggleCosmeticsCommand(Player player) {
        Boolean enabled = plugin.cosmeticsEnabled.get(player);
        if (enabled) {
            sendMessage(player, plugin.lang.getCosmeticsDisabledOther(), true);
            plugin.cosmeticsEnabled.put(player, false);
        } else {
            sendMessage(player, plugin.lang.getCosmeticsEnabledOther(), true);
            plugin.cosmeticsEnabled.put(player, true);
        }
    }

    @Subcommand("version")
    @CommandPermission("cosmetics.version")
    public void versionCommand(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bThe current plugin version is "+plugin.getName() + " &d"+plugin.getDescription().getVersion()));
    }
}
