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
            () -> new WaterHopping(MobEffectCategory.HARMFUL, 3124687));
    public static final RegistryObject<MobEffect> HOVER = MOB_EFFECTS.register("hover",
            () -> new WaterHopping(MobEffectCategory.BENEFICIAL, 3124687));

    public static void register(IEventBus eventbus){
        MOB_EFFECTS.register(eventbus);
    }
}
