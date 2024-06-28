package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.event.ModEvents;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class GloomContainer extends Item {

    public GloomContainer(Properties pProperties) {
        super(pProperties);
    }


//after consuming this decreases your maximum life by 2

    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {

        ItemStack stack = player.getItemInHand(pHand);
        if (!ModEvents.PlayerHealthEvents.canDecreaseBaseHealth(player)) {
            return InteractionResultHolder.fail(stack);
        }
     else {
        return ItemUtils.startUsingInstantly(pLevel, player, pHand);
    }}
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);
        pLevel.playSound(pEntityLiving,pEntityLiving.getOnPos(), ModSounds.HEAL.get(), SoundSource.PLAYERS, .5f, -3.5f);
        if (pEntityLiving instanceof ServerPlayer serverplayer) {
            Player player = (Player)pEntityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
            ModEvents.PlayerHealthEvents.addBaseHealthModifier((Player) pEntityLiving, -2F);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild){
                player.getUseItem().shrink(1);
            }
        }
        return pStack;
    }
    public int getUseDuration(ItemStack pStack) {
        return 32;
    }
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.EAT;
    }
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_EAT;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_EAT;
    }
        @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.translatable("item.gloom_heart.description").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
    }
}
