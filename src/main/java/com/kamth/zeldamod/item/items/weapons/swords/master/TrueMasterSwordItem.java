package com.kamth.zeldamod.item.items.weapons.swords.master;

import com.kamth.zeldamod.item.items.weapons.swords.GloomBreakingSword;
import com.kamth.zeldamod.util.interfaces.item.IBeamShootAction;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class TrueMasterSwordItem extends GloomBreakingSword implements IBeamShootAction {

    public TrueMasterSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, int gloomDestroySpeed) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, gloomDestroySpeed);
    }

    public TrueMasterSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        this(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, 28);
    }

    @Override
    public boolean healthRequirement() {
        return true;
    }

    @Override
    public int swingCooldownDuration() {
        return 15;
    }

    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.master_sword_true.description_advanced").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.zeldamod.master_sword.description_basic_2").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.zeldamod.master_sword_true.description_basic_1").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.zeldamod.master_sword_true.description_basic_2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }
    }
}


