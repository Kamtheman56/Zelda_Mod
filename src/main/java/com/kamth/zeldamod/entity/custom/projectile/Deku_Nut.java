package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
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
        double d0 = this.random.nextGaussian() * 0.02D;
        double d1 = this.random.nextGaussian() * 0.02D;
        double d2 = this.random.nextGaussian() * 0.02D;
        this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
        if (pResult.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) pResult.getEntity();
            target.setArrowCount(target.getArrowCount() - 1);
        }
    }


    @Override
    protected void onHitBlock(BlockHitResult ray) {
        super.onHitBlock(ray);
        this.discard();
        }

    private ParticleOptions getParticle() {
        ItemStack itemstack = ZeldaItems.DEKU_NUT.get().getDefaultInstance();
        return (ParticleOptions)(itemstack.isEmpty() ? ParticleTypes.HEART : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }
    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }}}
    /**
     * @return
     */
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






