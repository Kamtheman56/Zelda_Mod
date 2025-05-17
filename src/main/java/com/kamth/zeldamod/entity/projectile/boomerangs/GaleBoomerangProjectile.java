package com.kamth.zeldamod.entity.projectile.boomerangs;

import com.kamth.zeldamod.entity.projectile.bombs.BombEntity;
import com.kamth.zeldamod.item.ZeldaItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;


public class GaleBoomerangProjectile extends AbstractBoomerang {
    public GaleBoomerangProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public GaleBoomerangProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel, Player player, int returnSlot) {
        super(pEntityType, pLevel, player, returnSlot);
    }

    @Override
    protected float getDamage() {
        return 4;
    }

    @Override
    protected float getLifetimeLimit() {
        return 18;
    }

    @Override
    public ItemStack getItem() {
        return ZeldaItems.GALE_BOOMERANG.get().getDefaultInstance();
    }




    @Override
    public void tick() {
        Vec3 pos = position();
        this.xOld = pos.x;
        this.yOld = pos.y;
        this.zOld = pos.z;

        int particlesDensity = 3;
        float particlesSpeed = 0.1F;
        float particlesSpread = 0.6F;

        for (int i = 0; i < particlesDensity; i++) {
            double particleX = getX() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleY = getY() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleZ = getZ() + (random.nextFloat() * 3 - 1) * particlesSpread;
            double particleMotionX = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionY = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionZ = (random.nextFloat() * 2 - 1) * particlesSpeed;
            this.level().addParticle(ParticleTypes.CLOUD, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
        }

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
            List<Monster> monsters = level().getEntitiesOfClass(Monster.class, getBoundingBox().inflate(4));
            List<Animal> animals = level().getEntitiesOfClass(Animal.class, getBoundingBox().inflate(4));

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
            for (Monster monster : monsters) {
                if (!monster.isPassenger()) {
                    monster.startRiding(this);
                }
            }
            for (Animal animal : animals) {
                if (!animal.isPassenger()) {
                    animal.startRiding(this);
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
                            if (riding instanceof LivingEntity) {
                                riding.dismountTo(getX(), getY(), getZ());
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

    @Override
    protected boolean canAddPassenger(@Nonnull Entity passenger) {
        return super.canAddPassenger(passenger) || passenger instanceof ItemEntity || passenger instanceof ExperienceOrb || passenger instanceof Monster || passenger instanceof BombEntity;
    }

}