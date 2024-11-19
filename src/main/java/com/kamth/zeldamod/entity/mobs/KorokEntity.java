package com.kamth.zeldamod.entity.mobs;

import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.entity.mobs.variants.KorokVariants;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class KorokEntity extends Monster {


    public KorokEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(KorokEntity.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Monster.class, EntityDataSerializers.BYTE);
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
 


    protected void defineSynchedData() {
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
        super.defineSynchedData();
    }
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
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
        this.goalSelector.addGoal(0, new KorokMaskFollow(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new SitOnFlower(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
       this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, DekuScrubEntity.class, 3, 1.5, 1));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, DekuMadScrubEntity.class, 3, 1.5, 1));

    }




    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 6)
                .add(Attributes.KNOCKBACK_RESISTANCE, .8f)
                .add(Attributes.MOVEMENT_SPEED, .2f);
    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }







    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.GENERIC_HURT;
    }

    public static class KorokMaskFollow extends Goal {
        protected final KorokEntity mob;

        protected Player player;

        public KorokMaskFollow(KorokEntity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse()
        {
                this.player = this.mob.level().getNearestPlayer(TargetingConditions.DEFAULT,2D,2D, 1);
                if (this.player == null)
                {
                    return false;
                }
                else
                {
                    return shouldFollow(player);
                }
        }
        private <L> boolean shouldFollow(Player player)
        {
            ItemStack stack0 = player.getItemBySlot(EquipmentSlot.HEAD);
            boolean l =  (this.mob.distanceTo(this.player) < 12D);
            if ((!stack0.isEmpty() && l))
                return stack0.getItem() == ModItems.KOROK_MASK.get();
            return false;
        }
        public void tick()
        {
            this.mob.getLookControl().setLookAt(this.player, (float) (this.mob.getMaxHeadYRot() + 20), (float) this.mob.getMaxHeadXRot());
            this.mob.getNavigation().moveTo(this.player,1.2f);
        }}

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







    //VARIANTS

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_,
                                        MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_,
                                        @Nullable CompoundTag p_146750_) {
       KorokVariants variant = Util.getRandom(KorokVariants.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }

    public KorokVariants getVariant() {
        return KorokVariants.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(KorokVariants variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }


}
