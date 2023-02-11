package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.mojang.math.Vector3d;
import com.sun.jna.platform.win32.IPHlpAPI;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class SeedProjectile extends AbstractArrow {
    private static final double BASE_DAMAGE = 3.0D;
    private Item referenceItem;
    public SeedProjectile(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.referenceItem = Items.WHEAT_SEEDS;
    }
    public SeedProjectile(Level worldIn) {
        super(ModEntityTypes.WHEAT_SEED.get(), worldIn);
    }


    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }

    public void shoot(Vec3 direction, float speed, float spread) {
        super.shoot(direction.x, direction.y, direction.z, speed * getFlightSpeed(), 2);
        
    }

    protected float getFlightSpeed() {
        return 1F;
    }
    @Override
    public void tick() {
        super.tick();

        if (!isNoPhysics()) {
            Vec3 previousMovement = getDeltaMovement();
            setDeltaMovement(previousMovement.x, previousMovement.y, previousMovement.z);
        }

        if (inGround) {
            this.setRemoved(RemovalReason.DISCARDED);
        }

    }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);
    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int i = entity instanceof Spider ? 6 : 1;
        int j = entity instanceof Silverfish ? 20 : 1;
        int a = entity instanceof Endermite ? 3 : 1;
        int b = entity instanceof CaveSpider ? 3 : 1;
        int c = entity instanceof Bee ? 3 : 1;
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)j);
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)a);
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)b);
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)c);
        if (pResult.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) pResult.getEntity();
                target.setArrowCount(target.getArrowCount() - 1);
        }
    }







    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.CHICKEN_EGG;
    }
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
