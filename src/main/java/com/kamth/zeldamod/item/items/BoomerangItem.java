package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.BombProjectile;
import com.kamth.zeldamod.entity.custom.projectile.BoomerangProjectile;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BoomerangItem extends Item {
    public BoomerangItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 1F, 0.2F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            BoomerangProjectile bombEntity = new BoomerangProjectile(pLevel,player);
            bombEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 1, 1.3F, 0.9F);
            pLevel.addFreshEntity(bombEntity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }
        if (player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}



