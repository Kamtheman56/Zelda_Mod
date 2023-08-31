package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class Deku_Nut extends AbstractArrow {


    public Deku_Nut(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public Deku_Nut(Level world, LivingEntity owner) {
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
        if (pResult.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) pResult.getEntity();
            target.setArrowCount(target.getArrowCount() - 1);
        }
    }


    @Override
    protected void onHitBlock(BlockHitResult ray) {
        super.onHitBlock(ray);
        this.level().addParticle(ParticleTypes.SOUL, ray.getBlockPos().getX() + 0.5D, ray.getBlockPos().getY()  + 1.2D, ray.getBlockPos().getZ() + 0.5D, (double)1 / 24.0D, 0.0D, 0.0D);

        this.discard();
        }

    /**
     * @return
     */
    @Override
    protected ItemStack getPickupItem() {
        return null;
    }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.CHICKEN_EGG;
    }

    @Override
    public void tick() {
        super.tick();}

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}






