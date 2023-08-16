package com.kamth.mixin;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.allay.AllayAi;
import net.minecraft.world.entity.player.Player;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;


@Mixin(AllayAi.class)
public abstract class MixinAllayAI
{
    // This constructor is fake and never used
    protected MixinAllayAI()
    {
        super();
    }

    @Inject(method = "getLikedPlayer", at = @At("HEAD"), cancellable = true)
    private static void Player(LivingEntity pEntity, CallbackInfoReturnable<Boolean> ci)
    {

        if (pEntity != null && pEntity.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.SCENT_MASK.get())
        {

            ci.setReturnValue(false);
        }
    }
}