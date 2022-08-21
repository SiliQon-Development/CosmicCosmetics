package dev.wonkypigs.cosmiccosmetics.guis;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BowCosmeticsGUI {


    public static void openBowCosmeticsGUI(Player player) {
        // Set up the menu
        Inventory bowCosmetics = Bukkit.createInventory(player, 27, ChatColor.RED + "" + ChatColor.BOLD + "Bow Effects");

        // All Menu Items
        ItemStack cryingEffect = new ItemStack(Material.CRYING_OBSIDIAN, 1);
        ItemStack lavaEffect = new ItemStack(Material.LAVA_BUCKET, 1);
        ItemStack waterEffect = new ItemStack(Material.WATER_BUCKET, 1);
        ItemStack ashEffect = new ItemStack(Material.GUNPOWDER, 1);
        ItemStack sparkleEffect = new ItemStack(Material.NETHER_STAR, 1);
        ItemStack loveEffect = new ItemStack(Material.RED_DYE, 1);
        ItemStack critEffect = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemStack dragonEffect = new ItemStack(Material.DRAGON_BREATH, 1);
        ItemStack blossomEffect = new ItemStack(Material.SPORE_BLOSSOM, 1);
        ItemStack resetEffect = new ItemStack(Material.ARROW, 1);
        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemStack backButton = new ItemStack(Material.BARRIER, 1);

        // Get All Menu Item Meta Data
        ItemMeta cryingEffectMeta = cryingEffect.getItemMeta();
        ItemMeta lavaEffectMeta = lavaEffect.getItemMeta();
        ItemMeta waterEffectMeta = waterEffect.getItemMeta();
        ItemMeta ashEffectMeta = ashEffect.getItemMeta();
        ItemMeta sparkleEffectMeta = sparkleEffect.getItemMeta();
        ItemMeta loveEffectMeta = loveEffect.getItemMeta();
        ItemMeta critEffectMeta = critEffect.getItemMeta();
        ItemMeta dragonEffectMeta = dragonEffect.getItemMeta();
        ItemMeta blossomEffectMeta = blossomEffect.getItemMeta();
        ItemMeta resetEffectMeta = resetEffect.getItemMeta();
        ItemMeta fillerMeta = filler.getItemMeta();
        ItemMeta backButtonmeta = backButton.getItemMeta();

        // Set All Menu Item Meta Data
        cryingEffectMeta.setDisplayName(ChatColor.DARK_BLUE + "Crying Effect");
        cryingEffectMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        cryingEffect.setItemMeta(cryingEffectMeta);
        lavaEffectMeta.setDisplayName(ChatColor.RED + "Lava Effect");
        lavaEffectMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        lavaEffect.setItemMeta(lavaEffectMeta);
        waterEffectMeta.setDisplayName(ChatColor.DARK_BLUE + "Water Effect");
        waterEffectMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        waterEffect.setItemMeta(waterEffectMeta);
        ashEffectMeta.setDisplayName(ChatColor.GRAY + "Ash Effect");
        ashEffectMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ashEffect.setItemMeta(ashEffectMeta);
        sparkleEffectMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Sparkle Effect");
        sparkleEffectMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        sparkleEffect.setItemMeta(sparkleEffectMeta);
        loveEffectMeta.setDisplayName(ChatColor.AQUA + "Love Effect");
        loveEffectMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        loveEffect.setItemMeta(loveEffectMeta);
        critEffectMeta.setDisplayName(ChatColor.WHITE + "Crit Effect");
        critEffectMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        critEffect.setItemMeta(critEffectMeta);
        dragonEffectMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dragon Effect");
        dragonEffectMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        dragonEffect.setItemMeta(dragonEffectMeta);
        blossomEffectMeta.setDisplayName(ChatColor.GREEN + "Blossom Effect");
        blossomEffectMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        blossomEffect.setItemMeta(blossomEffectMeta);
        resetEffectMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Reset Arrow Effect");
        resetEffectMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        resetEffect.setItemMeta(resetEffectMeta);
        fillerMeta.setDisplayName(ChatColor.GRAY + " ");
        fillerMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        filler.setItemMeta(fillerMeta);
        backButtonmeta.setDisplayName(ChatColor.RED + "<- Go Back");
        backButtonmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        backButton.setItemMeta(backButtonmeta);

        // Add Items to Menu
        // Row 1
        bowCosmetics.setItem(0, filler);
        bowCosmetics.setItem(1, filler);
        bowCosmetics.setItem(2, filler);
        bowCosmetics.setItem(3, filler);
        bowCosmetics.setItem(4, filler);
        bowCosmetics.setItem(5, filler);
        bowCosmetics.setItem(6, filler);
        bowCosmetics.setItem(7, filler);
        bowCosmetics.setItem(8, filler);
        // Row 2
        bowCosmetics.setItem(9, cryingEffect);
        bowCosmetics.setItem(10, lavaEffect);
        bowCosmetics.setItem(11, waterEffect);
        bowCosmetics.setItem(12, ashEffect);
        bowCosmetics.setItem(13, sparkleEffect);
        bowCosmetics.setItem(14, loveEffect);
        bowCosmetics.setItem(15, critEffect);
        bowCosmetics.setItem(16, dragonEffect);
        bowCosmetics.setItem(17, blossomEffect);
        // Row 3
        bowCosmetics.setItem(18, filler);
        bowCosmetics.setItem(19, filler);
        bowCosmetics.setItem(20, filler);
        bowCosmetics.setItem(21, filler);
        bowCosmetics.setItem(22, resetEffect);
        bowCosmetics.setItem(23, filler);
        bowCosmetics.setItem(24, filler);
        bowCosmetics.setItem(25, filler);
        bowCosmetics.setItem(26, backButton);

        // Show menu to player
        player.openInventory(bowCosmetics);
    }
}
