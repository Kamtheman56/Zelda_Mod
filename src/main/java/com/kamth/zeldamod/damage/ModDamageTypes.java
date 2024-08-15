package com.kamth.zeldamod.damage;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;

public class ModDamageTypes {

    public static ResourceKey<DamageType> MALICE = register("gloom_damage");



    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(ZeldaMod.MOD_ID, name));
    }
    public static DamageSource causeMalice(LivingEntity attacker){
        return new DamageSource(attacker.level().registryAccess().registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(MALICE), attacker);
    }

}
