package com.kamth.zeldamod.event.events;

import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class MajoraMaskFlight {

    public static void tickFlight(TickEvent.PlayerTickEvent event) {
        Item mask = ZeldaItems.MAJORA_MASK.get();
        Player player = event.player;
        CompoundTag tag = player.getPersistentData();
        boolean wasFlying = tag.getBoolean("pastflight");
        if (!player.isCreative() && !player.isSpectator()) {
            if ((player.hasEffect(ModEffects.MAJORA.get()) || equipped(player, EquipmentSlot.HEAD, mask))) {
                if (!player.getAbilities().mayfly) {
                    player.getAbilities().mayfly = true;
                    tag.putBoolean("pastflight", true);
                    player.onUpdateAbilities();
                }
                if ((player.onGround() || player.isInWaterOrBubble() && equipped(player, EquipmentSlot.HEAD, mask))) {
                    player.getAbilities().flying = false;
                    player.onUpdateAbilities();
                }
            } else if (wasFlying && (!player.hasEffect(ModEffects.MAJORA.get()) || !equipped(player, EquipmentSlot.HEAD, mask))) {
                player.getAbilities().mayfly = false;
                player.getAbilities().flying = false;
                tag.putBoolean("pastflight", false);
                player.onUpdateAbilities();
            }
        }
    }

    public static void addFlightNBT(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        CompoundTag tag = player.getPersistentData();
        if (tag.get("pastflight") == null) {
            tag.putBoolean("pastflight", false);
        }
        if (tag.get("activeflight") == null) {
            tag.putInt("activeflight", 0);
        }
    }

    private static boolean equipped(LivingEntity player, EquipmentSlot slot, Item item) {
        return item == player.getItemBySlot(slot).getItem();
    }
}
