package com.kamth.zeldamod.particle;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ZeldaMod.MOD_ID);
    public static final RegistryObject<SimpleParticleType> CHAIN =
            PARTICLE_TYPES.register("chain_particles", () -> new SimpleParticleType(true));
    public static void register (IEventBus eventBus){
       PARTICLE_TYPES.register(eventBus);
    }
}
