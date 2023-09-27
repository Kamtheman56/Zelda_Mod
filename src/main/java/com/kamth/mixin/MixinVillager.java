package com.kamth.mixin;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(Villager.class)
public abstract class MixinVillager{


    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyVariable(method = "updateSpecialPrices", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/entity/npc/Villager;getPlayerReputation(Lnet/minecraft/world/entity/player/Player;)I"))
    private int increaseReputation(int i, Player player) {
        if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.POSTMAN_MASK.get())) {
          i = 120;
        }
        if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.KAFEI_MASK.get())) {
            i = 50;
        }
        return i;
    }
}


















