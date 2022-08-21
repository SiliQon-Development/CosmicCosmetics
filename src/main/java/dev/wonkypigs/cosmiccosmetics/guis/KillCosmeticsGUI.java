package dev.wonkypigs.cosmiccosmetics.guis;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KillCosmeticsGUI {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    public static void openKillCosmeticsGUI(Player player) {
        // Set up the menu
        Inventory killCosmetics = Bukkit.createInventory(player, 27, ChatColor.RED + "" + ChatColor.BOLD + "Kill Effects");

        // All Menu Items
        ItemStack bloodEffect = new ItemStack(Material.RED_DYE, 1);
        ItemStack celebrationEffect = new ItemStack(Material.FIREWORK_ROCKET, 1);
        ItemStack sparkleEffect = new ItemStack(Material.NETHER_STAR, 1);
        ItemStack musicalEffect = new ItemStack(Material.NOTE_BLOCK, 1);
        ItemStack voidEffect = new ItemStack(Material.END_GATEWAY, 1);
        ItemStack loveEffect = new ItemStack(Material.RED_DYE, 1);
        ItemStack critEffect = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemStack dragonEffect = new ItemStack(Material.DRAGON_BREATH, 1);
        ItemStack blossomEffect = new ItemStack(Material.SPORE_BLOSSOM, 1);
        ItemStack resetEffect = new ItemStack(Material.ARROW, 1);
        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemStack backButton = new ItemStack(Material.BARRIER, 1);

        // Get All Menu Item Meta Data
        ItemMeta bloodEffectMeta = bloodEffect.getItemMeta();
        ItemMeta celebrationEffectMeta = celebrationEffect.getItemMeta();
        ItemMeta sparkleEffectMeta = sparkleEffect.getItemMeta();
        ItemMeta musicalEffectMeta = musicalEffect.getItemMeta();
        ItemMeta voidEffectMeta = voidEffect.getItemMeta();
        ItemMeta loveEffectMeta = loveEffect.getItemMeta();
        ItemMeta critEffectMeta = critEffect.getItemMeta();
        ItemMeta dragonEffectMeta = dragonEffect.getItemMeta();
        ItemMeta blossomEffectMeta = blossomEffect.getItemMeta();
        ItemMeta resetEffectMeta = resetEffect.getItemMeta();
        ItemMeta fillerMeta = filler.getItemMeta();
        ItemMeta backButtonmeta = backButton.getItemMeta();

        // Set All Menu Item Meta Data
        bloodEffectMeta.setDisplayName(ChatColor.RED + "Blood Effect");
        bloodEffect.setItemMeta(bloodEffectMeta);
        celebrationEffectMeta.setDisplayName(ChatColor.GREEN + "Celebration Effect");
        celebrationEffect.setItemMeta(celebrationEffectMeta);
        sparkleEffectMeta.setDisplayName(ChatColor.WHITE + "Sparkle Effect");
        sparkleEffect.setItemMeta(sparkleEffectMeta);
        musicalEffectMeta.setDisplayName(ChatColor.YELLOW + "Musical Effect");
        musicalEffect.setItemMeta(musicalEffectMeta);
        voidEffectMeta.setDisplayName(ChatColor.DARK_PURPLE + "Void Effect");
        voidEffect.setItemMeta(voidEffectMeta);
        loveEffectMeta.setDisplayName(ChatColor.AQUA + "Love Effect");
        loveEffect.setItemMeta(loveEffectMeta);
        critEffectMeta.setDisplayName(ChatColor.WHITE + "Crit Effect");
        critEffect.setItemMeta(critEffectMeta);
        dragonEffectMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dragon Effect");
        dragonEffect.setItemMeta(dragonEffectMeta);
        blossomEffectMeta.setDisplayName(ChatColor.GREEN + "Blossom Effect");
        blossomEffect.setItemMeta(blossomEffectMeta);
        resetEffectMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Reset Arrow Effect");
        resetEffect.setItemMeta(resetEffectMeta);
        fillerMeta.setDisplayName(ChatColor.GRAY + " ");
        filler.setItemMeta(fillerMeta);
        backButtonmeta.setDisplayName(ChatColor.RED + "<- Go Back");
        backButton.setItemMeta(backButtonmeta);

        // Add Items to Menu
        // Row 1
        killCosmetics.setItem(0, filler);
        killCosmetics.setItem(1, filler);
        killCosmetics.setItem(2, filler);
        killCosmetics.setItem(3, filler);
        killCosmetics.setItem(4, filler);
        killCosmetics.setItem(5, filler);
        killCosmetics.setItem(6, filler);
        killCosmetics.setItem(7, filler);
        killCosmetics.setItem(8, filler);
        // Row 2
        killCosmetics.setItem(9, bloodEffect);
        killCosmetics.setItem(10, celebrationEffect);
        killCosmetics.setItem(11, sparkleEffect);
        killCosmetics.setItem(12, musicalEffect);
        killCosmetics.setItem(13, voidEffect);
        killCosmetics.setItem(14, loveEffect);
        killCosmetics.setItem(15, critEffect);
        killCosmetics.setItem(16, dragonEffect);
        killCosmetics.setItem(17, blossomEffect);
        // Row 3
        killCosmetics.setItem(18, filler);
        killCosmetics.setItem(19, filler);
        killCosmetics.setItem(20, filler);
        killCosmetics.setItem(21, filler);
        killCosmetics.setItem(22, resetEffect);
        killCosmetics.setItem(23, filler);
        killCosmetics.setItem(24, filler);
        killCosmetics.setItem(25, filler);
        killCosmetics.setItem(26, backButton);

        // Show menu to player
        player.openInventory(killCosmetics);
    }
}
