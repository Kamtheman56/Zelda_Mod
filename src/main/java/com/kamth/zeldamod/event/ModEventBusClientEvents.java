package com.kamth.zeldamod.event;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.entity.ModBlockEntities;
import com.kamth.zeldamod.block.entity.renderer.SwordPedestalEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.SWORD_PEDESTAL_BE.get(), SwordPedestalEntityRenderer::new);
    }
}
