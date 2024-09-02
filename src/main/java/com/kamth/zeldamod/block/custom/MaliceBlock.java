package com.kamth.zeldamod.block.custom;

import com.kamth.zeldamod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class MaliceBlock extends Block {
    public MaliceBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide) {
            if (pEntity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)pEntity;
                if (!(livingentity instanceof Monster)  && !livingentity.hasEffect(ModEffects.GLOOM.get()) && new Random().nextFloat() > .9f) {
                    livingentity.addEffect(new MobEffectInstance(ModEffects.GLOOM.get(), 45));
                }
            }
        }
    }
    public void animateTick(BlockState p_222503_, Level p_222504_, BlockPos p_222505_, RandomSource p_222506_) {
        int i = p_222505_.getX();
        int j = p_222505_.getY();
        int k = p_222505_.getZ();
        double d0 = (double)i + p_222506_.nextDouble();
        double d1 = (double)j + p_222506_.nextDouble();
        double d2 = (double)k + p_222506_.nextDouble();
        p_222504_.addParticle(ParticleTypes.CRIMSON_SPORE, d0, d1, d2, 0.0D, 0.0D, 0.0D);

        }

    }



