package com.kamth.zeldamod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab ZELDA_TAB = new CreativeModeTab("zeldatab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BLUE_EMERALD.get());

        }
    };
    public static final CreativeModeTab ZELDA_MASKS = new CreativeModeTab("zeldamask") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.DEKU_MASK.get());
        }};
    public static final CreativeModeTab ZELDA_FOODSTUFFS = new CreativeModeTab("zeldafood") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.PUMPKIN_SOUP.get());
        }};}




