package com.kamth.zeldamod.block.custom.plants;

import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.custom.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class WildBombFlowerBlock extends FaceAttachedHorizontalDirectionalBlock  {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public WildBombFlowerBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.FLOOR));

    }
    @Override
    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable net.minecraft.core.Direction face, @Nullable LivingEntity igniter) {
        explode(world,pos);
    }
    public static void explode(Level pLevel, BlockPos pPos) {
        explode(pLevel, pPos, (LivingEntity)null);
    }
    @Deprecated //Forge: Prefer using IForgeBlock#catchFire
    private static void explode(Level pLevel, BlockPos pPos, @Nullable LivingEntity pEntity) {
        if (!pLevel.isClientSide) {
            pLevel.explode(null, pPos.getX(), pPos.getY(), pPos.getZ(),  2f, Level.ExplosionInteraction.MOB);
            int radius = (int) Math.ceil(3);
            for (BlockPos pos : BlockPos.betweenClosed(pPos.offset(-radius, -radius, -radius), pPos.offset(radius, radius, radius))) {
                BlockState blockState = pLevel.getBlockState(pos).getBlock().defaultBlockState();
                if (blockState.is(ModTags.Blocks.BOMB)){
                    pLevel.destroyBlock(pos, false);
                }
                if (blockState.is(ZeldaBlocks.WILD_BOMB_FLOWER.get())){
                    pLevel.explode(null, pos.getX(), pos.getY(), pos.getZ(),  3.7f, Level.ExplosionInteraction.MOB);
                    pLevel.destroyBlock(pos, false);
                }
            }
        }
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!itemstack.is(Items.FLINT_AND_STEEL) && !itemstack.is(Items.FIRE_CHARGE)) {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        } else {
            onCaughtFire(pState, pLevel, pPos, pHit.getDirection(), pPlayer);
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 11);
            Item item = itemstack.getItem();
            if (!pPlayer.isCreative()) {
                if (itemstack.is(Items.FLINT_AND_STEEL)) {
                    itemstack.hurtAndBreak(1, pPlayer, (p_57425_) -> {
                        p_57425_.broadcastBreakEvent(pHand);
                    });
                } else {
                    itemstack.shrink(1);
                }
            }
            pPlayer.awardStat(Stats.ITEM_USED.get(item));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
    }
    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof AbstractArrow) {
            onCaughtFire(pState, pLevel, pPos, pEntity.getDirection(), ((AbstractArrow) pEntity).getControllingPassenger());
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 11);
            pEntity.remove(Entity.RemovalReason.DISCARDED);
        }}
    public void wasExploded(Level pLevel, BlockPos pPos, Explosion pExplosion) {
        if (!pLevel.isClientSide) {
            pLevel.explode(null, pPos.getX(), pPos.getY(), pPos.getZ(), 4f, Level.ExplosionInteraction.MOB);
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 11);
        }
    }
    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, FACE );
    }
}






