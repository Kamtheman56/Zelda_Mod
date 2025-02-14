package com.kamth.zeldamod.block.custom.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
    protected static final VoxelShape FLOWER = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape FLOWER_HITBOX = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.3D, 16.0D);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        return FLOWER.move(vec3.x, vec3.y, vec3.z);
    }
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(BlockTags.DIRT) || pState.is(Blocks.FARMLAND);
    }

    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }


    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return IPlantable.super.getPlantType(level, pos);
    }
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        if (pState.getBlock() == this)
            return pLevel.getBlockState(blockpos).canSustainPlant(pLevel, blockpos, Direction.UP, this);
        return this.mayPlaceOn(pLevel.getBlockState(blockpos), pLevel, blockpos);
    }
    @Override
    public BlockState getPlant(BlockGetter level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() != this) return defaultBlockState();
        return state;
    }
    @Override
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float height) {
        if (!world.isClientSide) {
            if (height > 2) {
                world.playSound(null, pos, SoundEvents.AZALEA_LEAVES_FALL, SoundSource.BLOCKS, 1F, 0.9F);
            }
        }
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter reader, Entity entity) {
        entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0D, 0.4D, 1.0D));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        return FLOWER_HITBOX.move(vec3.x, vec3.y, vec3.z);
    }
}






