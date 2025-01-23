package com.kamth.zeldamod.entity.projectile.seeds;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
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
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class DekuNutProjectile extends AbstractSeedProjectile {


    public DekuNutProjectile(EntityType<? extends AbstractSeedProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DekuNutProjectile(LivingEntity owner, Level world) {
        super(ModEntityTypes.DEKU_NUT.get(), owner, world);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0));;
    }

    @Override
    protected double getDamage() {
        return 2.0;
    }

    @Override
    public ItemStack getItem() {
        return ZeldaItems.DEKU_NUT.get().getDefaultInstance();
    }

    @Override
    public ItemStack getPickupItem() {
        return null;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        this.discard();
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX() , this.getY(0.5D), this.getZ(),
                    4, 1, 0.0D, 1, 0.0D);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        Entity entity = pResult.getEntity();
        entity.hurt(damageSources().generic(), 2);

        if (pResult.getEntity() instanceof LivingEntity target) {
            target.setArrowCount(target.getArrowCount() - 1);
        }

        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX() , this.getY(0.5D), this.getZ(),
                    4, 1, 0.0D, 1, 0.0D);
        }
    }
}






