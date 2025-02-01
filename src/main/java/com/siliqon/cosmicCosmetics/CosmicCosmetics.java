package com.siliqon.cosmicCosmetics;

import co.aikar.commands.PaperCommandManager;
import com.jeff_media.updatechecker.UpdateCheckSource;
import com.jeff_media.updatechecker.UpdateChecker;
import com.siliqon.cosmicCosmetics.commands.CosmeticsCommand;
import com.siliqon.cosmicCosmetics.data.ActiveEffectData;
import com.siliqon.cosmicCosmetics.files.*;
import com.siliqon.cosmicCosmetics.handlers.effects.Kill;
import com.siliqon.cosmicCosmetics.listeners.PlayerListener;
import com.siliqon.cosmicCosmetics.utils.general.storage;
import com.siliqon.cosmicCosmetics.utils.gui.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.siliqon.cosmicCosmetics.utils.general.files.initFiles;
import static com.siliqon.cosmicCosmetics.utils.general.messaging.*;
import static com.siliqon.cosmicCosmetics.utils.general.storage.saveAllData;

public final class CosmicCosmetics extends JavaPlugin {
    private static CosmicCosmetics INSTANCE ;{ INSTANCE = this; }
    public final String PLUGIN_VERSION = "v"+getDescription().getVersion();

    public NamespacedKey customItemKey = new NamespacedKey(this, "cosmiccosmetics-custom-item-for-menus");
    public String PREFIX = "&b[&aCosmetics&b]&r ";
    public final String SPIGOT_RESOURCE_ID = "106729";

    private PaperCommandManager commandManager;
    public GUIManager guiManager;
    private GUIListener guiListener;

    public MainConfig config;
    public LangFile lang;

    public Boolean vaultEnabled = false;
    public Permission vaultPerms = null;
    public Chat vaultChat = null;

    public Map<Player, ActiveEffectData> playerActiveEffects = new HashMap<>();
    public Map<Player, Boolean> cosmeticsEnabled = new HashMap<>();

    @Override
    public void onEnable() {
        initFiles();

        // plugin enabled?
        if (!config.getPluginEnabled()) {
            logError("Plugin is disabled in config.yml. Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        storage.load();

        // vault integration
        if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            log("Vault found. Initiating integration process...");
            setupVault();
        } else {
            log("Vault not found! Skipping integration process...");
        }

        // gui manager
        guiManager = new GUIManager();
        guiListener = new GUIListener(guiManager);
        registerListeners();

        // commands
        commandManager = new PaperCommandManager(this);
        registerCommandCompletions();
        registerCommands();

        // check for plugin updates
        new UpdateChecker(this, UpdateCheckSource.SPIGOT, SPIGOT_RESOURCE_ID)
                .setNotifyOpsOnJoin(config.getNotifyUpdates())
                .setChangelogLink(Integer.parseInt(SPIGOT_RESOURCE_ID))
                .setDownloadLink(Integer.parseInt(SPIGOT_RESOURCE_ID))
                .checkEveryXHours(24)
                .checkNow();

        log(PLUGIN_VERSION+ " enabled successfully");
    }

    @Override
    public void onDisable() {
        saveAllData(true);
        log("Disabled successfully");
    }

    private void registerCommandCompletions() {
        commandManager.getCommandCompletions().registerCompletion("AllPlayers", context -> {
            List<String> nameList = new ArrayList<>();
            for (OfflinePlayer player: Bukkit.getOfflinePlayers()) {
                nameList.add(player.getName());
            }
            return nameList;
        });
    }

    private void registerCommands() {
        commandManager.registerCommand(new CosmeticsCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new Kill(), this);
        Bukkit.getPluginManager().registerEvents(guiListener, this);
    }

    private void setupVault() {
        RegisteredServiceProvider<Chat> rspChat = getServer().getServicesManager().getRegistration(Chat.class);
        RegisteredServiceProvider<Permission> rspPerm = getServer().getServicesManager().getRegistration(Permission.class);
        if (rspChat != null && rspPerm != null) {
            vaultChat = rspChat.getProvider();
            vaultPerms = rspPerm.getProvider();
            log("Vault integration successful.");
            vaultEnabled = true;
        } else {
            logError("Failed to load Vault API!");
        }
    }

    public static CosmicCosmetics getInstance() {
        return INSTANCE;
    }
}
