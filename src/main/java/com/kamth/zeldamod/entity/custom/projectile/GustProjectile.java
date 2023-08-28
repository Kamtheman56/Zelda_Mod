package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class GustProjectile extends ThrowableProjectile {


    public GustProjectile(Level world, LivingEntity owner) {
        super(ModEntityTypes.GUST_PROJECTILE.get(), owner, world);
    }

    public GustProjectile(EntityType<GustProjectile> gustProjectileEntityType, Level level) {
        super(gustProjectileEntityType,level);

    }


    /**
     * @return
     */

    protected boolean canHitEntity(Entity p_37135_) {
        return super.canHitEntity(p_37135_) || p_37135_.isAlive() && p_37135_ instanceof ItemEntity;
    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        Direction direction = ray.getDirection();
        BlockPos blockpos = ray.getBlockPos();
        BlockPos blockpos1 = blockpos.relative(direction);
        BlockState blockHit = this.level().getBlockState(ray.getBlockPos());
        this.dowseFire(blockpos1);
        this.dowseFire(blockpos1.relative(direction.getOpposite()));
        for(Direction direction1 : Direction.Plane.HORIZONTAL) {
            this.dowseFire(blockpos1.relative(direction1));}
        if (blockHit.is(BlockTags.FLOWERS)){
            this.level().destroyBlock(ray.getBlockPos(), false);
        }
        if (blockHit.is(BlockTags.LEAVES)){
            this.level().destroyBlock(ray.getBlockPos(), true);
        }
        if (blockHit.is(BlockTags.SAND)){
            this.level().destroyBlock(ray.getBlockPos(), true);
        }
       else if (!blockHit.is(BlockTags.LEAVES) && !blockHit.is(BlockTags.SAND)){
            this.discard();
        }
    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.setDeltaMovement(entity.getDeltaMovement().add(getDeltaMovement().multiply(1.2D, 1.3D, 1.2D)));
        entity.clearFire();
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
    if (this.tickCount==20){
        this.discard();
    }
        int particlesDensity = 3;
        float particlesSpeed = 0.1F;
        float particlesSpread = 0.6F;

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
    private void dowseFire(BlockPos pPos) {
        BlockState blockstate = this.level().getBlockState(pPos);
        if (blockstate.is(BlockTags.FIRE)) {
            this.level().removeBlock(pPos, false);
        } else if (AbstractCandleBlock.isLit(blockstate)) {
            AbstractCandleBlock.extinguish(null, blockstate, this.level(), pPos);
        } else if (CampfireBlock.isLitCampfire(blockstate)) {
            this.level().levelEvent(null, 1009, pPos, 0);
            CampfireBlock.dowse(this.getOwner(), this.level(), pPos, blockstate);
            this.level().setBlockAndUpdate(pPos, blockstate.setValue(CampfireBlock.LIT, Boolean.valueOf(false)));
        }

    }
    @Override
    protected float getGravity() {
        return 0.0F;
    }
}
