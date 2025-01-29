package com.kamth.zeldamod.block.custom.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class DekuFlowerBlock extends Block implements net.minecraftforge.common.IPlantable {



    public DekuFlowerBlock(Properties pProperties) {
        super(pProperties);
    }
    protected static final VoxelShape SHAPE2 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        return SHAPE2.move(vec3.x, vec3.y, vec3.z);
    }
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(BlockTags.DIRT) || pState.is(Blocks.FARMLAND);
    }

    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
            pEntity.causeFallDamage(pFallDistance, 0.0F, pLevel.damageSources().fall());
        }
    @Override
    public void updateEntityAfterFallOn(BlockGetter pLevel, Entity pEntity) {
        pEntity.setDeltaMovement(pEntity.getDeltaMovement().multiply(20.0D, 0.0D, 1.0D));
        pEntity.setDeltaMovement(pEntity.getDeltaMovement().multiply(20.0D, 0.0D, 1.0D));
    }
    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return IPlantable.super.getPlantType(level, pos);
    }
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        if (pState.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return pLevel.getBlockState(blockpos).canSustainPlant(pLevel, blockpos, Direction.UP, this);
        return this.mayPlaceOn(pLevel.getBlockState(blockpos), pLevel, blockpos);
    }
    @Override
    public BlockState getPlant(BlockGetter level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() != this) return defaultBlockState();
        return state;
    }
}






