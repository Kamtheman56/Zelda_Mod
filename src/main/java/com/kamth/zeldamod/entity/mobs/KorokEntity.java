package com.kamth.zeldamod.entity.mobs;

import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.PathComputationType;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class KorokEntity extends Monster {


    public KorokEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Monster.class, EntityDataSerializers.BYTE);
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
 


    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
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

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));



        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new SitOnFlower(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

       this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Wolf.class, 3, 1.5, 1));


    }


    static class DekutoFlowerGoal extends MoveToBlockGoal {
        private final KorokEntity deku;

        DekutoFlowerGoal(KorokEntity deku, double pSpeedModifier) {
            super(deku, pSpeedModifier, 8, 2);
            this.deku = deku;
        }

        public BlockPos getMoveToTarget() {
            return this.blockPos;
        }

        public boolean canContinueToUse() {
            return !this.deku.isInLava() && this.isValidTarget(this.deku.level(), this.blockPos);
        }

        public boolean canUse() {
            return !this.deku.isInLava() && super.canUse();
        }


        public boolean shouldRecalculatePath() {
            return this.tryTicks % 20 == 0;
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(ModBlocks.DEKU_BLOCK.get()) && pLevel.getBlockState(pPos.above()).isPathfindable(pLevel, pPos, PathComputationType.LAND);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 6)
                .add(Attributes.KNOCKBACK_RESISTANCE, .8f)
                .add(Attributes.MOVEMENT_SPEED, .2f)
                .add(Attributes.ATTACK_DAMAGE, 8)
                .add(Attributes.ATTACK_SPEED, 3);
    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }







    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.GENERIC_HURT;
    }

    public static class DekuMaskFollow extends Goal {
        protected final KorokEntity mob;

        protected Player player;

        public DekuMaskFollow(KorokEntity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            this.player = this.mob.level().getNearestPlayer(TargetingConditions.forCombat(), 2D, 2D, 1);
            if (this.player == null) {
                return false;
            } else {
                return Follow(player);
            }
        }

        private <L> boolean Follow(Player player) {
            ItemStack stack0 = player.getItemBySlot(EquipmentSlot.HEAD);
            boolean l = (this.mob.distanceTo(this.player) < 10.25);
            if ((!stack0.isEmpty() && l))
                return stack0.getItem() == ModItems.DEKU_MASK.get();
            return false;
        }

        public int getMaxHeadXRot() {
            return 40;
        }

        public void tick() {
            this.mob.getLookControl().setLookAt(this.player.getX(), this.player.getEyeY(), this.player.getZ(), 10.0F, (float) this.getMaxHeadXRot());
            this.mob.setAggressive(false);
            this.mob.setTarget(null);
        }
    }

    public class SitOnFlower extends Goal {
        protected final KorokEntity mob;

        public SitOnFlower(KorokEntity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return !this.mob.isInLava() && this.mob.getBlockStateOn().is(ModBlocks.DEKU_BLOCK.get());
        }
        public void start() {
            this.mob.getNavigation().stop();
            this.mob.sitAnimationState.start(200);
        }
    }



}
