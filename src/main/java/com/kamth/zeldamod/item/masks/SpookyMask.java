package com.kamth.zeldamod.item.masks;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class SpookyMask extends ArmorItem {
    public SpookyMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial,type, pProperties);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("item.spooky_mask.description").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
    }
}
