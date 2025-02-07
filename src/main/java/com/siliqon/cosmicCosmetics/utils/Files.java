package com.siliqon.cosmicCosmetics.utils;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.files.LangFile;
import com.siliqon.cosmicCosmetics.files.MainConfig;
import de.exlll.configlib.ConfigLib;
import de.exlll.configlib.NameFormatters;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import org.bukkit.ChatColor;

import java.io.File;
import java.nio.file.Path;

import static com.siliqon.cosmicCosmetics.utils.Messaging.log;

public class Files {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    private static void initConfig() {
        YamlConfigurationProperties properties = ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder()
                .header(
                        """
                        Do not add or remove any keys, only edit them
                        Make sure to follow the formatting very strictly
                        """
                )
                .footer("Authors: Siliqon")
                .setNameFormatter(NameFormatters.LOWER_KEBAB_CASE)
                .setFieldFilter(field -> !field.getName().startsWith("private_"))
                .build();

        Path configFile = new File(plugin.getDataFolder(), "config.yml").toPath();
        plugin.config = YamlConfigurations.update(
                configFile, MainConfig.class, properties
        );
        if (plugin.debugLevel >= 2) log("Initialized config.yml file");
    }
    private static void initLangFile() {
        YamlConfigurationProperties properties = ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder()
                .header(
                        """
                        All color codes like &a, &d, &5, etc. are valid
                        Do not add or remove any keys, only edit them
                        Make sure to follow the formatting very strictly
                        """
                )
                .footer("Authors: Siliqon")
                .setNameFormatter(NameFormatters.LOWER_KEBAB_CASE)
                .setFieldFilter(field -> !field.getName().startsWith("private_"))
                .build();

        Path langFile = new File(plugin.getDataFolder(), "lang.yml").toPath();
        plugin.lang = YamlConfigurations.update(
                langFile, LangFile.class, properties
        );
        plugin.PREFIX = ChatColor.translateAlternateColorCodes('&', plugin.lang.getPrefix());
        if (plugin.debugLevel >= 2) log("Initialized lang.yml file");
    }

    public static void initFiles() {
        initConfig();
        initLangFile();
    }
}
