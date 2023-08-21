package com.kamth.zeldamod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class GloomEffect extends MobEffect {

    protected GloomEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier){
        super.applyEffectTick(pLivingEntity, pAmplifier);
        if (this == ModEffects.GLOOM.get()) {
            if (pLivingEntity.getHealth() > 1.0F) {
                pLivingEntity.hurt(pLivingEntity.damageSources().wither(), 1.0F);
            }
    }}

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}

