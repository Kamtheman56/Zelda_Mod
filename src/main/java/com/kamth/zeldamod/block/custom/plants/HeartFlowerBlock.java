package com.kamth.zeldamod.block.custom.plants;

import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.function.Supplier;

public class HeartFlowerBlock extends FlowerBlock implements BonemealableBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public HeartFlowerBlock(Supplier<MobEffect> effectSupplier, int pEffectDuration, Properties pProperties) {
        super(effectSupplier, pEffectDuration, pProperties);
    }

@Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {

//only works if the entity doesn't have max health!
    if (pEntity instanceof LivingEntity && pState.getValue(POWERED)) {
        LivingEntity livingentity = (LivingEntity)pEntity;
            if ((((LivingEntity) pEntity).getHealth() != ((LivingEntity) pEntity).getMaxHealth()) && !pEntity.isCrouching()) {
                livingentity.heal(2);
              //  pLevel.removeBlock(pPos,false);
                this.press(pState, pLevel, pPos);
                pLevel.playSound(null,pPos, ModSounds.HEAL.get(), SoundSource.BLOCKS);
                pLevel.addParticle(ParticleTypes.HEART, true, pPos.getX() +0, pPos.getY() +.6, pPos.getZ() +0, 0, 0, 0);
            }}}
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(POWERED,true);
    }
    public void press(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.setValue(POWERED,false);
        pLevel.setBlock(pPos, pState, 3);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return !pState.getValue(POWERED);
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos, pState.setValue(POWERED, Boolean.valueOf(true)), 2);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED);

    }
}






