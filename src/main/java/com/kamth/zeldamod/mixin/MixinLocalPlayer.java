package com.kamth.zeldamod.mixin;

import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.util.interfaces.mixin.SwordSpinPlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


//credit to DeadlyDiamond98 this man is my hero
@Mixin(LocalPlayer.class)
public abstract class MixinLocalPlayer {


    @Shadow
    protected abstract boolean isControlledCamera();

    @Unique
    private long rotationStartTick;
    @Unique
    private boolean startedSwordSpin;
    @Unique
    private float originalYaw;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        this.rotationStartTick = 0;
        this.startedSwordSpin = false;
        this.originalYaw = 0;
    }


    @Unique
    private LocalPlayer legendaryArmory$getPlayer() {
        return (LocalPlayer) (Object) this;
    }

    @Inject(method = "serverAiStep", at = @At("TAIL"))
    private void itemSlowdown(CallbackInfo ci) {

        LocalPlayer player = legendaryArmory$getPlayer();

        if (player.isUsingItem() && !player.isPassenger()  && this.isControlledCamera()
                && player.getUseItem().is(ZeldaItems.PARAGLIDER.get())) {

            player.xxa /= 0.2f; // side
            player.zza /= 0.18f; // front/back
        }
    }

    @Inject(method = "getViewYRot", at = @At("TAIL"), cancellable = true)
    private void rotateSwordSpin(float pPartialTick, CallbackInfoReturnable<Float> cir) {

        LocalPlayer player = legendaryArmory$getPlayer();

        SwordSpinPlayerData swordSpinPlayer = (SwordSpinPlayerData) player;

        if (swordSpinPlayer.legendaryArmory$isSwordSpinActive()) {

            long currentTick = player.tickCount;

            if (!this.startedSwordSpin) {
                this.rotationStartTick = currentTick;
                this.startedSwordSpin = true;
                this.originalYaw = player.getYRot();
            }

            float ticksElapsed = (currentTick - this.rotationStartTick) + pPartialTick;
            float fraction = ticksElapsed / 10;

            if (fraction >= 1.0F) {
                fraction = 1.0F;
            }

            float newYaw = this.originalYaw + 360.0F * fraction;

            cir.setReturnValue(newYaw);
        } else {
            this.startedSwordSpin = false;
        }
    }
}