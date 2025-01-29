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

public class ShockSwitchBlock extends Block {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    protected static final VoxelShape SHAPE = Block.box(3.75D, 0.0D, 3.75D, 12.25D, 25.0D, 12.25D);
    protected static final VoxelShape SHAPE2 = Block.box(5D, 0.0D, 5D, 11D, 16.0D, 11D);

    public ShockSwitchBlock(Properties pProperties) {
        super(pProperties);

    }

    @Override
    public void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        press(pState, pLevel, pPos);
        super.attack(pState, pLevel, pPos, pPlayer);
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pHand == InteractionHand.MAIN_HAND || pPlayer.getAbilities().instabuild){
            this.press(pState, pLevel, pPos);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
            float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
            pLevel.playSound((Player)null, pPos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, .8F, f);
        }
        return InteractionResult.SUCCESS;
    }
    @Override
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        Entity entity = pProjectile.getOwner();
        if (entity instanceof ServerPlayer serverplayer) {
            serverplayer.awardStat(Stats.TARGET_HIT);
            float f = pState.getValue(POWERED) ? 0.8F : 0.4F;
            pLevel.playSound((Player)null, pHit.getBlockPos(), SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, .8F, f);
            pLevel.setBlock(pHit.getBlockPos(), pState.cycle(POWERED),3);
            this.press(pState, pLevel, pHit.getBlockPos());
        }}
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
        float f = pState.getValue(POWERED) ? .9F : 0.7F;
        pLevel.playSound((Player)null, pPos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, .8F, f);
        this.updateNeighbours(pState, pLevel, pPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(POWERED,false);
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
        return pBlockState.getValue(POWERED) ? 15 : 0;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED);
    }


    @Override
    public VoxelShape getVisualShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
            return SHAPE2.move(vec3.x, vec3.y, vec3.z);
    }
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);

            return SHAPE2.move(vec3.x, vec3.y, vec3.z);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
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
