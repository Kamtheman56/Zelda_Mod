package com.kamth.zeldamod.entity.projectile.boomerangs;

import com.kamth.zeldamod.entity.projectile.bombs.BombEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractBoomerang extends Projectile implements ItemSupplier {
    private static final EntityDataAccessor<Boolean> RETURNING = SynchedEntityData.defineId(AbstractBoomerang.class, EntityDataSerializers.BOOLEAN);
    protected int liveTime;
    private int slot;

    public AbstractBoomerang(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public AbstractBoomerang(EntityType<? extends Projectile> pEntityType, Level pLevel, Player player, int returnSlot) {
        super(pEntityType, pLevel);
        this.setOwner(player);
        this.setPos(player.getEyePosition());
        this.slot = returnSlot;
    }

    protected abstract float getDamage();
    protected abstract float getLifetimeLimit();

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        entity.hurt(damageSources().generic(), getDamage());
        setReturning(true);
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);

        this.level().playSound(null, this.blockPosition(), getHitBlockSound(), SoundSource.PLAYERS);

        BlockState state = this.level().getBlockState(this.blockPosition());

        if (state.is(Blocks.LEVER)) {
            this.level().setBlock(this.blockPosition(), state.cycle(LeverBlock.POWERED), 3);
        }

        setReturning(true);
    }

    protected void checkImpact() {
        if (level().isClientSide) {
            return;
        }
        Vec3 motion = getDeltaMovement();
        Vec3 position = position();
        Vec3 rayEnd = position.add(motion);

        if (isAlive() && !isReturning()) {

            EntityHitResult entityHitresult = raycastEntities(position, rayEnd);
            if (entityHitresult != null && !(entityHitresult.getEntity() instanceof Player)) {
                onHit(entityHitresult);
            }

            HitResult result = level().clip(new ClipContext(position, rayEnd, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
            if (result.getType() != HitResult.Type.MISS) {
                onHit(result);
            }
        }
    }

    @Override
    public void tick() {
        Vec3 pos = position();
        this.xOld = pos.x;
        this.yOld = pos.y;
        this.zOld = pos.z;

        super.tick();

        if (!isReturning()) {
            checkImpact();
        }

        Vec3 motion = getDeltaMovement();
        setPos(pos.x + motion.x, pos.y + motion.y, pos.z + motion.z);

        float horizontalDistance = (float) motion.horizontalDistance();
        setYRot((float) (Mth.atan2(motion.x, motion.z) * 180 / (float) Math.PI));
        setXRot((float) (Mth.atan2(motion.y, horizontalDistance) * 180 / (float) Math.PI));

        float xRotDiff = getXRot() - this.xRotO;
        float normalizedXRotDiff = normalizeAngle(xRotDiff);
        this.xRotO = getXRot() - normalizedXRotDiff;

        float yRotDiff = getYRot() - this.yRotO;
        float normalizedYRotDiff = normalizeAngle(yRotDiff);
        this.yRotO = getYRot() - normalizedYRotDiff;

        setXRot(Mth.lerp(0.2f, this.xRotO, getXRot()));
        setYRot(Mth.lerp(0.2f, this.yRotO, getYRot()));

        float drag = 0.99f;
        if (isInWater()) {
            level().addParticle(
                    ParticleTypes.BUBBLE,
                    pos.x - motion.x * 0.25D,
                    pos.y - motion.y * 0.25D,
                    pos.z - motion.z * 0.25D,
                    motion.x, motion.y, motion.z
            );
            drag = 0.8f;
        }
        setDeltaMovement(motion.scale(drag).normalize());

        pos = position();
        setPos(pos.x, pos.y, pos.z);

        if (!isAlive()) {
            return;
        }
        this.liveTime++;

        Entity owner = getOwner();
        if (owner == null || !owner.isAlive() || !(owner instanceof Player)) {
            if (!level().isClientSide) {
                while (isInWall()) {
                    setPos(getX(), getY() + 1, getZ());
                }
                spawnAtLocation(getItem(), 0);
                discard();
            }
            return;
        }

        if (!isReturning()) {
            if (this.liveTime > getLifetimeLimit()) {
                setReturning(true);
            }
        } else {
            List<ItemEntity> items = level().getEntitiesOfClass(ItemEntity.class, getBoundingBox().inflate(2));
            List<ExperienceOrb> xpOrbs = level().getEntitiesOfClass(ExperienceOrb.class, getBoundingBox().inflate(2));

            for (ItemEntity item : items) {
                if (!item.isPassenger()) {
                    item.startRiding(this);
                    item.setPickUpDelay(5);
                }
            }
            for (ExperienceOrb xpOrb : xpOrbs) {
                if (!xpOrb.isPassenger()) {
                    xpOrb.startRiding(this);
                }
            }

            Vec3 ownerPos = owner.position().add(0, 1, 0);
            Vec3 toOwner = ownerPos.subtract(pos);
            double motionMag = 3.25 + 1 * 0.25;

            if (toOwner.lengthSqr() < motionMag) {
                Player player = (Player) owner;
                Inventory inventory = player.getInventory();
                ItemStack currentStack = inventory.getItem(slot);

                if (!level().isClientSide) {
                    if (!getItem().isEmpty()) {
                        if (player.isAlive() && currentStack.isEmpty()) {
                            inventory.setItem(slot, getItem());
                        } else if (!player.isAlive() || !inventory.add(getItem())) {
                            player.drop(getItem(), false);
                        }
                    }

                    if (player.isAlive()) {
                        for (ItemEntity item : items) {
                            if (item.isAlive()) {
                                giveItemToPlayer(player, item);
                            }
                        }
                        for (ExperienceOrb xpOrb : xpOrbs) {
                            if (xpOrb.isAlive()) {
                                xpOrb.playerTouch(player);
                            }
                        }
                        for (Entity riding : getPassengers()) {
                            if (!riding.isAlive()) continue;
                            if (riding instanceof ItemEntity) {
                                giveItemToPlayer(player, (ItemEntity) riding);
                            } else if (riding instanceof ExperienceOrb) {
                                riding.playerTouch(player);
                            }
                            if (riding instanceof ItemEntity && !inventory.add(getItem())) {
                                riding.dismountTo(getX(), getY(), getZ());
                            }
                        }
                    }
                    discard();
                }
            } else {
                setDeltaMovement(toOwner.normalize().scale(1.025f).normalize());
            }
        }
    }

    private float normalizeAngle(float angle) {
        return ((angle + 180.0f) % 360.0f + 360.0f) % 360.0f - 180.0f;
    }

    private void giveItemToPlayer(Player player, ItemEntity itemEntity) {
        itemEntity.setPickUpDelay(0);
        itemEntity.playerTouch(player);

        if (itemEntity.isAlive()) {
            ItemStack drop = itemEntity.getItem();

            player.drop(drop, false);
            itemEntity.discard();
        }
    }

    @Nullable
    protected EntityHitResult raycastEntities(Vec3 from, Vec3 to) {
        return ProjectileUtil.getEntityHitResult(level(), this, from, to, getBoundingBox().expandTowards(getDeltaMovement()).inflate(1),
                (entity) -> !entity.isSpectator() && entity.isAlive() && (entity.isPickable() || entity instanceof BoomerangProjectile) && entity != getOwner());
    }

    @Override
    protected boolean canAddPassenger(@Nonnull Entity passenger) {
        return super.canAddPassenger(passenger) || passenger instanceof ItemEntity || passenger instanceof ExperienceOrb || passenger instanceof BombEntity;
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0;
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setReturning(compound.getBoolean("returning"));
        liveTime = compound.getInt("liveTime");
        slot = compound.getInt("returnSlot");
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("returning", isReturning());
        pCompound.putInt("liveTime", liveTime);
        pCompound.putInt("returnSlot", slot);
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(RETURNING, false);
    }

    protected void setReturning(boolean returning) {
        entityData.set(RETURNING, returning);
    }


    public boolean isReturning() {
        return entityData.get(RETURNING);
    }

    public SoundEvent getHitBlockSound() {
        return SoundEvents.WOOD_HIT;
    }
}
