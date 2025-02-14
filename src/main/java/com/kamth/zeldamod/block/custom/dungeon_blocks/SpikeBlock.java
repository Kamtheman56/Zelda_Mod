package com.kamth.zeldamod.block.custom.dungeon_blocks;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.damage.ZeldaDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
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

public class SpikeBlock extends Block {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    protected static final VoxelShape SHAPE2 = Extended();
    public static final VoxelShape FLAT = Flat();
    public SpikeBlock(Properties pProperties) {
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
        this.cycle(pState, pLevel, pPos);
        float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
        pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
    }
        return InteractionResult.SUCCESS;
    }

    private void updateNeighbours(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.updateNeighborsAt(pPos, this);
    }
    public void cycle(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.cycle(POWERED);
        pLevel.setBlock(pPos, pState, 3);
        pLevel.playSound(null, pPos, SoundEvents.WOOD_BREAK, SoundSource.BLOCKS, 1F, 1);
        this.updateNeighbours(pState, pLevel, pPos);
    }
    public void slam(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.setValue(POWERED,true);
        pLevel.setBlock(pPos, pState, 3);
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

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof Player player && player.isCreative()) return;
        if (!pState.getValue(POWERED) && pEntity instanceof LivingEntity){
            pEntity.hurt(pLevel.damageSources().cactus(), 1.0F);
            pEntity.makeStuckInBlock(pState, new Vec3(0.8F, 0.75D, 0.8F));
        }
    }

    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            boolean flag = pState.getValue(POWERED);
            if (flag != pLevel.hasNeighborSignal(pPos)) {
                if (flag) {
                    pLevel.scheduleTick(pPos, this, 4);
                } else {
                    pLevel.setBlock(pPos, pState.cycle(POWERED), 2);
                }
            }
        }
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(POWERED) && !pLevel.hasNeighborSignal(pPos)) {
            pLevel.setBlock(pPos, pState.cycle(POWERED), 2);
        }
    }

    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        if (!pState.getValue(POWERED)) {
            pEntity.causeFallDamage(pFallDistance + 2.0F, 2.0F, pLevel.damageSources().cactus());
        } else {
            super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
        }

    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pIsMoving && !pState.is(pNewState.getBlock())) {
            this.updateNeighbours(pState, pLevel, pPos);
                for(Direction direction : Direction.values()) {
                pLevel.updateNeighborsAt(pPos.relative(direction), this);
                }
                super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }


    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        if (pState.getValue(POWERED)){
            return FLAT.move(vec3.x, vec3.y, vec3.z);
        }
        else return SHAPE2.move(vec3.x, vec3.y, vec3.z);
    }

    private static VoxelShape Flat(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.1875, 1), BooleanOp.OR);

        return shape;
    }

   private static VoxelShape Extended(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.1875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.1875, 0.0625, 0.9375, 0.8125, 0.9375), BooleanOp.OR);

        return shape;
    }

}
