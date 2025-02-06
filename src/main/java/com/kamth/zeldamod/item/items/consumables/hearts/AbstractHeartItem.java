package com.kamth.zeldamod.item.items.consumables.hearts;

import com.kamth.zeldamod.event.ModEvents;
import com.kamth.zeldamod.event.events.ZeldaPlayerHealth;
import com.kamth.zeldamod.item.items.TooltipItem;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
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

public abstract class AbstractHeartItem extends TooltipItem {

    private final float pitch;
    private final float healAmount;

    public AbstractHeartItem(Properties pProperties, String style, float pitch, float healAmount) {
        super(pProperties, style, false);
        this.pitch = pitch;
        this.healAmount = healAmount;
    }

    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack stack = player.getItemInHand(pHand);

        if (heartConsumable(player)) {
            if (this.healAmount > 0) {
                player.displayClientMessage(Component.translatable(stack.getDescriptionId() + ".full")
                        .withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD), true);
            }

            return InteractionResultHolder.fail(stack);
        }

        return ItemUtils.startUsingInstantly(pLevel, player, pHand);
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);

        pLevel.playSound(pEntityLiving, pEntityLiving.getOnPos(), ModSounds.HEAL.get(), SoundSource.PLAYERS, 0.5f, this.pitch);

        if (pEntityLiving instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
            ZeldaPlayerHealth.addBaseHealthModifier((Player) pEntityLiving, this.healAmount);

            if (this.healAmount > 0) {
                serverplayer.heal(serverplayer.getMaxHealth());
                serverplayer.removeAllEffects();
            }

            serverplayer.awardStat(Stats.ITEM_USED.get(this));

            if (!serverplayer.getAbilities().instabuild) {
                serverplayer.getUseItem().shrink(1);
            }
        }
        return pStack;
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.EAT;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_EAT;
    }

    public int getUseDuration(ItemStack pStack) {
        return 32;
    }

    protected abstract boolean heartConsumable(Player player);
}
