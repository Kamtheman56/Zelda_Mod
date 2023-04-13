package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

import java.time.Instant;

public class Deku_Nut extends ThrowableProjectile {



    public Deku_Nut(EntityType<Deku_Nut> bombProjectileEntityType, Level level) {
        super(bombProjectileEntityType,level);

    }

    public Deku_Nut(Level world, LivingEntity owner) {
        super(ModEntityTypes.DEKU_NUT.get(), owner, world);
    }




    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();

        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)2);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 0));
        }
    }



    @Override
    protected void onHitBlock(BlockHitResult ray) {
        super.onHitBlock(ray);
     this.discard();
        }
    @Override
    protected float getGravity() {
        return .1F;
    }
    @Override
    public void tick() {
        super.tick();}
    @Override
    protected void defineSynchedData() {
    }
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}






