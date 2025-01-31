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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class CopperPegBlock extends HammerPegBlock {
    public CopperPegBlock(Properties pProperties) {
        super(pProperties);

    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().is(ZeldaItems.MEGATON.get())){
            this.slam(pState, pLevel, pPos);
            float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
            pLevel.playSound(null, pPos, SoundEvents.WOOD_BREAK, SoundSource.BLOCKS, 1F, f);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
        }
        if (pPlayer.getAbilities().instabuild){
            this.press(pState, pLevel, pPos);
            float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
            pLevel.playSound(null, pPos, SoundEvents.WOOD_BREAK, SoundSource.BLOCKS, 1F, f);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
        }
        if (pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().is(ModTags.Items.SHOVEL_ITEMS) && pState.getValue(POWERED) == true){
            this.pull(pState, pLevel, pPos);
            pLevel.playSound(null, pPos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1F, 1);
        }
        return InteractionResult.SUCCESS;
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
