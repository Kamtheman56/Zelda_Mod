package com.kamth.zeldamod.mixin.swordspin;

import com.kamth.zeldamod.enchantments.SwordSpin;
import com.kamth.zeldamod.util.interfaces.mixin.SwordSpinPlayerData;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Code contributed by Deadlydiamond98 (c) 2024 under the MIT License.
// Added here with explicit permission by the original owner.

@Mixin(Player.class)
public class MixinPlayer implements SwordSpinPlayerData {

    @Unique
    private boolean legendaryArmory$swordSwinging;

    @Unique
    private int legendaryArmory$swordspinTicks;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        this.legendaryArmory$swordspinTicks = 0;
        this.legendaryArmory$swordSwinging = false;
    }

    @Unique
    private Player legendaryArmory$self() {
        return (Player) (Object) this;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        Player player = legendaryArmory$self();

        this.legendaryArmory$swordspinTicks = SwordSpin.doSwordSpin(player, this.legendaryArmory$swordspinTicks, legendaryArmory$isSwordSpinActive());
    }


    @Override
    public void legendaryArmory$setSwordSpinActive(boolean bl) {
        this.legendaryArmory$swordSwinging = bl;
    }
    
    @Override
    public boolean legendaryArmory$isSwordSpinActive() {
        return this.legendaryArmory$swordSwinging;
    }
}
