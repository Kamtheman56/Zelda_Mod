package com.kamth.zeldamod.event;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.client.HawkMaskOverlay;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public  class ClientModBusEvents {


 //   @SubscribeEvent
  //  public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
   //     event.registerAboveAll("hawk", HawkMaskOverlay.HAWK_MASK);
   // }
}
