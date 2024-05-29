package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.event.ModEvents;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class GloomContainer extends Item {

    public GloomContainer(Properties pProperties) {
        super(pProperties);
    }




    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {

        ItemStack stack = player.getItemInHand(pHand);
        if (!ModEvents.PlayerHealthEvents.canDecreaseBaseHealth(player)) {
            return InteractionResultHolder.fail(stack);
        } else {
            ModEvents.PlayerHealthEvents.addBaseHealthModifier(player, -2F);
            pLevel.playSound(player,player.getOnPos(), ModSounds.HEAL.get(), SoundSource.PLAYERS, .5f, -3.5f);
            if (!player.getAbilities().instabuild) stack.shrink(1);
            return InteractionResultHolder.consume(stack);
        }




    }

        @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Decrease your maximum vitality by one").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        }
    }
}
