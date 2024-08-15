package com.kamth.zeldamod.entity.mobs;

import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.entity.custom.projectile.SeedProjectile;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.items.SlingshotItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.PathComputationType;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class DekuScrubEntity extends Monster implements RangedAttackMob {


    public DekuScrubEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
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

        this.goalSelector.addGoal(0, new DekuMaskFollow(this));
        this.goalSelector.addGoal(2, new DekutoFlowerGoal(this, 1.3f));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 12));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new SitOnFlower(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
         this.goalSelector.addGoal(1, new RangedAttackGoal(this,1,40,90,10));
       this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Wolf.class, 3, 1.5, 1));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Parrot.class, false));

    }


    static class DekutoFlowerGoal extends MoveToBlockGoal {
        private final DekuScrubEntity deku;

        DekutoFlowerGoal(DekuScrubEntity deku, double pSpeedModifier) {
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

    public void rideTick() {
        super.rideTick();
        Entity entity = this.getControlledVehicle();
        if (entity instanceof PathfinderMob pathfindermob) {
            this.yBodyRot = pathfindermob.yBodyRot;
        }
    }

    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        super.populateDefaultEquipmentSlots(pRandom, pDifficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.SLINGSHOT.get()));
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        SeedProjectile seedprojectile = new SeedProjectile(this.level(), this);
        double d0 = pTarget.getX() - this.getX();
        double d1 = pTarget.getY(0.3333333333333333D) - seedprojectile.getY();
        double d2 = pTarget.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        seedprojectile.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level().getDifficulty().getId() * 4));
        this.playSound(SoundEvents.LLAMA_SPIT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(seedprojectile);

    }

    @Override
    public ItemStack getProjectile(ItemStack pShootable) {
        if (pShootable.getItem() instanceof SlingshotItem) {
            Predicate<ItemStack> predicate = ((ProjectileWeaponItem) pShootable.getItem()).getSupportedHeldProjectiles();
            ItemStack itemstack = ProjectileWeaponItem.getHeldProjectile(this, predicate);
            return net.minecraftforge.common.ForgeHooks.getProjectile(this, pShootable, itemstack.isEmpty() ? new ItemStack(Items.WHEAT_SEEDS) : itemstack);
        } else {
            return net.minecraftforge.common.ForgeHooks.getProjectile(this, pShootable, ItemStack.EMPTY);
        }
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.GENERIC_HURT;
    }

    public static class DekuMaskFollow extends Goal {
        protected final DekuScrubEntity mob;

        protected Player player;

        public DekuMaskFollow(DekuScrubEntity mob) {
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
        protected final DekuScrubEntity mob;

        public SitOnFlower(DekuScrubEntity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return !this.mob.isInLava() && this.mob.getBlockStateOn().is(ModBlocks.DEKU_BLOCK.get());
        }
        public void start() {
            this.mob.getNavigation().stop();
            this.mob.setInSittingPose(true);
            this.mob.sitAnimationState.start(200);
        }
    }

    public boolean isInSittingPose() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setInSittingPose(boolean pSitting) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (pSitting) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 | 1));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(b0 & -2));
        }

    }

}
