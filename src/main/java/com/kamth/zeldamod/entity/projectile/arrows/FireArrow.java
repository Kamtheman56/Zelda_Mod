package com.kamth.zeldamod.entity.projectile.arrows;

import com.kamth.zeldamod.Config;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class FireArrow extends AbstractArrow {
    private static final double BASE_DAMAGE = 3.0D;
    public FireArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FireArrow(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public FireArrow(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    /**
     * @return
     */
    @Override
    protected ItemStack getPickupItem() {
        return  ZeldaItems.FIRE_ARROW.get().getDefaultInstance();}
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.setSecondsOnFire(60);
    }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);
        this.setSecondsOnFire(600);
    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        if (level().isEmptyBlock(this.blockPosition()))
            level().setBlock(this.blockPosition(), Blocks.FIRE.defaultBlockState(),11);
        BlockState blockHit = level().getBlockState(ray.getBlockPos());

     if(Config.fire_arrow_melting()) {
            if (blockHit.getBlock() == Blocks.ICE) {
                level().destroyBlock(ray.getBlockPos(), false);
            }
            if (blockHit.getBlock() == Blocks.PACKED_ICE) {
                level().destroyBlock(ray.getBlockPos(), false);
            }
        }
        this.discard();
}

@Override
public void tick(){
        super.tick();
    Vec3 vec3 = this.getDeltaMovement();
    double d5 = vec3.x;
    double d6 = vec3.y;
    double d1 = vec3.z;
    double d7 = this.getX() + d5;
    double d2 = this.getY() + d6;
    double d3 = this.getZ() + d1;

    for(int i = 0; i < 4; ++i) {
        this.level().addParticle(ParticleTypes.FLAME, this.getX() + d5 * (double) i / 4.0D, this.getY() + d6 * (double) i / 4.0D, this.getZ() + d1 * (double) i / 4.0D, -d5, -d6 + 0.2D, -d1);
        }
    }

@Override
protected SoundEvent getDefaultHitGroundSoundEvent() {
    return SoundEvents.FIRECHARGE_USE;
}
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
