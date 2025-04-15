package com.kamth.zeldamod.mixin.swordspin;

import com.kamth.zeldamod.enchantments.SwordSpin;
import com.kamth.zeldamod.util.interfaces.mixin.SwordSpinPlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Code contributed by Deadlydiamond98 (c) 2024 under the MIT License.
// Added here with explicit permission by the original owner.

@Mixin(MouseHandler.class)
public class MixinMouse {

    @Shadow @Final private Minecraft minecraft;

    @Shadow private double accumulatedDX;

    @Shadow private double accumulatedDY;

    @Inject(method = "turnPlayer", at = @At("HEAD"))
    private void turnPlayerSwordSpin(CallbackInfo ci) {

        SwordSpinPlayerData playerData = (SwordSpinPlayerData) this.minecraft.player;

        if (this.minecraft.player != null && playerData.legendaryArmory$isSwordSpinActive()) {
            this.accumulatedDX = 0;
            this.accumulatedDY = 0;
        }
    }

}
