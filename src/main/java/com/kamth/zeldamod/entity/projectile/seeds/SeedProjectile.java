package com.kamth.zeldamod.entity.projectile.seeds;

import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class SeedProjectile extends AbstractSeedProjectile {

    public SeedProjectile(EntityType<? extends AbstractSeedProjectile> pEntityType, Level level) {
        super(pEntityType, level);
    }

    public SeedProjectile(LivingEntity pShooter, Level pLevel) {
        super(ModEntityTypes.SEED_PROJECTILE.get(), pShooter, pLevel);
    }

    @Override
    protected double getDamage() {
        return 4.0;
    }

    @Override
    public ItemStack getItem() {
        return Items.WHEAT_SEEDS.getDefaultInstance();
    }
}
