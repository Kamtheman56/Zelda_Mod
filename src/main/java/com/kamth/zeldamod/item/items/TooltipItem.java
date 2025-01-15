package com.kamth.zeldamod.item.items;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class TooltipItem extends Item {

    private final Style style;
    private final boolean hidden;

    public TooltipItem(Properties pProperties, Style style, boolean hidden) {
        super(pProperties);
        this.style = style;
        this.hidden = hidden;
    }

    public TooltipItem(Properties pProperties, Style style) {
        this(pProperties, style, true);
    }

    public TooltipItem(Properties pProperties) {
        this(pProperties, Style.EMPTY, true);
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
