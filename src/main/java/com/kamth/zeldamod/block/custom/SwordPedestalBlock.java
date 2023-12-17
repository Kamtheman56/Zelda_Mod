package com.kamth.zeldamod.block.custom;

import com.kamth.zeldamod.block.entity.SwordPedestalEntity;
import com.kamth.zeldamod.item.custom.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SwordPedestalBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final VoxelShape SHAPE = Block.box(0,0,0, 16,2,16);
    public SwordPedestalBlock(Properties pProperties) {
        super(pProperties);

    }
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SwordPedestalEntity(pPos, pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity te = pLevel.getBlockEntity(pPos);
        if (!pLevel.isClientSide && te instanceof SwordPedestalEntity displayTile && pHand == pHand.MAIN_HAND) {
            ItemStack inHand = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);

            ItemStack inPedestal = displayTile.getRenderStack().copy();
            inHand = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
            ItemStack toPedestal = inHand.copy();
            toPedestal.setCount(1);
            displayTile.setWeapon(toPedestal);
            inHand.shrink(1);
            pPlayer.setItemInHand(InteractionHand.MAIN_HAND,inPedestal);

            pLevel.updateNeighborsAt(pPos,this);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
    private boolean isAllowed(ItemStack stack){
        return stack.is(ModTags.Items.SWORDS);
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

}
