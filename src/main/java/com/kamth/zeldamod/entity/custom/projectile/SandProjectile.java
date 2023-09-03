package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class SandProjectile extends ThrowableProjectile {


    public SandProjectile(Level world, LivingEntity owner) {
        super(ModEntityTypes.SAND_PROJECTILE.get(), owner, world);
    }

    public SandProjectile(EntityType<SandProjectile> gustProjectileEntityType, Level level) {
        super(gustProjectileEntityType,level);

    }




    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);

        BlockState blockHit = this.level().getBlockState(ray.getBlockPos());
        if (!blockHit.is(BlockTags.SAND)    ){
            this.discard();
        }

    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
    this.discard();
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
    if (this.tickCount==10){
        this.discard();
    }
if (this.level().isEmptyBlock(this.blockPosition())){
    level().setBlockAndUpdate(this.blockPosition(), Blocks.SAND.defaultBlockState());
}


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
            this.level().addParticle(ParticleTypes.CLOUD, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
        }

        if (this.isInFluidType()){
            this.discard();
        }
        this.clearFire();
    }
    @Override
    protected float getGravity() {
        return 0.0F;
    }
}
