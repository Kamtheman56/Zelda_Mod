package com.kamth.zeldamod.block.custom;

import com.kamth.zeldamod.block.entity.LockedChestEntity;
import com.kamth.zeldamod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class LockedChestBlock extends ChestBlock {

    public LockedChestBlock(Properties properties) {
        super(Properties.copy(Blocks.CHEST), () -> ModBlockEntities.LOCKED_CHEST_BE.get());

    }
    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LockedChestEntity(pos,state);
    }
}
