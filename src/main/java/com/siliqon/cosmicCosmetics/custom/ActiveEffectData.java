package com.siliqon.cosmicCosmetics.custom;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.enums.EffectForm;
import com.siliqon.cosmicCosmetics.enums.EffectType;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

import static com.siliqon.cosmicCosmetics.utils.Messaging.log;

public class ActiveEffectData {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();

    private UUID player;
    private Map<EffectForm, EffectType> effects;
    private final Map<EffectForm, List<Integer>> taskIds;

    public ActiveEffectData(UUID player, Map<EffectForm, EffectType> effects, Map<EffectForm, List<Integer>> taskIds) {
        this.player = player;
        this.effects = effects;
        this.taskIds = taskIds;
    }

    public UUID getPlayer() {
        return player;
    }

    public void setPlayer(UUID player) {
        this.player = player;
    }

    public Map<EffectForm, EffectType> getEffects() {
        return effects;
    }

    public void setEffects(Map<EffectForm, EffectType> effects) {
        this.effects = effects;
    }

    public void addEffect(EffectForm form, EffectType type) {
        effects.put(form, type);
    }

    public void removeEffect(EffectForm form) {
        log("R "+form);
        if (taskIds.containsKey(form)) {
            log("T "+form);
            for (int taskId : taskIds.get(form)) {
                plugin.getServer().getScheduler().cancelTask(taskId);
            }
            taskIds.remove(form);
        }
        effects.remove(form);
    }

    public Map<EffectForm, List<Integer>> getTaskIds() {
        return taskIds;
    }

    public void addTaskId(EffectForm form, int taskId) {
        taskIds.computeIfAbsent(form, k -> new ArrayList<>());
        log(form.name());
        taskIds.get(form).add(taskId);
    }
}
