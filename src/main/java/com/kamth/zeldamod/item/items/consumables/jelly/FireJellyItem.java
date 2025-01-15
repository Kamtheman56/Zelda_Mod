package com.kamth.zeldamod.item.items.consumables.jelly;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FireJellyItem extends JellyItem {

    public FireJellyItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity user) {
        super.finishUsingItem(pStack, pLevel, user);
        user.setTicksFrozen(0);
        return pStack;
    }

}
