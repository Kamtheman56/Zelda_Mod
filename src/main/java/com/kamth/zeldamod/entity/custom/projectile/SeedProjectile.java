package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
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
       int i = entity instanceof Silverfish ? 20 : 0;
        entity.hurt(damageSources().generic(), (float)i);
     int k = entity instanceof Spider ? 2 : 0;
       entity.hurt(damageSources().generic(), (float)k);
       int e = entity instanceof CaveSpider ? 2 : 0;
      entity.hurt(damageSources().generic(), (float)e);
       int b = entity instanceof Bee ? 20 : 0;
      entity.hurt(damageSources().generic(), (float)b);
      int r = entity instanceof Endermite ? 20 : 0;
      entity.hurt(damageSources().generic(), (float)r);

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
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
