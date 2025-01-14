package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
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
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class AbstractBomb extends ThrowableProjectile {


    private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(AbstractBomb.class, EntityDataSerializers.BYTE);
    private boolean WaterProof;
    private boolean Bowled;
    private int ticksToExplode;
    private int explosionPower;

    public AbstractBomb(EntityType<AbstractBomb> bombProjectileEntityType, Level level, boolean waterProof, int explosionPower) {
        super(bombProjectileEntityType,level);

        WaterProof = waterProof;
    }

    public AbstractBomb(Level world, LivingEntity owner, boolean waterProof, int explosionPower) {
        super(ModEntityTypes.BOMB.get(), owner, world);
        WaterProof = waterProof;
    }

    @Override
    protected void onHit(HitResult result) {
        HitResult.Type lvt_2_1_ = result.getType();
        if (lvt_2_1_ == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult) result);
        } else if (lvt_2_1_ == HitResult.Type.BLOCK) {
            this.onHitBlock((BlockHitResult) result);
        }
    }
    protected void onHitEntity(EntityHitResult hit) {
        Vec3 vector3d = hit.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(vector3d);
        Vec3 vector3d1 = vector3d.normalize().scale(getGravity());
        this.setPosRaw(this.getX() - vector3d1.x, this.getY() - vector3d1.y, this.getZ() - vector3d1.z);
        this.setOnGround(true);
    }
    @Override
    protected void onHitBlock(BlockHitResult hit) {
        super.onHitBlock(hit);
        Vec3 vector3d = hit.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(vector3d);
        Vec3 vector3d1 = vector3d.normalize().scale(getGravity());
        this.setPosRaw(this.getX() - vector3d1.x, this.getY() - vector3d1.y, this.getZ() - vector3d1.z);
       this.setOnGround(true);
          }
@Override
protected float getGravity() {
    return 0.2F;
}



    @Override
    public void tick() {
        this.move(MoverType.SELF, this.getDeltaMovement().normalize());

        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.2D, 0.0D));
        }
        if (this.onGround() & !this.isBowled()){
    this.setDeltaMovement(0,-.75f,0);
}
        if (this.onGround() & this.isBowled()){
            this.setDeltaMovement(0,-.4f,0);
        }
    if (this.isOnFire()) {
        explode();
    }
    if (this.isInWater()) {
        this.playSound(SoundEvents.FIRE_EXTINGUISH, 1, 1);
        this.discard();
    }
    else if (this.tickCount % 8 == 0) {
        this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.55D, this.getZ(), 0.0D, 0.0D, 0.0D);
    }
    //explosion and sound
    if (!this.level().isClientSide) {
            if (this.ticksToExplode <= this.tickCount) {
                explode();}
        else if (this.tickCount % 25 == 0) {
                this.playSound(SoundEvents.TNT_PRIMED, 1, 1/ (this.level().getRandom().nextFloat() * 0.4F + 0.8F));
            }
    }
    }
    //credit to SupersLegends for the destroying specific block code
    private void explode() {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, Level.ExplosionInteraction.NONE);
            this.discard();
        BlockPos explosionPos = this.blockPosition();
        int radius = (int) Math.ceil(explosionPower);
        for (BlockPos pos : BlockPos.betweenClosed(explosionPos.offset(-radius, -radius, -radius), explosionPos.offset(radius, radius, radius))) {
            BlockState blockState = this.level().getBlockState(pos).getBlock().defaultBlockState();
            if (blockState.is(ModTags.Blocks.BOMB)){
                this.level().destroyBlock(pos, false);
        }
            if (blockState.is(ModTags.Blocks.BOMB_FLOWER_BLOCKS)){
                this.level().destroyBlock(pos, false);
                this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, Level.ExplosionInteraction.MOB);
            }
        }
    }


    public boolean isBowled() {
        byte b0 = this.entityData.get(ID_FLAGS);
        return (b0 & 1) != 0;
    }

    public void setBowling(boolean pCritArrow) {
        this.setFlag(1, pCritArrow);
    }
    private void setFlag(int pId, boolean pValue) {
        byte b0 = this.entityData.get(ID_FLAGS);
        if (pValue) {
            this.entityData.set(ID_FLAGS, (byte)(b0 | pId));
        } else {
            this.entityData.set(ID_FLAGS, (byte)(b0 & ~pId));
        }
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("bowling", this.isBowled());
        pCompound.putInt("ticks_to_explode", this.ticksToExplode);
        pCompound.putBoolean("water_proof", this.WaterProof);}


    public void readAdditionalSaveData(CompoundTag pCompound) {
        this.ticksToExplode = pCompound.getInt("ticks_to_explode");
        this.setBowling(pCompound.getBoolean("bowling"));
        this.WaterProof = pCompound.getBoolean("water_proof");
    }
    @Override
    protected void defineSynchedData() {
        this.entityData.define(ID_FLAGS, (byte)0);

    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}






