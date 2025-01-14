package com.kamth.zeldamod.client.particle;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleRegistry {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ZeldaMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> WHALE_SPLASH = PARTICLE_TYPES.register("whale_splash", ()-> new SimpleParticleType(false));



}
