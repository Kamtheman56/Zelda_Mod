package com.kamth.zeldamod.item.items.arrows;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.projectile.arrows.FireArrow;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class FireArrowItem extends TooltipArrow {
    public FireArrowItem(Properties pProperties) {
        super(pProperties, Style.EMPTY.withItalic(true).withColor(ChatFormatting.RED));
    }
    @Override
    public AbstractArrow createArrow(Level world, ItemStack ammoStack, LivingEntity shooter) {
        return new FireArrow(ModEntityTypes.FIRE_ARROW.get(), shooter, world);
    }
}
