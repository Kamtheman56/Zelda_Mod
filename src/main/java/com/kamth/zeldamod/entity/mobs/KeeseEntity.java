package com.kamth.zeldamod.entity.mobs;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class KeeseEntity extends FlyingMob implements Enemy {
    private static final int FLAG_IS_CHARGING = 1;
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(KeeseEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SITTING =
            SynchedEntityData.defineId(KeeseEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CHARGING =
            SynchedEntityData.defineId(KeeseEntity.class, EntityDataSerializers.BOOLEAN);

    public KeeseEntity(EntityType<? extends KeeseEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new KeeseMoveControl2(this);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
    }


    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Monster.class, EntityDataSerializers.BYTE);
    public final AnimationState idleAnimationState = new AnimationState();
   public final AnimationState attackAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;



    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new KeeseEntity.RandomFloatAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.goalSelector.addGoal(7, new KeeseEntity.FlyingAttackGoal());
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));


    }
    @Override
    public void tick() {
        super.tick();
   //    this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
        if (this.level().isClientSide) {
            setupAnimationStates();
        }
    }

    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.EVENTS;
    }

    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    private void setupAnimationStates() {
            this.idleAnimationState.start(this.tickCount);

        if (this.isAttacking() && attackAnimationTimeout<= 0){
            attackAnimationTimeout = 0;
            attackAnimationState.start(tickCount);
        } else {
            --this.attackAnimationTimeout;
        }
        if (!this.isAttacking()){
            this.attackAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
    }

    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        super.populateDefaultEquipmentSlots(pRandom, pDifficulty);
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ZeldaItems.MAGIC_SWORD.get()));
                 this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
        }



    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1)
                .add(Attributes.KNOCKBACK_RESISTANCE, -4f)
                .add(Attributes.MOVEMENT_SPEED, .3f)
                .add(Attributes.FLYING_SPEED, .4f)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.8f)
                .add(Attributes.ATTACK_SPEED, 1);
    }

    public void setAttacking(boolean attacking){
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking(){
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData(){
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    protected boolean shouldDespawnInPeaceful() {
        return true;
    }



    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.BAT_HURT;
    }
    @Nullable
    public SoundEvent getAmbientSound() {
        return  this.random.nextInt(4) != 0 ? null : SoundEvents.BAT_AMBIENT;
    }

@Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BAT_DEATH;
    }

    static class RandomFloatAroundGoal extends Goal {
        private final KeeseEntity keese;

        public RandomFloatAroundGoal(KeeseEntity pKeese) {
            this.keese = pKeese;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            MoveControl movecontrol = this.keese.getMoveControl();
            if (!movecontrol.hasWanted()) {
                return true;
            } else {
                double d0 = movecontrol.getWantedX() - this.keese.getX();
                double d1 = movecontrol.getWantedY() - this.keese.getY();
                double d2 = movecontrol.getWantedZ() - this.keese.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            RandomSource randomsource = this.keese.getRandom();
            double d0 = this.keese.getX() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.keese.getY() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.keese.getZ() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.keese.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }
    }


    public   class FlyingAttackGoal extends Goal {
        public FlyingAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            LivingEntity livingentity = KeeseEntity.this.getTarget();
            if (livingentity != null && livingentity.isAlive() && KeeseEntity.this.random.nextInt(reducedTickDelay(20)) == 0) {
                return KeeseEntity.this.distanceToSqr(livingentity) > 4.0D;
            } else {
                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return KeeseEntity.this.getMoveControl().hasWanted() && !KeeseEntity.this.isAttacking() && KeeseEntity.this.getTarget() != null && KeeseEntity.this.getTarget().isAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            LivingEntity livingentity = KeeseEntity.this.getTarget();
            if (livingentity != null) {
                Vec3 vec3 = livingentity.getEyePosition();
                KeeseEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 3.0D);
            }


          //  KeeseEntity.this.playSound(SoundEvents.VEX_CHARGE, 1.0F, 1.0F);
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        @Override
        public void stop() {
            KeeseEntity.this.setAttacking(false);
            super.stop();
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            LivingEntity livingentity = KeeseEntity.this.getTarget();
            if (livingentity != null) {
                if (KeeseEntity.this.getBoundingBox().inflate(1.3f).intersects(livingentity.getBoundingBox())) {
                    KeeseEntity.this.doHurtTarget(livingentity);
                    KeeseEntity.this.setAttacking(false);
                    stop();
                } else {
                    double d0 = KeeseEntity.this.distanceToSqr(livingentity);
                    if (d0 < 9.0D) {
                        Vec3 vec3 = livingentity.getEyePosition();
                        KeeseEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 3.0D);
                    }
                }

            }
        }
        private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
            return pDistToEnemySqr <= this.getAttackReachSqr(pEnemy);
        }
        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            return (double)(KeeseEntity.this.getBbWidth() * 4.0F * KeeseEntity.this.getBbWidth() * 4.0F + pAttackTarget.getBbWidth());
        }
    }

    class KeeseMoveControl extends MoveControl {
        public KeeseMoveControl(KeeseEntity pVex) {
            super(pVex);
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                Vec3 vec3 = new Vec3(this.wantedX - KeeseEntity.this.getX(), this.wantedY - KeeseEntity.this.getY(), this.wantedZ - KeeseEntity.this.getZ());
                double d0 = vec3.length();
                if (d0 < KeeseEntity.this.getBoundingBox().getSize()) {
                    this.operation = MoveControl.Operation.WAIT;
                    KeeseEntity.this.setDeltaMovement(KeeseEntity.this.getDeltaMovement().scale(0.5D));
                } else {
                    KeeseEntity.this.setDeltaMovement(KeeseEntity.this.getDeltaMovement().add(vec3.scale(this.speedModifier * 0.05D / d0)));
                    if (KeeseEntity.this.getTarget() == null) {
                        Vec3 vec31 = KeeseEntity.this.getDeltaMovement();
                        KeeseEntity.this.setYRot(-((float)Mth.atan2(vec31.x, vec31.z)) * (180F / (float)Math.PI));
                        KeeseEntity.this.yBodyRot = KeeseEntity.this.getYRot();
                    } else {
                        double d2 = KeeseEntity.this.getTarget().getX() - KeeseEntity.this.getX();
                        double d1 = KeeseEntity.this.getTarget().getZ() - KeeseEntity.this.getZ();
                        KeeseEntity.this.setYRot(-((float)Mth.atan2(d2, d1)) * (180F / (float)Math.PI));
                        KeeseEntity.this.yBodyRot = KeeseEntity.this.getYRot();
                    }
                }

            }
        }
    }
    static class KeeseMoveControl2 extends MoveControl {
        private final KeeseEntity ghast;
        private int floatDuration;

        public KeeseMoveControl2(KeeseEntity pGhast) {
            super(pGhast);
            this.ghast = pGhast;
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.ghast.getRandom().nextInt(5) + 2;
                    Vec3 vec3 = new Vec3(this.wantedX - this.ghast.getX(), this.wantedY - this.ghast.getY(), this.wantedZ - this.ghast.getZ());
                    double d0 = vec3.length();
                    vec3 = vec3.normalize();
                    if (this.canReach(vec3, Mth.ceil(d0))) {
                        this.ghast.setDeltaMovement(this.ghast.getDeltaMovement().add(vec3.scale(0.1D)));
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 pPos, int pLength) {
            AABB aabb = this.ghast.getBoundingBox();

            for(int i = 1; i < pLength; ++i) {
                aabb = aabb.move(pPos);
                if (!this.ghast.level().noCollision(this.ghast, aabb)) {
                    return false;
                }
            }

            return true;
        }
    }

    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return -pLevel.getPathfindingCostFromLightLevels(pPos);
    }

    /**
     * Static predicate for determining if the current light level and environmental conditions allow for a monster to
     * spawn.
     */
    public static boolean isDarkEnoughToSpawn(ServerLevelAccessor pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.getBrightness(LightLayer.SKY, pPos) > pRandom.nextInt(32)) {
            return false;
        } else {
            DimensionType dimensiontype = pLevel.dimensionType();
            int i = dimensiontype.monsterSpawnBlockLightLimit();
            if (i < 15 && pLevel.getBrightness(LightLayer.BLOCK, pPos) > i) {
                return false;
            } else {
                int j = pLevel.getLevel().isThundering() ? pLevel.getMaxLocalRawBrightness(pPos, 10) : pLevel.getMaxLocalRawBrightness(pPos);
                return j <= dimensiontype.monsterSpawnLightTest().sample(pRandom);
            }
        }
    }
    public static boolean checkKeeseSpawnRules(EntityType<KeeseEntity> pBat, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        if (pPos.getY() >= pLevel.getSeaLevel()) {
            return false;}
        else return pLevel.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn((ServerLevelAccessor) pLevel, pPos, pRandom) && checkMobSpawnRules(pBat, pLevel, pSpawnType, pPos, pRandom);
        }




}
