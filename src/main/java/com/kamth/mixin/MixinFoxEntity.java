package com.kamth.mixin;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;





@Mixin(Fox.class)
public abstract class MixinFoxEntity extends Animal
{
    // This constructor is fake and never used
    protected MixinFoxEntity()
    {
        super(null,null);
    }

    @Inject(method = "trusts", at = @At("HEAD"), cancellable = true)
    private void Trustmode(UUID uuid, CallbackInfoReturnable<Boolean> ci)
    {
        Player player = level.getPlayerByUUID(uuid);

        if (player != null && player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.KEATON_MASK.get())
        {
            ci.setReturnValue(true);
        }
    }
}