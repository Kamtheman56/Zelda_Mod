package com.kamth.zeldamod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties PUMPKIN_SOUP = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.5F).build();
    public static final FoodProperties STAMINA = (new FoodProperties.Builder()).nutrition(20).saturationMod(1F).build();
    public static final FoodProperties HEART = (new FoodProperties.Builder()).nutrition(0).saturationMod(0F).effect(new MobEffectInstance(MobEffects.HEAL, 10, 0), 1.0F).build();
    public static final FoodProperties MILK_BOTTLE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0F).build();
    public static final FoodProperties SHIELD = (new FoodProperties.Builder()).nutrition(0).saturationMod(0F).effect(new MobEffectInstance(MobEffects.HEAL, 2, 0), 1.0F).build();
    public static final FoodProperties BLUE = (new FoodProperties.Builder()).nutrition(20).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.HEAL, 10, 0), 1.0F).build();
    public static final FoodProperties MILK_MAGIC = (new FoodProperties.Builder()).nutrition(20).saturationMod(3F).build();
}
