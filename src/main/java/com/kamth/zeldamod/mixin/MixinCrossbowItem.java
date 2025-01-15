package com.kamth.zeldamod.mixin;


import com.kamth.zeldamod.item.items.bags.QuiverItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin (CrossbowItem.class)
public class MixinCrossbowItem {
    @Inject(
            method = "releaseUsing", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V",
            shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            cancellable = true)


    private void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int remainingUseTicks, CallbackInfo ci) {

        if (entity instanceof Player user) {
            ItemStack quiverStack = findQuiver(user);
            if (quiverStack != null) {
                QuiverItem quiver = (QuiverItem) quiverStack.getItem();
                Optional<ItemStack> arrowStack = quiver.getFirstItem(quiverStack);
                if (arrowStack.isPresent()) {
                    if (!user.getAbilities().instabuild) {
                        quiver.removeOneItem(quiverStack, arrowStack.get().getItem());
                    }
                    ci.cancel();
                }
            }
        }
    }

    @Unique
    private ItemStack findQuiver(Player player) {

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() instanceof QuiverItem) {
                return stack;
            }
        }
        return null;
    }
}
