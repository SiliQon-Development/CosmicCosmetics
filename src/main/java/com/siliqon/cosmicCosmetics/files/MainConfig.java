package com.siliqon.cosmicCosmetics.files;

import de.exlll.configlib.Configuration;

@Configuration
public class MainConfig {

    private Boolean pluginEnabled = true;
    private Boolean notifyUpdates = true;

    public Boolean getPluginEnabled() {
        return pluginEnabled;
    }

    public Boolean getNotifyUpdates() {
        return notifyUpdates;
    }
}
