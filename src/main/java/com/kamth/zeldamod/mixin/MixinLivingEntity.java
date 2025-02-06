package com.kamth.zeldamod.mixin;

import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ZeldaItems;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

    // TODO: Fix gravity things

    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot pSlot);

    @Shadow public abstract boolean hasEffect(MobEffect pEffect);

    @Shadow public abstract boolean addEffect(MobEffectInstance pEffectInstance);

    @Unique
    private LivingEntity getLivingEntity() {
        return (LivingEntity) (Object) this;
    }

    @WrapOperation(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getFriction(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;)F"))
    private float onTravel(BlockState instance, LevelReader levelReader, BlockPos pos, Entity entity, Operation<Float> original) {
        if (entity instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.FEET).getItem() == ZeldaItems.HOVER_BOOTS.get()) {
            return 1.05F;
        }
        if (entity instanceof LivingEntity living  && living.hasEffect(MobEffects.MOVEMENT_SPEED) && living.getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.GORON_MASK.get()) {
            return 1.08F;
        }
        return original.call(instance, levelReader, pos, entity);
    }

//    @ModifyVariable(method = "travel", at = @At("LOAD"), name = "d0", ordinal = 0)
//    public double inject3(double value) {
//        if (this.getItemBySlot(EquipmentSlot.MAINHAND).is(ZeldaItems.ROC_FEATHER_2.get()) || this.getItemBySlot(EquipmentSlot.OFFHAND).is(ZeldaItems.ROC_FEATHER_2.get() )) {
//            return 0.04;
//        }
//        if (this.getItemBySlot(EquipmentSlot.CHEST).is(ZeldaItems.ROC_CAPE.get())) {
//            return 0.05;
//        }
//        if (this.hasEffect(ModEffects.MINI.get())) {
//            return 0.02;
//        }
//        return value;
//    }

    @Inject(method = "getBlockSpeedFactor", at = @At("HEAD"), cancellable = true)
    private void onGetBlockSpeedFactor(CallbackInfoReturnable<Float> cir) {
        if (getLivingEntity().getItemBySlot(EquipmentSlot.FEET).getItem() == ZeldaItems.HOVER_BOOTS.get()) {
            cir.setReturnValue(.96F);
        }
        if (getLivingEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.GORON_MASK.get()) {
            cir.setReturnValue(.97F);
        }
    }

}


















