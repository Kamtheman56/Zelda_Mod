package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
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

        BlockState blockHit = level.getBlockState(ray.getBlockPos());
        if (blockHit.getMaterial() == Material.PLANT){
            level.destroyBlock(ray.getBlockPos(), false);
        }
        if (blockHit.getMaterial() == Material.REPLACEABLE_PLANT){
            level.destroyBlock(ray.getBlockPos(), false);
        }

        if (blockHit.getMaterial() == Material.LEAVES){
            level.destroyBlock(ray.getBlockPos(), true);
        }
        if (blockHit.is(BlockTags.SAND)){
            level.destroyBlock(ray.getBlockPos(), true);
        }
        if (blockHit.getBlock() == Blocks.FIRE){
            this.level.destroyBlock(ray.getBlockPos(), false);
        }
       else if (blockHit.getMaterial() != Material.LEAVES && !blockHit.is(BlockTags.SAND)  ){
            this.discard();
        }

    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.setDeltaMovement(entity.getDeltaMovement().add(getDeltaMovement().multiply(1.1D, 1.1D, 1.1D)));
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
            level.addParticle(ParticleTypes.CLOUD, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
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
