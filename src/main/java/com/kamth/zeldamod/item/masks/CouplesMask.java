package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class CouplesMask extends ArmorItem {
    public CouplesMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial,type, pProperties);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.COUPLES_MASK.get() && player.isCrouching()) {
            if(new Random().nextFloat() > .9f) {
                double particleX = player.getX() + (player.getRandom().nextBoolean() ? 0.1D : 0);
                double particleY = player.getY() + player.getRandom().nextFloat() * 0 + 1.2D;
                double particleZ = player.getZ() + (player.getRandom().nextBoolean() ? +0.8D : 0);
                player.level().addParticle(ParticleTypes.HEART, particleX, particleY, particleZ, 0, 0, 0);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.couples_mask.description_advanced").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
       else {
            components.add(Component.translatable("item.couples_mask.description_basic").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }
}
