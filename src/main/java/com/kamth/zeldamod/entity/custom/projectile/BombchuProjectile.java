package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.core.BlockPos;
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

public class BombchuProjectile extends ThrowableProjectile {


    private float ticksToExplode =100f;
    private int explosionPower = 3;
    private Object prevLookDirection;
    private static final double CHAIN_REACTION_RADIUS = 8.0;
    public boolean explodedByChainReaction = false;
    public BombchuProjectile(EntityType<BombchuProjectile> bombProjectileEntityType, Level level) {
        super(bombProjectileEntityType,level);

    }

    public BombchuProjectile(Level world, LivingEntity owner) {
        super(ModEntityTypes.BOMB.get(), owner, world);
    }


    public void lerpTo(double pX, double pY, double pZ, float pYaw, float pPitch, int pPosRotationIncrements, boolean pTeleport) {
        this.setPos(pX, pY, pZ);
        this.setRot(pYaw, pPitch);
    }

    /**
     * Updates the entity motion clientside, called by packets from the server
     */
    public void lerpMotion(double pX, double pY, double pZ) {
        super.lerpMotion(pX, pY, pZ);
        this.updateRotation();
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
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        this.move(MoverType.SELF, this.getDeltaMovement().normalize());



        if (this.isOnFire())
        {explode();}
        if (this.isInWater())
        {this.playSound(SoundEvents.FIRE_EXTINGUISH, 1, 1);
            this.discard();
        }

        //explosion and sound
        if (!this.level().isClientSide) {
            if (this.ticksToExplode <= this.tickCount) {
                explode();}
            else if (this.tickCount % 25 == 0) {
                this.playSound(SoundEvents.TNT_PRIMED, 1, 1/ (this.level().getRandom().nextFloat() * 0.4F + 0.8F));
            }}}

    private void explode() {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, Level.ExplosionInteraction.NONE);
        this.discard();
        //credit to SupersLegends for the destroying specific block code
        BlockPos explosionPos = this.blockPosition();
        int radius = (int) Math.ceil(explosionPower);



        for (BlockPos pos : BlockPos.betweenClosed(explosionPos.offset(-radius, -radius, -radius), explosionPos.offset(radius, radius, radius))) {
            BlockState blockState = this.level().getBlockState(pos).getBlock().defaultBlockState();
            if (blockState.is(ModTags.Blocks.BOMB)){
                this.level().destroyBlock(pos, false);
            }

        }}




    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}



