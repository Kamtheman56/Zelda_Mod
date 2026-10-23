package com.kamth.zeldamod.items.MovementItems;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TestItem extends Item {
    public TestItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getActiveItem();

            player.getCooldowns().addCooldown(itemStack,25);
            player.jumpFromGround();



        return InteractionResult.CONSUME;
    }
}
