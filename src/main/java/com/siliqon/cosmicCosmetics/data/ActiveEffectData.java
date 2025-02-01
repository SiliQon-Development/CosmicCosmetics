package com.siliqon.cosmicCosmetics.data;

import org.bukkit.Bukkit;

import java.util.Map;
import java.util.UUID;

public class ActiveEffectData {

    UUID player;
    Map<EffectForm, EffectType> effects;
    Map<EffectForm, Integer> taskIds;

    public ActiveEffectData(UUID player, Map<EffectForm, EffectType> effects, Map<EffectForm, Integer> taskIds) {
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
        if (taskIds.containsKey(form)) {
            Bukkit.getScheduler().cancelTask(taskIds.get(form));
            taskIds.remove(form);
        }
        effects.remove(form);
    }

    public Map<EffectForm, Integer> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(Map<EffectForm, Integer> taskIds) {
        this.taskIds = taskIds;
    }

    public void addTaskId(EffectForm form, int taskId) {
        taskIds.put(form, taskId);
    }
}
