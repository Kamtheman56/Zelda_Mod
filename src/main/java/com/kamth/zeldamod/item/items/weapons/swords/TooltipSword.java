package com.kamth.zeldamod.item.items.weapons.swords;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class TooltipSword extends SwordItem {

    private final Style style;
    private final boolean hidden;

    public TooltipSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, Style style, boolean hidden) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.style = style;
        this.hidden = hidden;
    }

    public TooltipSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, Style style) {
        this(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, style, true);
    }

    public TooltipSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        this(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, Style.EMPTY, true);
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
