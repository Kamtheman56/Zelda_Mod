package com.kamth.zeldamod.util;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ZeldaTooltipUtil {
    record Format(ChatFormatting formatting, String formatString) {}
    private static final Format[] FORMATTINGS = {
            new Format(ChatFormatting.DARK_RED, "-red"),
            new Format(ChatFormatting.GOLD, "-yellow"),
            new Format(ChatFormatting.DARK_GREEN, "-green"),
            new Format(ChatFormatting.DARK_AQUA, "-aqua"),
            new Format(ChatFormatting.DARK_BLUE, "-blue"),
            new Format(ChatFormatting.DARK_PURPLE, "-purple"),

            new Format(ChatFormatting.RED, "red"),
            new Format(ChatFormatting.YELLOW, "yellow"),
            new Format(ChatFormatting.GREEN, "green"),
            new Format(ChatFormatting.AQUA, "aqua"),
            new Format(ChatFormatting.BLUE, "blue"),
            new Format(ChatFormatting.LIGHT_PURPLE, "purple"),

            new Format(ChatFormatting.BLACK, "black"),
            new Format(ChatFormatting.DARK_GRAY, "-gray"),
            new Format(ChatFormatting.GRAY, "gray"),
            new Format(ChatFormatting.WHITE, "white"),

            new Format(ChatFormatting.OBFUSCATED, "?"),
            new Format(ChatFormatting.BOLD, "b"),
            new Format(ChatFormatting.STRIKETHROUGH, "s"),
            new Format(ChatFormatting.UNDERLINE, "u"),
            new Format(ChatFormatting.ITALIC, "i")
    };

    private static Style createStyle(String formatString) {
        Style style = Style.EMPTY;

        for (Format formatting : FORMATTINGS) {
            if (formatString.isEmpty()) {
                return style;
            }
            else if (formatString.contains(formatting.formatString)) {
                style = style.applyFormat(formatting.formatting);
                formatString = formatString.replaceFirst(formatting.formatString, "");
            }
        }
        return style;
    }

    private static List<Component> addTooltips(String key, int count, String... format) {
        List<Component> components = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            components.add(Component.translatable(key)
                    .setStyle(createStyle(format[i])));
        }
        return components;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static List<Component> createTooltips(ItemStack stack, String translation, boolean hidden, int count, String... format) {
        if (hidden) {
            if (Screen.hasShiftDown()) {
                return addTooltips(stack.getDescriptionId() + translation, count, format);
            }
            return new ArrayList<>();
        }

        return addTooltips(stack.getDescriptionId() + translation, count, format);
    }

    public static List<Component> createSingleTooltip(ItemStack stack, boolean hidden, String format) {
        return createTooltips(stack, ".description", hidden, 1, format);
    }

    public static List<Component> createBasicAdvancedTooltip(ItemStack stack, String formatAdvanced, String formatBasic) {
        List<Component> components = new ArrayList<>();

        components.addAll(createTooltips(stack, ".description_advanced", true, 1, formatBasic));
        if (!Screen.hasShiftDown()) {
            components.addAll(createTooltips(stack, ".description_basic", false, 1, formatBasic));
        }
        return components;
    }
}
