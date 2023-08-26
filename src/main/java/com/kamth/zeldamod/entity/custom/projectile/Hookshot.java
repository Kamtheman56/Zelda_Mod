package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class Hookshot extends AbstractArrow {
    private static final double BASE_DAMAGE = 1.0D;
    private int explosionPower = 3;
    public Hookshot(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
        this.setBaseDamage(0);
    }

    public Hookshot(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public Hookshot(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    private static final DataParameter<Integer> HOOKED_ENTITY_ID = EntityDataManager.defineId(Hookshot.class, DataSerializers.INT);
    boolean useBlockList = true;
    private double maxRange = 0D;
    private double maxSpeed = 0D;
    private boolean isPulling = false;
    private Player owner;
    private Entity hookedEntity;
    private ItemStack stack;
    private boolean motionUp = false;
    private double prevDistance = 30.D;


    

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOOKED_ENTITY_ID, 0);
    }

    /**
     * This is where everything related to movement happens.
     */
    @Override
    public void tick() {
        super.tick();
        if(this.tickCount % 3 == 0)
        {
            BlockPos currentPos = this.blockPosition();
            this.level.playSound(null, currentPos.getX(), currentPos.getY(), currentPos.getZ(), SoundInit.HOOKSHOT_EXTENDED.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
        }

        if (getOwner() instanceof PlayerEntity) {
            owner = (PlayerEntity) getOwner();

            if (isPulling && tickCount % 2 == 0) { //This is the sound that sounds when the hook is moving you.
                //level.playSound(null, owner.blockPosition(), SoundEvents.AXE_STRIP, SoundCategory.PLAYERS, 1F, 1F);
            }
            if (!level.isClientSide) {
                if (this.hookedEntity != null) { //In case the mob you are hooked to dies while you go towards it ..
                    if (isAlive()) {
                        this.hookedEntity = null;
                        onRemovedFromWorld();
                    } else {
                        this.absMoveTo(this.hookedEntity.getX(), this.hookedEntity.getY(0.8D), this.hookedEntity.getZ());
                    }
                }

                if (owner != null) { //Reasons to remove the hook.
                    if (owner.isDeadOrDying() || this.tickCount == 35 || !HookModel.get(owner).getHasHook() ||
                            !HookModel.get(owner).getHasHook() || owner.distanceTo(this) > maxRange ||
                            !(owner.getMainHandItem().getItem() instanceof HookshotItem ||
                                    owner.getOffhandItem().getItem() instanceof HookshotItem) ||
                            !HookModel.get(owner).getHasHook()) {

                        sprite = false;
                        kill();

                    }
                } else {
                    sprite = false;
                    kill();
                }

                if (owner.getMainHandItem() == stack || owner.getOffhandItem() == stack) {
                    if (isPulling) { //Movement start
                        Entity target = owner;
                        Entity origin = this;

                        if (owner.isCrouching() && hookedEntity != null) {
                            target = hookedEntity;
                            origin = owner;
                            owner.setNoGravity(true);
                        }

                        double brakeZone = ((6D * (maxSpeed)) / 10); //5
                        double pullSpeed = (maxSpeed) / 9D;
                        Vector3d distance = origin.position().subtract(target.position().add(0, target.getBbHeight() / 2, 0));
                        double reduction = (pullSpeed); //Get motion reduction.
                        Vector3d motion = distance.normalize().multiply(reduction, reduction, reduction); //Get last motion.

                        //In case the movement is at ground level.
                        if (Math.abs(distance.y) < 0.1D) {
                            motion = new Vector3d(motion.x, 0, motion.z);
                        }
                        //In case the movement is only upwards.
                        else if (new Vector3d(distance.x, 0, distance.z).length() < new Vector3d(target.getBbWidth() / 2, 0, target.getBbWidth() / 2).length() / 1.4) {
                            motion = new Vector3d(0, motion.y, 0);
                            motionUp = true;
                        }

                        target.fallDistance = 0; //Cancel Fall Damage

                        target.setDeltaMovement(motion); //Set motion.
                        target.hurtMarked = true; //Make motion works, this is necessary.

                        //Makes you off the hook early if entity.

                        if(hookedEntity != null){
                            motion = owner.getDeltaMovement();
                            if (distance.length() > prevDistance && prevDistance < 1){
                                kill();
                                sprite = false;
                                HookModel.get(owner).setHasHook(false);
                            }
                            //Timer if the entity if too BIG.
                            if(tickCount > 50){
                                kill();
                                sprite = false;
                                HookModel.get(owner).setHasHook(false);
                            }
                        }
                        //Makes you off the hook early if block.
                        if(hookedEntity == null) {
                            motion = owner.getDeltaMovement();
                            if (distance.length() > prevDistance && prevDistance < 1){
                                kill();
                                sprite = false;
                                HookModel.get(owner).setHasHook(false);
                            } else if (new Vector3d(distance.x, 0, distance.z).length() < 0.3D) {
                                kill();
                                sprite = false;
                                HookModel.get(owner).setHasHook(false);

                            }
                        }
                        prevDistance = distance.length();

                        //Take the entity if it is an item and check that it is in your inventory to kill the hook.
                        if(hookedEntity instanceof ItemEntity){
                            if(owner.inventory.add(((ItemEntity) hookedEntity).getItem())) {
                                sprite = false;
                                HookModel.get(owner).setHasHook(false);
                                kill();

                            }
                        }

                    }

                } else {
                    sprite = false;
                    HookModel.get(owner).setHasHook(false);
                    kill();

                }
            }
        }
    }

    //Prevents a crash. Name self-explanatory.
    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public void kill() {
        if (!level.isClientSide && owner != null) {
            HookModel.get(owner).setHasHook(false);
            owner.setNoGravity(false);
            owner.setPose(Pose.STANDING);
            owner.setDeltaMovement(0, 0, 0);
        }
        owner.hurtMarked = true;
        super.kill();
    }

    /**
     * This function is used to make the hook go slower or faster in water.
     * Currently it has no value.
     */
    @Override
    protected float getWaterInertia() {
        return 1.0F;
    }

    /**
     * This function is used to detect when the hook hits an object.
     * It is also used to collect items from the ground.
     * @param blockHitResult
     */
    @Override
    protected void onHitBlock(BlockRayTraceResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        isPulling = true;
        setHookableBlocks(); //Loads the list of blocks to which it can be hooked.

        if (!level.isClientSide && owner != null && hookedEntity == null) {
            owner.setNoGravity(true);

            //Initialization of the list of ItemEntities found on the floor
            // and selection of the size of the Bounding Box in which to search for them.
            List<ItemEntity> list = level.getEntitiesOfClass(ItemEntity.class, this.getBoundingBox().expandTowards(1D, 0.5D, 1D));

            if (useBlockList) { //If this value is "FALSE" all blocks will be hooked.
                //If the block is not in the list, the hook does not hook.
                if (!hookableBlocks.contains(level.getBlockState(blockHitResult.getBlockPos()).getBlock())) {
                    HookModel.get(owner).setHasHook(false);
                    isPulling = false;
                    onRemovedFromWorld();
                }
                //Catch Items
                if(list != null && list.size() > 0){
                    for (Entity entity : list) {
                        hookedEntity = entity;
                    }
                    HookModel.get(owner).setHasHook(true);
                    isPulling = true;
                    onRemovedFromWorld();
                }
            }
        }
    }

    /**
     * This function is used to detect when the hook hits an entity.
     * @param entityHitResult
     */
    @Override
    protected void onHitEntity(EntityRayTraceResult entityHitResult) {
        if (!level.isClientSide && owner != null && entityHitResult.getEntity() != owner) {
            if((entityHitResult.getEntity() instanceof LivingEntity || entityHitResult.getEntity() instanceof EnderDragonPartEntity) && hookedEntity == null) {
                hookedEntity = entityHitResult.getEntity();
                entityData.set(HOOKED_ENTITY_ID, hookedEntity.getId() + 1);
                isPulling = true;
                owner.setNoGravity(true);

            }
        }
    }


    @Override
    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
        maxRange = tag.getDouble("maxRange");
        maxSpeed = tag.getDouble("maxSpeed");
        isPulling = tag.getBoolean("isPulling");
        stack = ItemStack.of(tag.getCompound("hookshotItem"));

        if (level.getEntity(tag.getInt("owner")) instanceof PlayerEntity)
            owner = (PlayerEntity) level.getEntity(tag.getInt("owner"));
    }



    @Override
    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        tag.putDouble("maxRange", maxRange);
        tag.putDouble("maxSpeed", maxSpeed);
        tag.putBoolean("isPulling", isPulling);
        tag.put("hookshotItem", stack.save(new CompoundNBT()));
        tag.putInt("owner", owner.getId());
    }

    /**
     * Used to get the properties from the item.
     */
    public void setProperties(ItemStack stack, double maxRange, double maxVelocity, float pitch, float yaw, float roll, float modifierZ) {
        float f = 0.017453292F;
        float x = -MathHelper.sin(yaw * f) * MathHelper.cos(pitch * f);
        float y = -MathHelper.sin((pitch + roll) * f);
        float z = MathHelper.cos(yaw * f) * MathHelper.cos(pitch * f);
        this.shoot(x, y, z, modifierZ, 0);

        this.stack = stack;
        this.maxRange = maxRange;
        this.maxSpeed = maxVelocity;
    }

    //Disable ChangeDimensions.
    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    //Make the entity appear in the level.


    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event) {
        PlayerEntity player = event.player;
        if (true) {
            double maxRange = 15;
            List<HookshotEntity> entities = player.level.getEntitiesOfClass(HookshotEntity.class, new AxisAlignedBB(player.blockPosition().offset(-maxRange, -maxRange, -maxRange), player.blockPosition().offset(maxRange, maxRange, maxRange)));
            for(HookshotEntity entity : entities) {
                if(entity.getOwner() == player) {
                    if (entity.isPulling) {
                        player.setPose(Pose.SWIMMING);
                        player.setSwimming(true);
                    }
                }
            }
        }
    }


@Override
protected SoundEvent getDefaultHitGroundSoundEvent() {
    return SoundEvents.TNT_PRIMED;
}
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
