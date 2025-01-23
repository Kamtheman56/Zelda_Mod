package com.kamth.zeldamod.item.items.tools;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class MogmaMittsItem extends DiggingMittsItem {
    public MogmaMittsItem(int pAttackDamageModifier, float pAttackSpeedModifier, Tier pTier, TagKey<Block> pBlocks, Properties pProperties) {
        super(pAttackDamageModifier, pAttackSpeedModifier, pTier, pBlocks, pProperties);
    }
    @Override
    public float getDestroySpeed(ItemStack itemstack, BlockState blockstate) {return 12f;}

}
