package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
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

import javax.annotation.Nullable;
import java.util.List;

public class DrinkItem extends Item {
    public DrinkItem(Properties pProperties) {
        super(pProperties);
    }




    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);
        if (pEntityLiving instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }



        if (pStack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (pEntityLiving instanceof Player && !((Player)pEntityLiving).getAbilities().instabuild) {
                ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
                Player player = (Player)pEntityLiving;
                if (!player.getInventory().add(itemstack)) {
                    player.drop(itemstack, false);
                }
            }

            return pStack;
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (stack.is(ModItems.HEART_POTION.get()) && Screen.hasShiftDown()) {
            components.add(Component.literal("Heals health").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.STAMINA.get()) && Screen.hasShiftDown()) {
            components.add(Component.literal("Heals hunger").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.PUMPKIN_SOUP.get()) && Screen.hasShiftDown()) {
            components.add(Component.literal("A hearty soup").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.MILK_MAGIC.get()) && Screen.hasShiftDown()) {
            components.add(Component.literal("Regenerates Hunger").withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.SHIELD_POTION.get()) && Screen.hasShiftDown()) {
            components.add(Component.literal("Regenerates Health and Shields").withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC));
        }

    }

    public int getUseDuration(ItemStack pStack) {
        return 40;
    }


    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }


    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pHand);
    }
}

