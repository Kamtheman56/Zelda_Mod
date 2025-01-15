package com.kamth.zeldamod.item.masks;

import net.minecraft.client.gui.screens.Screen;
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

    private final Style style;
    private final boolean hidden;

    public TooltipMaskItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, Style style, boolean hidden) {
        super(pMaterial, pType, pProperties);
        this.style = style;
        this.hidden = hidden;
    }

    public TooltipMaskItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, Style style) {
        this(pMaterial, pType, pProperties, style, false);
    }

    public TooltipMaskItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        this(pMaterial, pType, pProperties, Style.EMPTY, false);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (this.hidden) {
            if (Screen.hasShiftDown()) {
                components.add(Component.translatable(stack.getDescriptionId() + ".description").setStyle(this.style));
            }
            return;
        }
        components.add(Component.translatable(stack.getDescriptionId() + ".description").setStyle(this.style));
    }
}
