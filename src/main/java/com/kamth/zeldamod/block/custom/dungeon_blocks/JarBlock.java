package com.kamth.zeldamod.block.custom.dungeon_blocks;

import com.kamth.zeldamod.block.entity.JarEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JarBlock extends BaseEntityBlock {

    public static final VoxelShape SHAPE = makeShape();
    public JarBlock(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override @Nullable
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new JarEntity(pPos, pState);
    }

    @Override
    public InteractionResult  use(BlockState pState, Level pLevel, BlockPos pPos,
                                  Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack stackInHand = pPlayer.getItemInHand(pHand);
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        JarEntity pedestal = (JarEntity) pLevel.getBlockEntity(pPos);
        BlockEntity te = pLevel.getBlockEntity(pPos);
        if (te instanceof JarEntity)
        {
       if (pedestal.getSword().isEmpty())
            if (pedestal.getSword().isEmpty())
            {
                pedestal.setSword(stackInHand);
                pLevel.playSound(pPlayer,pPos, SoundEvents.DECORATED_POT_PLACE, SoundSource.BLOCKS);
                pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                pLevel.addParticle(ParticleTypes.CLOUD, (double)pPos.getX() + 0.5D, (double)pPos.getY() + 1.2D, (double)pPos.getZ() + 0.5D, 0D, 0.0D, 0.0D);
                pLevel.updateNeighborsAt(pPos,this);
                return InteractionResult.SUCCESS;
            }
            else if (stackInHand.isEmpty() && !pedestal.getSword().isEmpty())
            {
                pLevel.updateNeighborsAt(pPos,this);
                pPlayer.setItemInHand(pHand, pedestal.getSword());
                pedestal.setSword(stackInHand);
                pLevel.addParticle(ParticleTypes.CLOUD, (double)pPos.getX() + 0.5D, (double)pPos.getY(), (double)pPos.getZ() + 0.5D, 0D, 0.0D, 0.0D);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }






    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof JarEntity) {
                ((JarEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
    public static VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.5, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.5, 0.375, 0.625, 0.5625, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.5625, 0.3125, 0.6875, 0.6875, 0.6875), BooleanOp.OR);
        return shape;
    }
    @Override
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        Entity entity = pProjectile.getOwner();
        if (entity instanceof ServerPlayer serverplayer) {
            serverplayer.awardStat(Stats.TARGET_HIT);
           pLevel.destroyBlock(pHit.getBlockPos(),false);
        }}
    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

}
