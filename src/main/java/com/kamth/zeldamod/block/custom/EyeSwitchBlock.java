package com.kamth.zeldamod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class EyeSwitchBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public EyeSwitchBlock(Properties pProperties) {
        super(pProperties);

    }
    protected static final VoxelShape SHAPE2 = Block.box(0D, 0.0D, 0D, 16D, 16.0D, 16D);
    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
                VoxelShape voxelshape;
                switch (direction) {
                    case EAST:
                        voxelshape =  EAST_AABB;
                        break;
                    case WEST:
                        voxelshape = WEST_AABB;
                        break;
                    case SOUTH:
                        voxelshape =  SOUTH_AABB;
                        break;
                    case NORTH:
                    case UP:
                    case DOWN:
                        voxelshape = NORTH_AABB;
                        break;
                    default:
                        throw new IncompatibleClassChangeError();
                }
                return voxelshape;

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
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (pState.getValue(POWERED) == this.hasNeighborSignal(pLevel, pPos, pState) && !pLevel.getBlockTicks().willTickThisTick(pPos, this)) {
            pLevel.scheduleTick(pPos, this, 2);
        }

    }
    protected boolean hasNeighborSignal(Level pLevel, BlockPos pPos, BlockState pState) {
        return pLevel.hasSignal(pPos.below(), Direction.DOWN);
    }
@Override
    public int getDirectSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pSide == Direction.DOWN ? pBlockState.getSignal(pBlockAccess, pPos, pSide) : 0;
    }
    @Override
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        Entity entity = pProjectile.getOwner();
        if (entity instanceof ServerPlayer serverplayer) {
            serverplayer.awardStat(Stats.TARGET_HIT);
            pLevel.playSound(pLevel.getNearestPlayer(entity, 10),pHit.getBlockPos(), SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS);
            pLevel.setBlock(pHit.getBlockPos(), pState.cycle(POWERED),3);
            if (pLevel.isClientSide){
                pLevel.playSound(pLevel.getNearestPlayer(entity, 10),pHit.getBlockPos(), SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS);
            }
        }}
    public @NotNull InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
       if (!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND){
        pLevel.setBlock(pHit.getBlockPos(), pState.cycle(POWERED),3);
           return InteractionResult.SUCCESS;
    }
       if (pLevel.isClientSide){
           pLevel.playSound(pPlayer,pPos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS);
           return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
       }
       else return InteractionResult.FAIL;
    }

    public void press(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, pState.setValue(POWERED, Boolean.valueOf(true)), 3);
    }
    public int getSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pBlockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, POWERED);
    }
}
