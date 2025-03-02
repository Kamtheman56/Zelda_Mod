package com.kamth.zeldamod.mixin;


import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.util.Mth;
import net.minecraft.util.SmoothDouble;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BowItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MixinMouse {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow @Final private SmoothDouble smoothTurnX;

    @Shadow @Final private SmoothDouble smoothTurnY;

    @Shadow private double accumulatedDX;

    @Shadow private double accumulatedDY;


    @Inject(method = "turnPlayer",at = @At("HEAD"),cancellable = true)
    public void onMouseUpdate(CallbackInfo ci){
        if (null != minecraft.player && minecraft.options.getCameraType().isFirstPerson() && minecraft.player.getItemBySlot(EquipmentSlot.HEAD).is(ZeldaItems.HAWKEYE_MASK.get())  && minecraft.player.getUseItem().getItem() instanceof BowItem){
            double displacementX,displacementY;
            double sensitivity = this.minecraft.options.sensitivity().get() * .6 + .2;
            double baseSensitivity = (sensitivity * sensitivity * sensitivity) * 2.2f;
            double smoothSensitivity= baseSensitivity * Mth.clamp(0 ,.3f,.5f);

            displacementX = this.smoothTurnX.getNewDeltaValue(this.accumulatedDX * smoothSensitivity, smoothSensitivity);

            displacementY = this.smoothTurnY.getNewDeltaValue(this.accumulatedDY * smoothSensitivity,  smoothSensitivity);

            accumulatedDX = .0;

            accumulatedDY = .0;

            int mouseDirection = minecraft.options.invertYMouse().get()? -1:1;

            minecraft.getTutorial().onMouse(displacementX, displacementY);

            if (minecraft.player != null) {
                minecraft.player.turn(displacementX, displacementY * mouseDirection);
            }
            ci.cancel();
        }

    }
}
