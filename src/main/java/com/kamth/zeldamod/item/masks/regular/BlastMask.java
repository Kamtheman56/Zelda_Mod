package com.kamth.zeldamod.item.masks.regular;

import com.kamth.zeldamod.item.masks.TooltipMaskItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlastMask extends TooltipMaskItem {
    public BlastMask(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties, "iyellow");
    }
    @Override
    public void onArmorTick (ItemStack stack, Level world, Player player) {
// Math used for bomb jumping
        float f7 = player.getYRot();
        float f = player.getXRot();
        float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
        float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
        float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
        float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
        float f5 = 3.5F * ((1.0F + (float) 1) / 4.0F);
        f1 *= f5 / f4;
        f2 *= f5 / f4;
        f3 *= f5 / f4;

        if (!player.getCooldowns().isOnCooldown(this) && player.isCrouching() && !player.onClimbable())
        {
            if (player.isBlocking() && player.onGround()){
                player.level().explode(player, player.getX(), player.getY(), player.getZ(), 4F, Level.ExplosionInteraction.NONE);
                player.getCooldowns().addCooldown(this,350);

            }
            if (player.isBlocking() && !player.onGround()){
                player.level().explode(player, player.getX(), player.getY(), player.getZ(), 2F, Level.ExplosionInteraction.NONE);
                player.getCooldowns().addCooldown(this,120);
                player.push(f1, f2, f3);
            }
            if (!player.isBlocking()){
                player.level().explode(player, player.getX(), player.getY(), player.getZ(), 4F, Level.ExplosionInteraction.NONE);
                player.getCooldowns().addCooldown(this,400);
                player.hurt(player.damageSources().magic(), 10);
                player.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(5, player, (p_43296_) -> {
                    p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD);
                });
            }
        }


    }

}
