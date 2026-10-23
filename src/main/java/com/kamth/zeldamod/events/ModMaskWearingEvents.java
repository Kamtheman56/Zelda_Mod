package com.kamth.zeldamod.events;


import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = ZeldaMod.MOD_ID)
public class ModMaskWearingEvents {

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.GIANTS_MASK.get()){
            event.getEntity().forceAddEffect(new MobEffectInstance(ModEffects.GIGANTIC,20, 1, false, false, true), null);
        }

    }
}
