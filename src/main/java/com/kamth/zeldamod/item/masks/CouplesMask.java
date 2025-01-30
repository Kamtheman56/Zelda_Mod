package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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
        super(pMaterial, type, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.COUPLES_MASK.get() && player.isCrouching()) {
            if (new Random().nextFloat() > 0.9f) {
                double particleX = player.getX() + (player.getRandom().nextBoolean() ? 0.1 : 0);
                double particleY = player.getY() + player.getRandom().nextFloat() * 0 + 1.2;
                double particleZ = player.getZ() + (player.getRandom().nextBoolean() ? + 0.8 : 0);
                player.level().addParticle(ParticleTypes.HEART, particleX, particleY, particleZ, 0, 0, 0);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.couples_mask.description_advanced").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.zeldamod.couples_mask.description_basic").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
    }

    public static void onInteract(Player user, Entity target) {
        if (target instanceof LivingEntity living) {
            user.hurt(user.damageSources().magic(), 2);
            living.playSound(ModSounds.HEAL.get(), 1, 1);
            living.heal(2);
            living.level().addParticle(ParticleTypes.HEART, true, living.getX() + 0, living.getY() + 0.6f, living.getZ() + 0, 0, 0, 0);
        }
    }
}