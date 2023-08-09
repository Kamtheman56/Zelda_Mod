package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.custom.projectile.LightArrow;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class LightArrowItem extends ArrowItem {
    public LightArrowItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public AbstractArrow createArrow(Level world, ItemStack ammoStack, LivingEntity shooter) {
        return new LightArrow(ModEntityTypes.LIGHT_ARROW.get(), shooter, world);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Smite evil in style").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));

        }

    }
