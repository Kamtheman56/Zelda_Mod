package com.kamth.zeldamod.block.custom.redstone_related;

import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
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
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class ZeldaStone extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public ZeldaStone(Properties pProperties) {
        super(pProperties);

    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().is(ZeldaItems.OCARINA.get()) && !pPlayer.getCooldowns().isOnCooldown(pPlayer.getMainHandItem().getItem()) && !pPlayer.getAbilities().instabuild ){
            this.press(pState, pLevel, pPos);
            float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
            pLevel.playSound((Player)null, pPos, ModSounds.SONG_ZELDA.get(), SoundSource.BLOCKS, .4F, 1f);
            pPlayer.getCooldowns().addCooldown(pPlayer.getMainHandItem().getItem(),250);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
        }
        if (pPlayer.getAbilities().instabuild){
            this.press(pState, pLevel, pPos);
            float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
            pLevel.playSound((Player)null, pPos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1F, f);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
        }
        return InteractionResult.SUCCESS;
    }

    private void updateNeighbours(BlockState pState, Level pLevel, BlockPos pPos) {
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
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(POWERED,false).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }
    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
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
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED);
        pBuilder.add(FACING);
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


}
