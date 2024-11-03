package com.kamth.zeldamod.event;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.entity.ModBlockEntities;
import com.kamth.zeldamod.block.entity.renderer.*;
import com.kamth.zeldamod.entity.client.*;
import com.kamth.zeldamod.item.armors.render.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.SWORD_PEDESTAL_BE.get(), SwordPedestalEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MASTER_SWORD_PEDESTAL_BE.get(), MasterSwordPedestalEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.UNLOCKED_SWORD_PEDESTAL_BE.get(), UnlockedSwordPedestalEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.LOCKED_CHEST_BE.get(), LockedChestEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ANCIENT_SWORD_PEDESTAL_BE.get(), AncientSwordPedestalEntityRenderer::new);

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
        event.registerLayerDefinition(ModModelLayers.CAP, CapModel2::createBodyLayer);
    }

}
