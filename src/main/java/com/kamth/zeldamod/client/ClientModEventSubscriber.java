package com.kamth.zeldamod.client;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.client.renderer.entity.*;
import com.kamth.zeldamod.entity.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntityTypes.SEED_PROJECTILE.get(), SeedRender::new);
        event.registerEntityRenderer(ModEntityTypes.WATER_BOMB.get(), WaterBombRender::new);
        event.registerEntityRenderer(ModEntityTypes.BOMB.get(), BombRender::new);
        event.registerEntityRenderer(ModEntityTypes.FIRE_ARROW.get(), FireArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.ICE_ARROW.get(), IceArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.LIGHT_ARROW.get(), LightArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.BOMB_ARROW.get(), BombArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.SWORD_BEAM.get(), SwordBeamRender::new);
        event.registerEntityRenderer(ModEntityTypes.LIGHTNING_ARROW.get(), LightningArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.ANCIENT_ARROW.get(), AncientArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.GUST_PROJECTILE.get(), GustRender::new);
        event.registerEntityRenderer(ModEntityTypes.DEKU_NUT.get(), DekuRender::new);
        event.registerEntityRenderer(ModEntityTypes.FIRE_PROJECTILE.get(), FireRender::new);
        event.registerEntityRenderer(ModEntityTypes.ICE_PROJECTILE.get(), IceRender::new);
        event.registerEntityRenderer(ModEntityTypes.BOOMERANG.get(), BoomerangRender::new);
        event.registerEntityRenderer(ModEntityTypes.MAGIC_BOOMERANG.get(), MagicBoomerangRender::new);
        event.registerEntityRenderer(ModEntityTypes.SAND_PROJECTILE.get(), SandRender::new);
        event.registerEntityRenderer(ModEntityTypes.HOOKSHOT.get(), HookshotRender::new);
        event.registerEntityRenderer(ModEntityTypes.CLAWSHOT.get(), ClawshotRender::new);
        event.registerEntityRenderer(ModEntityTypes.BOMB_FLOWER.get(), BombFlowerRender::new);
        event.registerEntityRenderer(ModEntityTypes.SWORD_BEAM2.get(), SwordBeam2Render::new);
        event.registerEntityRenderer(ModEntityTypes.GALE_BOOMERANG.get(), GaleBoomerangRender::new);
        event.registerEntityRenderer(ModEntityTypes.BOMB_SEED.get(), BombSeedRender::new);
        event.registerEntityRenderer(ModEntityTypes.BOMBCHU.get(), BombchuRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.KOROK_SEED.get(), KorokSeedRender::new);

    }
}
