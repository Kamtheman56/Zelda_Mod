package com.kamth.zeldamod.block.custom.pedastal;

import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.block.entity.SwordPedestalEntity;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MasterSwordPedestalBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty wisdom = BooleanProperty.create("wisdom");
    public static final BooleanProperty courage = BooleanProperty.create("courage");
    public static final BooleanProperty power = BooleanProperty.create("power");
    public static final BooleanProperty unlocked = BooleanProperty.create("unlocked");
    public static final VoxelShape SHAPE = Block.box(0,0,0, 16,2,16);
    public MasterSwordPedestalBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(wisdom,false).setValue(courage,false).setValue(power,false)
                .setValue(unlocked,false));
    }
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(wisdom,false).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }
    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }
    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
    @Override @Nullable
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SwordPedestalEntity(pPos, pState);
    }



    @Override
    public InteractionResult  use(BlockState pState, Level pLevel, BlockPos pPos,
                                  Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack stackInHand = pPlayer.getItemInHand(pHand);
        BlockEntity te = pLevel.getBlockEntity(pPos);
        SwordPedestalEntity pedestal = (SwordPedestalEntity) pLevel.getBlockEntity(pPos);

        if (te instanceof SwordPedestalEntity)
        {

            if (stackInHand.getItem() instanceof SwordItem && pedestal.getSword().isEmpty())
            {
                pedestal.setSword(stackInHand);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS);
                pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                pLevel.updateNeighborsAt(pPos,this);
                return InteractionResult.SUCCESS;
            }
            if( stackInHand.is(ZeldaItems.NAYRU_PEARL.get()) && !pState.getValue(wisdom)) {
                stackInHand.shrink(1);
                pLevel.playSound(pPlayer,pPos, SoundEvents.CHISELED_BOOKSHELF_INSERT_ENCHANTED,SoundSource.BLOCKS);
                pPlayer.displayClientMessage(Component.translatable("Wisdom").withStyle(ChatFormatting.BLUE).withStyle(ChatFormatting.BOLD), true);
                pLevel.setBlock(pPos, pState.cycle(wisdom),3);
                return InteractionResult.SUCCESS;
            }
            if( stackInHand.is(ZeldaItems.FARORE_PEARL.get()) && !pState.getValue(courage)) {
                stackInHand.shrink(1);
                pLevel.playSound(pPlayer,pPos, SoundEvents.CHISELED_BOOKSHELF_INSERT_ENCHANTED,SoundSource.BLOCKS);
                pPlayer.displayClientMessage(Component.translatable("Courage").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.BOLD), true);
                pLevel.setBlock(pPos, pState.cycle(courage),3);
                return InteractionResult.SUCCESS;
            }
            if( stackInHand.is(ZeldaItems.DIN_PEARL.get()) && !pState.getValue(power)) {
                stackInHand.shrink(1);
                pLevel.playSound(pPlayer,pPos, SoundEvents.CHISELED_BOOKSHELF_INSERT_ENCHANTED,SoundSource.BLOCKS);
                pPlayer.displayClientMessage(Component.translatable("Power").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD), true);
                pLevel.setBlock(pPos, pState.cycle(power),3);
                return InteractionResult.SUCCESS;
            }
            if(stackInHand.isEmpty() && pState.getValue(wisdom) && pState.getValue(courage) && pState.getValue(power)
                    && !pState.getValue(unlocked)) {
                pLevel.playSound(pPlayer,pPos, SoundEvents.BEACON_ACTIVATE,SoundSource.BLOCKS);
                pLevel.setBlock(pPos, pState.cycle(unlocked),3);

               pLevel.setBlockAndUpdate(pPos, ZeldaBlocks.UNLOCKED_SWORD_PEDESTAL.get().defaultBlockState());
                return InteractionResult.SUCCESS;
            }
           if (stackInHand.isEmpty() && pState.getValue(unlocked)&& !pedestal.getSword().isEmpty()|| pPlayer.getAbilities().instabuild)
            {
                pLevel.updateNeighborsAt(pPos,this);
                pPlayer.setItemInHand(pHand, pedestal.getSword());
                pedestal.setSword(stackInHand);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
         if (stackInHand.isEmpty() && !pState.getValue(unlocked) && !pedestal.getSword().isEmpty()){
               return InteractionResult.FAIL;}}
        return InteractionResult.FAIL;
    }
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getValue(unlocked)) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof SwordPedestalEntity) {
                ((SwordPedestalEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        pBuilder.add(wisdom);
        pBuilder.add(courage);
        pBuilder.add(power);
        pBuilder.add(unlocked);
    }

}
