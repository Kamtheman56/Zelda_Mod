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
    public static final RegistryObject<SoundEvent> DOOR_LOCK = registerSoundEvents ("locked_door");
    public static final RegistryObject<SoundEvent> DOOR_UNLOCK = registerSoundEvents ("door_unlock");
    public static final RegistryObject<SoundEvent> WOODEN_HAMMER = registerSoundEvents ("wooden_hammer");
    public static final RegistryObject<SoundEvent> SEED_BREAKS = registerSoundEvents ("seed_breaks");
    public static final RegistryObject<SoundEvent> DARKNUT_INJURED = registerSoundEvents ("darknut_injured");
    public static final RegistryObject<SoundEvent> SONG_HEALING = registerSoundEvents ("song_healing");
    public static final RegistryObject<SoundEvent> SONG_TIME = registerSoundEvents ("song_time");
    public static final RegistryObject<SoundEvent> SONG_SOARING = registerSoundEvents ("song_soaring");
    public static final RegistryObject<SoundEvent> SONG_STORMS = registerSoundEvents ("song_storms");
    public static final RegistryObject<SoundEvent> SONG_ZELDA = registerSoundEvents ("song_zelda");
    public static final RegistryObject<SoundEvent> SONG_SUN = registerSoundEvents ("song_sun");
    public static final RegistryObject<SoundEvent> SONG_EPONA = registerSoundEvents ("song_epona");
    public static final RegistryObject<SoundEvent> SONG_SARIA = registerSoundEvents ("song_saria");
    public static final RegistryObject<SoundEvent> OCARINA = registerSoundEvents ("ocarina_sound");
    public static final RegistryObject<SoundEvent> BROWN_BRICKS = registerSoundEvents ("brown_bricks");
    public static final RegistryObject<SoundEvent> SLINGSHOT_PULL = registerSoundEvents ("slingshot_pull");
    public static final RegistryObject<SoundEvent> SLINGSHOT_RELEASE = registerSoundEvents ("slingshot_release");
    public static final RegistryObject<SoundEvent> OLD_HURT = registerSoundEvents ("old_hurt");
    public static final RegistryObject<SoundEvent> BOOMERANG_TOSS = registerSoundEvents ("boomerang_toss");
    public static final RegistryObject<SoundEvent> MORSHU = registerSoundEvents ("gus");
    public static final RegistryObject<SoundEvent> KOROK_LIKES = registerSoundEvents ("korok_likes");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ZeldaMod.MOD_ID, name)));
    }


    public static void register (IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
