package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.custom.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class WaterBombProjectile extends ThrowableProjectile {
    private float ticksToExplode =100f;
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
           explode();
        } else if (lvt_2_1_ == HitResult.Type.BLOCK) {
            this.onHitBlock((BlockHitResult) result);
        }
    }
    protected boolean inGround;

    @Override
    protected void onHitBlock(BlockHitResult ray) {
        super.onHitBlock(ray);
        Vec3 vector3d = ray.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(vector3d);
        Vec3 vector3d1 = vector3d.normalize().scale((double)0.05F);
        this.setPosRaw(this.getX() - vector3d1.x, this.getY() - vector3d1.y, this.getZ() - vector3d1.z);
        this.inGround = true;
    }


    @Override
    protected void defineSynchedData() {

    }

@Override
protected float getGravity() {
    return 0.1F;
}

@Override
    public void tick() {
    super.tick();
  //  HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
    boolean flag = false;

  //  if (hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
  //      this.onHit(hitresult);
  //  }

    if (this.isOnFire())
    {explode();}
    if (this.isInWater())
    {
        this.explosionPower=3;
    }
    if (!this.level.isClientSide) {
            if (this.ticksToExplode <= this.tickCount) {
                explode();}
        else   if(this.tickCount % 25 == 0) {
                this.playSound(SoundEvents.TNT_PRIMED, 1, 1);
            }}}

    private void explode() {
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, Level.ExplosionInteraction.NONE);
            this.discard();
        //credit to SupersLegends for the destroying specific block code
            BlockPos explosionPos = this.blockPosition();
        int radius = 3;
        for (BlockPos pos : BlockPos.betweenClosed(explosionPos.offset(-radius, -radius, -radius), explosionPos.offset(radius, radius, radius))) {
            Block block = this.level.getBlockState(pos).getBlock();
            BlockState blockState = this.level.getBlockState(pos).getBlock().defaultBlockState();
            if (blockState.is(ModTags.Blocks.BOMB)){
                this.level.destroyBlock(pos, false);
            }}}
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


}






