package com.kamth.zeldamod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class GloomResist extends MobEffect {

    protected GloomResist(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier){
        super.applyEffectTick(pLivingEntity, pAmplifier);
        if (this == ModEffects.GLOOM_RESIST.get()) {
            pLivingEntity.removeEffect(MobEffects.WITHER);
                pLivingEntity.removeEffect(ModEffects.GLOOM.get());
    }}

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}

