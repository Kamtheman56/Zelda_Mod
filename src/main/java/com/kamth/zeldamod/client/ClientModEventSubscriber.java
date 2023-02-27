package com.kamth.zeldamod.client;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.client.renderer.entity.*;
import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntityTypes.WHEAT_SEED.get(), SeedRender::new);
        event.registerEntityRenderer(ModEntityTypes.WATER_BOMB.get(), WaterBombRender::new);
        event.registerEntityRenderer(ModEntityTypes.BOMB.get(), BombRender::new);
        event.registerEntityRenderer(ModEntityTypes.FIRE_ARROW.get(), FireArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.ICE_ARROW.get(), IceArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.LIGHT_ARROW.get(), LightArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.BOMB_ARROW.get(), BombArrowRender::new);
    }
}
