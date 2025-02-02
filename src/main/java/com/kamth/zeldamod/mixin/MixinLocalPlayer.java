package com.kamth.zeldamod.mixin;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


//credit to DeadlyDiamond98 this man is my hero
@Mixin(LocalPlayer.class)
public abstract class MixinLocalPlayer {


    @Shadow
    protected abstract boolean isControlledCamera();


    @Unique
    private LocalPlayer getPlayer() {
        return (LocalPlayer) (Object) this;
    }

    @Inject(method = "serverAiStep", at = @At("TAIL"))
    private void itemSlowdown(CallbackInfo ci) {


        if (getPlayer().isUsingItem() && !getPlayer().isPassenger()  && this.isControlledCamera()
                && getPlayer().getUseItem().is(ZeldaItems.PARAGLIDER.get())) {

            getPlayer().xxa /= 0.2f; // side
            getPlayer().zza /= 0.16f; // front/back
        }
    }
}


















