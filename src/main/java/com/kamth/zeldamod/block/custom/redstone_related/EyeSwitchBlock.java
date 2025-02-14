package com.kamth.zeldamod.block.custom.redstone_related;

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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


//A block that activates when hit with a projectile only
public class EyeSwitchBlock extends ZeldaRedstoneBlock {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public EyeSwitchBlock(Properties pProperties) {
        super(pProperties);

    }
    protected static final VoxelShape SHAPE2 = Block.box(0D, 0.0D, 0D, 16D, 16.0D, 16D);
    protected static final VoxelShape UP_AABB = Block.box(0D, 0.0D, 0D, 16D, 3.0D, 16D);
    protected static final VoxelShape DOWN_AABB = Block.box(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case DOWN -> DOWN_AABB;
            case UP -> UP_AABB;
            case EAST -> EAST_AABB;
            case WEST -> WEST_AABB;
            case NORTH -> NORTH_AABB;
            case SOUTH -> SOUTH_AABB;
        };
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(POWERED,false).setValue(FACING, pContext.getClickedFace());
    }
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }
    @Override
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        Entity entity = pProjectile.getOwner();
        if (entity instanceof ServerPlayer serverplayer) {
            serverplayer.awardStat(Stats.TARGET_HIT);
            float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
            pLevel.playSound((Player)null, pHit.getBlockPos(), SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 0.8F, f);
            pLevel.setBlock(pHit.getBlockPos(), pState.cycle(POWERED),3);
            this.press(pState, pLevel, pHit.getBlockPos());
        }
    }
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand == InteractionHand.MAIN_HAND && pPlayer.getUseItem().is(ItemStack.EMPTY.getItem()) && pPlayer.getAbilities().instabuild){
            this.press(pState, pLevel, pPos);
            float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
            pLevel.playSound((Player)null, pPos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1F, f);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void updateNeighbours(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.updateNeighborsAt(pPos, this);
        pLevel.updateNeighborsAt(pPos.relative(getConnectedDirection(pState).getOpposite()), this);
    }


    @Override
    public int getDirectSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pBlockState.getValue(POWERED) && getConnectedDirection(pBlockState) == pSide ? 15 : 0;
    }
    protected static Direction getConnectedDirection(BlockState pState) {
                return pState.getValue(FACING);
        }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return Shapes.block();
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, POWERED);
    }
}
