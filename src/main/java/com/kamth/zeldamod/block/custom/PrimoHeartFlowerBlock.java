package com.kamth.zeldamod.block.custom;

import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class PrimoHeartFlowerBlock extends FlowerBlock {
    public PrimoHeartFlowerBlock(Supplier<MobEffect> effectSupplier, int pEffectDuration, Properties pProperties) {
        super(effectSupplier, pEffectDuration, pProperties);
    }

@Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {


    if (pEntity instanceof LivingEntity) {
        LivingEntity livingentity = (LivingEntity)pEntity;
            if ((( !pEntity.isCrouching()))) {
                livingentity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 80, 0));
                pLevel.removeBlock(pPos,false);
                pLevel.playSound(null,pPos, ModSounds.HEAL.get(), SoundSource.BLOCKS, 1,-.5f);
                pLevel.addParticle(ParticleTypes.HEART, true, pPos.getX() +0, pPos.getY() +.2, pPos.getZ() +0, 0, 0, 0);
            }}}


}






