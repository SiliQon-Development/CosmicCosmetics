package dev.wonkypigs.cosmiccosmetics.handlers.gui_handlers;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class KillCosmeticsGUIHandler implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        PersistentDataContainer pdata = player.getPersistentDataContainer();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Trail Effects")) {
            e.setCancelled(true);
            player.closeInventory();
            if (e.getCurrentItem().getType().equals(Material.RED_DYE)) {
                if(player.hasPermission("ccosmetics.kill.blood")) {
                    pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "BLOOD");
                    player.sendMessage(plugin.prefix + "Activated Blood Kill effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
        }
    }
}
