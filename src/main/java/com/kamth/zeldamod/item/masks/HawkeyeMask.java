package com.kamth.zeldamod.item.masks;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.ComputeFovModifierEvent;

import javax.annotation.Nullable;
import java.util.List;

public class HawkeyeMask extends TooltipMaskItem {
    public HawkeyeMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties, Style.EMPTY.withColor(ChatFormatting.YELLOW).withItalic(true));
    }

    public static void hawkeyeZoom(ComputeFovModifierEvent event) {

    }
}
