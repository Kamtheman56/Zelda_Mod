package com.kamth.zeldamod.item.items.movement;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

import javax.annotation.Nullable;
import java.util.List;

public class ScaleItem extends Item {
    public ScaleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
        if ( player.isInWater() && itemstack.is(ZeldaItems.SILVER_SCALE.get())) {
            Vec3 vec3 = player.getDeltaMovement();
           // player.startUsingItem(pHand);
            itemstack.hurtAndBreak(3, player, (p_43296_) -> {
                p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                p_43296_.broadcastBreakEvent(EquipmentSlot.OFFHAND);
            });
            float f7 = player.getYRot();
            float f = player.getXRot();
            float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
            float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
            float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
            float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
            float f5 = 3.5F * ((1.0F + (float) 1) / 4.0F);
            f1 *= f5 / f4;
            f2 *= f5 / f4;
            f3 *= f5 / f4;
            player.push((double) f1, (double) f2, (double) f3);
            player.startAutoSpinAttack(20);
            player.getCooldowns().addCooldown(itemstack.getItem(), 40);
            return InteractionResultHolder.success(itemstack);
        }
        if ( player.isInWater() && itemstack.is(ZeldaItems.GOLDEN_SCALE.get())) {
            Vec3 vec3 = player.getDeltaMovement();
            itemstack.hurtAndBreak(5, player, (p_43296_) -> {
                p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                p_43296_.broadcastBreakEvent(EquipmentSlot.OFFHAND);
            });
            // player.startUsingItem(pHand);
            float f7 = player.getYRot();
            float f = player.getXRot();
            float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
            float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
            float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
            float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
            float f5 = 4.5F * ((1.0F + (float) 1) / 4.0F);
            f1 *= f5 / f4;
            f2 *= f5 / f4;
            f3 *= f5 / f4;
            player.push((double) f1, (double) f2, (double) f3);
            player.startAutoSpinAttack(30);
            player.getCooldowns().addCooldown(itemstack.getItem(), 60);
            return InteractionResultHolder.success(itemstack);
        }

        else return InteractionResultHolder.fail(itemstack);
    }
    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int pSlotId, boolean pIsSelected) {
        if (!world.isClientSide) {
            if (entity instanceof Player && ((Player) entity).getOffhandItem().getItem() == ZeldaItems.SILVER_SCALE.get() && !entity.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) ) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 90, 0, true, false));
            }
            if (entity instanceof Player && ((Player) entity).getMainHandItem().getItem() == ZeldaItems.SILVER_SCALE.get() &&  !entity.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) ) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 90, 0, true, false));
            }
            if (entity instanceof Player && ((Player) entity).getOffhandItem().getItem() == ZeldaItems.GOLDEN_SCALE.get() && !entity.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) ) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 185, 0, true, false));
            }
            if (entity instanceof Player && ((Player) entity).getMainHandItem().getItem() == ZeldaItems.GOLDEN_SCALE.get() &&  !entity.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) ) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 185, 0, true, false));
            }


        }

    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.scale.description_advanced_1").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.scale.description_advanced_2").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        }
        else   components.add(Component.translatable("item.scale.description_basic").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));

        super.appendHoverText(stack, level, components, flag);
    }

}






