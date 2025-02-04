package com.siliqon.cosmicCosmetics.files;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;

import java.util.List;

@Configuration
public class LangFile {

    private String prefix = "&b&lCosmetics &7> &r ";

    @Comment({" ", "main menu"})
    private String trailEffectsItemName = "&6Trail Effects";
    private List<String> trailEffectsItemLore = List.of(
            " ",
            "&7Active: %active_effect_name%"
    );
    private String projectileEffectsItemName = "&6Projectile Effects";
    private List<String> projectileEffectsItemLore = List.of(
            " ",
            "&7Active: %active_effect_name%"
    );
    private String killEffectsItemName = "&6Kill Effects";
    private List<String> killEffectsItemLore = List.of(
            " ",
            "&7Active: %active_effect_name%"
    );
    private String haloEffectsItemName = "&6Halo Effects";
    private List<String> haloEffectsItemLore = List.of(
            " ",
            "&7Active: %active_effect_name%"
    );
    private String resetAllEffectsItemName = "&cReset All Effects";
    private List<String> resetAllEffectsItemLore = List.of(
            " ",
            "&7Turn off all active effects."
    );

    @Comment({" ", "effect menus"})
    private String effectNotUnlockedName = "&7(&cLOCKED&7) %effect_name%";
    private String effectSelectedName = "&7(&aSELECTED&7) %effect_name%";
    private String resetEffectItemName = "&cReset Effect";
    private String backButtonName = "&cGo Back";

    private String mainMenuName = "&5&lCosmetic Options";
    private String projectileEffectsMenuName = "&6Projectile Effects";
    private String trailEffectsMenuName = "&6Trail Effects";
    private String killEffectsMenuName = "&6Kill Effects";
    private String haloEffectsMenuName = "&6Halo Effects";

    @Comment({" ", "effect data"})
    private String splashEffectName = "&bSplash";
    private List<String> splashEffectDescription = List.of(
            " ",
            "&7Splish splash goes the water"
    );
    private String enderEffectName = "&5Ender";
    private List<String> enderEffectDescription = List.of(
            " ",
            "&7Enderman cosplay"
    );
    private String flameEffectName = "&6Flame";
    private List<String> flameEffectDescription = List.of(
            " ",
            "&7Don't play with fire kids!"
    );
    private String charmEffectName = "&aCharm";
    private List<String> charmEffectDescription = List.of(
            " ",
            "&7WARNING: Charming!"
    );
    private String loveEffectName = "&cLove";
    private List<String> loveEffectDescription = List.of(
            " ",
            "&7Love em like you hate em"
    );
    private String blossomEffectName = "&dBlossom";
    private List<String> blossomEffectDescription = List.of(
            " ",
            "&7Like a cherry... blossom"
    );
    private String enchantedEffectName = "&eEnchanted";
    private List<String> enchantedEffectDescription = List.of(
            " ",
            "&7Truly enlightened"
    );
    private String tearsEffectName = "&9Tears";
    private List<String> tearsEffectDescription = List.of(
            " ",
            "&7Cry me a river"
    );
    private String musicalEffectName = "&aMusical";
    private List<String> musicalEffectDescription = List.of(
            " ",
            "&7*Intense piano playing*"
    );
    private String rainbowEffectName = "&5R&da&1i&an&eb&6o&cw";
    private List<String> rainbowEffectDescription = List.of(
            " ",
            "&7Nyan cat activated."
    );
    private String cloudEffectName = "&7Cloudy";
    private List<String> cloudEffectDescription = List.of(
            " ",
            "&7Cloudy with a chance",
            "&7of particles"
    );

    @Comment({"", "messages"})
    private String effectEnabled = "&bEnabled %effect_name%";
    private String effectDisabled = "&cDisabled %effect_name%";
    private String disabledAllEffectsMessage = "&cAll active cosmetics have been turned off.";
    private String cosmeticsDisabledOther = "&cYou will no longer see other player's cosmetics.";
    private String cosmeticsEnabledOther = "&aYou will now see other player's cosmetics.";

