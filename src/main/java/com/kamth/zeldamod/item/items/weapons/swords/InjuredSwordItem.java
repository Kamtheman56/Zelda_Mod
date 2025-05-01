package com.kamth.zeldamod.item.items.weapons.swords;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class InjuredSwordItem extends GloomBreakingSword {
    public InjuredSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, 15);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pStack.getDamageValue() < pStack.getMaxDamage() - 2) {
            pStack.hurtAndBreak(3, pAttacker, (p_43276_) -> p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        } else {
            ItemStack newItemStack = new ItemStack(ZeldaItems.MASTER_SWORD_DAGGER.get());
            pAttacker.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
            pAttacker.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        }
        return true;
    }


    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (pState.is(ModTags.Blocks.DEMON)) {
            pStack.hurtAndBreak(6, pEntityLiving, (p_43276_) -> p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            if (pStack.getDamageValue() < pStack.getMaxDamage() - 2) {
                pStack.hurtAndBreak(1, pEntityLiving, (p_43276_) -> p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            } else {
                ItemStack newItemStack = new ItemStack(ZeldaItems.MASTER_SWORD_DAGGER.get());
                pEntityLiving.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
                pEntityLiving.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            }
            return true;
        }


        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        if (blockstate.is(ModTags.Blocks.SACRED_FLAMES)) {
            pContext.getItemInHand().shrink(1);
            pContext.getPlayer().setItemInHand(pContext.getHand(), ZeldaItems.MASTER_SWORD.get().getDefaultInstance());
            pContext.getLevel().playSound(pContext.getPlayer(), blockpos, ModSounds.SWORD_ENCHANT.get(), SoundSource.BLOCKS, 1, 1f);
            return InteractionResult.SUCCESS;
        } else return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.injured_sword.description_advanced").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.zeldamod.injured_sword.description_basic_1").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.zeldamod.injured_sword.description_basic_2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }

    }

}

