package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.time.Instant;

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
    protected void onHitBlock(BlockHitResult p_230299_1_) {
        super.onHitBlock(p_230299_1_);
        setDeltaMovement(getDeltaMovement().multiply(0,0,0));
        setPos(this.getX(), this.getY(), this.getZ());
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
    HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
    boolean flag = false;

    if (hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
        this.onHit(hitresult);
    }

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
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, Explosion.BlockInteraction.NONE);
            this.discard();
        }
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


}






