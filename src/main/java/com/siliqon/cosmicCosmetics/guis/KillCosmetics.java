package com.siliqon.cosmicCosmetics.guis;

import com.siliqon.cosmicCosmetics.CosmicCosmetics;
import com.siliqon.cosmicCosmetics.data.EffectForm;
import com.siliqon.cosmicCosmetics.data.EffectType;
import com.siliqon.cosmicCosmetics.utils.gui.InventoryButton;
import com.siliqon.cosmicCosmetics.utils.gui.InventoryGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

import static com.siliqon.cosmicCosmetics.utils.general.effects.*;
import static com.siliqon.cosmicCosmetics.utils.general.gui.*;
import static com.siliqon.cosmicCosmetics.utils.general.gui.makeSimpleItem;
import static com.siliqon.cosmicCosmetics.utils.general.messaging.sendMessage;
import static com.siliqon.cosmicCosmetics.utils.general.misc.checkPlayerPermission;

public class KillCosmetics extends InventoryGUI {
    private static final CosmicCosmetics plugin = CosmicCosmetics.getInstance();
    private final Material backgroundMaterial = Material.GRAY_STAINED_GLASS_PANE;

    EffectType activeEffect;
    public KillCosmetics() {
        createInventory();
    }

    @Override
    protected void createInventory() {
        this.inventory = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', plugin.lang.getKillEffectsMenuName()));
    }

    @Override
    public void decorate(Player player) {
        clearButtons(true);

        setMenuBackground(inventory, backgroundMaterial, 0, 27, " ");
        activeEffect = getActiveEffect(player, EffectForm.KILL);

        int slot = 9;
        for (EffectType effectType : getFormEffects(EffectForm.KILL)) {
            if (effectType == null) continue;

            String name = getEffectDisplayName(effectType);
            List<String> description = getEffectDescription(effectType);
            Material material = getEffectDisplayMaterial(effectType);

            if (material == Material.AIR || description.isEmpty() || Objects.equals(name, "NONE")) continue;

            if (effectType == activeEffect) {
                name = plugin.lang.getEffectSelectedName().replace("%effect_name%", name);
            }

            if (checkPlayerPermission(player, "cosmetics.kill."+effectType.name().toLowerCase())) {
                this.addButton(slot, selectEffectButton(material, effectType, name, description));
            } else {
                this.addButton(slot, lockedEffectButton(name));
            }
            slot++;
        }

        this.addButton(18, backButton());
        this.addButton(22, resetEffectButton());

        decorateButtons(player);
    }

    private InventoryButton selectEffectButton(Material material, EffectType effectType, String name, List<String> description) {
        return new InventoryButton()
                .creator(player -> makeItemWithLore(material, name, 1, description))
                .consumer(event -> {
                    if (activeEffect == effectType) {
                        removeActiveEffect(player, EffectForm.KILL);
                        sendMessage(player, plugin.lang.getEffectDisabled().replace("%effect_name%", getEffectDisplayName(effectType)), true);
                    } else {
                        setActiveEffect(player, EffectForm.KILL, effectType);
                        sendMessage(player, plugin.lang.getEffectEnabled().replace("%effect_name%", getEffectDisplayName(effectType)), true);
                    }
                    decorate(player);
                });
    }

    private InventoryButton lockedEffectButton(String name) {
        return new InventoryButton()
                .creator(player -> makeSimpleItem(Material.BARRIER, plugin.lang.getEffectNotUnlockedName().replace("%effect_name%", name), 1));
    }

    private InventoryButton resetEffectButton() {
        return new InventoryButton()
                .creator(player -> makeSimpleItem(Material.REDSTONE_TORCH, plugin.lang.getResetEffectItemName(), 1))
                .consumer(event -> {
                    removeActiveEffect(player, EffectForm.KILL);
                    if (activeEffect != null) {
                        sendMessage(player, plugin.lang.getEffectDisabled().replace("%effect_name%", getEffectDisplayName(activeEffect)), true);
                        decorate(player);
                    }
                });
    }

    private InventoryButton backButton() {
        return new InventoryButton()
                .creator(player -> makeSimpleItem(Material.ARROW, plugin.lang.getBackButtonName(), 1))
                .consumer(event -> {
                    MainWindow mainWindow = new MainWindow();
                    plugin.guiManager.openGUI(mainWindow, player);
                });
    }
}