    public String getBackButtonName() {
        return backButtonName;
    }

    public String getMainMenuName() {
        return mainMenuName;
    }

    public String getCloudEffectName() {
        return cloudEffectName;
    }

    public List<String> getCloudEffectDescription() {
        return cloudEffectDescription;
    }

    public String getRainbowEffectName() {
        return rainbowEffectName;
    }

    public List<String> getRainbowEffectDescription() {
        return rainbowEffectDescription;
    }

    public String getTrailEffectsMenuName() {
        return trailEffectsMenuName;
    }

    public String getKillEffectsMenuName() {
        return killEffectsMenuName;
    }

    public String getSplashEffectName() {
        return splashEffectName;
    }

    public List<String> getSplashEffectDescription() {
        return splashEffectDescription;
    }

    public String getEnderEffectName() {
        return enderEffectName;
    }

    public List<String> getEnderEffectDescription() {
        return enderEffectDescription;
    }

    public String getCharmEffectName() {
        return charmEffectName;
    }

    public List<String> getCharmEffectDescription() {
        return charmEffectDescription;
    }

    public String getLoveEffectName() {
        return loveEffectName;
    }

    public List<String> getLoveEffectDescription() {
        return loveEffectDescription;
    }

    public String getBlossomEffectName() {
        return blossomEffectName;
    }

    public List<String> getBlossomEffectDescription() {
        return blossomEffectDescription;
    }

    public String getEnchantedEffectName() {
        return enchantedEffectName;
    }

    public List<String> getEnchantedEffectDescription() {
        return enchantedEffectDescription;
    }

    public String getTearsEffectName() {
        return tearsEffectName;
    }

    public List<String> getTearsEffectDescription() {
        return tearsEffectDescription;
    }

    public String getMusicalEffectName() {
        return musicalEffectName;
    }

    public List<String> getMusicalEffectDescription() {
        return musicalEffectDescription;
    }

    public String getCosmeticsDisabledOther() {
        return cosmeticsDisabledOther;
    }

    public String getCosmeticsEnabledOther() {
        return cosmeticsEnabledOther;
    }

    public String getResetEffectItemName() {
        return resetEffectItemName;
    }

    public String getEffectEnabled() {
        return effectEnabled;
    }

    public String getEffectDisabled() {
        return effectDisabled;
    }

    public String getDisabledAllEffectsMessage() {
        return disabledAllEffectsMessage;
    }

    public String getEffectSelectedName() {
        return effectSelectedName;
    }

    public String getEffectNotUnlockedName() {
        return effectNotUnlockedName;
    }

    public String getFlameEffectName() {
        return flameEffectName;
    }

    public List<String> getFlameEffectDescription() {
        return flameEffectDescription;
    }

    public String getProjectileEffectsMenuName() {
        return projectileEffectsMenuName;
    }

    public String getTrailEffectsItemName() {
        return trailEffectsItemName;
    }

    public String getProjectileEffectsItemName() {
        return projectileEffectsItemName;
    }

    public String getKillEffectsItemName() {
        return killEffectsItemName;
    }

    public String getHaloEffectsItemName() {
        return haloEffectsItemName;
    }

    public List<String> getHaloEffectsItemLore() {
        return haloEffectsItemLore;
    }

    public String getHaloEffectsMenuName() {
        return haloEffectsMenuName;
    }

    public List<String> getTrailEffectsItemLore() {
        return trailEffectsItemLore;
    }

    public List<String> getProjectileEffectsItemLore() {
        return projectileEffectsItemLore;
    }

    public List<String> getKillEffectsItemLore() {
        return killEffectsItemLore;
    }

    public String getResetAllEffectsItemName() {
        return resetAllEffectsItemName;
    }

    public List<String> getResetAllEffectsItemLore() {
        return resetAllEffectsItemLore;
    }

    public String getPrefix() {
        return prefix;
    }
}
