package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class RazorSwordItem extends SwordItem {
    public RazorSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        if(pStack.getDamageValue() < pStack.getMaxDamage() - 2) {
        }
        else {
            ItemStack	newItemStack = new ItemStack(ModItems.KOKIRI_SWORD2.get());

            pAttacker.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
            pAttacker.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        }
        return true;
    }



    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {

            pStack.hurtAndBreak(3, pEntityLiving, (p_43276_) -> p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            if(pStack.getDamageValue() < pStack.getMaxDamage() - 3) {
            }
            else {
                ItemStack	newItemStack = new ItemStack(ModItems.KOKIRI_SWORD2.get());

                pEntityLiving.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
                pEntityLiving.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            }
            return true;
        }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.razor_sword.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }

}

