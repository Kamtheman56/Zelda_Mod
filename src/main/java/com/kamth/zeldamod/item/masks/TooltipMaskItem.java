package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.util.ZeldaTooltipUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class TooltipMaskItem extends ArmorItem {

    private final String style;

    public TooltipMaskItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, String style) {
        super(pMaterial, pType, pProperties);
        this.style = style;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.addAll(ZeldaTooltipUtil.createSingleTooltip(stack, false, this.style));
    }
}
