package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.custom.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import java.time.Instant;

public class BombProjectile extends ThrowableProjectile {


    private static  int TICKS_PER_SECOND = 20;
    private float ticksToExplode =150f;





    private int explosionPower = 2;

    public BombProjectile(EntityType<BombProjectile> bombProjectileEntityType, Level level) {
        super(bombProjectileEntityType,level);

    }

    public BombProjectile(Level world, LivingEntity owner) {
        super(ModEntityTypes.BOMB.get(), owner, world);
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
        setDeltaMovement(getDeltaMovement().multiply(0,0,0));
        setPos(this.getX(), this.getY(), this.getZ());
        this.inGround = true;
        }



    @Override
    protected void defineSynchedData() {

    }

@Override
protected float getGravity() {
    return 0.2F;
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
    {this.playSound(SoundEvents.FIRE_EXTINGUISH, 1, 1);
        this.discard();
    }
    int particlesDensity = 1;
    float particlesSpeed = .2F;
    float particlesSpread = 0F;

    for (int i = 0; i < particlesDensity; i++)
    {
        double particleX = getX() + (random.nextFloat() * 2 - 1) * particlesSpread;
        double particleY = getY() + (random.nextFloat() * 3 - 1) * particlesSpread;
        double particleZ = getZ() + (random.nextFloat() * 2 - 1) * particlesSpread;
        double particleMotionX = (random.nextFloat() * 0 - 0) * particlesSpeed;
        double particleMotionY = (random.nextFloat() * 1 - 0) * particlesSpeed;
        double particleMotionZ = (random.nextFloat() * 0 - 0) * particlesSpeed;
        level.addParticle(ParticleTypes.SMOKE, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
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
        //credit to SupersLegends for the destroying specific block code
        BlockPos explosionPos = this.blockPosition();
        int radius = (int) Math.ceil(explosionPower);
        for (BlockPos pos : BlockPos.betweenClosed(explosionPos.offset(-radius, -radius, -radius), explosionPos.offset(radius, radius, radius))) {
            BlockState blockState = this.level.getBlockState(pos).getBlock().defaultBlockState();
            if (blockState.is(ModTags.Blocks.BOMB)){
                this.level.destroyBlock(pos, false);
        }

        }}

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}






