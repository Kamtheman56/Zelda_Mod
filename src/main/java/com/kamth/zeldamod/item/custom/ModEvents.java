package com.kamth.zeldamod.item.custom;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
            if (event.getEntity() instanceof Sheep) {
                if (event.getSource().getEntity() instanceof Player player) {
                    if (player.getMainHandItem().getItem() == Items.BEEF) {
                        player.sendSystemMessage(Component.literal(player.getName().getString() + " hurt a Sheep with BEEF! But why?"));
                    } else {
                        player.sendSystemMessage(Component.literal(player.getName().getString() + " hurt a Sheep!"));
                    }
                }
            }
        }
    }



    @SubscribeEvent
    public static void onLivingTickEvent (Player player) {
        if (player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HEAVY_BOOTS.get())
        {
             player.setSwimming(false);

        }

    }

}

