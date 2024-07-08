package com.kamth.zeldamod.entity.mobs;

import com.kamth.zeldamod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.PathComputationType;

import javax.annotation.Nullable;

public class DekuScrubEntity extends Monster {
    public DekuScrubEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    private static final int SITDOWN_DURATION_TICKS = 80;
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int sitAnimationTimeout = 0;
    protected int nextStartTick;
    protected int tryTicks;
    private int maxStayTicks;
    private boolean reachedTarget;
    protected int verticalSearchStart;

    @Override
    public void tick(){
        super.tick();

if (this.level().isClientSide){
setupAnimationStates();
}
    }
    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    protected void registerGoals(){
        this.goalSelector.addGoal(0, new FloatGoal(this));

this.goalSelector.addGoal(1, new DekugoToLavaGoal(this, 1));
        this.goalSelector.addGoal(2,new LookAtPlayerGoal(this, Player.class, 12));
    //    this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

    }


    static class DekugoToLavaGoal extends MoveToBlockGoal {
        private final DekuScrubEntity deku;

        DekugoToLavaGoal(DekuScrubEntity deku, double pSpeedModifier) {
            super(deku, pSpeedModifier, 8, 2);
            this.deku = deku;
        }

        public BlockPos getMoveToTarget() {
            return this.blockPos;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return !this.deku.isInLava() && this.isValidTarget(this.deku.level(), this.blockPos);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return !this.deku.isInLava() && super.canUse();
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 20 == 0;
        }

        /**
         * Return {@code true} to set given position as destination
         */
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(ModBlocks.DEKU_BLOCK.get()) && pLevel.getBlockState(pPos.above()).isPathfindable(pLevel, pPos, PathComputationType.LAND);
        }
    }

    public static AttributeSupplier.Builder createAttributes(){
return Monster.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH,4)
        .add(Attributes.KNOCKBACK_RESISTANCE,20)
        .add(Attributes.MOVEMENT_SPEED,.2f)
        .add(Attributes.ATTACK_DAMAGE,2)
        .add(Attributes.ATTACK_SPEED,3);
    }


    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.GENERIC_HURT;
    }


}
