package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;

public class RomaniMask extends TooltipMaskItem {
    public RomaniMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties, Style.EMPTY.withColor(ChatFormatting.YELLOW).withItalic(true));
    }

    public static void onInteract(Player user, Entity living) {
        if (living instanceof Cow) {
            ItemStack itemstack = user.getItemInHand(InteractionHand.MAIN_HAND);
            if (itemstack.is(Items.GLASS_BOTTLE)) {
                itemstack.shrink(1);
                user.addItem(ZeldaItems.MILK_BOTTLE.get().getDefaultInstance());
                living.playSound(SoundEvents.COW_MILK, 1, 1.8f);
                user.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(4, user, ((p_43296_) ->
                        p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD)));
            } else if (itemstack.is(Items.AIR)) {
                // TODO: REPLACE WITH TRANSLATION KEY
                user.sendSystemMessage(Component.literal(user.getName().getString() + " Knowing you're a friend to all cows, I'll show you how to put my milk into a bottle."));
            }
        }
    }
}
