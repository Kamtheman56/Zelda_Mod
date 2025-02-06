package com.kamth.zeldamod.item.items.consumables.hearts;

import com.kamth.zeldamod.event.ModEvents;
import com.kamth.zeldamod.event.events.ZeldaPlayerHealth;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class GoldHeartContainerItem extends AbstractHeartItem {

    public GoldHeartContainerItem(Properties pProperties) {
        super(pProperties, "i-yellow", 1.2f, 2);
    }

    @Override
    protected boolean heartConsumable(Player player) {
        return !ZeldaPlayerHealth.canIncreaseGoldHealth(player);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 42;
    }
}
