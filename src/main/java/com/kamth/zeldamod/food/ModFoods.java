package com.kamth.zeldamod.food;

import com.kamth.zeldamod.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoods {


    // FOOD STATS
    public static final FoodProperties SUPER_MUSHROOM = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).alwaysEdible().build();


    //FOOD CONSUMABLE STATS. Things such as effects given after eating.

    public static final Consumable SUPER_MUSHROOM_CONSUMABLE = Consumables.defaultFood().consumeSeconds(2)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.SUPER_EFFECT, 180) )).build();
    public static final Consumable MINI_MUSHROOM_CONSUMABLE = Consumables.defaultFood().consumeSeconds(2)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.MINI_EFFECT, 180) )).build();
    public static final Consumable MEGA_MUSHROOM_CONSUMABLE = Consumables.defaultFood().consumeSeconds(2)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.GIGANTIC, 180) )).build();
}
