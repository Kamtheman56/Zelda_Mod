package com.kamth.zeldamod.item.items.consumables.drink;

import com.kamth.zeldamod.item.items.TooltipItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class DrinkItem extends TooltipItem {


    public DrinkItem(Properties pProperties, Style style) {
        super(pProperties, style);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {

        if (pEntityLiving instanceof Player player) {

            if (player instanceof ServerPlayer serverplayer) {
                CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
                serverplayer.awardStat(Stats.ITEM_USED.get(this));
            }

            if (fullyConsume(pStack, pLevel, player)) {
                super.finishUsingItem(pStack, pLevel, pEntityLiving);

                ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);

                if (pStack.isEmpty()) {
                    return bottle;
                }
                else if (pEntityLiving instanceof Player && !player.isCreative()) {
                    if (!player.getInventory().add(bottle)) {
                        player.drop(bottle, false);
                    }
                }
            }

        }

        return pStack;
    }

    protected boolean fullyConsume(ItemStack pStack, Level pLevel, Player player) {
        return true;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 40;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {return SoundEvents.HONEY_DRINK;}

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
    }
}

