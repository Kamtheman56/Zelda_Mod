package com.kamth.zeldamod.block.custom.ocarina_blocks;

import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BrownBricks extends Block {
    public BrownBricks(Properties pProperties) {
        super(pProperties);

    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getItemBySlot(EquipmentSlot.HEAD).is(ZeldaItems.TRUTH_MASK.get())){
            pLevel.playSound(pPlayer, pPos, ModSounds.BROWN_BRICKS.get(), SoundSource.BLOCKS,
                    1f, 1F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            pLevel.addParticle(ParticleTypes.NOTE, (double)pPos.getX() + 0.5D, (double)pPos.getY() + 1.2D, (double)pPos.getZ() + 0.5D, (double)1 / 24.0D, 0.0D, 0.0D);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

}
