package com.kamth.zeldamod.effect;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import static com.kamth.zeldamod.effect.ModEffects.MOB_EFFECTS;

public class ZeldaGenericEffect extends MobEffect {
    protected ZeldaGenericEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }





    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        return super.applyEffectTick(serverLevel, mob, amplification);
    }
}
