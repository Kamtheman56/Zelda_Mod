package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ScentMask extends ArmorItem {
    public ScentMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (player.getCooldowns().isOnCooldown(ModItems.SCENT_MASK.get()))
        {
            return;
        }
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.SCENT_MASK.get() && !player.isCrouching() && level.getBlockState(player.getOnPos()).is(ModTags.Blocks.SCENT) ) {

            if (!level.isRaining()){
                player.hasPose(Pose.SWIMMING);
                player.setSwimming(true);
            }


            if (level.isRaining()){
                if(new Random().nextFloat() > .9f) {
                    player.playSound(SoundEvents.PIGLIN_ADMIRING_ITEM);
player.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(6, player, (p_43296_) -> {
    p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD);});
                    player.spawnAtLocation(ModItems.LIFE_MUSHROOM.get());
                   player.getCooldowns().addCooldown(ModItems.SCENT_MASK.get(),70);
                };
                if(new Random().nextFloat() > .7f) {
                    player.playSound(SoundEvents.PIGLIN_ADMIRING_ITEM);
                    player.spawnAtLocation(ModItems.MINI_MUSHROOM.get());
                    player.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(3, player, (p_43296_) -> {
                        p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD);});
                   player.getCooldowns().addCooldown(ModItems.SCENT_MASK.get(),65);
                }
                if(new Random().nextFloat() > .6f) {
                    player.playSound(SoundEvents.PIGLIN_ADMIRING_ITEM);
                    player.spawnAtLocation(ModItems.SUPER_MUSHROOM.get());
                    player.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(2, player, (p_43296_) -> {
                        p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD);});
                    player.getCooldowns().addCooldown(ModItems.SCENT_MASK.get(),55);
                }
            }
        }
     }
    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer){
        return stack.getItem() == ModItems.SCENT_MASK.get();
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.literal("Try digging around in the dirt when it's raining").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.literal("Smells like mushrooms").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }}

}
