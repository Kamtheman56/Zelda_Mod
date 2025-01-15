package com.kamth.zeldamod.item.items.consumables.jelly;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IceJellyItem extends JellyItem {

    public IceJellyItem(Properties pProperties) {
        super(pProperties);
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity user) {
        super.finishUsingItem(pStack, pLevel, user);
        user.setRemainingFireTicks(0);
        return pStack;
    }

}
