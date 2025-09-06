package com.kamth.zeldamod.entity.mobs.bosses;

import com.google.common.collect.ImmutableList;
import com.kamth.zeldamod.entity.mobs.variants.BokoblinVariants;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.Util;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class PhantomGanonEntity extends Monster  implements RangedAttackMob, PowerableMob {

    public final AnimationState idleAnimationState = new AnimationState();

    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = (p_31504_) -> {
        return p_31504_.getMobType() != MobType.UNDEAD && p_31504_.attackable();
    };
   private static final EntityDataAccessor<Integer> DATA_ID_INV = SynchedEntityData.defineId(PhantomGanonEntity.class, EntityDataSerializers.INT);
    private static final int INVULNERABLE_TICKS = 220;
    private int idleAnimationTimeout = 0;

    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    private final RangedBowAttackGoal<PhantomGanonEntity> bowGoal = new RangedBowAttackGoal<>(this, .8D, 28, 15.0F);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1D, false) {
        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            super.stop();
            PhantomGanonEntity.this.setAggressive(false);
        }

        public boolean canUse() {
            return  !PhantomGanonEntity.this.isSleeping()  && super.canUse();
        }
        public void start() {
            super.start();
            PhantomGanonEntity.this.setAggressive(true);
        }
    };

    public PhantomGanonEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.reassessWeaponGoal();
        this.setCanPickUpLoot(true);
        this.setHealth(this.getMaxHealth());
        this.xpReward = 50;

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 350)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0f)
                .add(Attributes.MOVEMENT_SPEED, .35f)
                .add(Attributes.ATTACK_DAMAGE, 2f)
                .add(Attributes.ATTACK_KNOCKBACK, 1f)
                .add(Attributes.ATTACK_SPEED, 1)
                .add(Attributes.ARMOR,4);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PhantomGanonDoNothingGoal());
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));

        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Bee.class, 3, 1.5, 1));


        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, LIVING_ENTITY_SELECTOR));

    }


    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.WITHER_SKELETON_HURT;
    }

    protected SoundEvent getAmbientSound() {


        return SoundEvents.WITHER_SKELETON_AMBIENT;
    }


    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_SKELETON_DEATH;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_ID_INV, 0);
        super.defineSynchedData();
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.reassessWeaponGoal();
        this.setInvulnerableTicks(pCompound.getInt("Invul"));

    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Invul", this.getInvulnerableTicks());

    }



    public int getInvulnerableTicks() {
        return this.entityData.get(DATA_ID_INV);
    }

    public void setInvulnerableTicks(int pInvulnerableTicks) {
        this.entityData.set(DATA_ID_INV, pInvulnerableTicks);
    }


    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        pSpawnData = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        RandomSource randomsource = pLevel.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, pDifficulty);
        this.populateDefaultEquipmentEnchantments(randomsource, pDifficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, this.createSpawnWeapon());
        this.makeInvulnerable();
        return pSpawnData;

    }

    public void reassessWeaponGoal() {
        if (this.level() != null && !this.level().isClientSide) {
            this.goalSelector.removeGoal(this.meleeGoal);
            this.goalSelector.removeGoal(this.bowGoal);
            ItemStack itemstack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof BowItem));
             if (this.getHealth() <= this.getMaxHealth()/2){
                 this.goalSelector.addGoal(4, this.bowGoal);
             }
             else {
                this.goalSelector.addGoal(4, this.meleeGoal);
            }

        }
    }

    public void makeInvulnerable() {
        this.setInvulnerableTicks(220);
        this.bossEvent.setProgress(0.0F);
        this.setHealth(this.getMaxHealth() / 3.0F);
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof BowItem)));
        AbstractArrow abstractarrow = this.getArrow(itemstack, pDistanceFactor);
        if (this.getMainHandItem().getItem() instanceof BowItem)
            abstractarrow = ((BowItem)this.getMainHandItem().getItem()).customArrow(abstractarrow);
        double d0 = pTarget.getX() - this.getX();
        double d1 = pTarget.getY(0.3333333333333333D) - abstractarrow.getY();
        double d2 = pTarget.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        abstractarrow.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(abstractarrow);
    }

    /**
     * Fires an arrow
     */
    protected AbstractArrow getArrow(ItemStack pArrowStack, float pVelocity) {
        return ProjectileUtil.getMobArrow(this, pArrowStack, pVelocity);
    }

    public boolean canFireProjectileWeapon(ProjectileWeaponItem pProjectileWeapon) {
        return pProjectileWeapon == Items.BOW;
    }
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {
        super.setItemSlot(pSlot, pStack);
        if (!this.level().isClientSide) {
            this.reassessWeaponGoal();
        }
    }

    public ItemStack createSpawnWeapon() {
        return (double)this.random.nextFloat() < 0.5D ? new ItemStack(Items.BOW) : new ItemStack(ZeldaItems.GLOOM_SWORD.get());
    }

    protected void customServerAiStep() {
        if (this.getInvulnerableTicks() > 0) {
            int k1 = this.getInvulnerableTicks() - 1;
            this.bossEvent.setProgress(1.0F - (float)k1 / 220.0F);
            if (k1 <= 0) {
                this.level().explode(this, this.getX(), this.getEyeY(), this.getZ(), 7.0F, false, Level.ExplosionInteraction.MOB);
                if (!this.isSilent()) {
                    this.level().globalLevelEvent(1023, this.blockPosition(), 0);
                }
            }

            this.setInvulnerableTicks(k1);
            if (this.tickCount % 10 == 0) {
                this.heal(10.0F);
            }

        } else {
            super.customServerAiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        }
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
            this.idleAnimationTimeout = this.random.nextInt(40) + 200;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    public void startSeenByPlayer(ServerPlayer pPlayer) {
        super.startSeenByPlayer(pPlayer);
        this.bossEvent.addPlayer(pPlayer);
    }

    public void stopSeenByPlayer(ServerPlayer pPlayer) {
        super.stopSeenByPlayer(pPlayer);
        this.bossEvent.removePlayer(pPlayer);
    }

    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public boolean isPowered() {
        return this.getHealth() <= this.getMaxHealth() / 2.0F;
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
    if (this.isInvulnerableTo(pSource)) {
        return false;
    } else if (!pSource.is(DamageTypeTags.WITHER_IMMUNE_TO) && !(pSource.getEntity() instanceof PhantomGanonEntity)) {
        if (this.getInvulnerableTicks() > 0 && !pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        } else {
            if (this.isPowered()) {
                Entity entity = pSource.getDirectEntity();
                if (entity instanceof AbstractArrow) {
                    return false;
                }
            }

            Entity entity1 = pSource.getEntity();
            if (entity1 != null && !(entity1 instanceof Player) && entity1 instanceof LivingEntity && ((LivingEntity)entity1).getMobType() == this.getMobType()) {
                return false;
            } else {

                return super.hurt(pSource, pAmount);
            }
        }
    }
    else {
        return false;
    }
    }


    class PhantomGanonDoNothingGoal extends Goal {
        public PhantomGanonDoNothingGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return PhantomGanonEntity.this.getInvulnerableTicks() > 0;
        }
    }


}

