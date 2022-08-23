package dev.wonkypigs.cosmiccosmetics.handlers;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import dev.wonkypigs.cosmiccosmetics.guis.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CosmeticsMenuHandler implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        PersistentDataContainer pdata = player.getPersistentDataContainer();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "" + ChatColor.BOLD + "Cosmetics Menu")) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType().equals(Material.BOW)) {
                player.closeInventory();
                BowCosmeticsGUI.openBowCosmeticsGUI(player);
            }
            else if(e.getCurrentItem().getType().equals(Material.DIAMOND_BOOTS)) {
                player.closeInventory();
                TrailCosmeticsGUI.openTrailCosmeticsGUI(player);
            }
            else if(e.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
                player.closeInventory();
                KillCosmeticsGUI.openKillCosmeticsGUI(player);
            }
            else if(e.getCurrentItem().getType().equals(Material.ENDER_PEARL)) {
                player.closeInventory();
                SpiralCosmeticsGUI.openSpiralCosmeticsGUI(player);
            }
            else if(e.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
                player.closeInventory();
                pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "NONE");
                pdata.set(new NamespacedKey(plugin, "trail_effect"), PersistentDataType.STRING, "NONE");
                pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "NONE");
                pdata.set(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING, "NONE");
                player.sendMessage(plugin.prefix + "All effects have been reset!");
            }
        }
    }
}
