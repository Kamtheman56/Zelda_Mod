package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
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

public class WaterBombProjectile extends ThrowableProjectile {
    private float ticksToExplode =80f;
    private int explosionPower = 1;

    public WaterBombProjectile(EntityType<WaterBombProjectile> waterBombProjectileEntityType, Level level) {
        super(waterBombProjectileEntityType,level);

    }
    public WaterBombProjectile(Level world, LivingEntity owner) {
        super(ModEntityTypes.WATER_BOMB.get(), owner, world);
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
    protected void defineSynchedData() {

    }

@Override
protected float getGravity() {
    return 0.15F;
}

    @Override
    public void tick() {
     ;  this.move(MoverType.SELF, this.getDeltaMovement().normalize());

        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.2D, 0.0D));
        }
        if (this.onGround()) {
            this.setDeltaMovement(0, -.60f, 0);
        }
        if (this.isOnFire()) {
            explode();
        }
    if (this.isInWater()) {
        this.explosionPower=3;
    }
    else if (this.tickCount % 8 == 0) {
        this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.55D, this.getZ(), 0.0D, 0.0D, 0.0D);
    }
    if (!this.level().isClientSide) {
            if (this.ticksToExplode <= this.tickCount) {
                explode();}
        else   if(this.tickCount % 20 == 0) {
                this.playSound(SoundEvents.TNT_PRIMED, 1, 5/ (this.level().getRandom().nextFloat() * 0.4F + 0.8F));
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
    @Override
    protected void updateRotation() {
    }
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


}






