package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
        }}

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (stack.is(ModItems.ROMANI_MASK.get())) {
            components.add(Component.literal("Friend to all cows").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.GERO_MASK.get())) {
            components.add(Component.literal("Conduct those frogs!").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.HAWK_MASK.get())) {
            components.add(Component.literal("Snipe enemies with ease").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.TROUPE_MASK.get())) {
            components.add(Component.literal("How sad").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.COUPLES_MASK.get())) {
            components.add(Component.literal("True love").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.KEATON_MASK.get())) {
            components.add(Component.literal("It looks valuable!").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.FAIRY_MASK.get())) {
            components.add(Component.literal("Heal with style").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.KAFEI_MASK.get())) {
            components.add(Component.literal("Who could say no to that face?").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.BREMEN_MASK.get())) {
            components.add(Component.literal("Follow the leader!").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.POSTMAN_MASK.get())) {
            components.add(Component.literal("Everyone gives good deals with this on!").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.CAPTAIN_MASK.get())) {
            components.add(Component.literal("Lead the undead archers").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }
}
