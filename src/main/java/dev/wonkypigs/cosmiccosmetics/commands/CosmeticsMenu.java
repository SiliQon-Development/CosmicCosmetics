package dev.wonkypigs.cosmiccosmetics.commands;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CosmeticsMenu implements CommandExecutor {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(sender.hasPermission("ccosmetics.menu")){
                Player player = (Player) sender;

                // Set up the menu
                Inventory cosmeticsMenu = Bukkit.createInventory(player, 27, ChatColor.GOLD + "Cosmetics Menu");
                ItemStack testCosmetic = new ItemStack(Material.RED_DYE, 1);
                ItemStack testCosmetic2 = new ItemStack(Material.GREEN_DYE, 1);
                ItemStack testCosmetic3 = new ItemStack(Material.BLUE_DYE, 1);
                cosmeticsMenu.addItem(testCosmetic);
                cosmeticsMenu.addItem(testCosmetic2);
                cosmeticsMenu.addItem(testCosmetic3);

                // Open the menu for the player
                player.openInventory(cosmeticsMenu);
            }else{
                sender.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
            }
        }else{
            sender.sendMessage(plugin.prefix + plugin.getConfigValue("must-be-a-player"));
        }
        return true;
    }
}
