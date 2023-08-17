package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class FireProjectile extends ThrowableProjectile {


    public FireProjectile(Level world, LivingEntity owner) {
        super(ModEntityTypes.FIRE_PROJECTILE.get(), owner, world);
    }

    public FireProjectile(EntityType<FireProjectile> fireProjectileEntityType, Level level) {
        super(fireProjectileEntityType,level);
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        if (this.level().isEmptyBlock(this.blockPosition()))
            this.level().setBlock(this.blockPosition(), Blocks.FIRE.defaultBlockState(),11);
        BlockState blockHit = this.level().getBlockState(ray.getBlockPos());
        if (blockHit.getBlock() == Blocks.ICE){
            this.level().destroyBlock(ray.getBlockPos(), false);
        }
        if (blockHit.getBlock() == Blocks.PACKED_ICE){
            this.level().destroyBlock(ray.getBlockPos(), false);
        }

        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.setSecondsOnFire(30);
        entity.hurt(damageSources().magic(),6);
        this.discard();
        this.playSound(SoundEvents.FIRECHARGE_USE);
    }

    /**
     *
     */
    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        super.tick();
    if (this.tickCount==5){
        this.discard();
    }
        int particlesDensity = 6;
        float particlesSpeed = 0.3F;
        float particlesSpread = .8F;

        for (int i = 0; i < particlesDensity; i++)
        {
            double particleX = getX() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleY = getY() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleZ = getZ() + (random.nextFloat() * 3 - 1) * particlesSpread;
            double particleMotionX = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionY = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionZ = (random.nextFloat() * 2 - 1) * particlesSpeed;
            this.level().addParticle(ParticleTypes.FLAME, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
        }
        if (this.isInFluidType()){
            this.discard();
        }
    }
    @Override
    protected float getGravity() {
        return 0.0F;
    }
}
