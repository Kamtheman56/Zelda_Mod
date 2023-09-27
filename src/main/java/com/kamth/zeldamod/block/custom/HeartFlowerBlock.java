package com.kamth.zeldamod.block.custom;

import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;

public class HeartFlowerBlock extends FlowerBlock {
    public HeartFlowerBlock(Supplier<MobEffect> effectSupplier, int pEffectDuration, Properties pProperties) {
        super(effectSupplier, pEffectDuration, pProperties);
    }

@Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {


    if (pEntity instanceof LivingEntity) {
        LivingEntity livingentity = (LivingEntity)pEntity;
            if ((((LivingEntity) pEntity).getHealth() < 20) && !pEntity.isCrouching()) {
                livingentity.heal(2);
                pLevel.removeBlock(pPos,false);
                pLevel.playSound(null,pPos, ModSounds.HEAL.get(), SoundSource.BLOCKS);
                pLevel.addParticle(ParticleTypes.HEART, true, pPos.getX() +0, pPos.getY() +.6, pPos.getZ() +0, 0, 0, 0);
            }}}


}






