package com.kamth.zeldamod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class GanonGloomEffect extends MobEffect {

    protected GanonGloomEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier){
        super.applyEffectTick(pLivingEntity, pAmplifier);
        if (this == ModEffects.GLOOM.get() && !pLivingEntity.hasEffect(ModEffects.GLOOM_RESIST.get()) && pLivingEntity.getHealth() > 2) {
        }
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}

