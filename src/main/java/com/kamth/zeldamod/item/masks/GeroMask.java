package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.block.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class GeroMask extends TooltipMaskItem {
    public GeroMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties, Style.EMPTY.withColor(ChatFormatting.YELLOW).withItalic(true));
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
                living.spawnAtLocation(ModBlocks.CARMINE_FROGLIGHT.get());
            }
        }
    }
}
