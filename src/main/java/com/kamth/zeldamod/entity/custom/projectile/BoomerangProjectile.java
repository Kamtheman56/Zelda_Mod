package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;


public class BoomerangProjectile extends AbstractArrow {
    private static final double BASE_DAMAGE = 2.0D;

    private final ItemStack boomerangItem = new ItemStack(ModItems.BOOMERANG.get());

    public BoomerangProjectile(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);

    }
    public BoomerangProjectile(Level pLevel, LivingEntity pShooter, ItemStack pStack){
        super(ModEntityTypes.BOOMERANG.get(), pShooter, pLevel);

    }

    boolean dealtDamage;
    public int boomerangreturn;

    public BoomerangProjectile(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }
    public BoomerangProjectile(Level world, LivingEntity owner) {
        super(ModEntityTypes.BOOMERANG.get(), owner, world);
    }


    @Override
    protected ItemStack getPickupItem() {

        return boomerangItem;
    }
    protected boolean tryPickup(Player pPlayer) {
        return super.tryPickup(pPlayer) || this.isNoPhysics() && this.ownedBy(pPlayer)  && pPlayer.getInventory().add(this.getPickupItem());
    }


    public void tick() {
        this.setNoGravity(true);
        this.captureDrops();

        if (this.tickCount > 15) {
            this.dealtDamage = true;


        }

        Entity entity = this.getOwner();
        int i = 1;
        if (i > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            } else {
                this.turn(this.getY(),this.getX());
                this.setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
               this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015D * (double)i, this.getZ());
                if (this.level().isClientSide) {
                    this.yOld = this.getY();
                }

                double d0 = 0.5D * (double)i;
               this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vec3.normalize().scale(d0)));
                if (this.boomerangreturn == 0) {
                    this.playSound(SoundEvents.WOOD_BREAK, 1.0F, 1.0F);
                }

                ++this.boomerangreturn;
            }
        }

        super.tick();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
       this.dealtDamage=true;
       this.tickCount=16;
        BlockState blockHit = this.level().getBlockState(ray.getBlockPos());
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);
    }
@Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        Entity entity = pResult.getEntity();
        entity.hurt(damageSources().generic(), (float) this.getBaseDamage());
            this.dealtDamage=true;
        Entity entity1 = this.getOwner();
        if (entity instanceof LivingEntity) {
            LivingEntity livingentity1 = (LivingEntity)entity;
            if (entity1 instanceof LivingEntity) {
                EnchantmentHelper.doPostHurtEffects(livingentity1, entity1);
                EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity1);
            }
            this.doPostHurtEffects(livingentity1);
        }
    }
    @Override
    public void playerTouch(@NotNull Player pEntity) {
        if (this.ownedBy(pEntity) || this.getOwner() == null) {
            super.playerTouch(pEntity);
        }

    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.put("Trident", this.boomerangItem.save(new CompoundTag()));
        pCompound.putBoolean("DealtDamage", this.dealtDamage);
    }
    @Override
    public boolean canChangeDimensions() {
        return false;
    }
    protected boolean canHitEntity(Entity p_37135_) {
        return super.canHitEntity(p_37135_) || p_37135_.isAlive() && p_37135_ instanceof ItemEntity;
    }
    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }
@Override
protected SoundEvent getDefaultHitGroundSoundEvent() {
    return SoundEvents.WOOD_HIT;
}
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}







