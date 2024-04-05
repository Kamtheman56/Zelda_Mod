package com.kamth.zeldamod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LockedChestEntity  extends ChestBlockEntity {


    public LockedChestEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.LOCKED_CHEST_BE.get(), pPos, pBlockState);
    }

    public LockedChestEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.LOCKED_CHEST_BE.get(),pPos, pBlockState);
    }
}