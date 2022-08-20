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
import org.bukkit.inventory.meta.ItemMeta;

public class CosmeticsMenu implements CommandExecutor {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(sender.hasPermission("ccosmetics.menu")){
                Player player = (Player) sender;
                openMainCosmeticsGUI(player);
            }else{
                sender.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
            }
        }else{
            sender.sendMessage(plugin.prefix + plugin.getConfigValue("must-be-a-player"));
        }
        return true;
    }

    public static void openMainCosmeticsGUI(Player player) {
        // Set up the menu
        Inventory cosmeticsMenu = Bukkit.createInventory(player, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "Cosmetics Menu");

        // Bow Effects Section
        ItemStack bowCosmetics = new ItemStack(Material.BOW, 1);
        ItemMeta bowCosmeticsMeta = bowCosmetics.getItemMeta();
        bowCosmeticsMeta.setDisplayName(ChatColor.GREEN + "Bow Effects");
        bowCosmetics.setItemMeta(bowCosmeticsMeta);

        ItemStack trailCosmetics = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta trailCosmeticsMeta = trailCosmetics.getItemMeta();
        trailCosmeticsMeta.setDisplayName(ChatColor.GREEN + "Trail Effects");
        trailCosmetics.setItemMeta(trailCosmeticsMeta);

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        // Add Items To Menu
        cosmeticsMenu.setItem(0, filler);
        cosmeticsMenu.setItem(1, filler);
        cosmeticsMenu.setItem(2, filler);
        cosmeticsMenu.setItem(3, filler);
        cosmeticsMenu.setItem(4, filler);
        cosmeticsMenu.setItem(5, filler);
        cosmeticsMenu.setItem(6, filler);
        cosmeticsMenu.setItem(7, filler);
        cosmeticsMenu.setItem(8, filler);
        // Row 2
        cosmeticsMenu.setItem(9, filler);
        cosmeticsMenu.setItem(10, filler);
        cosmeticsMenu.setItem(11, filler);
        cosmeticsMenu.setItem(12, bowCosmetics);
        cosmeticsMenu.setItem(13, filler);
        cosmeticsMenu.setItem(14, trailCosmetics);
        cosmeticsMenu.setItem(15, filler);
        cosmeticsMenu.setItem(16, filler);
        cosmeticsMenu.setItem(17, filler);
        // Row 3
        cosmeticsMenu.setItem(18, filler);
        cosmeticsMenu.setItem(19, filler);
        cosmeticsMenu.setItem(20, filler);
        cosmeticsMenu.setItem(21, filler);
        cosmeticsMenu.setItem(22, filler);
        cosmeticsMenu.setItem(23, filler);
        cosmeticsMenu.setItem(24, filler);
        cosmeticsMenu.setItem(25, filler);
        cosmeticsMenu.setItem(26, filler);

        // Open the menu for the player
        player.openInventory(cosmeticsMenu);
    }
}
