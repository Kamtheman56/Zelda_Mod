package com.kamth.zeldamod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier ZELDA = new ForgeTier(2, 59, 1.5f, 2f,
            3, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(Items.IRON_INGOT));
    public static final ForgeTier RAZOR = new ForgeTier(3, 200, 2.5f, 2f,
            3, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ZeldaItems.KOKIRI_SWORD_OOT.get()));

    public static final ForgeTier GILDED = new ForgeTier(4, 2001, 1.5f, 2f,
            2, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ZeldaItems.GOLD_DUST.get()));
    //Unbreakable items go in this tier
    public static final ForgeTier ZELDAU = new ForgeTier(2, 0, 1.5f, 2f,
            0, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ItemStack.EMPTY));
    public static final ForgeTier MASTER = new ForgeTier(2, 0, 1.5f, 2f,
            0, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ZeldaItems.MASTER_ORE.get()));
    public static final ForgeTier GORON = new ForgeTier(2, 0, 1.5f, 2f,
            0, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(Items.NETHERITE_SCRAP));
    public static final ForgeTier MASTER_TRUE = new ForgeTier(2, 0, 1.5f, 2f,
            0, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ItemStack.EMPTY));
    public static final ForgeTier DEITY = new ForgeTier(2, 0, 1.5f, 2f,
            0, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ZeldaItems.DEITY_SHARD.get()));
    public static final ForgeTier GLOOM = new ForgeTier(12, 59, 1.5f, 2f,
            3, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ZeldaItems.GLOOM_CLUMP.get()));
}
