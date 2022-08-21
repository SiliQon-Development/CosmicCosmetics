package dev.wonkypigs.cosmiccosmetics.handlers.gui_handlers;

import dev.wonkypigs.cosmiccosmetics.CosmicCosmetics;
import dev.wonkypigs.cosmiccosmetics.commands.CosmeticsMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
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
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Kill Effects")) {
            e.setCancelled(true);
            player.closeInventory();
            if (e.getCurrentItem().getType().equals(Material.REDSTONE)) {
                if(player.hasPermission("cc.kill.blood")) {
                    pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "BLOOD");
                    player.sendMessage(plugin.prefix + "Activated Blood Kill effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.FIREWORK_ROCKET)) {
                if(player.hasPermission("cc.kill.celebration")) {
                    pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "CELEBRATION");
                    player.sendMessage(plugin.prefix + "Activated Celebration Kill effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.NETHER_STAR)) {
                if(player.hasPermission("cc.kill.sparkle")) {
                    pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "SPARKLE");
                    player.sendMessage(plugin.prefix + "Activated Sparkle Kill effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.NOTE_BLOCK)) {
                if(player.hasPermission("cc.kill.musical")) {
                    pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "MUSICAL");
                    player.sendMessage(plugin.prefix + "Activated Musical Kill effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.ENDER_PEARL)) {
                if(player.hasPermission("cc.kill.void")) {
                    pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "VOID");
                    player.sendMessage(plugin.prefix + "Activated Void Kill effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.RED_DYE)) {
                if(player.hasPermission("cc.kill.love")) {
                    pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "LOVE");
                    player.sendMessage(plugin.prefix + "Activated Love Kill effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.WHITE_WOOL)) {
                if(player.hasPermission("cc.kill.cloud")) {
                    pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "CLOUD");
                    player.sendMessage(plugin.prefix + "Activated Cloud Kill effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.SPORE_BLOSSOM)) {
                if(player.hasPermission("cc.kill.blossom")) {
                    pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "BLOSSOM");
                    player.sendMessage(plugin.prefix + "Activated Blossom Kill effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.DRAGON_BREATH)) {
                if (player.hasPermission("cc.kill.dragon")) {
                    pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "DRAGON");
                    player.sendMessage(plugin.prefix + "Activated Dragon Kill effect");
                }else {
                    player.sendMessage(plugin.prefix + plugin.getConfigValue("no-permission"));
                }
            }
            else if(e.getCurrentItem().getType().equals(Material.WOODEN_SWORD)) {
                pdata.set(new NamespacedKey(plugin, "kill_effect"), PersistentDataType.STRING, "NONE");
                player.sendMessage(plugin.prefix + "Reset All Kill effects");
            }
            else if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
                CosmeticsMenu.openMainCosmeticsGUI(player);
            }
        }
    }
}
