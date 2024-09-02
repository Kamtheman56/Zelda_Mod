package com.kamth.zeldamod.item.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class GloomWeaponItem extends SwordItem {
    public GloomWeaponItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.gloom_sword.description_advanced").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.gloom_sword.description_basic").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        }

    }

}

