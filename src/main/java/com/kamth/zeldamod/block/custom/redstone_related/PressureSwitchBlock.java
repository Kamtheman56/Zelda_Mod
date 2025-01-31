package com.kamth.zeldamod.block.custom.redstone_related;

import com.kamth.zeldamod.custom.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
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

public class PressureSwitchBlock extends ZeldaRedstoneBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    protected static final VoxelShape SHAPE2 = full();
    protected static final VoxelShape FLAT = flat();
    public PressureSwitchBlock(Properties pProperties) {
        super(pProperties);

    }
@Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
    if (pPlayer.getAbilities().instabuild){
        this.press(pState, pLevel, pPos);
        float f = pState.getValue(POWERED) ? 0.6F : 0.5F;
        pLevel.playSound((Player)null, pPos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1F, f);
        pLevel.gameEvent(pPlayer, GameEvent.BLOCK_ACTIVATE, pPos);
    }
    if (pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().is(ModTags.Items.SHOVEL_ITEMS) && pState.getValue(POWERED) == true){
        this.pull(pState, pLevel, pPos);
        pLevel.playSound((Player)null, pPos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1F, 1);
    }
        return InteractionResult.SUCCESS;
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!pLevel.isClientSide && pState.getValue(POWERED).equals(false))  {
         this.slam(pState,pLevel,pPos);
            pLevel.playSound((Player)null, pPos, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS, 1F, 1);
        }
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
    public static VoxelShape full(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.875, 0.125, 0.875), BooleanOp.OR);
        return shape;
    }
    public static VoxelShape flat(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.875, 0.03125, 0.875), BooleanOp.OR);
        return shape;
    }


}
