package com.kamth.zeldamod.entity.mobs;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class IronKnuckleEntity extends  Monster  {

    public IronKnuckleEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 54)
                .add(Attributes.KNOCKBACK_RESISTANCE, 3f)
                .add(Attributes.MOVEMENT_SPEED, .18f)
                .add(Attributes.ATTACK_DAMAGE, 12)
                .add(Attributes.ATTACK_KNOCKBACK, 5f)
                .add(Attributes.ATTACK_SPEED, 8)
                .add(Attributes.ARMOR,20);
    }
}
