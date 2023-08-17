package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

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

        entity.hurt(damageSources().generic(), (float)2);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0));
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
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}






