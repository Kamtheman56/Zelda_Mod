package com.kamth.zeldamod.block.custom.ocarina_blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class ZoraGraveBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public ZoraGraveBlock(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }


    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
    public static VoxelShape north(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.4375, 0, 0.625, 0.5625, 1.375, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.624375, 0.6875, 1.5, 0.624375), BooleanOp.OR);
        return shape;
    }
    public static VoxelShape east(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.4375, 0.375, 1.375, 0.5625), BooleanOp.OR);
        return shape;
    }
    public static VoxelShape west(){
        VoxelShape shape = Shapes.empty();

        shape = Shapes.join(shape, Shapes.box(0.625, 0, 0.4375, 0.75, 1.375, 0.5625), BooleanOp.OR);
        return shape;
    }
    public static VoxelShape south(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.4375, 0, 0.25, 0.5625, 1.375, 0.375), BooleanOp.OR);
        return shape;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        VoxelShape voxelshape;
        Direction direction = pState.getValue(FACING);

        switch (direction) {
            case EAST:
                voxelshape =  east();
                break;
            case WEST:
                voxelshape = west();
                break;
            case SOUTH:
                voxelshape =  south();
                break;
            case NORTH:
            case UP:
            case DOWN:
                voxelshape = north();
                break;
            default:
                throw new IncompatibleClassChangeError();
        }
        return voxelshape;

    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
