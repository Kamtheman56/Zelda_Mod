package com.kamth.zeldamod.custom;

import com.kamth.zeldamod.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties BLUE = (new FoodProperties.Builder()).nutrition(20).saturationMod(1.2F).alwaysEat().effect(new MobEffectInstance(MobEffects.HEAL, 10, 0), 1.0F).build();
    public static final FoodProperties HEART = (new FoodProperties.Builder()).nutrition(0).saturationMod(0F).alwaysEat().effect(new MobEffectInstance(MobEffects.HEAL, 10, 0), 1.0F).build();
    public static final FoodProperties GLOOM_RESIST = (new FoodProperties.Builder()).nutrition(0).saturationMod(0F).alwaysEat().effect(new MobEffectInstance(ModEffects.GLOOM_RESIST.get(), 3200, 0, false, true, true), 1.0F).build();

    public static final FoodProperties KOROK = (new FoodProperties.Builder()).nutrition(2).saturationMod(.4F).alwaysEat().fast().effect(new MobEffectInstance(MobEffects.WEAKNESS, 80, 0), .6F).build();
    public static final FoodProperties LIFE_MUSHROOM = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).fast().alwaysEat().effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0), 1.0F).build();
    public static final FoodProperties MILK_BOTTLE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.2F).alwaysEat().effect(new MobEffectInstance(MobEffects.HEAL, 1, 0), 1.0F).build();
    public static final FoodProperties MILK_MAGIC = (new FoodProperties.Builder()).nutrition(20).saturationMod(3F).build();
    public static final FoodProperties MINI_MUSHROOM = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).fast().alwaysEat().effect(new MobEffectInstance(ModEffects.MINI.get(), 200, 0), 1.0F).build();
    public static final FoodProperties PUMPKIN_SOUP = (new FoodProperties.Builder()).nutrition(15).saturationMod(0.8F).build();
    public static final FoodProperties SHIELD = (new FoodProperties.Builder()).nutrition(0).saturationMod(0F).alwaysEat().effect(new MobEffectInstance(MobEffects.HEAL, 2, 0), 1.0F).build();
    public static final FoodProperties STAMINA = (new FoodProperties.Builder()).nutrition(20).saturationMod(1F).build();
    public static final FoodProperties SUPER_MUSHROOM = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.6F).fast().alwaysEat().effect(new MobEffectInstance(MobEffects.HEAL, 2, 0), 1.0F).build();
}
