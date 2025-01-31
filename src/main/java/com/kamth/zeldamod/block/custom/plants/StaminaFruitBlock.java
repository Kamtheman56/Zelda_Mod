package com.kamth.zeldamod.block.custom.plants;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class StaminaFruitBlock extends HeartFlowerBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public StaminaFruitBlock(Supplier<MobEffect> effectSupplier, int pEffectDuration, Properties pProperties) {
        super(effectSupplier, pEffectDuration, pProperties);

    }
    protected static final VoxelShape FULL = full();
    protected static final VoxelShape FLAT = flat();

    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1F;
    }


    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity) pEntity;
            if ((!pEntity.isCrouching() && pState.getValue(POWERED))) {
                livingentity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 5, 0));
                this.press(pState, pLevel, pPos);
                pLevel.playSound(null, pPos, ModSounds.HEAL.get(), SoundSource.BLOCKS, 1, 2.5f);
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (blockstate.is(ModTags.Blocks.STAMINA_FRUIT)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public VoxelShape getVisualShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        if (pState.getValue(POWERED)){
            return FULL.move(vec3.x, vec3.y, vec3.z);
        }
        else
            return FLAT.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        if (pState.getValue(POWERED)) {
            return FULL.move(vec3.x, vec3.y, vec3.z);
        }
        else return FLAT.move(vec3.x, vec3.y, vec3.z);
    }

    public static VoxelShape flat() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0.001875, 0.25, 0.75, 0.064375, 0.75), BooleanOp.OR);
        return shape;
    }

    public static VoxelShape full() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0.001875, 0.25, 0.75, 0.314375, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75125, 0.125, 0.25, 0.75125, 0.5, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.2499375, 0.125, 0.25, 0.2499375, 0.5, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0.125, 0.2499375, 0.75, 0.5, 0.2499375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0.125, 0.75125, 0.75, 0.5, 0.75125), BooleanOp.OR);

        return shape;
    }
}




