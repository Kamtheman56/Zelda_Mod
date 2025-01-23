package com.kamth.zeldamod.entity.projectile.seeds;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class BombSeedProjectile extends SeedProjectile {

    private final float explosionPower;

    public BombSeedProjectile(EntityType<? extends SeedProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.explosionPower = 1.5f;
    }
    public BombSeedProjectile(Level worldIn) {
        super(ModEntityTypes.BOMB_SEED.get(), worldIn);
        this.explosionPower = 1.5f;
    }

    @Override
    public ItemStack getItem() {
        return ZeldaItems.BOMB_SEEDS.get().getDefaultInstance();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        explode();
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        explode();

        if (pResult.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) pResult.getEntity();
            target.setArrowCount(target.getArrowCount() - 1);
        }
    }

    private void explode() {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, Level.ExplosionInteraction.NONE);
        this.discard();
    }
}
