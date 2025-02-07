package com.siliqon.cosmicCosmetics;

import co.aikar.commands.PaperCommandManager;
import com.jeff_media.updatechecker.UpdateCheckSource;
import com.jeff_media.updatechecker.UpdateChecker;
import com.siliqon.cosmicCosmetics.commands.CosmeticsCommand;
import com.siliqon.cosmicCosmetics.custom.ActiveEffectData;
import com.siliqon.cosmicCosmetics.enums.EffectForm;
import com.siliqon.cosmicCosmetics.files.*;
import com.siliqon.cosmicCosmetics.handlers.effects.Halo;
import com.siliqon.cosmicCosmetics.handlers.effects.Kill;
import com.siliqon.cosmicCosmetics.handlers.effects.Projectile;
import com.siliqon.cosmicCosmetics.handlers.effects.Trail;
import com.siliqon.cosmicCosmetics.listeners.PlayerListener;
import com.siliqon.cosmicCosmetics.listeners.ServerListener;
import com.siliqon.cosmicCosmetics.registries.*;
import com.siliqon.cosmicCosmetics.utils.Storage;
import com.siliqon.cosmicCosmetics.guis.lib.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import redempt.crunch.data.Pair;

import java.util.*;

import static com.siliqon.cosmicCosmetics.utils.Effects.getPlayerActiveEffectData;
import static com.siliqon.cosmicCosmetics.utils.Files.initFiles;
import static com.siliqon.cosmicCosmetics.utils.Messaging.log;
import static com.siliqon.cosmicCosmetics.utils.Messaging.logError;
import static com.siliqon.cosmicCosmetics.utils.Storage.saveAllData;

public final class CosmicCosmetics extends JavaPlugin {
    private static CosmicCosmetics INSTANCE ;{ INSTANCE = this; }
    public final String PLUGIN_VERSION = "v"+getDescription().getVersion();

    public NamespacedKey customItemKey = new NamespacedKey(this, "menu-item");
    public String PREFIX = "&b&lCosmetics &7> &r ";
    public final String SPIGOT_RESOURCE_ID = "104768";

    public EffectNameRegistry effectNameRegistry;
    public EffectDescriptionRegistry effectDescriptionRegistry;
    public EffectMaterialRegistry effectMaterialRegistry;
    public EffectParticleRegistry effectParticleRegistry;
    public EffectDensityRegistry effectDensityRegistry;

    private PaperCommandManager commandManager;
    public GUIManager guiManager;
    private GUIListener guiListener;

    public MainConfig config;
    public LangFile lang;

    public boolean vaultEnabled = false;
    public Permission vaultPerms = null;
    public Chat vaultChat = null;

    public Map<UUID, ActiveEffectData> playerActiveEffects = new HashMap<>();
    public Map<UUID, Boolean> cosmeticsEnabled = new HashMap<>();

    @Override
    public void onEnable() {
        initFiles();

        effectNameRegistry = EffectNameRegistry.getInstance();
        effectDescriptionRegistry = EffectDescriptionRegistry.getInstance();
        effectMaterialRegistry = EffectMaterialRegistry.getInstance();
        effectParticleRegistry = EffectParticleRegistry.getInstance();
        effectDensityRegistry = EffectDensityRegistry.getInstance();

        // plugin enabled?
        if (!config.getPluginEnabled()) {
            logError("Plugin is disabled in config.yml. Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Storage.load();

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
                .onFail(((commandSenders, e) -> logError("Failed to check for plugin updates!")));

        getOnlinePlayerData(); // protect data vanishing into thin air during /reload
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
        Bukkit.getPluginManager().registerEvents(guiListener, this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new Kill(), this);
        Bukkit.getPluginManager().registerEvents(new ServerListener(), this);
    }

    private void getOnlinePlayerData() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Pair<Boolean, ActiveEffectData> pdata = Storage.getPlayerData(player.getUniqueId());
            setupPlayerData(player, pdata);
        }
    }

    public void setupPlayerData(Player player, Pair<Boolean, ActiveEffectData> pdata) {
        cosmeticsEnabled.put(player.getUniqueId(), pdata.getFirst());
        playerActiveEffects.put(player.getUniqueId(), pdata.getSecond());

        // resume any active effect tasks
        ActiveEffectData ped = getPlayerActiveEffectData(player);
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
