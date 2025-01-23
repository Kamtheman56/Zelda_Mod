package com.kamth.zeldamod.entity.projectile.magic;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
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
           // if (this.level().isEmptyBlock(this.blockPosition().above())){
          //      this.level().setBlockAndUpdate(this.blockPosition(), Blocks.SNOW.defaultBlockState());}
            this.discard();
        }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.setTicksFrozen(entity.getTicksFrozen()+60);
        entity.hurt(damageSources().magic(),4);
        this.playSound(SoundEvents.PLAYER_HURT_FREEZE);


        if (pResult.getEntity().getType().is(ModTags.Entities.CHUCHU_FIRE)){
            entity.remove(RemovalReason.DISCARDED);
        }


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
            BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
            float f = (float)Math.min(1, 1);


            for(BlockPos blockpos : BlockPos.betweenClosed(this.blockPosition().offset((int) -f, (int) +1.0D, (int) -f), this.blockPosition().offset((int) f, (int) +1.0D, (int) f))) {

                {
                    BlockState blockstate2 = level().getBlockState(blockpos);
                    boolean isFull = blockstate2.getBlock() == Blocks.WATER && blockstate2.getValue(LiquidBlock.LEVEL) == 0; //TODO: Forge, modded waters?
                    if (blockstate2.getBlock() == Blocks.WATER && isFull && blockstate.canSurvive(level(), blockpos) && level().isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(this, net.minecraftforge.common.util.BlockSnapshot.create(level().dimension(), level(), blockpos), net.minecraft.core.Direction.UP)) {
                        level().setBlockAndUpdate(blockpos, blockstate);
                        level().scheduleTick(blockpos, Blocks.FROSTED_ICE, Mth.nextInt(this.random, 60, 150));
                        this.discard();
                    }
                }
            }
        }
        if (this.isInLava()){
            level().setBlockAndUpdate(this.blockPosition(), Blocks.FROSTED_ICE.defaultBlockState());
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
            level().addParticle(ParticleTypes.SNOWFLAKE, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
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
