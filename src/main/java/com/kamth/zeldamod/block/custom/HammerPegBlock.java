package com.kamth.zeldamod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HammerPegBlock extends Block {


    protected static final VoxelShape SHAPE = Block.box(3.75D, 0.0D, 3.75D, 12.25D, 25.0D, 12.25D);
    protected static final VoxelShape SHAPE2 = Block.box(3.75D, 0.0D, 3.75D, 12.25D, 16.0D, 12.25D);
    public HammerPegBlock (Properties pProperties) {
        super(pProperties);
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


}
