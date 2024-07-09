package com.kamth.zeldamod.entity.mobs;

import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.entity.custom.projectile.SeedProjectile;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.items.SlingshotItem;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.PathComputationType;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class DekuScrubEntity extends Monster implements RangedAttackMob {
    public DekuScrubEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    private final RangedBowAttackGoal<AbstractSkeleton> bowGoal = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);
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

        this.goalSelector.addGoal(2, new DekugoToLavaGoal(this, 1.3f));
        this.goalSelector.addGoal(2,new LookAtPlayerGoal(this, Player.class, 12));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this,1,40,90,10));
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
        ItemStack itemstack2 = this.getSeed(Items.WHEAT_SEEDS.getDefaultInstance(),2).getPickResult();
     //  ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.BowItem)));
      //  AbstractArrow abstractarrow = this.getArrow(itemstack, pDistanceFactor);
      //  ItemStack seedProjectile = this.getProjectile(itemstack);




       // this.level().addFreshEntity(abstractarrow);


        SeedProjectile seedprojectile = new SeedProjectile(this.level(),this);


        double d0 = pTarget.getX() - this.getX();
        double d1 = pTarget.getY(0.3333333333333333D) - seedprojectile.getY();
        double d2 = pTarget.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        seedprojectile.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
        this.playSound(SoundEvents.DISPENSER_DISPENSE, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(seedprojectile);

    }

    /**
     * Fires an arrow
     */
    protected AbstractArrow getArrow(ItemStack pArrowStack, float pVelocity) {
        return ProjectileUtil.getMobArrow(this, pArrowStack, pVelocity);
    }
    protected AbstractArrow getSeed(ItemStack seed, float pVelocity) {
        return ProjectileUtil.getMobArrow(this, seed, pVelocity);
    }
@Override
    public ItemStack getProjectile(ItemStack pShootable) {
        if (pShootable.getItem() instanceof SlingshotItem) {
            Predicate<ItemStack> predicate = ((ProjectileWeaponItem)pShootable.getItem()).getSupportedHeldProjectiles();
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


}
