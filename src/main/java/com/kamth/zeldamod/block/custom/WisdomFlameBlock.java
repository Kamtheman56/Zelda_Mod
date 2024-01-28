package com.kamth.zeldamod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class WisdomFlameBlock extends Block {
    public WisdomFlameBlock(Properties pProperties) {
        super(pProperties);
    }

    protected static final VoxelShape shapeDown = Block.box(0.0D, 0.0D, 0.0D, 15.99D, 1.0D, 15.99D);

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return shapeDown;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return this.canSurvive(pState, pLevel, pCurrentPos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        return pLevel.getBlockState(blockpos).isFaceSturdy(pLevel, blockpos, Direction.UP);
    }



    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(24) == 0) {
            pLevel.playLocalSound((double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 1.0F + pRandom.nextFloat(), pRandom.nextFloat() * 0.7F + 0.3F, false);
        }

        BlockPos blockpos = pPos.below();
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (!this.canBurn(blockstate) && !blockstate.isFaceSturdy(pLevel, blockpos, Direction.UP)) {
            if (this.canBurn(pLevel.getBlockState(pPos.west()))) {
                for(int j = 0; j < 2; ++j) {
                    double d3 = (double)pPos.getX() + pRandom.nextDouble() * (double)0.1F;
                    double d8 = (double)pPos.getY() + pRandom.nextDouble();
                    double d13 = (double)pPos.getZ() + pRandom.nextDouble();
                    pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d3, d8, d13, 0.0D, 0.0D, 0.0D);
                }
            }

            if (this.canBurn(pLevel.getBlockState(pPos.east()))) {
                for(int k = 0; k < 2; ++k) {
                    double d4 = (double)(pPos.getX() + 1) - pRandom.nextDouble() * (double)0.1F;
                    double d9 = (double)pPos.getY() + pRandom.nextDouble();
                    double d14 = (double)pPos.getZ() + pRandom.nextDouble();
                    pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d4, d9, d14, 0.0D, 0.0D, 0.0D);
                }
            }

            if (this.canBurn(pLevel.getBlockState(pPos.north()))) {
                for(int l = 0; l < 2; ++l) {
                    double d5 = (double)pPos.getX() + pRandom.nextDouble();
                    double d10 = (double)pPos.getY() + pRandom.nextDouble();
                    double d15 = (double)pPos.getZ() + pRandom.nextDouble() * (double)0.1F;
                    pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d5, d10, d15, 0.0D, 0.0D, 0.0D);
                }
            }

            if (this.canBurn(pLevel.getBlockState(pPos.south()))) {
                for(int i1 = 0; i1 < 2; ++i1) {
                    double d6 = (double)pPos.getX() + pRandom.nextDouble();
                    double d11 = (double)pPos.getY() + pRandom.nextDouble();
                    double d16 = (double)(pPos.getZ() + 1) - pRandom.nextDouble() * (double)0.1F;
                    pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d6, d11, d16, 0.0D, 0.0D, 0.0D);
                }
            }

            if (this.canBurn(pLevel.getBlockState(pPos.above()))) {
                for(int j1 = 0; j1 < 2; ++j1) {
                    double d7 = (double)pPos.getX() + pRandom.nextDouble();
                    double d12 = (double)(pPos.getY() + 1) - pRandom.nextDouble() * (double)0.1F;
                    double d17 = (double)pPos.getZ() + pRandom.nextDouble();
                    pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d7, d12, d17, 0.0D, 0.0D, 0.0D);
                }
            }
        } else {
            for(int i = 0; i < 3; ++i) {
                double d0 = (double)pPos.getX() + pRandom.nextDouble();
                double d1 = (double)pPos.getY() + pRandom.nextDouble() * 0.5D + 0.5D;
                double d2 = (double)pPos.getZ() + pRandom.nextDouble();
                pLevel.addParticle(ParticleTypes.LARGE_SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    protected boolean canBurn(BlockState state) {
        return true;
    }
}
