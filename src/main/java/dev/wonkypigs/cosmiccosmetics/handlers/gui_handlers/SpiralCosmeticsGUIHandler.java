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

public class SpiralCosmeticsGUIHandler implements Listener {

    private final CosmicCosmetics plugin = CosmicCosmetics.getPlugin(CosmicCosmetics.class);

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        PersistentDataContainer pdata = player.getPersistentDataContainer();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Spiral Effects")) {
            e.setCancelled(true);
            player.closeInventory();
            if (e.getCurrentItem().getType().equals(Material.LAVA_BUCKET)) {
                if(player.hasPermission("cc.spiral.lava")) {
                    pdata.set(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING, "FALLING_LAVA");
                    player.sendMessage(plugin.prefix + "Activated Lava Spiral effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if (e.getCurrentItem().getType().equals(Material.GUNPOWDER)) {
                if(player.hasPermission("cc.spiral.ash")) {
                    pdata.set(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING, "ASH");
                    player.sendMessage(plugin.prefix + "Activated Ash Spiral effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if (e.getCurrentItem().getType().equals(Material.NETHER_STAR)) {
                if(player.hasPermission("cc.spiral.sparkle")) {
                    pdata.set(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING, "FIREWORKS_SPARK");
                    player.sendMessage(plugin.prefix + "Activated Sparkle Spiral effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if (e.getCurrentItem().getType().equals(Material.NOTE_BLOCK)) {
                if(player.hasPermission("cc.spiral.musical")) {
                    pdata.set(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING, "NOTE");
                    player.sendMessage(plugin.prefix + "Activated Musical Spiral effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if (e.getCurrentItem().getType().equals(Material.WATER_BUCKET)) {
                if(player.hasPermission("cc.spiral.water")) {
                    pdata.set(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING, "FALLING_WATER");
                    player.sendMessage(plugin.prefix + "Activated Water Spiral effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if (e.getCurrentItem().getType().equals(Material.RED_DYE)) {
                if(player.hasPermission("cc.spiral.love")) {
                    pdata.set(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING, "HEART");
                    player.sendMessage(plugin.prefix + "Activated Love Spiral effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if (e.getCurrentItem().getType().equals(Material.EMERALD)) {
                if(player.hasPermission("cc.spiral.happy")) {
                    pdata.set(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING, "VILLAGER_HAPPY");
                    player.sendMessage(plugin.prefix + "Activated Happy Spiral effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if (e.getCurrentItem().getType().equals(Material.SPORE_BLOSSOM)) {
                if(player.hasPermission("cc.spiral.blossom")) {
                    pdata.set(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING, "FALLING_SPORE_BLOSSOM");
                    player.sendMessage(plugin.prefix + "Activated Blossom Spiral effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if (e.getCurrentItem().getType().equals(Material.DRAGON_BREATH)) {
                if(player.hasPermission("cc.spiral.dragon")) {
                    pdata.set(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING, "SPELL_WITCH");
                    player.sendMessage(plugin.prefix + "Activated Dragon Spiral effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                pdata.set(new NamespacedKey(plugin, "spiral_effect"), PersistentDataType.STRING, "NONE");
                player.sendMessage(plugin.prefix + "Reset All Spiral effects");
            }
            else if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
                CosmeticsMenu.openMainCosmeticsGUI(player);
            }
        }
    }
}