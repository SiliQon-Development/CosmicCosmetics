package com.siliqon.cosmiccosmetics.files;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;

@Configuration
public class MainConfig {

    private Boolean pluginEnabled = true;
    @Comment("Notify ops every time a new plugin update is available?")
    private Boolean notifyUpdates = true;

    @Comment("Use for debugging purposes or when asking for support. (0-2)")
    private Integer debugLevel = 0;

    public Integer getDebugLevel() {
        return debugLevel;
    }

    public Boolean getPluginEnabled() {
        return pluginEnabled;
    }

    public Boolean getNotifyUpdates() {
        return notifyUpdates;
    }
}
