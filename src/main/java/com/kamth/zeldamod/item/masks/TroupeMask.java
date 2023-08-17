package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.List;

public class TroupeMask extends ArmorItem {
    public TroupeMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.TROUPE_MASK.get() && player.isCrouching()) {
                double particleX = player.getX() + (player.getRandom().nextBoolean() ? -0.1D : 0);
                double particleY = player.getY() + player.getRandom().nextFloat() * 0 - -1.2D;
                double particleZ = player.getZ() + (player.getRandom().nextBoolean() ? -0.1D : 0);
                player.level().addParticle(ParticleTypes.RAIN, particleX, particleY, particleZ, 0, 0, 0);

        }}

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("How sad").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

    }

}
