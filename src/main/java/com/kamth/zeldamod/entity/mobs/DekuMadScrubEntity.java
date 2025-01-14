package com.kamth.zeldamod.entity.mobs;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class DekuMadScrubEntity extends DekuScrubEntity {


    public DekuMadScrubEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Monster.class, EntityDataSerializers.BYTE);
    public final AnimationState idleAnimationState = new AnimationState();
 

@Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 12)
                .add(Attributes.KNOCKBACK_RESISTANCE, .8f)
                .add(Attributes.MOVEMENT_SPEED, .16f)
                .add(Attributes.ATTACK_DAMAGE, 10)
                .add(Attributes.ARMOR, 8)
                .add(Attributes.ATTACK_SPEED, 2);
    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }



    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.GENERIC_HURT;
    }



}
