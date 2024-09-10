package com.kamth.zeldamod.block.custom;

import com.kamth.zeldamod.block.entity.SwordPedestalEntity;
import com.kamth.zeldamod.block.entity.UnlockedSwordPedestalEntity;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UnlockedSwordPedestalBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final VoxelShape SHAPE = Block.box(0,0,0, 16,2,16);
    public UnlockedSwordPedestalBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
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
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
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
        return new UnlockedSwordPedestalEntity(pPos, pState);
    }



    @Override
    public InteractionResult  use(BlockState pState, Level pLevel, BlockPos pPos,
                                  Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack stackInHand = pPlayer.getItemInHand(pHand);
        ItemStack three = ModItems.MASTER_SWORD3.get().getDefaultInstance();
        BlockEntity te = pLevel.getBlockEntity(pPos);
        UnlockedSwordPedestalEntity pedestal = (UnlockedSwordPedestalEntity) pLevel.getBlockEntity(pPos);
        if (te instanceof UnlockedSwordPedestalEntity)
        {
            if (stackInHand.is(ModItems.MASTER_SWORD3.get()) && pedestal.getSword().isEmpty())
            {
                if (stackInHand.getAllEnchantments().containsKey(Enchantments.SMITE) &&
                        stackInHand.getAllEnchantments().containsKey(Enchantments.SWEEPING_EDGE) &&
                        stackInHand.getAllEnchantments().containsKey(Enchantments.UNBREAKING)){
                pedestal.setSword(ModItems.MASTER_SWORD_TRUE.get().getDefaultInstance());
                pedestal.getSword().enchant(Enchantments.SMITE,3);
                pedestal.getSword().enchant(Enchantments.SWEEPING_EDGE,3);
                pedestal.getSword().enchant(Enchantments.MOB_LOOTING,3);
                pLevel.playSound(pPlayer,pPos, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS);
                pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                pLevel.updateNeighborsAt(pPos,this);
                return InteractionResult.SUCCESS;}
            }
            if (stackInHand.is(ModTags.Items.BROKEN_SWORDS) && pPlayer.getMaxHealth() >= 26 && pedestal.getSword().isEmpty())
            {
                    pedestal.setSword(ModItems.MASTER_SWORD.get().getDefaultInstance());
                    pLevel.playSound(pPlayer,pPos, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS);
                    pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                    pLevel.updateNeighborsAt(pPos,this);
                    return InteractionResult.SUCCESS;}
            }
            if (stackInHand.isEmpty() && !pedestal.getSword().isEmpty())
            {
                pLevel.updateNeighborsAt(pPos,this);
                pPlayer.setItemInHand(pHand, pedestal.getSword());
                pedestal.setSword(stackInHand);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
            else  if (stackInHand.getItem() instanceof SwordItem && pedestal.getSword().isEmpty())
        {
            pedestal.setSword(stackInHand);
            pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS);
            pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
            pLevel.updateNeighborsAt(pPos,this);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
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
    }

}
