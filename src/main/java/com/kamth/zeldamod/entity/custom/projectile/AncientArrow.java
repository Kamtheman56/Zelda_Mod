package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class AncientArrow extends AbstractArrow {
    private static final double BASE_DAMAGE = 20.0D;
    public AncientArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public AncientArrow(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public AncientArrow(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    /**
     * @return
     */
    @Override
    protected ItemStack getPickupItem() {
      return  ModItems.LIGHT_ARROW.get().getDefaultInstance();}
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int i = entity instanceof EnderDragon ? 10 : 0;
        entity.hurt(DamageSource.GENERIC.setProjectile(), (float)i);
        int w = entity instanceof WitherBoss ? 10 : 0;
        entity.hurt(DamageSource.GENERIC.setProjectile(), (float)w);
        int s = entity instanceof Warden ? 10 : 0;
        entity.hurt(DamageSource.GENERIC.setProjectile(), (float)s);


    }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);

       setKnockback(5);
    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        this.discard();
}
@Override
protected SoundEvent getDefaultHitGroundSoundEvent() {
    return SoundEvents.AMETHYST_CLUSTER_HIT;
}
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
