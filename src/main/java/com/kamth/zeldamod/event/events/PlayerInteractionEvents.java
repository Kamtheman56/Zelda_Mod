package com.kamth.zeldamod.event.events;

import com.kamth.zeldamod.entity.mobs.KorokEntity;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.item.masks.CouplesMask;
import com.kamth.zeldamod.item.masks.GeroMask;
import com.kamth.zeldamod.item.masks.MajoraMask;
import com.kamth.zeldamod.item.masks.RomaniMask;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PlayerInteractionEvents {
    
    public static void maskInteractionEvents(PlayerInteractEvent.EntityInteract event, Player user, Entity target) {
        if (PlayerInteractionEvents.useMask(event, ZeldaItems.ROMANI_MASK.get())) {
            RomaniMask.onInteract(user, target);
        }

        if (PlayerInteractionEvents.useMask(event, ZeldaItems.GERO_MASK.get())) {
            GeroMask.onInteract(user, target);
        }

        if (PlayerInteractionEvents.useMask(event, ZeldaItems.MAJORA_MASK.get())) {
            MajoraMask.onInteract(user, target);
        }

        if (PlayerInteractionEvents.useMaskNotCreative(event, ZeldaItems.COUPLES_MASK.get())) {
            CouplesMask.onInteract(user, target);
        }
    }

    public static void interactionCraftEvents(PlayerInteractEvent.EntityInteract event, Player user, Entity target) {
        if (PlayerInteractionEvents.craftWithItemInteraction(event, Items.GOLDEN_APPLE)) {
            // Romani Mask
            if (target instanceof Cow cow) {
                PlayerInteractionEvents.eatGapple(user, (LivingEntity) target, ZeldaItems.ROMANI_MASK.get());
                target.playSound(SoundEvents.COW_AMBIENT, 1.0f, 1.9f);
                cow.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 80));
            }

            // Fairy Mask
            if (target instanceof Allay && user.isCrouching()) {
                PlayerInteractionEvents.eatGapple(user, (LivingEntity) target, ZeldaItems.FAIRY_MASK.get());
                target.playSound(SoundEvents.ALLAY_AMBIENT_WITH_ITEM, 1.0f, 1.4f);
                target.discard();
            }

            // Bunny Mask
            if (target instanceof Rabbit rabbit && rabbit.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                PlayerInteractionEvents.eatGapple(user, (LivingEntity) target, ZeldaItems.BUNNY_MASK.get());
                target.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1.0f, 1.4f);
                target.discard();
            }
        }

        if (PlayerInteractionEvents.craftWithItemInteraction(event, ZeldaItems.OCARINA.get())) {
            // Gibdo Mask
            if (target instanceof Husk husk && husk.hasEffect(MobEffects.WEAKNESS)) {
                PlayerInteractionEvents.playOcarina(husk, ZeldaItems.GIBDO_MASK.get(), SoundEvents.ITEM_FRAME_REMOVE_ITEM, ModSounds.SONG_HEALING.get());
            }

            // Korok Mask
            if (target instanceof KorokEntity korok && korok.hasEffect(MobEffects.REGENERATION)) {
                PlayerInteractionEvents.playOcarina(korok, ZeldaItems.KOROK_MASK.get(), ModSounds.KOROK_LIKES.get(), ModSounds.SONG_SARIA.get());
            }
        }

        // Gibdo Mask
        if (PlayerInteractionEvents.craftWithItemInteraction(event, Items.GOAT_HORN)) {
            if (target instanceof Husk husk && husk.hasEffect(MobEffects.WEAKNESS)) {
                PlayerInteractionEvents.createMask(user, husk, ZeldaItems.GIBDO_MASK.get());
                target.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1, 1.4f);
                target.playSound(SoundEvents.GOAT_HORN_PLAY, 1, 1.2f);
                target.discard();
            }
        }

        // Captain Mask
        if (PlayerInteractionEvents.craftWithItemInteraction(event, Items.WITHER_SKELETON_SKULL)) {
            if (target instanceof WitherSkeleton) {
                PlayerInteractionEvents.createMask(user, (LivingEntity) target, ZeldaItems.CAPTAIN_MASK.get());
                target.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1, 1.4f);
                target.playSound(SoundEvents.WITHER_SKELETON_DEATH, 1, 1.2f);
                target.discard();
            }
        }

        // Magic Boomerang
        if (PlayerInteractionEvents.craftWithItemInteraction(event, ZeldaItems.BOOMERANG.get()) && !user.isCreative()) {
            if (target instanceof Allay) {
                PlayerInteractionEvents.createMask(user, (LivingEntity) target, ZeldaItems.MAGIC_BOOMERANG.get());
                target.discard();
                target.playSound(SoundEvents.ALLAY_AMBIENT_WITH_ITEM, 1, 2);
            }
        }

        // Gibdo Mask
        if (!target.level().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && user.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof RecordItem) {
            if (target instanceof Husk husk && husk.hasEffect(MobEffects.WEAKNESS)) {
                PlayerInteractionEvents.createMask(user, husk, ZeldaItems.GIBDO_MASK.get());
                target.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1, 1.4f);
                target.playSound(ModSounds.SONG_HEALING.get(), 1, 1.3f);
                target.discard();
            }
        }
    }
    
    
    

    public static boolean craftWithItemInteraction(PlayerInteractEvent.EntityInteract event, Item item) {
        return isClient(event, item, EquipmentSlot.MAINHAND);
    }

    public static boolean useMaskNotCreative(PlayerInteractEvent.EntityInteract event, Item mask) {
        return !event.getEntity().isCreative() && useMask(event, mask);
    }

    public static boolean useMask(PlayerInteractEvent.EntityInteract event, Item mask) {
        return isClient(event, mask, EquipmentSlot.HEAD);
    }

    public static boolean isClient(PlayerInteractEvent.EntityInteract event, Item item, EquipmentSlot slot) {
        return !event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(slot).getItem() == item;
    }

    public static void createMask(Player user, LivingEntity target, Item item) {
        target.spawnAtLocation(item);
        ItemStack itemstack = user.getItemInHand(InteractionHand.MAIN_HAND);
        itemstack.shrink(1);
    }

    public static void eatGapple(Player user, LivingEntity target, Item item) {
        createMask(user, target, item);
        target.playSound(SoundEvents.HORSE_EAT, 1, 1.9f);
    }

    public static void playOcarina(LivingEntity target, Item item, SoundEvent sound, SoundEvent music) {
        target.spawnAtLocation(item);
        target.playSound(sound, 1.0f, 1.4f);
        target.playSound(music, 1.0f, 1.0f);
        target.discard();
    }
}
