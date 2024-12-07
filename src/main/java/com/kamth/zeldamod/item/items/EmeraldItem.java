package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class EmeraldItem extends Item {

    public EmeraldItem(Properties pProperties) {
        super(pProperties);
    }



        @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {

            if (Screen.hasShiftDown() && stack.is(ModItems.BLUE_EMERALD.get() )) {
                components.add(Component.translatable("item.blue_emerald.description").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
            }
            if (Screen.hasShiftDown() && stack.is(ModItems.RED_EMERALD.get() )) {
                components.add(Component.translatable("item.red_emerald.description").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
            }
            if (Screen.hasShiftDown() && stack.is(ModItems.PURPLE_EMERALD.get() )) {
                components.add(Component.translatable("item.purple_emerald.description").withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC));
            }
            if (Screen.hasShiftDown() && stack.is(ModItems.GOLD_EMERALD.get() )) {
                components.add(Component.translatable("item.gold_emerald.description").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
            }
            if (Screen.hasShiftDown() && stack.is(ModItems.SILVER_EMERALD.get() )) {
                components.add(Component.translatable("item.silver_emerald.description").withStyle(ChatFormatting.WHITE).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
            }

    }
}
