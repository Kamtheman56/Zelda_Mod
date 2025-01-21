package com.kamth.zeldamod.item.items.weapons;

import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.item.items.weapons.swords.TooltipSword;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class RazorSwordItem extends TooltipSword {

    private final Item swordChild;

    public RazorSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, Item swordChild) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, Style.EMPTY.withItalic(true));
        this.swordChild = swordChild;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        damageSword(pStack, pAttacker, 1);
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        damageSword(pStack, pEntityLiving, 3);
        return true;
    }

    public void damageSword(ItemStack stack, LivingEntity user, int damage) {
        stack.hurtAndBreak(damage, user, (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));

        if(!(stack.getDamageValue() < stack.getMaxDamage() - 3)) {
            ItemStack newItemStack = new ItemStack(this.swordChild);

            user.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
            user.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        }
    }
}

