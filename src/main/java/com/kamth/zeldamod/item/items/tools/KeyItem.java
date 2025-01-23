package com.kamth.zeldamod.item.items.tools;

import com.kamth.zeldamod.item.items.TooltipItem;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class KeyItem extends TooltipItem {

    private final Block lock;

    public KeyItem(Properties pProperties, Block lock) {
        super(pProperties, Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY));
        this.lock = lock;
    }

    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    public InteractionResult useOn(UseOnContext pContext) {

        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        ItemStack itemstack = pContext.getItemInHand();
        BlockState blockstate = level.getBlockState(blockpos);

        if (blockstate.is(this.lock)) {
            level.destroyBlock(blockpos,false, pContext.getPlayer());
            level.playSound(pContext.getPlayer(),pContext.getClickedPos(), ModSounds.DOOR_UNLOCK.get(), SoundSource.BLOCKS);
            itemstack.shrink(1);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }
}
