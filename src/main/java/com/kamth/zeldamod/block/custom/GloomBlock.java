package com.kamth.zeldamod.block.custom;

import com.kamth.zeldamod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

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
                if (!livingentity.isInvulnerableTo(pLevel.damageSources().wither()) && new Random().nextFloat() > .9f) {
                    livingentity.addEffect(new MobEffectInstance(ModEffects.GLOOM.get(), 45));
                }
            }
        }
    }

    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        VoxelShape voxelshape = this.getShape(pState, pLevel, pPos, CollisionContext.empty());
        Vec3 vec3 = voxelshape.bounds().getCenter();
        double d0 = (double)pPos.getX() + vec3.x;
        double d1 = (double)pPos.getZ() + vec3.z;

        for(int i = 0; i < 3; ++i) {
            if (pRandom.nextBoolean()) {
                pLevel.addParticle(ParticleTypes.CRIMSON_SPORE, d0 + pRandom.nextDouble() / 5.0D, (double)pPos.getY() + (0.5D - pRandom.nextDouble()), d1 + pRandom.nextDouble() / 5.0D, 0.0D, 0.0D, 0.0D);
            }
        }

    }

}
