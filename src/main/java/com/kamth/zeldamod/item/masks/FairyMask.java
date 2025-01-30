package com.kamth.zeldamod.item.masks;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class FairyMask extends ArmorItem {
    public FairyMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial,type, pProperties);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.fairy_mask.description_advanced_1").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.zeldamod.fairy_mask.description_advanced_2").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        }
       else {
            components.add(Component.translatable("item.zeldamod.fairy_mask.description_basic").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
    }
}
