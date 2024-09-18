package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.effect.ModEffects;
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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class GloomResistPotionItem extends Item {

    public GloomResistPotionItem(Properties pProperties) {
        super(pProperties);
    }
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
            return ItemUtils.startUsingInstantly(pLevel, player, pHand);

    }
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);
        pLevel.playSound(pEntityLiving,pEntityLiving.getOnPos(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, .5f, 1);
        if (pEntityLiving instanceof ServerPlayer serverplayer) {
            Player player = (Player)pEntityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
            player.addEffect(new MobEffectInstance(ModEffects.GLOOM_RESIST.get(), 3200, 0, false, true, true));
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild){
                player.getUseItem().shrink(1);}}
        return pStack;
    }
    public int getUseDuration(ItemStack pStack) {
        return 32;
    }
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }


        @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.translatable("item.gloom_resist_potion.description").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
    }
}
