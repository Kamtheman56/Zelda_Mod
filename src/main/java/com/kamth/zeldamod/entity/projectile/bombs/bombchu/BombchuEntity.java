package com.kamth.zeldamod.entity.projectile.bombs.bombchu;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.util.interfaces.entity.ISurfaceSticker;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

// Code contributed by Deadlydiamond98 (c) 2024 under the MIT License.
// Added here with explicit permission by the original owner.

public class BombchuEntity extends Entity implements ISurfaceSticker, TraceableEntity {

    protected static final EntityDataAccessor<Direction> ATTACHED_FACE_CLIENT = SynchedEntityData.defineId(BombchuEntity.class, EntityDataSerializers.DIRECTION);
    protected static final EntityDataAccessor<Boolean> GRAVITY_CLIENT = SynchedEntityData.defineId(BombchuEntity.class, EntityDataSerializers.BOOLEAN);

    protected static final EntityDataAccessor<Direction> PREV_ATTACHED_FACE = SynchedEntityData.defineId(BombchuEntity.class, EntityDataSerializers.DIRECTION);

    protected static final EntityDataAccessor<Integer> FUSE = SynchedEntityData.defineId(BombchuEntity.class, EntityDataSerializers.INT);

    private static final double SPEED = 0.25;

    private final float power;
    private Entity owner;

    private ForwardMove frontState = ForwardMove.NORMAL;
    private FloorAttachState attachedFace;
    private float frontDistance = 0;

    public BombchuEntity(EntityType<? extends Entity> entityType, Level world) {
        super(entityType, world);
        this.power = 3;
        this.setFuse(80);
        this.attachedFace = FloorAttachState.THROWN;
    }

    public BombchuEntity(Level world, double x, double y, double z, @Nullable Player player) {
        this(ModEntityTypes.BOMBCHU.get(), world);
        this.setPos(x, y, z);
        this.setOwner(player);
    }

    @Override
    public boolean isNoGravity() {
        boolean hasGravity = this.attachedFace.canApplyGravity();
        setGravityClient(hasGravity);
        return !hasGravity;
    }

    @Override
    public void tick() {
        applyGravity();

        super.tick();
        this.tickMovement();

        this.manageFuse();

        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    private void manageFuse() {
        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.level().isClientSide) {
                explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
        }
    }

    private void explode() {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.power, Level.ExplosionInteraction.NONE);

        this.discard();

        BlockPos explosionPos = this.blockPosition();

        int radius = (int) Math.ceil(this.power);

