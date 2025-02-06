package com.kamth.zeldamod.item.masks.regular;

import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.item.masks.TooltipMaskItem;
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

public class TroupeMask extends TooltipMaskItem {
    public TroupeMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial,type, pProperties, "iyellow");
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.TROUPE_MASK.get() && player.isCrouching()) {
            double particleX = player.getX() + (player.getRandom().nextBoolean() ? -0.1D : 0);
            double particleY = player.getY() + player.getRandom().nextFloat() * 0 - -1.2D;
            double particleZ = player.getZ() + (player.getRandom().nextBoolean() ? -0.1D : 0);
            player.level().addParticle(ParticleTypes.RAIN, particleX, particleY, particleZ, 0, 0, 0);

        }
    }
}
