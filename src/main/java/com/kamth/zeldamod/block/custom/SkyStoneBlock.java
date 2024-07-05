package com.kamth.zeldamod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class SkyStoneBlock extends Block {

    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public SkyStoneBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.valueOf(false)));
    }

    public void press(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.cycle(LIT);
        pLevel.setBlock(pPos, pState, 3);
    }
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().is(Items.AIR)|| pPlayer.getAbilities().instabuild && pPlayer.getMainHandItem().is(Items.AIR) ){
            this.press(pState, pLevel, pPos);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
            float f = pState.getValue(LIT) ? 0.6F : 0.5F;
            pLevel.playSound((Player)null, pPos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, .8F, f);
            return InteractionResult.SUCCESS;
        }
        else return InteractionResult.PASS;

    }


    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(LIT, Boolean.valueOf(pContext.getLevel().hasNeighborSignal(pContext.getClickedPos())));
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT);
    }

}
