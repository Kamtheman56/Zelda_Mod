package com.kamth.zeldamod.item.masks.regular;

import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.item.masks.TooltipMaskItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;

public class GeroMask extends TooltipMaskItem {
    public GeroMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties, "iyellow");
    }

    public static void onInteract(Player user, Entity living) {
        if (living instanceof Frog) {
            // TODO: REPLACE WITH TRANSLATION KEY
            user.sendSystemMessage(Component.literal(user.getName().getString() + "Try eating some Magma Cubes!"));
            living.playSound(SoundEvents.FROG_AMBIENT, 1, 2.6f);
        }

        if (living instanceof Slime) {
            living.discard();
            living.playSound(SoundEvents.FROG_EAT, 1, 1);
            living.playSound(SoundEvents.PLAYER_BURP, 1, 1);

            if (living instanceof MagmaCube) {
                living.spawnAtLocation(ZeldaBlocks.CARMINE_FROGLIGHT.get());
            }
        }
    }
}
