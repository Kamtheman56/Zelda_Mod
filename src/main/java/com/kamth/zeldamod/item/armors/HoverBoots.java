package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.swing.text.JTextComponent;

public class HoverBoots extends ArmorItem {
    public HoverBoots(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }


    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        if (player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HOVER_BOOTS.get()) {

            if (player.isSprinting()) {
                BlockPos pos = new BlockPos(Mth.floor(player.getX()), Mth.floor(player.getY() - 0.2), Mth.floor(player.getZ()));
                player.level.getBlockState(pos).getFluidState().isEmpty();

            }

        }
    }
}



