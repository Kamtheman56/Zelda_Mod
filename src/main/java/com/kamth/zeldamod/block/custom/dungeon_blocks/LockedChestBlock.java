package com.kamth.zeldamod.block.custom.dungeon_blocks;

import com.kamth.zeldamod.block.entity.LockedChestEntity;
import com.kamth.zeldamod.block.entity.ZeldaBlockEntities;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class LockedChestBlock extends AbstractChestBlock<LockedChestEntity> {
    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LOCKED = BlockStateProperties.LOCKED;
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    public LockedChestBlock(Properties properties) {
        super(Properties.of().noOcclusion().mapColor(MapColor.METAL).strength(3.5f).requiresCorrectToolForDrops(), ZeldaBlockEntities.LOCKED_CHEST_ENTITY::get);
        registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(TYPE, ChestType.SINGLE));

    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LockedChestEntity(pos,state);
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(LOCKED, true).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.is(pNewState.getBlock())) return;

        if (pLevel.getBlockEntity(pPos) instanceof Container container) {
            Containers.dropContents(pLevel, pPos, container);
            pLevel.updateNeighbourForOutputSignal(pPos, this);
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.getBlockEntity(pPos) instanceof LockedChestEntity entity) {
            entity.recheckOpen();
        }
    }
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide() && pState.getValue(LOCKED) && !pPlayer.getAbilities().instabuild) {
            return InteractionResult.FAIL;
        }
        if (pHand == InteractionHand.MAIN_HAND && !pPlayer.getAbilities().instabuild && pPlayer.getMainHandItem().is(ZeldaItems.SMALL_KEY.get())){
            this.press(pState, pLevel, pPos);
            pLevel.playSound((Player)null, pPos, ModSounds.DOOR_UNLOCK.get(), SoundSource.BLOCKS, .4F, 1f);
            pPlayer.getUseItem().shrink(1);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
        }

        if (!pLevel.isClientSide() && pPlayer.getAbilities().instabuild) {
    MenuProvider menuprovider = getMenuProvider(pState, pLevel, pPos);
    if (menuprovider != null) {
        pPlayer.openMenu(menuprovider);
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
}
      if (!pLevel.isClientSide() && !pState.getValue(LOCKED)) {
            MenuProvider menuprovider = getMenuProvider(pState, pLevel, pPos);
            if (menuprovider != null) {
                pPlayer.openMenu(menuprovider);
                PiglinAi.angerNearbyPiglins(pPlayer, true);
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
    public void press(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.cycle(LOCKED);
        pLevel.setBlock(pPos, pState, 3);
    }

    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
        if (pStack.hasCustomHoverName() && pLevel.getBlockEntity(pPos) instanceof LockedChestEntity entity) {
            entity.setCustomName(pStack.getHoverName());
        }
    }
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE, LOCKED);
    }
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide() ? createTickerHelper(pBlockEntityType, ZeldaBlockEntities.LOCKED_CHEST_ENTITY.get(), LockedChestEntity::lidAnimateTick) : null;
    }
    @Override
    public DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> combine(BlockState pState, Level pLevel, BlockPos pPos, boolean pOverride) {
        return DoubleBlockCombiner.Combiner::acceptNone;
    }
}
