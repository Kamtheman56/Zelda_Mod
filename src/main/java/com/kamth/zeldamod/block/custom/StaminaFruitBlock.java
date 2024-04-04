package com.kamth.zeldamod.block.custom;

import com.kamth.zeldamod.item.custom.ModTags;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class StaminaFruitBlock extends FlowerBlock {
    public StaminaFruitBlock(Supplier<MobEffect> effectSupplier, int pEffectDuration, Properties pProperties) {
        super(effectSupplier, pEffectDuration, pProperties);

    }
    protected static final VoxelShape SHAPE2 = Block.box(5.1D, 0.0D, 5.1D, 11.0D, 5.1D, 11.0D);
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        return SHAPE2.move(vec3.x, vec3.y, vec3.z);
    }


    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }

@Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {


    if (pEntity instanceof LivingEntity) {
        LivingEntity livingentity = (LivingEntity)pEntity;
            if ((!pEntity.isCrouching())) {
           livingentity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 5, 0));
                pLevel.removeBlock(pPos,false);
                pLevel.playSound(null,pPos, ModSounds.HEAL.get(), SoundSource.BLOCKS, 1,2.5f);
            }}}
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


}






