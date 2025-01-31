package com.kamth.zeldamod.entity.projectile.arrows;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class IceArrow extends AbstractArrow {
    private static final double BASE_DAMAGE = 3.0D;
    public IceArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public IceArrow(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }
    public IceArrow(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    /**
     * @return
     */
    @Override
    protected ItemStack getPickupItem() {
        return ZeldaItems.ICE_ARROW.get().getDefaultInstance();
    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.SNOWFLAKE, this.getX() , this.getY(0.5D), this.getZ() , 5, 1, 0.0D, 1, 0.0D);
        }
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity)
    {
        super.doPostHurtEffects(entity);
        entity.setTicksFrozen(entity.getTicksFrozen()+40);
    }

    @Override
    public void tick() {
        super.tick();
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

        }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);


    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.SNOW_BREAK;
    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);

        BlockState blockHit = level().getBlockState(ray.getBlockPos());
        if (blockHit == Blocks.FIRE.defaultBlockState()) {
            level().destroyBlock(ray.getBlockPos(), false);
        }
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.SNOWFLAKE, this.getX() , this.getY(0.5D), this.getZ() , 5, 1, 0.0D, 1, 0.0D);
        }
        this.discard();

}
    protected float getWaterInertia() {
        return 0.01F;
    }
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
