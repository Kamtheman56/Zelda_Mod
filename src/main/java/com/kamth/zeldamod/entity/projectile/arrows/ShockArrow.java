package com.kamth.zeldamod.entity.projectile.arrows;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class ShockArrow extends AbstractArrow {
    private static final double BASE_DAMAGE = 3.0D;
    public ShockArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ShockArrow(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public ShockArrow(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    /**
     * @return
     */
    @Override
    protected ItemStack getPickupItem() {
        return  ZeldaItems.SHOCK_ARROW.get().getDefaultInstance();}

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
            if (this.level() instanceof ServerLevel) {
                ((ServerLevel)this.level()).sendParticles(ParticleTypes.ELECTRIC_SPARK, this.getX() , this.getY(0.5D), this.getZ() , 5, 1, 0.0D, 1, 0.0D);
                BlockPos blockpos = entity.blockPosition();
                if (this.level().canSeeSky(blockpos) && this.level().isRaining()) {
                    LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level());
                    lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
                    this.level().addFreshEntity(lightningbolt);
    }
            }
        int i = entity instanceof IronGolem ? 10 : 0;
       entity.hurt(damageSources().magic(), (float)i);
    if (entity.isInWaterOrRain()){
        setBaseDamage(10);

    }
    }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);

    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        this.discard();

        if (this.level() instanceof ServerLevel) {
            BlockPos blockpos = this.blockPosition();
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.ELECTRIC_SPARK, this.getX() , this.getY(0.5D), this.getZ() , 5, 1, 0.0D, 1, 0.0D);

            if (this.level().canSeeSky(blockpos) && this.level().isRaining()) {
                LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level());
                lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
                this.level().addFreshEntity(lightningbolt);
            }
        }

}
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
    return SoundEvents.ARROW_HIT;
}
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
