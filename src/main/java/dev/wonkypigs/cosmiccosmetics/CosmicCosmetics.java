package dev.wonkypigs.cosmiccosmetics;

import dev.wonkypigs.cosmiccosmetics.commands.*;
import dev.wonkypigs.cosmiccosmetics.handlers.*;
import dev.wonkypigs.cosmiccosmetics.handlers.cosmetic_handlers.*;
import dev.wonkypigs.cosmiccosmetics.handlers.gui_handlers.*;
import dev.wonkypigs.cosmiccosmetics.listeners.*;
import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;


public final class CosmicCosmetics extends JavaPlugin {
    private static CosmicCosmetics instance;{ instance = this; }
    public String prefix = getConfig().getString("prefix").replace("&", "§");
    // Plugin startup logic
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        registerPermissions();

        int pluginId = 16202; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        UpdateChecker updateChecker = new UpdateChecker();
        updateChecker.check();

        getLogger().info("CosmicCosmetics has been enabled successfully!");
    }

    // Plugin shutdown logic
    @Override
    public void onDisable() {
        getLogger().info("CosmicCosmetics has been disabled!");
    }

    // Registering all plugin commands
    private void registerCommands() {
        getServer().getPluginCommand("cosmiccosmetics").setExecutor(new CosmeticsMenu());
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
        getServer().getPluginManager().registerEvents(new UpdateChecker(), this);
    }

    private void registerPermissions() {
        getServer().getPluginManager().addPermission(new Permission("cc.menu"));
        getServer().getPluginManager().addPermission(new Permission("cc.info"));

        getServer().getPluginManager().addPermission(new Permission("cc.bow"));
        getServer().getPluginManager().addPermission(new Permission("cc.trail"));
        getServer().getPluginManager().addPermission(new Permission("cc.kill"));
        getServer().getPluginManager().addPermission(new Permission("cc.spiral"));
    }

    public static CosmicCosmetics getInstance() {
        return instance;
    }

    // Getting values from config with color coding
    public String getConfigValue(String key) {
        String ans = getConfig().getString(key);
        return ChatColor.translateAlternateColorCodes('&', ans);
    }
}
