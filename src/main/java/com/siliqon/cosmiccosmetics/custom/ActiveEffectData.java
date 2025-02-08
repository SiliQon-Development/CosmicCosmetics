package com.siliqon.cosmiccosmetics.custom;

import com.siliqon.cosmiccosmetics.enums.EffectForm;
import com.siliqon.cosmiccosmetics.enums.EffectType;
import org.bukkit.Bukkit;

import java.util.*;

public class ActiveEffectData {

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
        if (taskIds.containsKey(form)) {
            for (int taskId : taskIds.get(form)) {
                Bukkit.getScheduler().cancelTask(taskId);
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
        taskIds.get(form).add(taskId);
    }
}
