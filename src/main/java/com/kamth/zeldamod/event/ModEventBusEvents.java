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
        event.put(ModEntityTypes.CHUCHU.get(), ChuchuEntity.createAttributes().build());
        event.put(ModEntityTypes.CHUCHU_ICE.get(), IceChuchuEntity.createAttributes().build());
        event.put(ModEntityTypes.CHUCHU_FIRE.get(), FireChuchuEntity.createAttributes().build());
        event.put(ModEntityTypes.CHUCHU_ELECTRIC.get(), ElectricChuchuEntity.createAttributes().build());
        event.put(ModEntityTypes.KOROK.get(), KorokEntity.createAttributes().build());
        event.put(ModEntityTypes.SKULLTULA.get(), SkulltulaEntity.createAttributes().build());
        event.put(ModEntityTypes.IRON_KNUCKLE.get(), IronKnuckleEntity.createAttributes().build());
    }
}
