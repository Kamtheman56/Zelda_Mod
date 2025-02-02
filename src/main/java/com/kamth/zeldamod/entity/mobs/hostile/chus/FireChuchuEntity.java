package com.kamth.zeldamod.entity.mobs.hostile.chus;

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
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class FireChuchuEntity extends ChuchuEntity {



    public FireChuchuEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.fixupDimensions();

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

    public boolean isOnFire() {
        return false;
    }








    protected ParticleOptions getParticleType() {
        return ParticleTypes.FLAME;
    }

    @Override
    public void tick() {
        this.squish += (this.targetSquish - this.squish) * 0.5F;
        this.oSquish = this.squish;
        super.tick();


        if (this.level().isClientSide) {
            for(int i = 0; i < 2; ++i) {
                this.level().addParticle(ParticleTypes.FLAME, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }
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
        }
        else if (!this.onGround() && this.wasOnGround) {
            this.targetSquish = 1.0F;
        }
        this.wasOnGround = this.onGround();
        this.decreaseSquish();
    }


        @Override
    protected void dealDamage(LivingEntity pLivingEntity) {
        if (this.isAlive()) {
            int i = this.getSize();
            if (this.distanceToSqr(pLivingEntity) < 0.6D * (double)i * 0.6D * (double)i && i != 1 && this.hasLineOfSight(pLivingEntity) && pLivingEntity.hurt(this.damageSources().mobAttack(this), this.getAttackDamage())) {
                this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
               this.getTarget().setRemainingFireTicks(this.getRemainingFireTicks()+40);
                this.doEnchantDamageEffects(this, pLivingEntity);
            }
            if (this.distanceToSqr(pLivingEntity) < 0.9D * (double)i * 0.9D * (double)i && i == 1 && this.hasLineOfSight(pLivingEntity) && pLivingEntity.hurt(this.damageSources().mobAttack(this), this.getAttackDamage())) {
                this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.getTarget().setRemainingFireTicks(this.getRemainingFireTicks()+40);
                this.doEnchantDamageEffects(this, pLivingEntity);
            }
        }

    }


  @Override
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
                FireChuchuEntity chuchu = (FireChuchuEntity) this.getType().create(this.level());
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

}
