package com.kamth.zeldamod.item.items.consumables.hearts;

import com.kamth.zeldamod.event.ModEvents;
import com.kamth.zeldamod.event.events.ZeldaPlayerHealth;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class GloomContainer extends AbstractHeartItem {

    public GloomContainer(Properties pProperties) {
        super(pProperties, "ired", -3.5f, -2);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 18;
    }

    @Override
    protected boolean heartConsumable(Player player) {
        return !ZeldaPlayerHealth.canDecreaseBaseHealth(player);
    }
}
