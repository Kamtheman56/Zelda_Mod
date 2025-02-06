package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.util.ZeldaTooltipUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class TooltipItem extends Item {

    private final String style;
    private final boolean hidden;

    public TooltipItem(Properties pProperties, String style, boolean hidden) {
        super(pProperties);
        this.style = style;
        this.hidden = hidden;
    }

    public TooltipItem(Properties pProperties, String style) {
        this(pProperties, style, true);
    }

    public TooltipItem(Properties pProperties) {
        this(pProperties, "", true);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.addAll(ZeldaTooltipUtil.createSingleTooltip(stack, this.hidden, this.style));
    }
}
