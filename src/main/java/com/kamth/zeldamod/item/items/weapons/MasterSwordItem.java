package com.kamth.zeldamod.item.items.weapons;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.custom.projectile.SwordBeam;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.item.ModTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class MasterSwordItem extends SwordItem {
    public MasterSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(ModTags.Blocks.DEMON) ) {   return 18.0F;
        }
      else return 1;
    }
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if(pStack.getDamageValue() < pStack.getMaxDamage() - 1) {
            super.hurtEnemy(pStack, pTarget, pAttacker);
        }
        else {
            ItemStack	newItemStack = new ItemStack(ZeldaItems.MASTER_SWORD_INJURED.get());
            pAttacker.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
            pAttacker.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        }
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (pState.is(ModTags.Blocks.DEMON) && this.getTier() != ModTiers.MASTER_TRUE) {
            pStack.hurtAndBreak(3, pEntityLiving, (p_43276_) -> p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            if(pStack.getDamageValue() < pStack.getMaxDamage() - 2) {
            }
            else {
                ItemStack	newItemStack = new ItemStack(ZeldaItems.MASTER_SWORD_INJURED.get());
                pEntityLiving.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
                pEntityLiving.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            }
            return true;
        }


        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
        if (!pLevel.isClientSide && player.isCrouching() && player.getHealth() >= player.getMaxHealth() ||  player.isCrouching() && player.getAbilities().instabuild) {
        player.getCooldowns().addCooldown(this, 30);
        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, .8F, 5F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            SwordBeam projectile = new SwordBeam(pLevel,player);
            projectile.setOwner(player);
            projectile.setPos(player.getEyePosition(1F).add(0, -0.1, 0));
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.6f,0f);
            pLevel.addFreshEntity(projectile);
        }
        else {
            return InteractionResultHolder.pass(itemstack);
        }


        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.master_sword.description_advanced").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.master_sword.description_basic").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }

    }

}

