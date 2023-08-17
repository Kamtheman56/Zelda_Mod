package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class SwordBeam extends AbstractArrow {
    private static final double BASE_DAMAGE = 3.0D;
    public SwordBeam(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SwordBeam(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    public SwordBeam(Level world, LivingEntity owner) {
        super(ModEntityTypes.SWORD_BEAM.get(), owner, world);
    }

    /**
     * @return
     */
    @Override
    protected ItemStack getPickupItem() {
        return  null;}
    @Override
    public boolean isNoGravity()
    {
        return true;
    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int z = entity instanceof Zombie ? 2 : 0;
        entity.hurt(damageSources().generic(), (float)z);
        int k = entity instanceof Skeleton ? 2 : 0;
        entity.hurt(damageSources().generic(), (float)k);
        int p = entity instanceof Phantom ? 2 : 0;
        entity.hurt(damageSources().generic(), (float)p);
        int r = entity instanceof Stray ? 2 : 0;
        entity.hurt(damageSources().generic(), (float)r);
        int h = entity instanceof Husk ? 2 : 0;
        entity.hurt(damageSources().generic(), (float)h);
        int w = entity instanceof WitherSkeleton ? 10 : 0;
        entity.hurt(damageSources().generic(), (float)w);
        int e = entity instanceof WitherBoss ? 20 : 0;
        entity.hurt(damageSources().generic(), (float)e);
        int s = entity instanceof Warden ? 20 : 0;
        entity.hurt(damageSources().generic(), (float)s);
        this.discard();
    }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);

    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        this.discard();
        BlockState blockHit = level.getBlockState(ray.getBlockPos());
        if (blockHit.is(BlockTags.FLOWERS) ){
            level.destroyBlock(ray.getBlockPos(), false);
        }
}

    @Override
    public boolean canChangeDimensions() {
        return false;
    }



    @Override
    public void tick()
    {
        super.tick();
        if (tickCount > 15)
        {
            this.discard();
        }
        if (this.isInWater()){
            this.discard();
        }

    }



    @Override
protected SoundEvent getDefaultHitGroundSoundEvent() {
    return null;
}
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
