package com.siliqon.cosmicCosmetics.utils;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.custom.ActiveEffectData;
import com.siliqon.cosmicCosmetics.enums.EffectForm;
import com.siliqon.cosmicCosmetics.enums.EffectType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import redempt.crunch.data.Pair;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.siliqon.cosmicCosmetics.utils.Effects.getEffectsEnabled;
import static com.siliqon.cosmicCosmetics.utils.Effects.getPlayerActiveEffectData;
import static com.siliqon.cosmicCosmetics.utils.Messaging.logError;

public class Storage {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();
    private static File dataFile; public static FileConfiguration data;

    public Storage() {}

    public static void load() {
        dataFile = new File(plugin.getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            plugin.saveResource("data.yml", false);
        }

        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    public static Pair<Boolean, ActiveEffectData> getPlayerData(UUID player) {
        ActiveEffectData pdata = new ActiveEffectData(player, new HashMap<>(), new HashMap<>());
        ConfigurationSection section = data.getConfigurationSection(player.toString());

        if (section == null) return new Pair<>(false, pdata);

        boolean enabled = false;
        if (section.contains("enabled")) enabled = section.getBoolean("enabled");

        ConfigurationSection effectsData = section.getConfigurationSection("effects");
        if (effectsData == null) return new Pair<>(enabled, pdata);

        Map<EffectForm, EffectType> activeEffects = new HashMap<>();
        for (String key : effectsData.getKeys(false)) {
            activeEffects.put(EffectForm.valueOf(key), EffectType.valueOf(effectsData.getString(key)));
        }
        pdata.setEffects(activeEffects);

        return new Pair<>(enabled, pdata);
    }
    public static void savePlayerData(UUID playerUUID, Boolean enabled, ActiveEffectData pdata) {
        data.set(playerUUID.toString(), null);

        ConfigurationSection section = data.getConfigurationSection(playerUUID.toString());

        if (section == null) section = data.createSection(playerUUID.toString());
        section.set("enabled", enabled);

        if (pdata == null) return;

        ConfigurationSection effectsSection = section.getConfigurationSection("effects");
        if (effectsSection == null) effectsSection = section.createSection("effects");

        for (EffectForm form : pdata.getEffects().keySet()) {
            EffectType type = pdata.getEffects().get(form);
            effectsSection.set(form.toString(), type.toString());
        }

        try {
            data.save(dataFile);
        } catch (Exception e) {
            logError("Failed to save player data to file");
            e.printStackTrace();
        }
    }
    public static void saveAllData(boolean log) {
        Bukkit.getOnlinePlayers().forEach(player ->
                savePlayerData(player.getUniqueId(), getEffectsEnabled(player), getPlayerActiveEffectData(player))
        );
        try {
            data.save(dataFile);
        } catch (Exception e) {
            logError("Failed to save all data to file");
            e.printStackTrace();
        }
    }
}
