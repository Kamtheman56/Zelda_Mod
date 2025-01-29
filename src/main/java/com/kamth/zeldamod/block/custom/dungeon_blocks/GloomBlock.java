package com.kamth.zeldamod.block.custom.dungeon_blocks;

import com.kamth.zeldamod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GloomBlock extends Block {
    public GloomBlock(Properties pProperties) {
        super(pProperties);
    }
    public static final Map<String, Integer> damageTimer = new HashMap<>();
    public static final Map<String, Integer> healTimer = new HashMap<>();
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


}
