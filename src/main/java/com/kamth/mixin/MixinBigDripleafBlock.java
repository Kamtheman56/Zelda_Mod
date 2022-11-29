package com.kamth.mixin;


import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BigDripleafBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Tilt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(BigDripleafBlock.class)
public class MixinBigDripleafBlock { @Inject(at = @At("HEAD"), method = "Lnet/minecraft/world/level/block/BigDripleafBlock;setTiltAndScheduleTick(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/properties/Tilt;Lnet/minecraft/sounds/SoundEvent;)V", cancellable = true)
public void setTiltAndScheduleTick(BlockState p_152283_, Level p_152284_, BlockPos p_152285_, Tilt p_152286_, SoundEvent p_152287_, CallbackInfo ci) {

}




    }



