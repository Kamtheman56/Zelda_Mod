package com.kamth.zeldamod.item.items;

import net.minecraft.world.item.ShieldItem;

public class WoodShieldItem extends ShieldItem {
    public WoodShieldItem(Properties pProperties) {
        super(pProperties);
    }
    public static final int EFFECTIVE_BLOCK_DELAY = 5;
    public static final float MINIMUM_DURABILITY_DAMAGE = 3.0F;
}
