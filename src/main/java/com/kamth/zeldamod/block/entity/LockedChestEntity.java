package com.kamth.zeldamod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LockedChestEntity  extends ChestBlockEntity {
    public LockedChestEntity(BlockPos pPos, BlockState pBlockState) {
        super(ZeldaBlockEntities.LOCKED_CHEST_ENTITY.get(),pPos, pBlockState);
    }
    @Override
    protected Component getDefaultName() {
        return (Component.translatable("Locked Chest"));
    }
}