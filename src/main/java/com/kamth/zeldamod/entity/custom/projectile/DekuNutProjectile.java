package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class DekuNutProjectile extends AbstractArrow {


    public DekuNutProjectile(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public DekuNutProjectile(Level world, LivingEntity owner) {
        super(ModEntityTypes.DEKU_NUT.get(), owner, world);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity)
    {
        super.doPostHurtEffects(entity);
     entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0));;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.hurt(damageSources().generic(), (float)2);
        double d0 = this.random.nextGaussian() * 0.02D;
        double d1 = this.random.nextGaussian() * 0.02D;
        double d2 = this.random.nextGaussian() * 0.02D;
        if (pResult.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) pResult.getEntity();
            target.setArrowCount(target.getArrowCount() - 1);
        }
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX() , this.getY(0.5D), this.getZ() , 4, 1, 0.0D, 1, 0.0D);
        }
    }


    @Override
    protected void onHitBlock(BlockHitResult ray) {
        super.onHitBlock(ray);
        this.discard();
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX() , this.getY(0.5D), this.getZ() , 4, 1, 0.0D, 1, 0.0D);
        }
    }
    @Override
    protected ItemStack getPickupItem() {
        return null;
    }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return ModSounds.SEED_BREAKS.get();
    }
    @Override
    public void tick() {
        super.tick();}

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}






