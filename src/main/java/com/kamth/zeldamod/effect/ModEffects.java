package com.kamth.zeldamod.effect;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {


    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ZeldaMod.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> SUPER_EFFECT = MOB_EFFECTS.register("super_sized",
            () -> new ZeldaGenericEffect(MobEffectCategory.BENEFICIAL, 16596817)
                    .addAttributeModifier(Attributes.SCALE,
                            Identifier.withDefaultNamespace("effect.super_sized"), 1, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.STEP_HEIGHT,
                            Identifier.withDefaultNamespace("effect.super_sized"), 1, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.SAFE_FALL_DISTANCE,
                            Identifier.withDefaultNamespace("effect.super_sized"), 6, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE,
                            Identifier.withDefaultNamespace("effect.super_sized"), 4, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE,
                            Identifier.withDefaultNamespace("effect.super_sized"), 4, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE,
                            Identifier.withDefaultNamespace("effect.super_sized"), 4, AttributeModifier.Operation.ADD_VALUE));

    public static final DeferredHolder<MobEffect, MobEffect> MINI_EFFECT = MOB_EFFECTS.register("miniaturized",
            () -> new ZeldaGenericEffect(MobEffectCategory.BENEFICIAL, 4454876)
                    .addAttributeModifier(Attributes.SCALE,
                            Identifier.withDefaultNamespace("effect.mini_sized"), -0.65,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.GRAVITY,
                            Identifier.withDefaultNamespace("effect.mini_sized"), -.05,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            Identifier.withDefaultNamespace("effect.mini_sized"), .1,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.SAFE_FALL_DISTANCE,
                            Identifier.withDefaultNamespace("effect.mini_sized"), 12,
                            AttributeModifier.Operation.ADD_VALUE));

    public static final DeferredHolder<MobEffect, MobEffect> GIGANTIC = MOB_EFFECTS.register("gigantic",
            () -> new ZeldaGenericEffect(MobEffectCategory.BENEFICIAL, 16361795)
                    .addAttributeModifier(Attributes.SCALE,
                            Identifier.withDefaultNamespace("effect.gigantic"), 1.8,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE,
                            Identifier.withDefaultNamespace("effect.gigantic"), 8,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.STEP_HEIGHT,
                            Identifier.withDefaultNamespace("effect.gigantic"), 2,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE,
                            Identifier.withDefaultNamespace("effect.gigantic"), 8,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.SAFE_FALL_DISTANCE,
                            Identifier.withDefaultNamespace("effect.gigantic"), 10,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE,
                            Identifier.withDefaultNamespace("effect.gigantic"), 6,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.ATTACK_KNOCKBACK,
                            Identifier.withDefaultNamespace("effect.gigantic"), 4,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.JUMP_STRENGTH,
                            Identifier.withDefaultNamespace("effect.gigantic"), 0.38,
                            AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.GRAVITY,
                            Identifier.withDefaultNamespace("effect.gigantic"), 0.03,
                            AttributeModifier.Operation.ADD_VALUE)
    );

    public static void register (IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
