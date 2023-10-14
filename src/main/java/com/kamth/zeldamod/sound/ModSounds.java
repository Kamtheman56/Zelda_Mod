package com.kamth.zeldamod.sound;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ZeldaMod.MOD_ID);
    
    
    public static final RegistryObject<SoundEvent> HEAL = registerSoundEvents ("heal");
    public static final RegistryObject<SoundEvent> LEAF = registerSoundEvents ("leaf");
    public static final RegistryObject<SoundEvent> MAJORA = registerSoundEvents ("majora");
    public static final RegistryObject<SoundEvent> CLAWSHOT = registerSoundEvents ("clawshot");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ZeldaMod.MOD_ID, name)));
    }


    public static void register (IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
