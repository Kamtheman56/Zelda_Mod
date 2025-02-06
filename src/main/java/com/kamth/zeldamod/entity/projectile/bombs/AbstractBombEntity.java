package com.kamth.zeldamod.entity.projectile.bombs;

import com.kamth.zeldamod.custom.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;

public abstract class AbstractBombEntity extends ThrowableProjectile implements ItemSupplier {

    private static final EntityDataAccessor<Integer> TICKS_TO_EXPLODE = SynchedEntityData.defineId(AbstractBombEntity.class, EntityDataSerializers.INT);

    private int explosionPower;
    private boolean waterProof;
    private boolean bowled;

    protected AbstractBombEntity(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.explosionPower = 0;
        this.waterProof = false;
    }

    protected AbstractBombEntity(EntityType<? extends ThrowableProjectile> pEntityType, LivingEntity pShooter, Level pLevel, int ticksToExplode,
                                 int explosionPower, boolean waterProof, boolean bowled) {
        super(pEntityType, pShooter, pLevel);
        setFuse(ticksToExplode);
        this.explosionPower = explosionPower;
        this.waterProof = waterProof;
        this.bowled = bowled;
    }

    @Override
    public void tick() {

        this.move(MoverType.SELF, this.getDeltaMovement().normalize());
        this.updateInWaterStateAndDoFluidPushing();
        hitDetection();

        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -getGravity(), 0));
        }

        if (this.onGround()) {
            this.setDeltaMovement(0, -0.75, 0);
        }

        if (this.isOnFire()) {
            explode();
        }

        if (this.isInWater() && !this.level().isClientSide) {
            if (this.waterProof) {
                this.explosionPower = 5;
            }

            if (!this.waterProof){
                this.discard();
                this.playSound(SoundEvents.FIRE_EXTINGUISH, 1, 1);
            }
        }

        if (this.tickCount % 8 == 0) {
            this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.55, this.getZ(), 0, 0, 0);
        }

        if (!this.level().isClientSide) {
            if (getFuse() <= this.tickCount) {
                explode();}
            else if (this.tickCount % 25 == 0) {
                this.playSound(SoundEvents.TNT_PRIMED, 1, 1 / (this.level().getRandom().nextFloat() * 0.4f + 0.8f));
            }
        }

    }

    private void hitDetection() {
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        boolean flag = false;
        if (hitresult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult)hitresult).getBlockPos();
            BlockState blockstate = this.level().getBlockState(blockpos);
            if (blockstate.is(Blocks.NETHER_PORTAL)) {
                this.handleInsidePortal(blockpos);
                flag = true;
            } else if (blockstate.is(Blocks.END_GATEWAY)) {
                BlockEntity blockentity = this.level().getBlockEntity(blockpos);
                if (blockentity instanceof TheEndGatewayBlockEntity && TheEndGatewayBlockEntity.canEntityTeleport(this)) {
                    TheEndGatewayBlockEntity.teleportEntity(this.level(), blockpos, blockstate, this, (TheEndGatewayBlockEntity)blockentity);
                }

                flag = true;
            }
        }

        if (hitresult.getType() != HitResult.Type.MISS && !flag && !ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }
    }


    // credit to SupersLegends for the destroying block code
    private void explode() {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, Level.ExplosionInteraction.NONE);

        this.discard();

        BlockPos explosionPos = this.blockPosition();

        int radius = (int) Math.ceil(this.explosionPower);

        for (BlockPos pos : BlockPos.betweenClosed(explosionPos.offset(-radius, -radius, -radius), explosionPos.offset(radius, radius, radius))) {

            BlockState blockState = this.level().getBlockState(pos).getBlock().defaultBlockState();

            if (blockState.is(ModTags.Blocks.BOMB)){
                this.level().destroyBlock(pos, false);
            }

            if (blockState.is(ModTags.Blocks.BOMB_FLOWER_BLOCKS)) {
                this.level().destroyBlock(pos, false);
                this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, Level.ExplosionInteraction.MOB);
            }
        }
    }

    @Override
    protected float getGravity() {
        return 0.2f;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TICKS_TO_EXPLODE, 100);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("bowling", this.bowled);
        pCompound.putBoolean("water_proof", this.waterProof);
        pCompound.putShort("fuse", (short)this.getFuse());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.bowled = (pCompound.getBoolean("bowling"));
        this.waterProof = pCompound.getBoolean("water_proof");
        setFuse(pCompound.getShort("fuse"));
    }

    public int getFuse() {
        return this.entityData.get(TICKS_TO_EXPLODE);
    }

    void setFuse(int ticks) {
        this.entityData.set(TICKS_TO_EXPLODE, ticks);
    }
}
