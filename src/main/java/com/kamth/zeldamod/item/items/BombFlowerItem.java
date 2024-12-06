package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.BombFlower;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BombFlowerItem extends BombItem {
    public BombFlowerItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 1F, 0.7F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            BombFlower bombEntity = new BombFlower(pLevel,player);
            bombEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 1, 1.25F, 0.86F);
            pLevel.addFreshEntity(bombEntity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            if (player.getItemInHand(pHand).is(ModItems.BOMB.get())) {
                itemstack.shrink(1);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}

