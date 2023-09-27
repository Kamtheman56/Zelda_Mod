package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.sound.ModSounds;
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
import net.minecraft.world.entity.item.ItemEntity;
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


public class GaleBoomerangProjectile extends Projectile {
    private static final double BASE_DAMAGE = 4.0D;
    private static final EntityDataAccessor<ItemStack> STACK = SynchedEntityData.defineId(BoomerangProjectile.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Boolean> RETURNING = SynchedEntityData.defineId(BoomerangProjectile.class, EntityDataSerializers.BOOLEAN);
    private final ItemStack boomerangItem = new ItemStack(ModItems.BOOMERANG.get());



    boolean dealtDamage;
    public int boomerangreturn;
    protected int liveTime;
    private int slot;
    private int blockHitCount;
    private IntOpenHashSet entitiesHit;
    protected LivingEntity owner;
    private UUID ownerId;

    private static final String TAG_RETURNING = "returning";
    private static final String TAG_LIVE_TIME = "liveTime";
    private static final String TAG_BLOCKS_BROKEN = "hitCount";
    private static final String TAG_RETURN_SLOT = "returnSlot";
    private static final String TAG_ITEM_STACK = "itemStack";



    public GaleBoomerangProjectile(EntityType<? extends Projectile> type, Level worldIn, LivingEntity throwerIn) {
        super(type, worldIn);
        Vec3 pos = throwerIn.position();
        this.setPos(pos.x, pos.y + throwerIn.getEyeHeight(), pos.z);
        ownerId = throwerIn.getUUID();
    }

    public GaleBoomerangProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
        float f = -Mth.sin(rotationYawIn * ((float)Math.PI / 180F)) * Mth.cos(rotationPitchIn * ((float)Math.PI / 180F));
        float f1 = -Mth.sin((rotationPitchIn + pitchOffset) * ((float)Math.PI / 180F));
        float f2 = Mth.cos(rotationYawIn * ((float)Math.PI / 180F)) * Mth.cos(rotationPitchIn * ((float)Math.PI / 180F));
        this.shoot(f, f1, f2, velocity, inaccuracy);
        Vec3 Vector3d = entityThrower.getDeltaMovement();
        this.setDeltaMovement(this.getDeltaMovement().add(Vector3d.x, entityThrower.onGround() ? 0.0D : Vector3d.y, Vector3d.z));
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        Vec3 vec = (new Vec3(x, y, z)).normalize().add(this.random.nextGaussian() * 0.0075F * inaccuracy, this.random.nextGaussian() * 0.0075F * inaccuracy, this.random.nextGaussian() * 0.0075F * inaccuracy).scale(velocity);
        this.setDeltaMovement(vec);
        float f = (float) vec.horizontalDistance();
        setYRot((float)(Mth.atan2(vec.x, vec.z) * (180F / (float)Math.PI)));
        setXRot((float)(Mth.atan2(vec.y, f) * (180F / (float)Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.hurt(damageSources().generic(), (float) BASE_DAMAGE);
            setReturning();
        }
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void lerpMotion(double x, double y, double z) {
        this.setDeltaMovement(x, y, z);
        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            float f = (float) Math.sqrt(x * x + z * z);
            setYRot((float)(Mth.atan2(x, z) * (180F / (float)Math.PI)));
            setXRot((float)(Mth.atan2(y, f) * (180F / (float)Math.PI)));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }

    }


    @Override
    protected void defineSynchedData() {
        entityData.define(STACK, new ItemStack(ModItems.BOOMERANG.get()));
        entityData.define(RETURNING, false);
    }
    protected void checkImpact() {
        if(level().isClientSide){
            return;}
        Vec3 motion = getDeltaMovement();
        Vec3 position = position();
        Vec3 rayEnd = position.add(motion);

        boolean doEntities = true;
        int tries = 10;

        while(isAlive() && !isReturning()) {
            if(doEntities) {
                EntityHitResult result = raycastEntities(position, rayEnd);
                if(result != null)
                    onHit(result);
                else doEntities = false;
            } else {
                HitResult result = level().clip(new ClipContext(position, rayEnd, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
                if(result.getType() == HitResult.Type.MISS)
                    return;
                else {
                    onHit(result);
                }
            }
        }
    }

    @Nullable
    protected EntityHitResult raycastEntities(Vec3 from, Vec3 to) {
        return ProjectileUtil.getEntityHitResult(level(), this, from, to, getBoundingBox().expandTowards(getDeltaMovement()).inflate(1.0D), (entity) ->
                !entity.isSpectator()
                        && entity.isAlive()
                        && (entity.isPickable() || entity instanceof BoomerangProjectile)
                        && entity != getThrower()
                        && (entitiesHit == null || !entitiesHit.contains(entity.getId())));
    }
    @Override
    protected void onHit(@Nonnull HitResult result) {
        super.onHit(result);
        if (result.getType() == HitResult.Type.BLOCK && result instanceof BlockHitResult blockHitResult) {
            BlockPos hit = blockHitResult.getBlockPos();
            BlockState state = level().getBlockState(hit);
            clank();
        }}

    public void clank () {
        setReturning();
    }
    protected void setReturning() {
        entityData.set(RETURNING, true);
    }
    @Override
    public boolean isPushedByFluid() {
        return false;
    }
    @Nullable
    public LivingEntity getThrower() {
        if (this.owner == null && this.ownerId != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerId);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            } else {
                this.ownerId = null;
            }
        }

        return this.owner;
    }
    public ItemStack getStack() {
        return entityData.get(STACK);
    }

    public void setStack(ItemStack stack) {
        entityData.set(STACK, stack);
    }

    @Override
    protected boolean canAddPassenger(@Nonnull Entity passenger) {
        return super.canAddPassenger(passenger) || passenger instanceof ItemEntity || passenger instanceof ExperienceOrb
                || passenger instanceof Mob;
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0;
    }
    public boolean isReturning() {
        return entityData.get(RETURNING);
    }
    public void setThrowData(int slot, ItemStack stack) {
        this.slot = slot;
        setStack(stack.copy());
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

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderAtSqrDistance(double distance) {
        double d0 = this.getBoundingBox().getSize() * 4.0D;
        if (Double.isNaN(d0)) d0 = 4.0D;

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }
    @Override
    public void tick() {
        super.tick();
        int particlesDensity = 3;
        float particlesSpeed = 0.1F;
        float particlesSpread = 0.6F;

        for (int i = 0; i < particlesDensity; i++)
        {
            double particleX = getX() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleY = getY() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleZ = getZ() + (random.nextFloat() * 3 - 1) * particlesSpread;
            double particleMotionX = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionY = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionZ = (random.nextFloat() * 2 - 1) * particlesSpeed;
            this.level().addParticle(ParticleTypes.CLOUD, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
        }
        Vec3 pos = position();

        this.xOld = pos.x;
        this.yOld = pos.y;
        this.zOld = pos.z;
        super.tick();

        if(!isReturning())
            checkImpact();


        Vec3 ourMotion = this.getDeltaMovement();
        setPos(pos.x + ourMotion.x, pos.y + ourMotion.y, pos.z + ourMotion.z);

        float f = (float) ourMotion.horizontalDistance();
        setYRot((float)(Mth.atan2(ourMotion.x, ourMotion.z) * (180F / (float)Math.PI)));

        setXRot((float)(Mth.atan2(ourMotion.y, f) * (180F / (float)Math.PI)));
        while (this.getXRot() - this.xRotO < -180.0F) this.xRotO -= 360.0F;

        while(this.getXRot() - this.xRotO >= 180.0F) this.xRotO += 360.0F;

        while(this.getYRot() - this.yRotO < -180.0F) this.yRotO -= 360.0F;

        while(this.getYRot() - this.yRotO >= 180.0F) this.yRotO += 360.0F;

        setXRot(Mth.lerp(0.2F, this.xRotO, this.getXRot()));
        setYRot(Mth.lerp(0.2F, this.yRotO, this.getYRot()));
        pos = position();
        this.setPos(pos.x, pos.y, pos.z);

        if(!isAlive())
            return;

        ItemStack stack = getStack();

        boolean returning = isReturning();
        liveTime++;

        LivingEntity owner = getThrower();
        if(owner == null || !owner.isAlive() || !(owner instanceof Player)) {
            if(!level().isClientSide) {
                while(isInWall())
                    setPos(getX(), getY() + 1, getZ());
                spawnAtLocation(stack, 0);
                discard();
            }

            return;
        }

        if(!returning) {
            if(liveTime > 18)
                setReturning();}
        else {
            noPhysics = true;
            List<ItemEntity> items = level().getEntitiesOfClass(ItemEntity.class, getBoundingBox().inflate(2));
            List<ExperienceOrb> xp = level().getEntitiesOfClass(ExperienceOrb.class, getBoundingBox().inflate(2));
            List<Mob> mob = level().getEntitiesOfClass(Mob.class, getBoundingBox().inflate(2));

            Vec3 ourPos = position();
            for(ItemEntity item : items) {
                if (item.isPassenger())
                    continue;
                item.startRiding(this);

                item.setPickUpDelay(5);
            }

            for(ExperienceOrb xpOrb : xp) {
                if (xpOrb.isPassenger())
                    continue;
                xpOrb.startRiding(this);
            }
            for(Mob mob2 : mob) {
                if (mob2.isPassenger())
                    continue;
                mob2.startRiding(this);
            }

            Vec3 ownerPos = owner.position().add(0, 1, 0);
            Vec3 motion = ownerPos.subtract(ourPos);
            double motionMag = 3.25 + 1 * 0.25;

            if(motion.lengthSqr() < motionMag) {
                Player player = (Player) owner;
                Inventory inventory = player.getInventory();
                ItemStack stackInSlot = inventory.getItem(slot);

                if(!level().isClientSide) {
                    if(!stack.isEmpty()) if (player.isAlive() && stackInSlot.isEmpty())
                        inventory.setItem(slot, stack);
                    else if (!player.isAlive() || !inventory.add(stack))
                        player.drop(stack, false);

                    if (player.isAlive()) {
                        for (ItemEntity item : items)
                            if(item.isAlive())
                                giveItemToPlayer(player, item);

                        for (ExperienceOrb xpOrb : xp)
                            if(xpOrb.isAlive())
                                xpOrb.playerTouch(player);

                        for (Entity riding : getPassengers()) {
                            if (!riding.isAlive())
                                continue;

                            if (riding instanceof ItemEntity)
                                giveItemToPlayer(player, (ItemEntity) riding);
                            else if (riding instanceof ExperienceOrb)
                                riding.playerTouch(player);
                        }
                    }

                    discard();
                }
            } else
                setDeltaMovement(motion.normalize().scale(0.7 + 1f * 0.325F));
        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        Direction direction = ray.getDirection();
        BlockPos blockpos = ray.getBlockPos();
        BlockPos blockpos1 = blockpos.relative(direction);
        BlockState blockHit = this.level().getBlockState(ray.getBlockPos());
        this.dowseFire(blockpos1);
        this.dowseFire(blockpos1.relative(direction.getOpposite()));
        for(Direction direction1 : Direction.Plane.HORIZONTAL) {
            this.dowseFire(blockpos1.relative(direction1));}
        BlockState state = this.level().getBlockState(this.blockPosition());
        if (state.is(Blocks.LEVER)) {
            this.level().setBlockAndUpdate(blockPosition(), state.cycle(LeverBlock.POWERED));
        }
        if (blockHit.is(BlockTags.LEAVES)){
            this.level().destroyBlock(ray.getBlockPos(), true);
        }
        if (!blockHit.is(BlockTags.LEAVES)){
            setReturning();
        }

    }



    @Override
    public void playerTouch(@NotNull Player pEntity) {
        if (this.ownedBy(pEntity) || this.getOwner() == null) {
            super.playerTouch(pEntity);
        }


    }
    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag compound) {
        entityData.set(RETURNING, compound.getBoolean(TAG_RETURNING));
        liveTime = compound.getInt(TAG_LIVE_TIME);
        blockHitCount = compound.getInt(TAG_BLOCKS_BROKEN);
        slot = compound.getInt(TAG_RETURN_SLOT);

        if (compound.contains(TAG_ITEM_STACK))
            setStack(ItemStack.of(compound.getCompound(TAG_ITEM_STACK)));
        else
            setStack(new ItemStack(ModItems.BOOMERANG.get()));

        if (compound.contains("owner", 10)) {
            Tag owner = compound.get("owner");
            if (owner != null)
                this.ownerId = NbtUtils.loadUUID(owner);
        }
    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean(TAG_RETURNING, isReturning());
        pCompound.putInt(TAG_LIVE_TIME, liveTime);
        pCompound.putInt(TAG_BLOCKS_BROKEN, blockHitCount);
        pCompound.putInt(TAG_RETURN_SLOT, slot);

        pCompound.put(TAG_ITEM_STACK, getStack().serializeNBT());
        if (this.ownerId != null)
            pCompound.put("owner", NbtUtils.createUUID(this.ownerId));
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }
    protected boolean canHitEntity(Entity p_37135_) {
        return super.canHitEntity(p_37135_) || p_37135_.isAlive() && p_37135_ instanceof ItemEntity;
    }
    private void dowseFire(BlockPos pPos) {
        BlockState blockstate = this.level().getBlockState(pPos);
        if (blockstate.is(BlockTags.FIRE)) {
            this.level().removeBlock(pPos, false);
        } else if (AbstractCandleBlock.isLit(blockstate)) {
            AbstractCandleBlock.extinguish(null, blockstate, this.level(), pPos);
        } else if (CampfireBlock.isLitCampfire(blockstate)) {
            this.level().levelEvent(null, 1009, pPos, 0);
            CampfireBlock.dowse(this.getOwner(), this.level(), pPos, blockstate);
            this.level().setBlockAndUpdate(pPos, blockstate.setValue(CampfireBlock.LIT, Boolean.valueOf(false)));
        }

    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.WOOD_HIT;
    }
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}








