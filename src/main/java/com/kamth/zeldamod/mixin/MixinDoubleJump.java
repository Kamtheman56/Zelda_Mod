package com.kamth.zeldamod.mixin;




import com.kamth.zeldamod.item.items.movement.FeatherItem;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class MixinDoubleJump extends AbstractClientPlayer {
    @Unique
    private int jumps=0;
    @Unique
    private boolean lastJumped=false;

    public MixinDoubleJump(ClientLevel pClientLevel, GameProfile pGameProfile) {
        super(pClientLevel, pGameProfile);
    }


    @Inject(method = "aiStep()V", at = @At("HEAD"))
    private void doubleJump(CallbackInfo info) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        if (player.onGround() || player.onClimbable()) jumps = 1;
        else if (!lastJumped && jumps > 0 && player.getDeltaMovement().y < 0) {
            if (player.input.jumping && !player.getAbilities().flying) {
                if (canPerformJump(player)) {

                    --jumps;
                    player.jumpFromGround();
                    player.resetFallDistance();
                }
            }
        }
        lastJumped = player.input.jumping;
    }

    @Unique
    private boolean canPerformJump(LocalPlayer player) {
        ItemStack itemStack = player.getItemBySlot(EquipmentSlot.MAINHAND);
        ItemStack itemStack2 = player.getItemBySlot(EquipmentSlot.OFFHAND);
        return  (itemStack.getItem() instanceof FeatherItem && !player.isFallFlying() && !player.isPassenger()
                && !player.isInWater() && !player.hasEffect(MobEffects.LEVITATION)
                || itemStack2.getItem() instanceof FeatherItem && !player.isFallFlying() && !player.isPassenger()
                && !player.isInWater() && !player.hasEffect(MobEffects.LEVITATION));
    }


}