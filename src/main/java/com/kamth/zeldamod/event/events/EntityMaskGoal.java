package com.kamth.zeldamod.event.events;

import com.kamth.zeldamod.entity.ai.*;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.piglin.Piglin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EntityMaskGoal {
    private static final Map<Class<? extends Mob>, Consumer<Mob>> ENTITY_GOAL_MAP = new HashMap<>();

    static {
        ENTITY_GOAL_MAP.put(Animal.class, mob -> mob.goalSelector.addGoal(3, new BremenMask(mob, 1.3)));
        ENTITY_GOAL_MAP.put(Allay.class, mob -> mob.goalSelector.addGoal(3, new FairyMask(mob, 1.2)));
        ENTITY_GOAL_MAP.put(Husk.class, mob -> mob.goalSelector.addGoal(1, new GibdoMask(mob)));
        ENTITY_GOAL_MAP.put(Skeleton.class, mob -> mob.goalSelector.addGoal(1, new CaptainMask(mob)));
        ENTITY_GOAL_MAP.put(Piglin.class, mob -> mob.goalSelector.addGoal(1, new KamaroMask(mob)));
    }

    public static void assignMaskGoals(Mob mob) {
        ENTITY_GOAL_MAP.forEach((entityClass, goalAssigner) -> {
            if (entityClass.isInstance(mob)) {
                goalAssigner.accept(mob);
            }
        });
    }
}
