package dev.wonkypigs.cosmiccosmetics.handlers.gui_handlers;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import dev.wonkypigs.cosmiccosmetics.commands.CosmeticsMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BowCosmeticsGUIHandler implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        PersistentDataContainer pdata = player.getPersistentDataContainer();
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Bow Effects")) {
            e.setCancelled(true);
            player.closeInventory();
            if(e.getCurrentItem().getType().equals(Material.CRYING_OBSIDIAN)) {
                if(player.hasPermission("ccosmetics.bow.tears")) {
                    pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "FALLING_OBSIDIAN_TEAR");
                    player.sendMessage(plugin.prefix + "Activated Tears Arrow effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.LAVA_BUCKET)) {
                if(player.hasPermission("ccosmetics.bow.lava")) {
                    pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "DRIPPING_DRIPSTONE_LAVA");
                    player.sendMessage(plugin.prefix + "Activated Lava Arrow effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.WATER_BUCKET)) {
                if(player.hasPermission("ccosmetics.bow.water")) {
                    pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "FALLING_WATER");
                    player.sendMessage(plugin.prefix + "Activated Water Arrow effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.GUNPOWDER)) {
                if(player.hasPermission("ccosmetics.bow.smoke")) {
                    pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "SMOKE");
                    player.sendMessage(plugin.prefix + "Activated Smoke Arrow effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.NETHER_STAR)) {
                if(player.hasPermission("ccosmetics.bow.sparkle")) {
                    pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "WAX_OFF");
                    player.sendMessage(plugin.prefix + "Activated Sparkle Arrow effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.RED_DYE)) {
                if(player.hasPermission("ccosmetics.bow.love")) {
                    pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "HEART");
                    player.sendMessage(plugin.prefix + "Activated Love Arrow effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
                if(player.hasPermission("ccosmetics.bow.crit")) {
                    pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "CRIT");
                    player.sendMessage(plugin.prefix + "Activated Crit Arrow effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.DRAGON_BREATH)) {
                if(player.hasPermission("ccosmetics.bow.dragon")) {
                    pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "REVERSE_PORTAL");
                    player.sendMessage(plugin.prefix + "Activated Dragon Arrow effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.SPORE_BLOSSOM)) {
                if(player.hasPermission("ccosmetics.bow.blossom")) {
                    pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "FALLING_SPORE_BLOSSOM");
                    player.sendMessage(plugin.prefix + "Activated Blossom Arrow effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.ARROW)) {
                pdata.set(new NamespacedKey(plugin, "bow_effect"), PersistentDataType.STRING, "NONE");
                player.sendMessage(plugin.prefix + "Reset all bow effects");
            }
            else if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
                CosmeticsMenu.openMainCosmeticsGUI(player);
            }
        }
    }
}
