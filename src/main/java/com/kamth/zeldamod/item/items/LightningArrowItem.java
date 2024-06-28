package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.custom.projectile.LightningArrow;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class LightningArrowItem extends ArrowItem {
    public LightningArrowItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public AbstractArrow createArrow(Level world, ItemStack ammoStack, LivingEntity shooter) {
        return new LightningArrow(ModEntityTypes.LIGHTNING_ARROW.get(), shooter, world);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()){
            components.add(Component.translatable("item.lightning_arrow.description").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }}
}
