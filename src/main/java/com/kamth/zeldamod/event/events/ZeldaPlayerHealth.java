package com.kamth.zeldamod.event.events;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class ZeldaPlayerHealth {

    public static final UUID BASE_HEALTH_UUID = UUID.fromString("6ed6de9f-a743-4bee-8e59-8a56d54bb054");
    public static final UUID HEALTH_UUID = UUID.fromString("3dc4214d-14eb-455c-9700-a2ab1433dfcc");

    public static void adjustBaseHealth(Player player) {
        AttributeInstance maxHPAttrib = getMaxHealthAttribute(player);

        AttributeModifier baseHealthModifier =
                new AttributeModifier(BASE_HEALTH_UUID, "Base", 2, AttributeModifier.Operation.ADDITION);

        if (!maxHPAttrib.hasModifier(baseHealthModifier)) {
            maxHPAttrib.addPermanentModifier(baseHealthModifier);
        } else {
            AttributeModifier oldModifier = maxHPAttrib.getModifier(BASE_HEALTH_UUID);
            Objects.requireNonNull(oldModifier);

            if (oldModifier.getAmount() != baseHealthModifier.getAmount()) {
                maxHPAttrib.removeModifier(BASE_HEALTH_UUID);
                maxHPAttrib.addPermanentModifier(baseHealthModifier);
            }
        }

        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }

    public static void reapplyHealthModifiers(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            return;
        }

        AttributeInstance maxHPAttrib = getMaxHealthAttribute(event.getOriginal());
        AttributeModifier modifier = maxHPAttrib.getModifier(HEALTH_UUID);
        if (modifier != null) {

            AttributeInstance cloneMaxHealth = getMaxHealthAttribute(event.getEntity());
            cloneMaxHealth.addPermanentModifier(modifier);

            event.getEntity().setHealth(event.getEntity().getMaxHealth());
        }
    }

    public static double getBaseHealth(Player player) {
        AttributeInstance maxHPAttrib = getMaxHealthAttribute(player);
        double baseHealth = maxHPAttrib.getBaseValue();

        AttributeModifier heartsModifier = maxHPAttrib.getModifier(HEALTH_UUID);
        if (heartsModifier != null) {
            baseHealth += heartsModifier.getAmount();
        }
        AttributeModifier baseModifier = maxHPAttrib.getModifier(BASE_HEALTH_UUID);
        if (baseModifier != null) {
            baseHealth += baseModifier.getAmount();
        }
        return baseHealth;
    }

    public static void addBaseHealthModifier(Player player, float amount) {
        AttributeInstance maxHPAttrib = getMaxHealthAttribute(player);
        AttributeModifier modifier = maxHPAttrib.getModifier(HEALTH_UUID);

        if (modifier == null) {
            modifier = new AttributeModifier(HEALTH_UUID, "Hearts", amount, AttributeModifier.Operation.ADDITION);
        } else {
            maxHPAttrib.removeModifier(modifier);
            modifier = new AttributeModifier(
                    HEALTH_UUID,
                    "Hearts",
                    modifier.getAmount() + amount,
                    AttributeModifier.Operation.ADDITION
            );
        }

        maxHPAttrib.addPermanentModifier(modifier);

        if (amount > 0) {
            player.heal(amount);
        } else if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }

    @NotNull
    static AttributeInstance getMaxHealthAttribute(Player player) {
        AttributeInstance maxHPAttrib = player.getAttribute(Attributes.MAX_HEALTH);
        return Objects.requireNonNull(maxHPAttrib);
    }

    public static boolean canIncreaseGoldHealth(Player player) {
        return getBaseHealth(player) < 60;
    }

    public static boolean canIncreaseBaseHealth(Player player) {
        return getBaseHealth(player) < 40;
    }

    public static boolean canDecreaseBaseHealth(Player player) {
        return getBaseHealth(player) > 2;
    }
}