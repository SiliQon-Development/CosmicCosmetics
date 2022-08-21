package dev.wonkypigs.cosmiccosmetics.handlers;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import dev.wonkypigs.cosmiccosmetics.guis.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CosmeticsMenuHandler implements Listener {


    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
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
        }
    }
}
