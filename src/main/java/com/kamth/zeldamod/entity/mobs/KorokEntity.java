package com.kamth.zeldamod.entity.mobs;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.mobs.variants.KorokVariants;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class KorokEntity extends Animal {


    private static final EntityDataAccessor<Boolean> DATA_CAN_SEED = SynchedEntityData.defineId(KorokEntity.class, EntityDataSerializers.BOOLEAN);
    private long duplicationCooldown;

    public KorokEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(KorokEntity.class, EntityDataSerializers.INT);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState danceAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int danceAnimationTimeout = 0;
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.APPLE, ZeldaItems.BAKED_APPLE.get(), Items.COOKIE);
    public static final String VARIANT_KEY = "variant";
    ;
    private boolean partyKorok;
    private BlockPos jukebox;

    protected void defineSynchedData() {
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
        super.defineSynchedData();
        this.entityData.define(DATA_CAN_SEED, true);
    }
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
        tag.putLong("DuplicationCooldown", this.duplicationCooldown);
        tag.putBoolean("CanSeed", this.canSeed());
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
        this.duplicationCooldown = (long)tag.getInt("DuplicationCooldown");
        this.entityData.set(DATA_CAN_SEED, tag.getBoolean("CanSeed"));
    }
    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0 && !this.isPartyKorok()) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.danceAnimationTimeout <= 0 && this.isPartyKorok()) {
            this.danceAnimationTimeout = this.random.nextInt(40) + 80;
            this.danceAnimationState.start(this.tickCount);
        } else {
            --this.danceAnimationTimeout;
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
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new SitOnFlower(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, DekuScrubEntity.class, 3, 1.5, 1));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, DekuMadScrubEntity.class, 3, 1.5, 1));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 6)
                .add(Attributes.KNOCKBACK_RESISTANCE, .8f)
                .add(Attributes.MOVEMENT_SPEED, .2f);}
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }
    @Override
    public void aiStep() {
        if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 3.46D) || !this.level().getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
            this.partyKorok = false;
            this.jukebox = null;
        }
        this.updateDuplicationCooldown();
        super.aiStep();
    }
    public void setRecordPlayingNearby(BlockPos pPos, boolean pIsPartying) {
        this.jukebox = pPos;
        this.partyKorok = pIsPartying;
    }
    public boolean isPartyKorok() {
        return this.partyKorok;
    }
    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.APPLE);
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
            this.player = this.mob.level().getNearestPlayer(TargetingConditions.DEFAULT,3D,3D, 1);
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
                return stack0.getItem() == ZeldaItems.KOROK_MASK.get();
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
            return !this.mob.isInLava() && this.mob.getBlockStateOn().is(ModTags.Blocks.KOROK);
        }
        public void start() {
            this.mob.getNavigation().stop();
            this.mob.sitAnimationState.start(200);
        }
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(ModTags.Items.KOROK_LIKES_BIG) && !this.isBaby() && this.canSeed()) {
            itemstack.shrink(1);
            pPlayer.playSound(ModSounds.KOROK_CRUNCH.get(), 1.0F, .8F);
            pPlayer.playSound(ModSounds.KOROK_LIKES.get(), 1.0F, 1.0F);
            ItemStack itemstack1 = ZeldaItems.KOROK_SEED.get().getDefaultInstance();
            ItemStack itemstack2 = ZeldaItems.KOROK_SEED.get().getDefaultInstance();
            ItemStack itemstack3 = ZeldaItems.KOROK_SEED.get().getDefaultInstance();
            this.spawnAtLocation(itemstack1);
            this.spawnAtLocation(itemstack2);
            this.spawnAtLocation(itemstack3);
            this.resetDuplicationCooldown();
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        if (itemstack.is(ModTags.Items.KOROK_LIKES) && !this.isBaby() && this.canSeed()) {
            itemstack.shrink(1);
            pPlayer.playSound(ModSounds.KOROK_CRUNCH.get(), 1.0F, 1.0F);
            pPlayer.playSound(ModSounds.KOROK_LIKES.get(), 1.0F, 1.0F);
            ItemStack itemstack1 = ZeldaItems.KOROK_SEED.get().getDefaultInstance();
            this.spawnAtLocation(itemstack1);
            this.resetDuplicationCooldown();
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        else {
            return super.mobInteract(pPlayer, pHand);
        }
    }
    private void updateDuplicationCooldown() {
        if (this.duplicationCooldown > 0L) {
            --this.duplicationCooldown;
        }

        if (!this.level().isClientSide() && this.duplicationCooldown == 0L && !this.canSeed()) {
            this.entityData.set(DATA_CAN_SEED, true);
        }

    }
    private void resetDuplicationCooldown() {
        this.duplicationCooldown = 6000L;
        this.entityData.set(DATA_CAN_SEED, false);
    }

    private boolean canSeed() {
        return this.entityData.get(DATA_CAN_SEED);
    }
    //VARIANTS
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        Holder<Biome> holder = pLevel.getBiome(this.blockPosition());
        if (holder.is(ModTags.Biomes.SPAWNS_BIRCH_KOROK)) {
            this.setVariant(KorokVariants.BIRCH);
        }
        else if (holder.is(ModTags.Biomes.SPAWNS_ACACIA_KOROK)) {
            this.setVariant(KorokVariants.ACACIA);
        }
        else if (holder.is(ModTags.Biomes.SPAWNS_JUNGLE_KOROK)) {
            this.setVariant(KorokVariants.JUNGLE);
        }
        else  if (holder.is(ModTags.Biomes.SPAWNS_DARK_OAK_KOROK)) {
            this.setVariant(KorokVariants.DARK);
        }
        else  if (holder.is(ModTags.Biomes.SPAWNS_MUSHROOM_KOROK)) {
            this.setVariant(KorokVariants.MUSHROOM);
        }
        else  if (holder.is(ModTags.Biomes.SPAWNS_CHERRY_KOROK)) {
            this.setVariant(KorokVariants.CHERRY);
        }
        else {
            this.setVariant(KorokVariants.DEFAULT);
        }
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public boolean canMate(Animal pOtherAnimal) {
        return false;
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }


    public KorokVariants getVariant() {
        return KorokVariants.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    public void setVariant(KorokVariants variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    public static void convertFromLightning(EntityStruckByLightningEvent event) {
        if (event.getEntity().getType() == ModEntityTypes.KOROK.get() && !event.getEntity().level().isClientSide) {
            ServerLevel level = (ServerLevel) event.getEntity().level();
            event.setCanceled(true);
            KorokEntity korok = ModEntityTypes.KOROK.get().create(event.getEntity().level());
            korok.moveTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity().getYRot(), event.getEntity().getXRot());
            korok.finalizeSpawn(level, level.getCurrentDifficultyAt(korok.blockPosition()), MobSpawnType.CONVERSION, null, null);
            if (event.getEntity().hasCustomName()) {
                korok.setCustomName(event.getEntity().getCustomName());
                korok.setCustomNameVisible(event.getEntity().isCustomNameVisible());
            }
            korok.setVariant(KorokVariants.MUSHROOM);
            korok.setPersistenceRequired();
            level.addFreshEntityWithPassengers(korok);
            event.getEntity().discard();
        }
    }
}
