package dev.wonkypigs.cosmiccosmetics.listeners;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CosmeticsMenuHandler implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase("Cosmetics Menu")) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType().equals(Material.RED_DYE)) {
                player.sendMessage(plugin.prefix + "You clicked on a red dye!");
            }else if(e.getCurrentItem().getType().equals(Material.GREEN_DYE)) {
                player.sendMessage(plugin.prefix + "You clicked on a green dye!");
            }else if(e.getCurrentItem().getType().equals(Material.BLUE_DYE)) {
                player.sendMessage(plugin.prefix + "You clicked on a blue dye!");
            }
        }
    }
}
