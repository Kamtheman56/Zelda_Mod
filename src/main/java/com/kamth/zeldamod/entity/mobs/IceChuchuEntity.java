package com.kamth.zeldamod.entity.mobs;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class IceChuchuEntity extends Monster {
    private static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(Slime.class, EntityDataSerializers.INT);
    public static final int MIN_SIZE = 1;
    public static final int MAX_SIZE = 127;
    public float targetSquish;
    public float squish;
    public float oSquish;
    private boolean wasOnGround;
    public IceChuchuEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.fixupDimensions();

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new IceChuchuEntity.SlimeFloatGoal(this));
        this.goalSelector.addGoal(2, new IceChuchuEntity.SlimeAttackGoal(this));
        this.goalSelector.addGoal(3, new IceChuchuEntity.SlimeRandomDirectionGoal(this));
        this.goalSelector.addGoal(5, new IceChuchuEntity.SlimeKeepOnJumpingGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p_289461_) -> {
            return Math.abs(p_289461_.getY() - this.getY()) <= 4.0D;
        }));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.moveControl = new IceChuchuEntity.SlimeMoveControl(this);
    }

    @VisibleForTesting
    public void setSize(int pSize, boolean pResetHealth) {
        int i = Mth.clamp(pSize, 1, 127);
        this.entityData.set(ID_SIZE, i);
        this.reapplyPosition();
        this.refreshDimensions();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)(i * i));
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)(0.2F + 0.1F * (float)i));
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)i);
        if (pResetHealth) {
            this.setHealth(this.getMaxHealth());
        }

        this.xpReward = i;
    }





    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 4)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0f)
                .add(Attributes.MOVEMENT_SPEED, .5f)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.8f)
                .add(Attributes.ATTACK_SPEED, 1);
    }



    @Override
    protected void defineSynchedData(){
        super.defineSynchedData();
        this.entityData.define(ID_SIZE, 1);

    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SLIME_HURT;
    }
    public void push(Entity pEntity) {
        super.push(pEntity);
        if (pEntity instanceof IronGolem && this.isDealsDamage()) {
            this.dealDamage((LivingEntity)pEntity);
        }

    }
    public float getLightLevelDependentMagicValue() {
        return 1.0F;
    }
    /**
     * Called by a player entity when they collide with an entity
     */
    public void playerTouch(Player pEntity) {
        if (this.isDealsDamage()) {
            this.dealDamage(pEntity);
        }

    }
    protected boolean spawnCustomParticles() { return false; }
    protected boolean isDealsDamage() {
        return   this.isEffectiveAi();
    }
    protected ParticleOptions getParticleType() {
        return ParticleTypes.SNOWFLAKE;
    }
    protected SoundEvent getSquishSound() {
        return this.isTiny() ? SoundEvents.SLIME_SQUISH_SMALL : SoundEvents.SLIME_SQUISH;
    }
    public void tick() {
        this.squish += (this.targetSquish - this.squish) * 0.5F;
        this.oSquish = this.squish;
        super.tick();
        if (this.onGround() && !this.wasOnGround) {
            int i = this.getSize();

            // Forge: Don't spawn particles if it's handled by the implementation itself
            if (!spawnCustomParticles())
                for(int j = 0; j < i * 8; ++j) {
                    float f = this.random.nextFloat() * ((float)Math.PI * 2F);
                    float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                    float f2 = Mth.sin(f) * (float)i * 0.5F * f1;
                    float f3 = Mth.cos(f) * (float)i * 0.5F * f1;
                    this.level().addParticle(this.getParticleType(), this.getX() + (double)f2, this.getY(), this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
                }

            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            this.targetSquish = -0.5F;
        } else if (!this.onGround() && this.wasOnGround) {
            this.targetSquish = 1.0F;
        }

        this.wasOnGround = this.onGround();
        this.decreaseSquish();
    }

    protected void decreaseSquish() {
        this.targetSquish *= 0.6F;
    }

    protected void dealDamage(LivingEntity pLivingEntity) {
        if (this.isAlive()) {
            int i = this.getSize();
            if (this.distanceToSqr(pLivingEntity) < 0.6D * (double)i * 0.6D * (double)i && i != 1 && this.hasLineOfSight(pLivingEntity) && pLivingEntity.hurt(this.damageSources().mobAttack(this), this.getAttackDamage())) {
                this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
               this.getTarget().setTicksFrozen(this.getTicksFrozen()+80);
                this.doEnchantDamageEffects(this, pLivingEntity);
            }
            if (this.distanceToSqr(pLivingEntity) < 0.9D * (double)i * 0.9D * (double)i && i == 1 && this.hasLineOfSight(pLivingEntity) && pLivingEntity.hurt(this.damageSources().mobAttack(this), this.getAttackDamage())) {
                this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.getTarget().setTicksFrozen(this.getTicksFrozen()+80);
                this.doEnchantDamageEffects(this, pLivingEntity);
            }
        }

    }
    protected float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }
    static class SlimeAttackGoal extends Goal {
        private final IceChuchuEntity slime;
        private int growTiredTimer;

        public SlimeAttackGoal(IceChuchuEntity pSlime) {
            this.slime = pSlime;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            LivingEntity livingentity = this.slime.getTarget();
            if (livingentity == null) {
                return false;
            } else {
                return !this.slime.canAttack(livingentity) ? false : this.slime.getMoveControl() instanceof IceChuchuEntity.SlimeMoveControl;
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.growTiredTimer = reducedTickDelay(300);
            super.start();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            LivingEntity livingentity = this.slime.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!this.slime.canAttack(livingentity)) {
                return false;
            } else {
                return --this.growTiredTimer > 0;
            }
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            LivingEntity livingentity = this.slime.getTarget();
            if (livingentity != null) {
                this.slime.lookAt(livingentity, 10.0F, 10.0F);
            }

            MoveControl movecontrol = this.slime.getMoveControl();
            if (movecontrol instanceof IceChuchuEntity.SlimeMoveControl slime$slimemovecontrol) {
                slime$slimemovecontrol.setDirection(this.slime.getYRot(), this.slime.isDealsDamage());
            }

        }
    }
    protected int getJumpDelay() {
        return this.random.nextInt(20) + 10;
    }
    static class SlimeFloatGoal extends Goal {
        private final IceChuchuEntity slime;

        public SlimeFloatGoal(IceChuchuEntity pSlime) {
            this.slime = pSlime;
            this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
            pSlime.getNavigation().setCanFloat(true);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return (this.slime.isInWater() || this.slime.isInLava()) && this.slime.getMoveControl() instanceof IceChuchuEntity.SlimeMoveControl;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.slime.getRandom().nextFloat() < 0.8F) {
                this.slime.getJumpControl().jump();
            }

            MoveControl movecontrol = this.slime.getMoveControl();
            if (movecontrol instanceof IceChuchuEntity.SlimeMoveControl slime$slimemovecontrol) {
                slime$slimemovecontrol.setWantedMovement(1.25D);
            }

        }
    }

    static class SlimeKeepOnJumpingGoal extends Goal {
        private final IceChuchuEntity slime;

        public SlimeKeepOnJumpingGoal(IceChuchuEntity pSlime) {
            this.slime = pSlime;
            this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return !this.slime.isPassenger();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            MoveControl movecontrol = this.slime.getMoveControl();
            if (movecontrol instanceof IceChuchuEntity.SlimeMoveControl slime$slimemovecontrol) {
                slime$slimemovecontrol.setWantedMovement(1.0D);
            }

        }
    }

    static class SlimeMoveControl extends MoveControl {
        private float yRot;
        private int jumpDelay;
        private final IceChuchuEntity slime;
        private boolean isAggressive;

        public SlimeMoveControl(IceChuchuEntity pSlime) {
            super(pSlime);
            this.slime = pSlime;
            this.yRot = 180.0F * pSlime.getYRot() / (float)Math.PI;
        }

        public void setDirection(float pYRot, boolean pAggressive) {
            this.yRot = pYRot;
            this.isAggressive = pAggressive;
        }

        public void setWantedMovement(double pSpeed) {
            this.speedModifier = pSpeed;
            this.operation = Operation.MOVE_TO;
        }

        public void tick() {
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90.0F));
            this.mob.yHeadRot = this.mob.getYRot();
            this.mob.yBodyRot = this.mob.getYRot();
            if (this.operation != Operation.MOVE_TO) {
                this.mob.setZza(0.0F);
            } else {
                this.operation = Operation.WAIT;
                if (this.mob.onGround()) {
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.slime.getJumpDelay();
                        if (this.isAggressive) {
                            this.jumpDelay /= 3;
                        }

                        this.slime.getJumpControl().jump();
                        if (this.slime.doPlayJumpSound()) {
                            this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), this.slime.getSoundPitch());
                        }
                    } else {
                        this.slime.xxa = 0.0F;
                        this.slime.zza = 0.0F;
                        this.mob.setSpeed(0.0F);
                    }
                } else {
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                }

            }
        }
    }

    float getSoundPitch() {
        float f = this.isTiny() ? 1.4F : 0.8F;
        return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
    }
    public int getSize() {
        return this.entityData.get(ID_SIZE);
    }
    protected SoundEvent getJumpSound() {
        return this.isTiny() ? SoundEvents.SLIME_JUMP_SMALL : SoundEvents.SLIME_JUMP;
    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Size", this.getSize() - 1);
        pCompound.putBoolean("wasOnGround", this.wasOnGround);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        this.setSize(pCompound.getInt("Size") + 1, false);
        super.readAdditionalSaveData(pCompound);
        this.wasOnGround = pCompound.getBoolean("wasOnGround");
    }

    public boolean isTiny() {
        return this.getSize() <= 1;
    }
    protected boolean doPlayJumpSound() {
        return this.getSize() > 0;
    }
    static class SlimeRandomDirectionGoal extends Goal {
        private final IceChuchuEntity slime;
        private float chosenDegrees;
        private int nextRandomizeTime;

        public SlimeRandomDirectionGoal(IceChuchuEntity pSlime) {
            this.slime = pSlime;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }


        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return this.slime.getTarget() == null && (this.slime.onGround() || this.slime.isInWater() || this.slime.isInLava() || this.slime.hasEffect(MobEffects.LEVITATION)) && this.slime.getMoveControl() instanceof IceChuchuEntity.SlimeMoveControl;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (--this.nextRandomizeTime <= 0) {
                this.nextRandomizeTime = this.adjustedTickDelay(40 + this.slime.getRandom().nextInt(60));
                this.chosenDegrees = (float)this.slime.getRandom().nextInt(360);
            }

            MoveControl movecontrol = this.slime.getMoveControl();
            if (movecontrol instanceof IceChuchuEntity.SlimeMoveControl slime$slimemovecontrol) {
                slime$slimemovecontrol.setDirection(this.chosenDegrees, false);
            }

        }
    }
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (ID_SIZE.equals(pKey)) {
            this.refreshDimensions();
            this.setYRot(this.yHeadRot);
            this.yBodyRot = this.yHeadRot;
            if (this.isInWater() && this.random.nextInt(20) == 0) {
                this.doWaterSplashEffect();
            }
        }

        super.onSyncedDataUpdated(pKey);
    }
    public void remove(RemovalReason pReason) {
        int i = this.getSize();
        if (!this.level().isClientSide && i > 1 && this.isDeadOrDying()) {
            Component component = this.getCustomName();
            boolean flag = this.isNoAi();
            float f = (float)i / 4.0F;
            int j = i / 2;
            int k = 2 + this.random.nextInt(3);

            for(int l = 0; l < k; ++l) {
                float f1 = ((float)(l % 2) - 0.5F) * f;
                float f2 = ((float)(l / 2) - 0.5F) * f;
                IceChuchuEntity chuchu = (IceChuchuEntity) this.getType().create(this.level());
                if (chuchu!= null) {
                    if (this.isPersistenceRequired()) {
                        chuchu.setPersistenceRequired();
                    }

                    chuchu.setCustomName(component);
                    chuchu.setNoAi(flag);
                    chuchu.setInvulnerable(this.isInvulnerable());
                    chuchu.setSize(j, true);
                    chuchu.moveTo(this.getX() + (double)f1, this.getY() + 0.5D, this.getZ() + (double)f2, this.random.nextFloat() * 360.0F, 0.0F);
                    this.level().addFreshEntity(chuchu);
                }
            }
        }

        super.remove(pReason);
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        RandomSource randomsource = pLevel.getRandom();
        int i = randomsource.nextInt(3);
        if (i < 2 && randomsource.nextFloat() < 0.5F * pDifficulty.getSpecialMultiplier()) {
            ++i;
        }

        int j = 1 << i;
        this.setSize(j, true);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }
    public EntityDimensions getDimensions(Pose pPose) {
        return super.getDimensions(pPose).scale(0.255F * (float)this.getSize());
    }
}
