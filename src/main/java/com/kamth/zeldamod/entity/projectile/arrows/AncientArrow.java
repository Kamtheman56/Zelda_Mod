package com.kamth.zeldamod.entity.projectile.arrows;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
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
      return  ZeldaItems.LIGHT_ARROW.get().getDefaultInstance();}
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int i = entity instanceof EnderDragon ? 10 : 0;
     entity.hurt(damageSources().generic(), (float) i);
        int w = entity instanceof WitherBoss ? 10 : 0;
      entity.hurt(damageSources().generic(), (float)w);
        int s = entity instanceof Warden ? 10 : 0;
       entity.hurt(damageSources().generic(), (float)s);
        int p = entity instanceof Player ? 0 : 0;
        entity.hurt(damageSources().generic(), (float)p);
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.GLOW_SQUID_INK, this.getX() , this.getY(0.5D), this.getZ() , 5, 1, 0.0D, 1, 0.0D);
        }


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
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.GLOW_SQUID_INK, this.getX() , this.getY(0.5D), this.getZ() , 5, 1, 0.0D, 1, 0.0D);
        }
        this.discard();
}
@Override
protected SoundEvent getDefaultHitGroundSoundEvent() {
    return SoundEvents.AMETHYST_CLUSTER_HIT;
}
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
