package com.kamth.zeldamod.item.custom;


import com.kamth.zeldamod.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.eventbus.api.SubscribeEvent;
public class HeavyBoots extends ArmorItem {
    public HeavyBoots(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }

    @SubscribeEvent
    public static void onPlayerTick(Player player) {
        boolean isWearingHeavyBoots = true;
        if (player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HEAVY_BOOTS.get())
            {

                player.flyingSpeed = 0.04F;


            }

        }

    }

