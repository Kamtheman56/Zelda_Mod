package com.kamth.zeldamod.mixin;

import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
//credit to DeadlyDiamond98

    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot pSlot);

    @Shadow public abstract boolean hasEffect(MobEffect pEffect);

    @Shadow public abstract boolean hasItemInSlot(EquipmentSlot pSlot);

    @Shadow public abstract boolean addEffect(MobEffectInstance pEffectInstance);

    @Shadow public abstract boolean removeAllEffects();

    @Shadow public abstract void setHealth(float pHealth);

    @Shadow protected abstract void playHurtSound(DamageSource pSource);

    public MixinLivingEntity(EntityType<?> pEntityType, Level pLevel) {
        super(null, null);
    }
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getFriction(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;)F"))
    private float onTravel(BlockState state, LevelReader level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HOVER_BOOTS.get()) {
                return 1.05F;
        }
        if (entity instanceof LivingEntity living  && living.hasEffect(MobEffects.MOVEMENT_SPEED) && living.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GORON_MASK.get()) {
            return 1.08F;
        }
        return state.getFriction(level, pos, entity);}
    @ModifyVariable(method = "travel", at = @At("LOAD"), name = "d0", ordinal = 0, index = 2)//return
    public double inject3(double value) {
        if (this.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.ROC_FEATHER_2.get()) || this.getItemBySlot(EquipmentSlot.OFFHAND).is(ModItems.ROC_FEATHER_2.get() )) {
                return 0.04;
        }
        if (this.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.ROC_CAPE.get())) {
            return 0.05;
        }
    if (this.hasEffect(ModEffects.MINI.get())) {
                return 0.02;
        }
        return value;
    }


    @SuppressWarnings("InvalidInjectorMethodSignature")
    @Inject(method = "getBlockSpeedFactor", at = @At("HEAD"), cancellable = true)
    private void onGetBlockSpeedFactor(CallbackInfoReturnable<Float> cir) {

        if (((Object)this) instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HOVER_BOOTS.get()) {
            cir.setReturnValue(.96F);}

 if (((Object)this) instanceof LivingEntity living  && living.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GORON_MASK.get()) {
        cir.setReturnValue(.97F);}
    }

    @Inject(method = "playHurtSound", at = @At("HEAD"))
    private void onServerPlayHurtSound(CallbackInfo info) {
        //noinspection ConstantConditions
        playSound(ModSounds.OLD_HURT.get());
    }
}


















