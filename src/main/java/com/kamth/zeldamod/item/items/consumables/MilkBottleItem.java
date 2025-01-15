package com.kamth.zeldamod.item.items.consumables;

import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.items.consumables.drink.DrinkItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class MilkBottleItem extends DrinkItem {

    public MilkBottleItem(Properties pProperties) {
        super(pProperties, Style.EMPTY);
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide) {
            pEntityLiving.removeEffect(MobEffects.POISON);
            pEntityLiving.removeEffect(MobEffects.HUNGER);
            pEntityLiving.removeEffect(MobEffects.WEAKNESS);
        }
        return super.finishUsingItem(pStack, pLevel, pEntityLiving);
    }

    @Override
    protected boolean fullyConsume(ItemStack pStack, Level pLevel, Player player) {
        if (!player.isCreative()) {
            CompoundTag nbt = pStack.getOrCreateTag();
            boolean used = nbt.getBoolean("used");

            nbt.putBoolean("used", !used);
            pStack.setTag(nbt);
            return used;
        }

        return false;
    }

    @Override
    public Component getName(ItemStack pStack) {
        CompoundTag nbt = pStack.getOrCreateTag();
        return !nbt.getBoolean("used") ? super.getName(pStack) : Component.translatable(pStack.getDescriptionId() + "_half");
    }
}

