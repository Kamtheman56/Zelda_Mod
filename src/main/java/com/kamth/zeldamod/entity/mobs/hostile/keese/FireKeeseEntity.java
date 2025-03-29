package com.kamth.zeldamod.entity.mobs.hostile.keese;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class FireKeeseEntity extends KeeseEntity {




    public FireKeeseEntity(EntityType<? extends FireKeeseEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new KeeseMoveControl(this);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
    }





    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new FireKeeseEntity.RandomFloatAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 12));

        this.goalSelector.addGoal(7, new FireKeeseEntity.FlyingAttackGoal());
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }



    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1)
                .add(Attributes.KNOCKBACK_RESISTANCE, -4f)
                .add(Attributes.MOVEMENT_SPEED, .3f)
                .add(Attributes.FLYING_SPEED, .4f)
                .add(Attributes.ATTACK_DAMAGE, 1)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.8f)
                .add(Attributes.ATTACK_SPEED, 1.2);
    }



    public class FlyingAttackGoal extends Goal {
        public FlyingAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            LivingEntity livingentity = FireKeeseEntity.this.getTarget();
            if (livingentity != null && livingentity.isAlive() && FireKeeseEntity.this.random.nextInt(reducedTickDelay(30)) == 0) {
                return FireKeeseEntity.this.distanceToSqr(livingentity) > 4.0D;
            }
            else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return FireKeeseEntity.this.getMoveControl().hasWanted() && !FireKeeseEntity.this.isAttacking() && FireKeeseEntity.this.getTarget() != null && FireKeeseEntity.this.getTarget().isAlive();
        }

        public void start() {
            LivingEntity livingentity = FireKeeseEntity.this.getTarget();
            if (livingentity != null) {
                Vec3 vec3 = livingentity.getEyePosition();
                FireKeeseEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 2.0D);
            }
        }

        @Override
        public void stop() {
            FireKeeseEntity.this.setAttacking(false);
            super.stop();
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = FireKeeseEntity.this.getTarget();
            if (livingentity != null) {
                if (FireKeeseEntity.this.getBoundingBox().inflate(1.45f).intersects(livingentity.getBoundingBox())) {
                    FireKeeseEntity.this.doHurtTarget(livingentity);
                    FireKeeseEntity.this.getTarget().setRemainingFireTicks(getTarget().getRemainingFireTicks()+40);
                    FireKeeseEntity.this.setAttacking(false);
                    stop();
                } else {
                    double d0 = FireKeeseEntity.this.distanceToSqr(livingentity);
                    if (d0 < 9.0D) {
                        Vec3 vec3 = livingentity.getEyePosition();
                        FireKeeseEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 2.0D);
                    }
                }

            }
        }
    }





}