        for (BlockPos pos : BlockPos.betweenClosed(explosionPos.offset(-radius, -radius, -radius), explosionPos.offset(radius, radius, radius))) {

            BlockState blockState = this.level().getBlockState(pos).getBlock().defaultBlockState();

            if (blockState.is(ModTags.Blocks.BOMB)){
                this.level().destroyBlock(pos, false);
            }

            if (blockState.is(ModTags.Blocks.BOMB_FLOWER_BLOCKS)) {
                this.level().destroyBlock(pos, false);
                this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.power, Level.ExplosionInteraction.MOB);
            }
        }
    }

    protected void applyGravity() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
        }
    }

    protected void tickMovement() {
        if (!this.level().isClientSide()) {
            if (this.onGround() && this.attachedFace.canApplyGravity()) {
                this.attachedFace = FloorAttachState.FLOOR;
            }

            if (!this.attachedFace.canApplyGravity()) {
                updateFrontState(4);
                avoidEdge();
                updateAttachedFaceWithFloor();
            }

            this.setAttachedFaceClient(this.attachedFace.getDirection());
        }
        else {
            this.attachedFace = FloorAttachState.getFromDirection(this.getAttachedFaceClient());
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
    }

    private void updateAttachedFaceWithFloor() {
        this.setDeltaMovement(updateVelocityDirection(this.attachedFace, this.getYRot(), this.getXRot(), SPEED));

        double length = 0.51;

        HitResult centerCast = doRaycast(this.getCenterPos(), this.getDeltaMovement(), length);
        HitResult downCast = doRaycast(this.getCenterPos(), this.getYRot(), this.getXRot() + 90, length);

        boolean front = centerCast.getType() == HitResult.Type.BLOCK;
        boolean down = downCast.getType() == HitResult.Type.BLOCK;

        if (down && front) {
            rotateOnEdge((BlockHitResult) centerCast, (BlockHitResult) downCast);
        } else if (!down && !this.attachedFace.canApplyGravity()) {
            unstick();
        }
    }

    private void avoidEdge() {
        float rotAmount = this.frontState.getRotation(this.frontDistance);

        if (this.getXRot() > 90) {
            rotAmount *= -1;
        }

        this.setYRot(this.getYRot() + rotAmount);
    }

    private void rotateOnEdge(BlockHitResult frontHit, BlockHitResult downHit) {
        BlockState downBlock = this.level().getBlockState(downHit.getBlockPos());
        BlockState frontBlock = this.level().getBlockState(frontHit.getBlockPos());

        if (downBlock.isSolid() && frontBlock.isSolid()) {

            Direction direction = frontHit.getDirection().getOpposite();

            FloorAttachState prevOldState = changeSide(getPrevAttachedFaceClient());
            setPrevAttachedFaceClient(this.attachedFace.getDirection());

            FloorAttachState newState = changeSide(direction);

            boolean wallToWall = this.attachedFace.isWall() && newState.isWall();

            int pitch = 0;
            int yaw = 0;

            if (!wallToWall) {
                pitch = 90;

                if (newState.ceiling() && prevOldState.isWall()) {
                    yaw = -rotateWallToWall(prevOldState, this.attachedFace);
                }
            }
            else {
                yaw = rotateWallToWall(this.attachedFace, newState);
            }

            this.setRot(this.getYRot() - yaw, this.getXRot() - pitch);
            this.attachedFace = newState;

            Vec3 newVelocity = updateVelocityDirection(this.attachedFace, this.getYRot(), this.getXRot(), SPEED);

            this.move(MoverType.SELF, new Vec3(this.attachedFace.getDirection().step()));

            this.setDeltaMovement(newVelocity);
        }
    }

    private void updateFrontState(int rayCastLength) {
        HitResult centerCast = doRaycast(this.getCenterPos(), this.getYRot(), this.getXRot(), rayCastLength);

        double halfWidth = (this.getBoundingBox().getSize() + 0.1) / 2.0;
        Vec3 sideOffset = getPerpendicularOffset(this.getYRot(), halfWidth);

        Vec3 leftStart = this.getCenterPos().add(sideOffset);
        Vec3 rightStart = this.getCenterPos().subtract(sideOffset);

        HitResult leftCast = doRaycast(leftStart, this.getYRot(), this.getXRot(), rayCastLength);
        HitResult rightCast = doRaycast(rightStart, this.getYRot(), this.getXRot(), rayCastLength);

        boolean center = centerCast.getType() == HitResult.Type.BLOCK;
        boolean left = leftCast.getType() == HitResult.Type.BLOCK;
        boolean right = rightCast.getType() == HitResult.Type.BLOCK;

        if (left && !right && !center) {
            this.frontState = ForwardMove.RIGHT;
            setFrontDistance((BlockHitResult) leftCast);
        }
        else if (right && !left && !center) {
            this.frontState = ForwardMove.LEFT;
            setFrontDistance((BlockHitResult) rightCast);
        }
        else {
            this.frontState = ForwardMove.NORMAL;
        }
    }

    private void setFrontDistance(BlockHitResult hitResult) {
        this.frontDistance = (float) hitResult.getBlockPos().getCenter().distanceTo(this.position());
    }

    public void unstick() {
        boolean bl = this.getXRot() != 0;

        setPrevAttachedFaceClient(this.attachedFace.getDirection());
        this.setXRot(0);
        this.setYRot(this.attachedFace.ceiling() ? this.getYRot() - 180 : this.getYRot());
        this.attachedFace = FloorAttachState.DETACHED;

        if (bl) {
            this.setPos(this.position().add(0, 0.25, 0));

            this.move(MoverType.SELF, updateVelocityDirection(FloorAttachState.FLOOR, this.getYRot(), this.getXRot(), SPEED));

            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    public void setFuse(int fuse) {
        this.entityData.set(FUSE, fuse);
    }

    public int getFuse() {
        return this.entityData.get(FUSE);
    }

    @Nullable
    @Override
    public Entity getOwner() {
        return this.owner;
    }
    public void setOwner(Entity owner) {
        this.owner = owner;
    }
    public float getPower() {
        return this.power;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("AttachedFace", this.attachedFace.getName());
        pCompound.putBoolean("GravityClient", this.getGravityClient());
        pCompound.putShort("Fuse", (short)this.getFuse());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("AttachedFace")) {
            this.attachedFace = this.attachedFace.getByName(pCompound.getString("AttachedFace"));
        }
        if (pCompound.contains("GravityClient")) {
            this.setGravityClient(pCompound.getBoolean("GravityClient"));
        }
        this.setFuse(pCompound.getShort("Fuse"));
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(ATTACHED_FACE_CLIENT, Direction.DOWN);
        this.entityData.define(PREV_ATTACHED_FACE, Direction.DOWN);
        this.entityData.define(GRAVITY_CLIENT, true);
        this.entityData.define(FUSE, 80);
    }

    public Direction getAttachedFaceClient() {
        return this.entityData.get(ATTACHED_FACE_CLIENT);
    }

    private void setAttachedFaceClient(Direction face) {
        this.entityData.set(ATTACHED_FACE_CLIENT, face);
    }

    public Direction getPrevAttachedFaceClient() {
        return this.entityData.get(PREV_ATTACHED_FACE);
    }

    private void setPrevAttachedFaceClient(Direction face) {
        this.entityData.set(PREV_ATTACHED_FACE, face);
    }

    public boolean getGravityClient() {
        return this.entityData.get(GRAVITY_CLIENT);
    }

    private void setGravityClient(boolean bool) {
        this.entityData.set(GRAVITY_CLIENT, bool);
    }

    @Override
    public Level getRaycastWorld() {
        return this.level();
    }

    @Override
    public Vec3 getRaycastPos() {
        return this.position();
    }

    @Override
    public float getRaycastHeight() {
        return this.getBbHeight();
    }
}



