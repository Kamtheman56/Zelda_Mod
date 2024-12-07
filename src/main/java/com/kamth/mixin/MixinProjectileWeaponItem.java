package com.kamth.mixin;


import com.kamth.zeldamod.api.LegendaryArmoryPlayerData;
import com.kamth.zeldamod.item.items.QuiverItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Predicate;

//Credits to DeadlyDiamond98 for this code!

@Mixin (ProjectileWeaponItem.class)
public class MixinProjectileWeaponItem {
    @Inject(method = "getHeldProjectile", at = @At("HEAD"), cancellable = true)
    private static void getArrowFromQuiver(LivingEntity entity, Predicate<ItemStack> predicate, CallbackInfoReturnable<ItemStack> cir) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            LegendaryArmoryPlayerData accessor = (LegendaryArmoryPlayerData) player;


            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {

                ItemStack stack = player.getInventory().getItem(i);
                if (stack.getItem() instanceof QuiverItem) {
                    handleQuiver(stack, accessor, cir);
                    if (cir.getReturnValue() != null) {
                        return;
                    }
                }
            }
        }

    }

    @Unique
    private static void handleQuiver(ItemStack stack, LegendaryArmoryPlayerData accessor, CallbackInfoReturnable<ItemStack> cir) {
        QuiverItem customBundle = (QuiverItem) stack.getItem();
        Optional<ItemStack> arrowStack = customBundle.getFirstItem(stack);
        if (arrowStack.isPresent()) {
            if (accessor.hasArrowBeenRemoved()) {
                ItemStack arrowToRemove = arrowStack.get();
                cir.setReturnValue(arrowToRemove);
                customBundle.removeOneItem(stack, arrowToRemove.getItem());
                accessor.setArrowRemoved(false);
            } else {
                ItemStack arrowToRemove = arrowStack.get();
                cir.setReturnValue(arrowToRemove);
                accessor.setArrowRemoved(true);
            }
        }
    }
}