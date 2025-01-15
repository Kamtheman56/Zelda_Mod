package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.items.grapples.ClawshotItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;

import javax.annotation.Nullable;

public class Clawshot extends AbstractArrow {
    private static final double BASE_DAMAGE = 5.0D;
    private final ItemStack hookshot = new ItemStack(ModItems.CLAWSHOT.get());
    boolean isPulling = false;
    private Entity hookedEntity;
    private double maxRange = 0D;
    private double maxSpeed = 0D;

    private Player owner;

    private boolean motionUp = false;
    private double prevDistance = 30.D;
    private ItemStack stack;

    public Clawshot(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
       this.pickup = Pickup.DISALLOWED;

    }


    public Clawshot(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    public Clawshot(Level world, Player user) {
        super(ModEntityTypes.CLAWSHOT.get(), user, world);
        this.pickup = Pickup.DISALLOWED;
    }
    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        int particlesDensity = 1;
        float particlesSpeed = 0.1F;
        float particlesSpread = 0.0F;

        for (int i = 0; i < particlesDensity; i++)
        {
            double particleX = getX() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleY = getY() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleZ = getZ() + (random.nextFloat() * 3 - 1) * particlesSpread;
            double particleMotionX = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionY = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionZ = (random.nextFloat() * 2 - 1) * particlesSpeed;
            this.level().addParticle(ParticleTypes.CRIT, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
        }



        if (getOwner() instanceof Player) {
            owner = (Player) getOwner();

            if (isPulling && tickCount % 4 == 0) {
                BlockPos currentPos = this.owner.blockPosition();
                this.level().playSound(null, currentPos.getX(), currentPos.getY(), currentPos.getZ(), SoundEvents.CHAIN_HIT, SoundSource.PLAYERS, .5f, 1.0f);

            }
            if (!level().isClientSide) {
                if (this.hookedEntity != null) {
                    if (isAlive()) {
                        this.hookedEntity = null;
                        onRemovedFromWorld();
                    } else {
                        this.absMoveTo(this.hookedEntity.getX(), this.hookedEntity.getY(0.8D), this.hookedEntity.getZ());
                    }
                }

                if (owner != null) {
                    if (owner.isDeadOrDying() || this.tickCount == 50 ||
                            !(owner.getMainHandItem().getItem() instanceof ClawshotItem ||
                                    owner.getOffhandItem().getItem() instanceof ClawshotItem)){
                        kill();
                    }
                    if    (owner.distanceTo(this) > maxRange){
                        this.discard();
                    }
                }
                if (this.level().getBlockState(this.blockPosition()).is(ModTags.Blocks.CLAWSHOT)){
                        isPulling = true;
                        this.setDeltaMovement(0,0,0);
                }

                if (owner.getMainHandItem() == stack || owner.getOffhandItem() == stack) {
                    if (isPulling) { //Movement start
                        Entity target = owner;
                        Entity origin = this;
                        owner.setNoGravity(false);
                        if ( hookedEntity != null) {
                            target = hookedEntity;
                            origin = owner;
                            owner.setNoGravity(true);
                        }

                        double brakeZone = ((6D * (maxSpeed)) / 10); //5
                        double pullSpeed = (maxSpeed) / 9D;
                        Vec3 distance = origin.position().subtract(target.position().add(0, target.getBbHeight() / 2, 0));
                        double reduction = (pullSpeed); //Get motion reduction.
                        Vec3 motion = distance.normalize().multiply(reduction, reduction, reduction); //Get last motion.
                        //In case the movement is at ground level.
                        if (Math.abs(distance.y) < 0.1D) {
                            motion = new Vec3(motion.x, 0, motion.z);
                        }
                        //In case the movement is only upwards.
                        else if (new Vector3d(distance.x, 0, distance.z).length() < new Vector3d(target.getBbWidth() / 2, 0, target.getBbWidth() / 2).length() / 1.4) {
                            motion = new Vec3(0, motion.y, 0);
                            boolean motionUp = true;
                        }

                        target.fallDistance = 0; //Cancel Fall Damage
                        target.setDeltaMovement(motion); //Set motion.
                        target.hurtMarked = true; //Make motion works, this is necessary.

                        //Makes you off the hook early if entity.

                        if(hookedEntity != null){
                            motion = owner.getDeltaMovement();
                            if (distance.length() > prevDistance && prevDistance < 1){
                                kill();
                            }
                            //Timer if the entity is too BIG.
                            if(tickCount > 50){
                                this.discard();
                                // kill();
                            }}
                        //Makes you off the hook early if block.
                        if(hookedEntity == null) {
                            motion = owner.getDeltaMovement();
                            if (distance.length() > prevDistance && prevDistance < 1){
                                kill();
                            } else if (new Vector3d(distance.x, 0, distance.z).length() < 0.3D) {
                                kill();}
                        }
                        prevDistance = distance.length();
                    }
                }
            }
        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        BlockState blockHit = this.level().getBlockState(blockHitResult.getBlockPos());
        if (blockHit.is(ModTags.Blocks.CLAWSHOT)) {
            isPulling = true;
            if (!level().isClientSide && owner != null && hookedEntity == null) {
                owner.setNoGravity(false);}
        }
        else this.discard();
    }
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
  super.onHitEntity(entityHitResult);
    }

    public void Properties(ItemStack stack, double maxRange, double maxVelocity, float pitch, float yaw, float roll, float modifierZ) {
        float f = 0.017453292F;
        float x = (float) (-Math.sin(yaw * f) * Math.cos(pitch * f));
        float y = (float) -Math.sin((pitch + roll) * f);
        float z = (float) (Math.cos(yaw * f) * Math.cos(pitch * f));
        this.shoot(x, y, z, modifierZ, 0);
        this.stack = stack;
        this.maxRange = maxRange;
        this.maxSpeed = maxVelocity;
    }

    @Override
    public void kill() {
        if (!level().isClientSide && owner != null) {
            isPulling=false;
            owner.setNoGravity(false);
            owner.setPose(Pose.STANDING);
            owner.setDeltaMovement(0, 0, 0);
        }
        owner.hurtMarked = true;
        super.kill();
    }

    @Override
    public boolean isNoGravity()
    {
        return true;
    }
    public void setOwner(@Nullable Entity pOwner) {
        super.setOwner(pOwner);
    }
    @Nullable
    public Player getPlayerOwner() {
        Entity entity = this.getOwner();
        return entity instanceof Player ? (Player)entity : null;
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.METAL_HIT;
    }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);}
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        maxRange = tag.getDouble("maxRange");
        maxSpeed = tag.getDouble("maxSpeed");
        isPulling = tag.getBoolean("isPulling");
        stack = ItemStack.of(tag.getCompound("hookshotItem"));

        if (level().getEntity(tag.getInt("owner")) instanceof Player)
            owner = (Player) level().getEntity(tag.getInt("owner"));
    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.put("hookshot", this.hookshot.save(new CompoundTag()));

        pCompound.putBoolean("Pulling", this.isPulling);
    }



    @Override
    protected ItemStack getPickupItem() {
        return  ItemStack.EMPTY;}
    protected float getWaterInertia() {
        return 1F;
    }
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

