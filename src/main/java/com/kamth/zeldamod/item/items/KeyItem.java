package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class KeyItem extends Item {

    public KeyItem(Properties pProperties) {
        super(pProperties);
    }




    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        ItemStack itemstack = pContext.getItemInHand();
        BlockState blockstate = level.getBlockState(blockpos);

        if (blockstate.is(ModBlocks.LOCKED_DOOR.get())) {
            level.destroyBlock(blockpos,false, pContext.getPlayer());
            level.playSound(pContext.getPlayer(),pContext.getClickedPos(), ModSounds.DOOR_UNLOCK.get(), SoundSource.BLOCKS);
            itemstack.shrink(1);
            return InteractionResult.SUCCESS;
        }

        {
    return InteractionResult.FAIL;}}

        @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.key_small.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }
}
