package com.kamth.zeldamod.event;


import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.mobs.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
public static void registerAttributes(EntityAttributeCreationEvent event){
    event.put(ModEntityTypes.DEKU.get(), DekuScrubEntity.createAttributes().build());
        event.put(ModEntityTypes.DEKU_MAD.get(), DekuMadScrubEntity.createAttributes().build());
    event.put(ModEntityTypes.DARK_NUT.get(), DarknutEntity.createAttributes().build());
        event.put(ModEntityTypes.DARK_KNIGHT.get(), DarkKnightEntity.createAttributes().build());
        event.put(ModEntityTypes.KEESE.get(), KeeseEntity.createAttributes().build());
}
}
