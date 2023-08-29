package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.custom.projectile.BoomerangProjectile;
import com.kamth.zeldamod.entity.custom.projectile.MagicBoomerangProjectile;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MagicBoomerangItem extends Item {
    public MagicBoomerangItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 1F, -0.2F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            player.getCooldowns().addCooldown(this, 35);
            MagicBoomerangProjectile boomerang = new  MagicBoomerangProjectile(ModEntityTypes.MAGIC_BOOMERANG.get(), pLevel, player);
            boomerang.shootFromRotation(player, player.getXRot(), player.getYRot(), 1, 1.6F, 0.9F);
            boomerang.setThrowData(pHand.ordinal(), itemstack);
            pLevel.addFreshEntity(boomerang);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}



