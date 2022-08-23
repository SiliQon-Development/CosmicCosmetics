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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CosmeticsMenu implements CommandExecutor {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(sender.hasPermission("cc.menu")){
                Player player = (Player) sender;
                openMainCosmeticsGUI(player);
            }else{
                if(args[0].equalsIgnoreCase("info")) {
                    sender.sendMessage(ChatColor.GREEN + "------------------------");
                    sender.sendMessage(plugin.prefix);
                    sender.sendMessage(ChatColor.BLUE + "Author: WonkyPigs");
                    sender.sendMessage(ChatColor.BLUE + "Version: 1.0");
                    sender.sendMessage(ChatColor.GREEN + "------------------------");
                }
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
        bowCosmeticsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        bowCosmetics.setItemMeta(bowCosmeticsMeta);

        ItemStack trailCosmetics = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta trailCosmeticsMeta = trailCosmetics.getItemMeta();
        trailCosmeticsMeta.setDisplayName(ChatColor.GREEN + "Trail Effects");
        trailCosmeticsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        trailCosmetics.setItemMeta(trailCosmeticsMeta);

        ItemStack killCosmetics = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta killCosmeticsMeta = killCosmetics.getItemMeta();
        killCosmeticsMeta.setDisplayName(ChatColor.GREEN + "Kill Effects");
        killCosmeticsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        killCosmetics.setItemMeta(killCosmeticsMeta);

        ItemStack spiralCosmetics = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta spiralCosmeticsMeta = spiralCosmetics.getItemMeta();
        spiralCosmeticsMeta.setDisplayName(ChatColor.GREEN + "Spiral Effects");
        spiralCosmeticsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        spiralCosmetics.setItemMeta(spiralCosmeticsMeta);


        ItemStack resetAllEffects = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta resetAllEffectsMeta = resetAllEffects.getItemMeta();
        resetAllEffectsMeta.setDisplayName(ChatColor.RED + "Reset All Effects");
        resetAllEffectsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        resetAllEffects.setItemMeta(resetAllEffectsMeta);

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        fillerMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
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
        cosmeticsMenu.setItem(10, trailCosmetics);
        cosmeticsMenu.setItem(11, filler);
        cosmeticsMenu.setItem(12, killCosmetics);
        cosmeticsMenu.setItem(13, filler);
        cosmeticsMenu.setItem(14, spiralCosmetics);
        cosmeticsMenu.setItem(15, filler);
        cosmeticsMenu.setItem(16, bowCosmetics);
        cosmeticsMenu.setItem(17, filler);
        // Row 3
        cosmeticsMenu.setItem(18, filler);
        cosmeticsMenu.setItem(19, filler);
        cosmeticsMenu.setItem(20, filler);
        cosmeticsMenu.setItem(21, filler);
        cosmeticsMenu.setItem(22, resetAllEffects);
        cosmeticsMenu.setItem(23, filler);
        cosmeticsMenu.setItem(24, filler);
        cosmeticsMenu.setItem(25, filler);
        cosmeticsMenu.setItem(26, filler);

        // Open the menu for the player
        player.openInventory(cosmeticsMenu);
    }
}
