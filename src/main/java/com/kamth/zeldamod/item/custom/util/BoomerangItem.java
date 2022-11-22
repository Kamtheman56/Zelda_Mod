package com.kamth.zeldamod.item.custom.util;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nonnull;

public class BoomerangItem extends Item{



    public BoomerangItem(Properties p_41383_) {
        super(p_41383_);

    }

    @Override
    public boolean hurtEnemy(ItemStack stack, @Nonnull LivingEntity target, @Nonnull LivingEntity attacker) {
        stack.hurtAndBreak(2, attacker, (player) -> player.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        return true;
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, @Nonnull InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        playerIn.setItemInHand(handIn, ItemStack.EMPTY);
        int eff = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, itemstack);
        Vec3 pos = playerIn.position();
        worldIn.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ARROW_SHOOT, SoundSource.NEUTRAL, 0.5F + eff * 0.14F, 0.4F / (worldIn.random.nextFloat() * 0.4F + 0.8F));
        if(!worldIn.isClientSide) {
            Inventory inventory = playerIn.getInventory();
            int slot = handIn == InteractionHand.OFF_HAND ? inventory.getContainerSize() - 1 : inventory.selected;
        }




        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
    }


    }