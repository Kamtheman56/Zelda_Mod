package com.kamth.zeldamod.item.items.consumables.drink;

import com.kamth.zeldamod.effect.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class GloomResistPotionItem extends DrinkItem {

    public GloomResistPotionItem(Properties pProperties) {
        super(pProperties, Style.EMPTY.withItalic(true).withColor(ChatFormatting.GOLD));
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 32;
    }

}
