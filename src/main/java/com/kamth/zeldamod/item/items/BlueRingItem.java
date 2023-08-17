package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class BlueRingItem extends Item {
    public BlueRingItem(Properties pProperties) {
        super(pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
    }

    public void onLivingHurtEvent(LivingHurtEvent event){
        if (event.getEntity() instanceof Player) { // Check if the entity taking damage is a Player
            Player player = (Player) event.getEntity();
            ItemStack itemstack = player.getItemInHand(InteractionHand.OFF_HAND);
            if (event.getEntity().getItemBySlot(EquipmentSlot.OFFHAND).getItem() == ModItems.BLUE_RING.get()) {
                itemstack.hurtAndBreak(4, player, (p_43296_) ->
                        p_43296_.broadcastBreakEvent(EquipmentSlot.OFFHAND));
                event.setAmount(event.getAmount() / 1.4f);
            }
        }
    }
  }

