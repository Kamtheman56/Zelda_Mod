package com.kamth.zeldamod.item.items.arrows;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.projectile.arrows.BombArrow;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BombArrowItem extends TooltipArrow {
    public BombArrowItem(Properties pProperties) {
        super(pProperties, Style.EMPTY.withItalic(true).withColor(ChatFormatting.WHITE));
    }
    @Override
    public AbstractArrow createArrow(Level world, ItemStack ammoStack, LivingEntity shooter) {
        return new BombArrow(ModEntityTypes.BOMB_ARROW.get(), shooter, world);
    }
}
