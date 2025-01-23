package com.kamth.zeldamod.entity.projectile.arrows;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractZeldaArrowEntity extends AbstractArrow {

    public AbstractZeldaArrowEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public AbstractZeldaArrowEntity(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel, double damage) {
        super(pEntityType, pShooter, pLevel);
        setBaseDamage(damage);
    }
}
