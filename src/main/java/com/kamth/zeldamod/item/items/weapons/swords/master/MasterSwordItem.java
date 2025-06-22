package com.kamth.zeldamod.item.items.weapons.swords.master;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.enchantments.ZeldaEnchantments;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.item.ModTiers;
import com.kamth.zeldamod.item.items.weapons.swords.GloomBreakingSword;
import com.kamth.zeldamod.util.interfaces.item.IBeamShootAction;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class MasterSwordItem extends GloomBreakingSword implements IBeamShootAction {

    public MasterSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, 15);
    }

    @Override
    public boolean healthRequirement() {
        return true;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pStack.getDamageValue() < pStack.getMaxDamage() - 1) {
            super.hurtEnemy(pStack, pTarget, pAttacker);
        } else {
            ItemStack newItemStack = new ItemStack(ZeldaItems.MASTER_SWORD_INJURED.get());
            pAttacker.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
            pAttacker.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        }
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (pState.is(ModTags.Blocks.DEMON) && this.getTier() != ModTiers.MASTER_TRUE) {
            pStack.hurtAndBreak(3, pEntityLiving, (p_43276_) -> p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            if (!(pStack.getDamageValue() < pStack.getMaxDamage() - 2)) {
                ItemStack newItemStack = new ItemStack(ZeldaItems.MASTER_SWORD_INJURED.get());
                pEntityLiving.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
                pEntityLiving.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            }
        }
        return true;
    }



    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.master_sword.description_advanced").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.zeldamod.master_sword.description_basic_2").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
        else {
            components.add(Component.translatable("item.zeldamod.master_sword.description_basic").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }

    }
}

