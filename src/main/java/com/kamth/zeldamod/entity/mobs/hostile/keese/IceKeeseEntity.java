package com.kamth.zeldamod.entity.mobs.hostile.keese;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class IceKeeseEntity extends KeeseEntity {




    public IceKeeseEntity(EntityType<? extends IceKeeseEntity> pEntityType, Level pLevel) {
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
        this.goalSelector.addGoal(4, new RandomFloatAroundGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 12));

        this.goalSelector.addGoal(7, new IceKeeseEntity.FlyingAttackGoal());
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
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            LivingEntity livingentity = IceKeeseEntity.this.getTarget();
            if (livingentity != null && livingentity.isAlive() && IceKeeseEntity.this.random.nextInt(reducedTickDelay(40)) == 0) {
                return IceKeeseEntity.this.distanceToSqr(livingentity) > 4.0D;
            }
            else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return IceKeeseEntity.this.getMoveControl().hasWanted() && !IceKeeseEntity.this.isAttacking() && IceKeeseEntity.this.getTarget() != null && IceKeeseEntity.this.getTarget().isAlive();
        }

        public void start() {
            LivingEntity livingentity = IceKeeseEntity.this.getTarget();
            if (livingentity != null) {
                Vec3 vec3 = livingentity.getEyePosition();
                IceKeeseEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 2.0D);
            }
        }

        @Override
        public void stop() {
            IceKeeseEntity.this.setAttacking(false);
            super.stop();
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = IceKeeseEntity.this.getTarget();
            if (livingentity != null) {
                if (IceKeeseEntity.this.getBoundingBox().inflate(1.45f).intersects(livingentity.getBoundingBox())) {
                    IceKeeseEntity.this.doHurtTarget(livingentity);
                    IceKeeseEntity.this.getTarget().setTicksFrozen(getTarget().getTicksFrozen()+40);
                    IceKeeseEntity.this.setAttacking(false);
                    stop();
                } else {
                    double d0 = IceKeeseEntity.this.distanceToSqr(livingentity);
                    if (d0 < 9.0D) {
                        Vec3 vec3 = livingentity.getEyePosition();
                        IceKeeseEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 2.0D);
                    }
                }

            }
        }
    }





}