package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class KamaroMask extends ArmorItem {
    public KamaroMask(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);

    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        Level level = world;
        if (level.getBlockState(player.getOnPos()).is(Blocks.JUKEBOX)) {
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, 10, 0, true, true));
        }

        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.KAMARO_MASK.get() && player.isCrouching()) {
            if(new Random().nextFloat() > .9f) {
                double particleX = player.getX() + (player.getRandom().nextBoolean() ? 0.1D : 0);
                double particleY = player.getY() + player.getRandom().nextFloat() * 0 + 1.2D;
                double particleZ = player.getZ() + (player.getRandom().nextBoolean() ? +0.8D : 0);
                world.playSound(player,player.getOnPos(), SoundEvents.NOTE_BLOCK_FLUTE.get(), SoundSource.PLAYERS,1,1/ (player.level().getRandom().nextFloat() * 0.4F + 0.8F));
                player.level().addParticle(ParticleTypes.NOTE, particleX, particleY, particleZ, 0, 0, 0);
            }

        }

    }

@Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("item.kamaro_mask.description").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
}}
