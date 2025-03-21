package com.kamth.zeldamod.item.masks.transformation;


import be.florens.expandability.api.forge.LivingFluidCollisionEvent;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.List;


public class DekuMask extends ArmorItem {
    public DekuMask(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingFluidCollisionEvent);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
    }

    public void onLivingFluidCollisionEvent(LivingFluidCollisionEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.DEKU_MASK.get()) {
            if (event.getEntity().isSprinting()) {
                if (event.getEntity().hasEffect(ModEffects.DEKU.get()))
                    event.setResult(Event.Result.ALLOW);
                event.getEntity().shouldDiscardFriction();
            }
        }
    }


    public void onLivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.DEKU_MASK.get()) {
            if (event.getSource().is(DamageTypes.ON_FIRE)) {
                event.setAmount(event.getAmount() * 2);}
            if (event.getSource().is(DamageTypes.IN_FIRE)) {
                event.setAmount(event.getAmount() * 2);}
            if (event.getSource().is(DamageTypes.LAVA)) {
                event.setAmount(event.getAmount() * 3);}
            if (event.getSource().is(DamageTypes.DROWN)) {
                event.setAmount(event.getAmount() * 4);}
            if (event.getSource().is(DamageTypes.CACTUS)) {
                event.setResult(Event.Result.DENY);}
        }
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 1, true, false, false));
        if (player.isCrouching()) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 2, true, false));
        }

        Level level = world;
        if (level.getBlockState(player.getOnPos()).is(ModTags.Blocks.DEKU)) {
            if (player.isCrouching()) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 6, true, false));
            }
        }
        if (level.getBlockState(player.getOnPos()).is(ModTags.Blocks.DEKU_GOLD)) {
            if (player.isCrouching()) {
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 9, true, false));
            }
        }
        if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.LILY_PAD) {
            player.addEffect((new MobEffectInstance(ModEffects.DEKU.get(), 80, 0, true, false)));
        }
        //if not standing on water or in water, get effect. Else get nothing
        if (player.onGround() && level.getBlockState(player.getOnPos().below(1)).getBlock() != Blocks.WATER) {
            player.addEffect(((new MobEffectInstance(ModEffects.DEKU.get(), 80, 0, true, false))));
        }
        if (level.getBlockState(player.getOnPos().below(0)).getBlock() == Blocks.LAVA) {
            player.removeEffect(ModEffects.DEKU.get());
        }
        else if (player.isSwimming()){
            player.removeEffect(ModEffects.DEKU.get());
        }

    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.deku_mask.description_advanced").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.zeldamod.deku_mask.description_basic").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.ITALIC));

        }
    }
}















