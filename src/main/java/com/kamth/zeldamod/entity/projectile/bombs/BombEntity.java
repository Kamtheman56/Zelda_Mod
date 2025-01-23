package com.kamth.zeldamod.entity.projectile.bombs;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BombEntity extends AbstractBombEntity {
    public BombEntity(EntityType<BombEntity> bombProjectileEntityType, Level level) {
        super(bombProjectileEntityType, level);
    }

    public BombEntity(LivingEntity pShooter, Level pLevel, boolean bowled) {
        super(ModEntityTypes.BOMB.get(), pShooter, pLevel, 100, 3, false, bowled);
    }

    @Override
    public ItemStack getItem() {
        return ZeldaItems.BOMB.get().getDefaultInstance();
    }
}






