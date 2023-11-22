package com.kamth.zeldamod.effect;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ZeldaMod.MOD_ID);

    public static final RegistryObject<MobEffect> DEKU = MOB_EFFECTS.register("deku",
            () -> new WaterHopping(MobEffectCategory.BENEFICIAL, 3124687));
    public static final RegistryObject<MobEffect> GORON = MOB_EFFECTS.register("goron",
            () -> new WaterHopping(MobEffectCategory.BENEFICIAL, 3124687));
    public static final RegistryObject<MobEffect> FIRE = MOB_EFFECTS.register("fire",
            () -> new WaterHopping(MobEffectCategory.BENEFICIAL, 3124687));
    public static final RegistryObject<MobEffect> HOVER = MOB_EFFECTS.register("hover",
            () -> new WaterHopping(MobEffectCategory.BENEFICIAL, 3124687));
    public static final RegistryObject<MobEffect> MINI = MOB_EFFECTS.register("mini",
            () -> new MiniEffect(MobEffectCategory.BENEFICIAL, 3124687));
    public static final RegistryObject<MobEffect> GLOOM = MOB_EFFECTS.register("gloom",
            () -> new GloomEffect(MobEffectCategory.HARMFUL, 13901836));
    public static final RegistryObject<MobEffect> GLOOM_RESIST = MOB_EFFECTS.register("gloom_resist",
            () -> new GloomEffect(MobEffectCategory.BENEFICIAL, 15726080));

    public static void register(IEventBus eventbus){
        MOB_EFFECTS.register(eventbus);
    }
}
