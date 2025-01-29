package com.kamth.zeldamod.block.custom.ocarina_blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TimeGhostBlock extends GlassBlock {

    public TimeGhostBlock(Properties pProperties) {
        super(pProperties);

    }
    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        return ItemStack.EMPTY;
    }
}
