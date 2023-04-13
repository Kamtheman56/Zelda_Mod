package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class IceProjectile extends ThrowableProjectile {


    public IceProjectile(Level world, LivingEntity owner) {
        super(ModEntityTypes.ICE_PROJECTILE.get(), owner, world);
    }

    public IceProjectile(EntityType<IceProjectile> iceProjectileEntityType, Level level) {
        super(iceProjectileEntityType,level);
    }

        @Override
        protected void onHitBlock(@NotNull BlockHitResult ray) {
            super.onHitBlock(ray);
            BlockState blockHit = level.getBlockState(ray.getBlockPos());
            if (level.isEmptyBlock(this.blockPosition())){
                level.setBlockAndUpdate(this.blockPosition(), Blocks.SNOW.defaultBlockState());}
            this.discard();
        }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.setTicksFrozen(40);
        entity.hurt(DamageSource.MAGIC,6);
        this.playSound(SoundEvents.SNOW_BREAK);
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
    if (this.tickCount==35){
        this.discard();
    }
        if (this.isInWater()){
            level.setBlockAndUpdate(this.blockPosition(), Blocks.FROSTED_ICE.defaultBlockState());
            this.discard();}
        if (this.isInLava()){
            level.setBlockAndUpdate(this.blockPosition(), Blocks.FROSTED_ICE.defaultBlockState());
            this.discard();}
        int particlesDensity = 3;
        float particlesSpeed = 0.1F;
        float particlesSpread = 0.3F;

        for (int i = 0; i < particlesDensity; i++)
        {
            double particleX = getX() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleY = getY() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleZ = getZ() + (random.nextFloat() * 3 - 1) * particlesSpread;
            double particleMotionX = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionY = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionZ = (random.nextFloat() * 2 - 1) * particlesSpeed;
            level.addParticle(ParticleTypes.SNOWFLAKE, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
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
