package com.siliqon.cosmicCosmetics.custom;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;

import java.util.HashMap;
import java.util.Map;

public abstract class RegistryTemplate {
    protected static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    protected Map<Object, Object> registryMap = new HashMap<>();

    public Map<Object, Object> getRegistry() {
        return registryMap;
    }

    public void register(Object key, Object value) {
        registryMap.put(key, value);
    }

    public Object get(Object key) {
        return registryMap.get(key);
    }
}
