package com.kamth.zeldamod.entity.mobs.hostile.keese;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class KeeseEntity extends FlyingMob implements Enemy {

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(KeeseEntity.class, EntityDataSerializers.BOOLEAN);


    public KeeseEntity(EntityType<? extends KeeseEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new KeeseMoveControl(this);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
    }



    public final AnimationState idleAnimationState = new AnimationState();

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new KeeseEntity.RandomFloatAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 12));

        this.goalSelector.addGoal(7, new KeeseEntity.FlyingAttackGoal());
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }


    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.EVENTS;
    }

    public boolean isPushable() {
        return false;
    }

    protected void doPush(Entity pEntity) {}

    protected void pushEntities() {}

    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1)
                .add(Attributes.KNOCKBACK_RESISTANCE, -4f)
                .add(Attributes.MOVEMENT_SPEED, .3f)
                .add(Attributes.FLYING_SPEED, .4f)
                .add(Attributes.ATTACK_DAMAGE, 1)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.8f)
                .add(Attributes.ATTACK_SPEED, 1.2);
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

    protected float getSoundVolume() {
        return 0.2F;
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

    // Randomly Fly Around
    static class RandomFloatAroundGoal extends Goal {
        private final KeeseEntity keese;

        public RandomFloatAroundGoal(KeeseEntity pKeese) {
            this.keese = pKeese;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }


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


        public boolean canContinueToUse() {
            return false;
        }


        public void start() {
            RandomSource randomsource = this.keese.getRandom();
            double d0 = this.keese.getX() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.keese.getY() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.keese.getZ() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.keese.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }
    }

    // Flying Attack
    public class FlyingAttackGoal extends Goal {
        public FlyingAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            LivingEntity livingentity = KeeseEntity.this.getTarget();
            if (livingentity != null && livingentity.isAlive() && KeeseEntity.this.random.nextInt(reducedTickDelay(20)) == 0) {
                return KeeseEntity.this.distanceToSqr(livingentity) > 4.0D;
            }
            else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return KeeseEntity.this.getMoveControl().hasWanted() && !KeeseEntity.this.isAttacking() && KeeseEntity.this.getTarget() != null && KeeseEntity.this.getTarget().isAlive();
        }

        public void start() {
            LivingEntity livingentity = KeeseEntity.this.getTarget();
            if (livingentity != null) {
                Vec3 vec3 = livingentity.getEyePosition();
                KeeseEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 3.0D);
            }
        }

        @Override
        public void stop() {
            KeeseEntity.this.setAttacking(false);
            super.stop();
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = KeeseEntity.this.getTarget();
            if (livingentity != null) {
                if (KeeseEntity.this.getBoundingBox().inflate(1.45f).intersects(livingentity.getBoundingBox())) {
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
    }

    // Move Control
    static class KeeseMoveControl extends MoveControl {
        private final KeeseEntity keese;
        private int floatDuration;

        public KeeseMoveControl(KeeseEntity keese) {
            super(keese);
            this.keese = keese;
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.keese.getRandom().nextInt(5) + 2;
                    Vec3 vec3 = new Vec3(this.wantedX - this.keese.getX(), this.wantedY - this.keese.getY(), this.wantedZ - this.keese.getZ());
                    double d0 = vec3.length();
                    vec3 = vec3.normalize();
                    if (this.canReach(vec3, Mth.ceil(d0))) {
                        this.keese.setDeltaMovement(this.keese.getDeltaMovement().add(vec3.scale(0.1D)));
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }
            }
        }

        private boolean canReach(Vec3 pPos, int pLength) {
            AABB aabb = this.keese.getBoundingBox();

            for(int i = 1; i < pLength; ++i) {
                aabb = aabb.move(pPos);
                if (!this.keese.level().noCollision(this.keese, aabb)) {
                    return false;
                }
            }
            return true;
        }
    }

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
    public static boolean checkKeeseSpawnRules(EntityType<? extends KeeseEntity> pBat, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        if (pPos.getY() >= pLevel.getSeaLevel()) {
            return false;
        }
        else return pLevel.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn((ServerLevelAccessor) pLevel, pPos, pRandom) && checkMobSpawnRules(pBat, pLevel, pSpawnType, pPos, pRandom);
    }
}