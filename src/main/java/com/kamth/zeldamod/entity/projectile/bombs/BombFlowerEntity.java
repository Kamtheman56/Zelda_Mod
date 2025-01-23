package com.kamth.zeldamod.entity.projectile.bombs;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BombFlowerEntity extends AbstractBombEntity {
    public BombFlowerEntity(EntityType<BombFlowerEntity> bombProjectileEntityType, Level level) {
        super(bombProjectileEntityType, level);
    }

    public BombFlowerEntity(LivingEntity pShooter, Level pLevel, boolean bowled) {
        super(ModEntityTypes.BOMB_FLOWER.get(), pShooter, pLevel, 80, 2, false, bowled);
    }

    @Override
    public ItemStack getItem() {
        return ZeldaItems.BOMB_FLOWER.get().getDefaultInstance();
    }
}






