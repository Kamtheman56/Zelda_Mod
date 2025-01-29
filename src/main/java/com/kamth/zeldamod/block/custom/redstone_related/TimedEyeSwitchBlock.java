package com.kamth.zeldamod.block.custom.redstone_related;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;


//A block that activates when hit with a projectile only
public class TimedEyeSwitchBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public final int ticksToStayPressed;
    private final boolean arrowsCanPress;
    public TimedEyeSwitchBlock(Properties pProperties, int ticksToStayPressed, boolean arrowsCanPress) {
        super(pProperties);

        this.ticksToStayPressed = ticksToStayPressed;
        this.arrowsCanPress = arrowsCanPress;
    }
    protected static final VoxelShape SHAPE2 = Block.box(0D, 0.0D, 0D, 16D, 16.0D, 16D);
    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel,  BlockPos pPos, CollisionContext pContext) {
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
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        Entity entity = pProjectile.getOwner();
        if (entity instanceof ServerPlayer serverplayer) {
            serverplayer.awardStat(Stats.TARGET_HIT);
            float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
            pLevel.playSound((Player)null, pHit.getBlockPos(), SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 0.8F, f);
            pLevel.setBlock(pHit.getBlockPos(), pState.cycle(POWERED),3);
            this.press(pState, pLevel, pHit.getBlockPos());
        }}

    public @NotNull InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand == InteractionHand.MAIN_HAND && pPlayer.getUseItem().is(ItemStack.EMPTY.getItem()) && pPlayer.getAbilities().instabuild){
            this.press(pState, pLevel, pPos);
            float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
            pLevel.playSound((Player)null, pPos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1F, f);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
        }
        return InteractionResult.SUCCESS;
    }
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pIsMoving && !pState.is(pNewState.getBlock())) {
            if (pState.getValue(POWERED)) {
                this.updateNeighbours(pState, pLevel, pPos);
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }
    private void updateNeighbours(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.updateNeighborsAt(pPos, this);
        pLevel.updateNeighborsAt(pPos.relative(getConnectedDirection(pState).getOpposite()), this);
    }
    public void press(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, pState.setValue(POWERED, Boolean.valueOf(true)), 3);
        this.updateNeighbours(pState, pLevel, pPos);
        pLevel.scheduleTick(pPos, this, this.ticksToStayPressed);
    }
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(POWERED)) {
            this.checkPressed(pState, pLevel, pPos);
        }
    }
    protected void checkPressed(BlockState pState, Level pLevel, BlockPos pPos) {
        AbstractArrow abstractarrow = this.arrowsCanPress ? pLevel.getEntitiesOfClass(AbstractArrow.class, pState.getShape(pLevel, pPos).bounds().move(pPos)).stream().findFirst().orElse((AbstractArrow)null) : null;
        boolean flag = abstractarrow != null;
        boolean flag1 = pState.getValue(POWERED);
        if (flag != flag1) {
            pLevel.setBlock(pPos, pState.setValue(POWERED, Boolean.valueOf(flag)), 3);
            this.updateNeighbours(pState, pLevel, pPos);
            pLevel.gameEvent(abstractarrow, flag ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pPos);
        }
        if (flag) {
            pLevel.scheduleTick(new BlockPos(pPos), this, this.ticksToStayPressed);
        }
    }
    @Override
    public boolean isSignalSource(BlockState pState) {
        return true;
    }
    @Override
    public int getSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pBlockState.getValue(POWERED) ? 15 : 0;
    }
    @Override
    public int getDirectSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pBlockState.getValue(POWERED) && getConnectedDirection(pBlockState) == pSide ? 15 : 0;
    }
    protected static Direction getConnectedDirection(BlockState pState) {
                return pState.getValue(FACING);
        }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, POWERED);
    }
}
