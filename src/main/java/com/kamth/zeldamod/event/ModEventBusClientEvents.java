package com.kamth.zeldamod.event;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.entity.ModBlockEntities;
import com.kamth.zeldamod.block.entity.renderer.*;
import com.kamth.zeldamod.client.renderer.entity.*;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.client.*;
import com.kamth.zeldamod.item.armors.render.*;
import com.kamth.zeldamod.networking.ZeldaNetworking;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
//    public static boolean attackKeyWasPressed = false;

//    @SubscribeEvent
//    public static void onClientTick(TickEvent.ClientTickEvent event) {
//        Minecraft client = Minecraft.getInstance();
//
//        if (event.phase == TickEvent.Phase.END) {
//            if (client.options.keyAttack.isDown()) {
//                if (!attackKeyWasPressed) {
//                    ZeldaNetworking.sendSwordSwingPacket();
//                    attackKeyWasPressed = true;
//
//                }
//            }
//        }
//    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.SWORD_PEDESTAL_BE.get(), SwordPedestalEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MASTER_SWORD_PEDESTAL_BE.get(), MasterSwordPedestalEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.UNLOCKED_SWORD_PEDESTAL_BE.get(), UnlockedSwordPedestalEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.LOCKED_CHEST_BE.get(), LockedChestEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ANCIENT_SWORD_PEDESTAL_BE.get(), AncientSwordPedestalEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerRenderLayers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.WHEAT_SEED.get(), SeedRender::new);
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

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ARCHAIC, ArchaicTunicModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.LEATHERS, ChampionLeathersModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BUNNY, BunnyHoodModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PEGASUS, PegasusBootsModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.DEKU_LAYER, DekuModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.DEKU_MAD_LAYER, DekuModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.DARK_NUT_LAYER, DarknutModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.DARK_KNIGHT_LAYER, DarknutModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.KEESE_LAYER, KeeseModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.CHUCHU_LAYER, ChuchuModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.ICE_CHUCHU_LAYER, ChuchuModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.FIRE_CHUCHU_LAYER, ChuchuModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.ELECTRIC_CHUCHU_LAYER, ChuchuModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.CHUCHU_OUTER_LAYER, ChuchuModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.KOROK_LAYER, KorokModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CAP, CapModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.SKULLTULA_LAYER, SkulltulaModel::createBodyLayer);
    }
}
