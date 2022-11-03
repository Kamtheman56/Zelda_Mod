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
}
