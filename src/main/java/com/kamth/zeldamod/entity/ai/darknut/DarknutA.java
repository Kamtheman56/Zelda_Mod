package com.kamth.zeldamod.entity.ai.darknut;

import com.kamth.zeldamod.entity.mobs.hostile.darknuts.DarknutEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class DarknutA extends MeleeAttackGoal {
    public DarknutA(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        entity = ((DarknutEntity) pMob);
    }
    private final DarknutEntity entity;
    private int raiseArmTicks;


    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        super.stop();
        this.entity.setAggressive(false);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        super.tick();
        ++this.raiseArmTicks;
        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.entity.setAggressive(true);
        } else {
            this.entity.setAggressive(false);
        }

    }
}