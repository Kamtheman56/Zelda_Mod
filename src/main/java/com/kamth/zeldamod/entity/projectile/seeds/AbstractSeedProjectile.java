package com.kamth.zeldamod.entity.projectile.seeds;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
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

public abstract class AbstractSeedProjectile extends AbstractArrow implements ItemSupplier {

    public AbstractSeedProjectile(EntityType<? extends AbstractSeedProjectile> pEntityType, Level level) {
        super(pEntityType, level);
        setBaseDamage(getDamage());
    }

    public AbstractSeedProjectile(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
        setBaseDamage(getDamage());
    }


    protected abstract double getDamage();

    @Override
    public abstract ItemStack getItem();


    @Override
    public ItemStack getPickupItem() {
        return getItem();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        Entity entity = pResult.getEntity();

        int z = insectDamage(entity);

        entity.hurt(damageSources().generic(), z);

        if (pResult.getEntity() instanceof LivingEntity target) {
            target.setArrowCount(target.getArrowCount() - 1);
        }
        this.discard();
    }

    protected int insectDamage(Entity entity) {
        return entity.getType().is(ModTags.Entities.BUGS) ? 4 : 0;
    }


    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        this.discard();
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(),
                    2, 0, 0, 0, 0);
        }
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return ModSounds.SEED_BREAKS.get();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
