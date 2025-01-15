package com.kamth.zeldamod.item.items.consumables.jelly;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ElectricJelly extends JellyItem {

    public ElectricJelly(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity user) {
        super.finishUsingItem(pStack, pLevel, user);
        user.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120));
        return pStack;
    }
}
