package com.kamth.zeldamod.item.items.bombs;

import com.kamth.zeldamod.entity.projectile.bombs.bombchu.BombchuEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BombchuItem extends Item {

    public BombchuItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {

        if (!pLevel.isClientSide) {

            BombchuEntity bombEntity = new BombchuEntity(pLevel, player.getX(), player.getY(), player.getZ(), player);

            Vec3 vec3d = player.getViewVector(1);
            bombEntity.setDeltaMovement(vec3d.x, vec3d.y, vec3d.z);
            bombEntity.setYRot(player.getYHeadRot());
            pLevel.addFreshEntity(bombEntity);

            player.playNotifySound(SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 1, 1);
        }
        if (player.getItemInHand(pHand).getItem() instanceof BombchuItem) {
            player.getItemInHand(pHand).shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(pHand), pLevel.isClientSide());
    }
}

