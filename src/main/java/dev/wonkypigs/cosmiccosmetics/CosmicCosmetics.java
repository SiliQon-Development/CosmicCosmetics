package dev.wonkypigs.cosmiccosmetics;

import dev.wonkypigs.cosmiccosmetics.commands.*;
import dev.wonkypigs.cosmiccosmetics.handlers.*;
import dev.wonkypigs.cosmiccosmetics.handlers.cosmetic_handlers.*;
import dev.wonkypigs.cosmiccosmetics.handlers.gui_handlers.*;
import dev.wonkypigs.cosmiccosmetics.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CosmicCosmetics extends JavaPlugin {
    public String prefix = ChatColor.translateAlternateColorCodes('&', "&8&l[&6CosmicCosmetics&8]&r ");

    // Plugin startup logic
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        getLogger().info("CosmicCosmetics has been enabled successfully!");
    }

    // Plugin shutdown logic
    @Override
    public void onDisable() {
        getLogger().info("CosmicCosmetics has been disabled!");
    }

    // Registering all plugin commands
    private void registerCommands() {
        Objects.requireNonNull(getCommand("cosmiccosmetics")).setExecutor(new CosmeticsMenu());
        getLogger().info("Commands have been registered!");
    }

    // Registering all plugin listeners
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new CosmeticsMenuHandler(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new BowCosmeticsGUIHandler(), this);
        getServer().getPluginManager().registerEvents(new BowCosmeticsHandler(), this);
        getServer().getPluginManager().registerEvents(new TrailCosmeticsGUIHandler(), this);
        getServer().getPluginManager().registerEvents(new TrailCosmeticsHandler(), this);
        getServer().getPluginManager().registerEvents(new KillCosmeticsGUIHandler(), this);
        getServer().getPluginManager().registerEvents(new KillCosmeticsHandler(), this);
        getServer().getPluginManager().registerEvents(new SpiralCosmeticsGUIHandler(), this);
        getServer().getPluginManager().registerEvents(new SpiralCosmeticsHandler(), this);

        getLogger().info("Listeners have been registered!");
    }

    // Getting values from config with color coding
    public String getConfigValue(String key) {
        String ans = getConfig().getString(key);
        return ChatColor.translateAlternateColorCodes('&', ans);
    }
}
