package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ScentMask extends ArmorItem {
    public ScentMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (player.getCooldowns().isOnCooldown(ZeldaItems.SCENT_MASK.get()))
        {
            return;
        }
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.SCENT_MASK.get() && !player.isCrouching() && level.getBlockState(player.getOnPos()).is(ModTags.Blocks.SCENT) ) {

            if (!level.isRaining()){
                player.hasPose(Pose.SWIMMING);
                player.setSwimming(true);
            }


            if (level.isRaining()){
                if(new Random().nextFloat() > .9f) {
                    player.playSound(SoundEvents.PIGLIN_ADMIRING_ITEM);
player.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(6, player, (p_43296_) -> {
    p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD);});
                    player.spawnAtLocation(ZeldaItems.LIFE_MUSHROOM.get());
                   player.getCooldowns().addCooldown(ZeldaItems.SCENT_MASK.get(),70);
                }
                if(new Random().nextFloat() > .7f) {
                    player.playSound(SoundEvents.PIGLIN_ADMIRING_ITEM);
                    player.spawnAtLocation(ZeldaItems.MINI_MUSHROOM.get());
                    player.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(3, player, (p_43296_) -> {
                        p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD);});
                   player.getCooldowns().addCooldown(ZeldaItems.SCENT_MASK.get(),65);
                }
                if(new Random().nextFloat() > .6f) {
                    player.playSound(SoundEvents.PIGLIN_ADMIRING_ITEM);
                    player.spawnAtLocation(ZeldaItems.SUPER_MUSHROOM.get());
                    player.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(2, player, (p_43296_) -> {
                        p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD);});
                    player.getCooldowns().addCooldown(ZeldaItems.SCENT_MASK.get(),55);
                }
            }
        }
     }
    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer){
        return stack.getItem() == ZeldaItems.SCENT_MASK.get();
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.scent_mask.description_advanced").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.zeldamod.scent_mask.description_basic").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }}

}
