package com.kamth.zeldamod.event;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.entity.ModBlockEntities;
import com.kamth.zeldamod.block.entity.renderer.LockedChestEntityRenderer2;
import com.kamth.zeldamod.block.entity.renderer.MasterSwordPedestalEntityRenderer;
import com.kamth.zeldamod.block.entity.renderer.SwordPedestalEntityRenderer;
import com.kamth.zeldamod.block.entity.renderer.UnlockedSwordPedestalEntityRenderer;
import com.kamth.zeldamod.entity.client.DarknutModel;
import com.kamth.zeldamod.entity.client.DekuModel;
import com.kamth.zeldamod.entity.client.KeeseModel;
import com.kamth.zeldamod.entity.client.MobModelLayers;
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
        event.registerBlockEntityRenderer(ModBlockEntities.LOCKED_CHEST_BE.get(), LockedChestEntityRenderer2::new);

    }
    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ARCHAIC, ArchaicTunicModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.LEATHERS, ChampionLeathersModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BUNNY, BunnyHoodModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PEGASUS, PegasusBootsModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.DEKU_LAYER, DekuModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.DARK_NUT_LAYER, DarknutModel::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.KEESE_LAYER, KeeseModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CAP, CapModel2::createBodyLayer);
    }

}
