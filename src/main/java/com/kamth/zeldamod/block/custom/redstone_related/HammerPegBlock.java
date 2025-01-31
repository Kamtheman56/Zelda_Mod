package com.kamth.zeldamod.block.custom.redstone_related;

import com.kamth.zeldamod.custom.ModTags;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HammerPegBlock extends Block {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    protected static final VoxelShape SHAPE = Block.box(3.75D, 0.0D, 3.75D, 12.25D, 25.0D, 12.25D);
    protected static final VoxelShape SHAPE2 = Block.box(5D, 0.0D, 5D, 11D, 16.0D, 11D);
    protected static final VoxelShape FLAT = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 1.0D, 11.0D);
    public HammerPegBlock (Properties pProperties) {
        super(pProperties);

    }
@Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().is(ModTags.Items.HAMMERS)){
            this.slam(pState, pLevel, pPos);
            float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
            pLevel.playSound((Player)null, pPos, SoundEvents.WOOD_BREAK, SoundSource.BLOCKS, 1F, f);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
        }
    if (pPlayer.getAbilities().instabuild){
        this.press(pState, pLevel, pPos);
        float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
        pLevel.playSound((Player)null, pPos, SoundEvents.WOOD_BREAK, SoundSource.BLOCKS, 1F, f);
        pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
    }
    if (pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().is(ModTags.Items.SHOVEL_ITEMS) && pState.getValue(POWERED) == true){
        this.pull(pState, pLevel, pPos);
        pLevel.playSound((Player)null, pPos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1F, 1);
    }
        return InteractionResult.SUCCESS;
    }

    public void updateNeighbours(BlockState pState, Level pLevel, BlockPos pPos) {
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
        pState = pState.setValue(POWERED,true);
        pLevel.setBlock(pPos, pState, 3);
        this.updateNeighbours(pState, pLevel, pPos);
    }
    public void pull(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.setValue(POWERED,false);
        pLevel.setBlockAndUpdate(pPos, pState);
        this.updateNeighbours(pState, pLevel, pPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(POWERED,false);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED);
    }
    @Override
    public VoxelShape getVisualShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        if (pState.getValue(POWERED)){
            return FLAT.move(vec3.x, vec3.y, vec3.z);
        }
        else
            return SHAPE2.move(vec3.x, vec3.y, vec3.z);
    }
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        if (pState.getValue(POWERED)){
            return FLAT.move(vec3.x, vec3.y, vec3.z);
        }
        else
            return SHAPE2.move(vec3.x, vec3.y, vec3.z);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        if (pState.getValue(POWERED)){
            return FLAT.move(vec3.x, vec3.y, vec3.z);
        }
        else
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pIsMoving && !pState.is(pNewState.getBlock())) {
            if (pState.getValue(POWERED)) {
                this.updateNeighbours(pState, pLevel, pPos);
            }
            for(Direction direction : Direction.values()) {
                pLevel.updateNeighborsAt(pPos.relative(direction), this);
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }


}
