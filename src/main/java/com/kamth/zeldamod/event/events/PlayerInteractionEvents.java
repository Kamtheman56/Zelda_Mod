package com.kamth.zeldamod.event.events;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PlayerInteractionEvents {



    public static boolean usingMask(PlayerInteractEvent.EntityInteract event, Item mask) {
        return !event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == mask;
    }

    public static boolean usingMaskNotCreative(PlayerInteractEvent.EntityInteract event, Item mask) {
        return !event.getEntity().isCreative() && usingMask(event, mask);
    }
}
