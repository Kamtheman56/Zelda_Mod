package com.kamth.zeldamod.block.custom.redstone_related;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class ZeldaRedstoneBlock extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public ZeldaRedstoneBlock(Properties pProperties) {
        super(pProperties);

    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pIsMoving && !pState.is(pNewState.getBlock())) {
            if (pState.getValue(POWERED)) {
                this.updateNeighbours(pState, pLevel, pPos);
            }
            for(Direction direction : Direction.values()) {
                pLevel.updateNeighborsAt(pPos.relative(direction), this);
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    public void updateNeighbours(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.updateNeighborsAt(pPos, this);
        pLevel.updateNeighborsAt(pPos.below(), this);
        pLevel.updateNeighborsAt(pPos.east(), this);
        pLevel.updateNeighborsAt(pPos.west(), this);
        pLevel.updateNeighborsAt(pPos.south(), this);
        pLevel.updateNeighborsAt(pPos.north(), this);

    }

    public void press(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.cycle(POWERED);
        pLevel.setBlock(pPos, pState, 3);
        this.updateNeighbours(pState, pLevel, pPos);
    }
    public void slam(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.setValue(POWERED,true);
        pLevel.setBlock(pPos, pState, 3);
        this.updateNeighbours(pState, pLevel, pPos);
    }
    public void pull(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.setValue(POWERED,false);
        pLevel.setBlockAndUpdate(pPos, pState);
        this.updateNeighbours(pState, pLevel, pPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(POWERED,false);
    }
    @Override
    public boolean isSignalSource(BlockState pState) {
        return true;
    }
    @Override
    public int getSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pBlockState.getValue(POWERED) && Direction.UP != pSide ? 15 : 0;
    }
    @Override
    public int getDirectSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pBlockState.getValue(POWERED) ? 15 : 0;
    }

}
