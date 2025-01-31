package com.kamth.zeldamod.damage;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ZeldaDamageTypes {


    public static final ResourceKey<DamageType> MALICE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("zeldamod:gloom"));
    public static final ResourceKey<DamageType> SPIKE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("zeldamod:spike"));


    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(ZeldaMod.MOD_ID, name));
    }
    public static DamageSource causeMalice(LivingEntity attacker){
        return new DamageSourceRandomMessages(attacker.level().registryAccess().registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(MALICE), attacker);
    }
    public static DamageSource causeSpike(LivingEntity attacker){
        return new DamageSourceRandomMessages(attacker.level().registryAccess().registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(SPIKE), attacker);
    }
    private static class DamageSourceRandomMessages extends DamageSource {


        public DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, @Nullable Entity entity1, @Nullable Entity entity2, @Nullable Vec3 from) {
            super(damageTypeHolder, entity1, entity2, from);
        }

        public DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, @Nullable Entity entity1, @Nullable Entity entity2) {
            super(damageTypeHolder, entity1, entity2);
        }

        public DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, Vec3 from) {
            super(damageTypeHolder, from);
        }

        public DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, @Nullable Entity entity) {
            super(damageTypeHolder, entity);
        }

        public DamageSourceRandomMessages(Holder<DamageType> p_270475_) {
            super(p_270475_);
        }

        @Override
        public Component getLocalizedDeathMessage(LivingEntity attacked) {
            int type = attacked.getRandom().nextInt(2);
            LivingEntity livingentity = attacked.getKillCredit();
            String s = "death.attack." + this.getMsgId() + "_" + type;
            String s1 = s + ".player";
            return livingentity != null ? Component.translatable(s1, attacked.getDisplayName(), livingentity.getDisplayName()) : Component.translatable(s, attacked.getDisplayName());
        }
    }

}
