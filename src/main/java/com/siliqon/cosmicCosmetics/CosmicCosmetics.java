package com.siliqon.cosmicCosmetics;

import co.aikar.commands.PaperCommandManager;
import com.jeff_media.updatechecker.UpdateCheckSource;
import com.jeff_media.updatechecker.UpdateChecker;
import com.siliqon.cosmicCosmetics.commands.CosmeticsCommand;
import com.siliqon.cosmicCosmetics.data.ActiveEffectData;
import com.siliqon.cosmicCosmetics.data.EffectForm;
import com.siliqon.cosmicCosmetics.files.*;
import com.siliqon.cosmicCosmetics.handlers.effects.Halo;
import com.siliqon.cosmicCosmetics.handlers.effects.Kill;
import com.siliqon.cosmicCosmetics.handlers.effects.Projectile;
import com.siliqon.cosmicCosmetics.handlers.effects.Trail;
import com.siliqon.cosmicCosmetics.listeners.PlayerListener;
import com.siliqon.cosmicCosmetics.listeners.ServerListener;
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
import redempt.crunch.data.Pair;

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
    public String PREFIX = "&b&lCosmetics &7> &r ";
    public final String SPIGOT_RESOURCE_ID = "104768";

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
        new UpdateChecker(this, UpdateCheckSource.SPIGET, SPIGOT_RESOURCE_ID)
                .setNotifyOpsOnJoin(config.getNotifyUpdates())
                .setDownloadLink("https://www.spigotmc.org/resources/"+SPIGOT_RESOURCE_ID)
                .checkEveryXHours(12)
                .checkNow()
                .onFail(((commandSenders, e) -> {
                    logError("Failed to check for plugin updates!");
                }));

        log(PLUGIN_VERSION+ " enabled successfully");
        getOnlinePlayerData();
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
        Bukkit.getPluginManager().registerEvents(guiListener, this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new Kill(), this);
        Bukkit.getPluginManager().registerEvents(new ServerListener(), this);
    }

    private void getOnlinePlayerData() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Pair<Boolean, ActiveEffectData> pdata = storage.getPlayerData(player.getUniqueId());
            setupPlayerData(player, pdata);
        }
    }

    public void setupPlayerData(Player player, Pair<Boolean, ActiveEffectData> pdata) {
        if (pdata == null) {
            cosmeticsEnabled.put(player, true);
            return;
        };
        cosmeticsEnabled.put(player, pdata.getFirst());

        if (pdata.getSecond() == null) return;
        playerActiveEffects.put(player, pdata.getSecond());

        // resume any active effect tasks
        ActiveEffectData ped = playerActiveEffects.get(player);
        for (EffectForm form : ped.getEffects().keySet()) {
            if (ped.getTaskIds().containsKey(form)) continue;
            switch (form) {
                case PROJECTILE: {
                    Projectile.startForPlayer(player);
                }
                case TRAIL: {
                    Trail.startForPlayer(player);
                }
                case HALO: {
                    Halo.startForPlayer(player);
                }
            }
        }
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
