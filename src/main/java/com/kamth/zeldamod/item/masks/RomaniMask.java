package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
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

public class RomaniMask extends ArmorItem {
    public RomaniMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial,type, pProperties);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.TROUPE_MASK.get() && player.isCrouching()) {
            double particleX = player.getX() + (player.getRandom().nextBoolean() ? -0.1D : 0);
            double particleY = player.getY() + player.getRandom().nextFloat() * 0 - -1.2D;
            double particleZ = player.getZ() + (player.getRandom().nextBoolean() ? -0.1D : 0);
            player.level().addParticle(ParticleTypes.RAIN, particleX, particleY, particleZ, 0, 0, 0);

        }
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
        if (stack.is(ModItems.ROMANI_MASK.get())) {
            components.add(Component.translatable("item.romani_mask.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.GERO_MASK.get())) {
            components.add(Component.translatable("item.gero_mask.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.HAWK_MASK.get())) {
            components.add(Component.translatable("item.hawk_mask.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.TROUPE_MASK.get())) {
            components.add(Component.translatable("item.troupe_mask.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.KEATON_MASK.get())) {
            components.add(Component.literal("It looks valuable!").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }

        if (stack.is(ModItems.POSTMAN_MASK.get())) {
            components.add(Component.translatable("item.postman_mask.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.CAPTAIN_MASK.get())) {
            components.add(Component.translatable("item.captain_mask.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.GIBDO_MASK.get())){
            components.add(Component.translatable("item.gibdo_mask.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.SKULL_MASK.get())){
            components.add(Component.translatable("item.skull_mask.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.SPOOKY_MASK.get())){
            components.add(Component.translatable("item.spooky_mask.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.GERUDO_MASK.get())){
            components.add(Component.translatable("item.gerudo_mask.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }
}
