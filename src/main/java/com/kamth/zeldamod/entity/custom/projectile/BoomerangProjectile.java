package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class BoomerangProjectile extends AbstractArrow {
    public BoomerangProjectile(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }
    private boolean dealtDamage;
    public int clientSideReturnTridentTickCount;
    public BoomerangProjectile(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public BoomerangProjectile(Level world, LivingEntity owner) {
        super(ModEntityTypes.BOOMERANG.get(), owner, world);
    }

    /**
     * @return
     */
    @Override
    protected ItemStack getPickupItem() {

        return ModItems.BOOMERANG.get().getDefaultInstance();
    }
    protected boolean tryPickup(Player pPlayer) {
        return super.tryPickup(pPlayer) || this.isNoPhysics() && this.ownedBy(pPlayer) && pPlayer.getInventory().add(this.getPickupItem());
    }
    public void tick() {
        this.setNoGravity(true);
        if (this.tickCount > 30) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        int i = 1;
        if (i > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level.isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            } else {
                this.setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015D * (double)i, this.getZ());
                if (this.level.isClientSide) {
                    this.yOld = this.getY();
                }

                double d0 = 0.05D * (double)i;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vec3.normalize().scale(d0)));
                if (this.clientSideReturnTridentTickCount == 0) {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.clientSideReturnTridentTickCount;
            }
        }

        super.tick();
    }
    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        int i = 1;
        Entity entity = this.getOwner();
        this.setNoPhysics(true);
        Vec3 vec3 = entity.getEyePosition().subtract(this.position());
        this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015D * (double)i, this.getZ());
        if (this.level.isClientSide) {
            this.yOld = this.getY();
        }

        double d0 = 0.05D * (double)i;
        this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vec3.normalize().scale(d0)));
        if (this.clientSideReturnTridentTickCount == 0) {
            this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
        }
        ++this.clientSideReturnTridentTickCount;
    }
    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }




}





