package com.kamth.zeldamod.block.custom.redstone_related;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RustedPressureSwitchBlock extends Block {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    protected static final VoxelShape SHAPE2 = full();
    protected static final VoxelShape FLAT = flat();

    public RustedPressureSwitchBlock(Properties pProperties) {
        super(pProperties);

    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getAbilities().instabuild) {
            this.press(pState, pLevel, pPos);
            float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
            pLevel.playSound((Player) null, pPos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1F, f);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
        }
        if (pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().is(ModTags.Items.SHOVEL_ITEMS) && pState.getValue(POWERED) == true) {
            this.pull(pState, pLevel, pPos);
            pLevel.playSound((Player) null, pPos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1F, 1);
        }
        if (pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().is(ZeldaItems.MEGATON.get()) && pState.getValue(POWERED) == false) {
            this.slam(pState, pLevel, pPos);
            pLevel.playSound((Player) null, pPos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 1F, 1);
        }
        return InteractionResult.SUCCESS;
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof LivingEntity livingentity) {
            if (!pLevel.isClientSide && pState.getValue(POWERED).equals(false)) {
                if (livingentity.getItemBySlot(EquipmentSlot.FEET).is(ZeldaItems.HEAVY_BOOTS.get()) || livingentity.getItemBySlot(EquipmentSlot.HEAD).is(ZeldaItems.GORON_MASK.get())) {
                    this.slam(pState, pLevel, pPos);
                    pLevel.playSound((Player) null, pPos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 1F, 1);
                }
            }
        }
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
        pState = pState.setValue(POWERED, true);
        pLevel.setBlock(pPos, pState, 3);
        this.updateNeighbours(pState, pLevel, pPos);
    }

    public void pull(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.setValue(POWERED, false);
        pLevel.setBlockAndUpdate(pPos, pState);
        this.updateNeighbours(pState, pLevel, pPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(POWERED, false);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        if (pState.getValue(POWERED)) {
            return FLAT.move(vec3.x, vec3.y, vec3.z);
        } else return SHAPE2.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pIsMoving && !pState.is(pNewState.getBlock())) {
            if (pState.getValue(POWERED)) {
                this.updateNeighbours(pState, pLevel, pPos);
            }
            for (Direction direction : Direction.values()) {
                pLevel.updateNeighborsAt(pPos.relative(direction), this);
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    public static VoxelShape full() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.875, 0.125, 0.875), BooleanOp.OR);
        return shape;
    }

    public static VoxelShape flat() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.875, 0.03125, 0.875), BooleanOp.OR);
        return shape;
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
    }

}
