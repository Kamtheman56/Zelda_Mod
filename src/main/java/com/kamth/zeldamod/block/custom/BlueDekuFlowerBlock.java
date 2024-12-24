package com.kamth.zeldamod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;

public class BlueDekuFlowerBlock extends DekuFlowerBlock implements IPlantable {



    public BlueDekuFlowerBlock(Properties pProperties) {
        super(pProperties);
    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide) {
            if (pEntity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)pEntity;
                {
                    livingentity.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 3, true, false));
                }}}}
    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {


        if (pEntity instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)pEntity;
            livingentity.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 3, true, false));
            }}
}






