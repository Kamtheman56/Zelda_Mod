package com.kamth.zeldamod.item.items.consumables.drink;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class BluePotionItem extends DrinkItem {


    public BluePotionItem(Properties pProperties) {
        super(pProperties, "iblue");
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {

        if (!pLevel.isClientSide) {
            pEntityLiving.removeEffect(MobEffects.POISON);
            pEntityLiving.removeEffect(MobEffects.WITHER);
            pEntityLiving.removeEffect(MobEffects.HUNGER);
            pEntityLiving.removeEffect(MobEffects.WEAKNESS);
            pEntityLiving.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
            pEntityLiving.removeEffect(MobEffects.DIG_SLOWDOWN);
            pEntityLiving.removeEffect(MobEffects.BLINDNESS);
            pEntityLiving.removeEffect(MobEffects.CONFUSION);
            pEntityLiving.removeEffect(MobEffects.BAD_OMEN);
        }

        return super.finishUsingItem(pStack, pLevel, pEntityLiving);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 65;
    }
}

