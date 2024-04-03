package com.kamth.zeldamod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class HammeredCopperPegBlock extends HammeredPegBlock {


    public HammeredCopperPegBlock(Properties properties) {
        super(properties);
    }

    public boolean isSignalSource(BlockState pState) {
        return true;
    }

    /**
     * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#getSignal}
     * whenever possible. Implementing/overriding is fine.
     */
    public int getSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return 15;
    }

}
