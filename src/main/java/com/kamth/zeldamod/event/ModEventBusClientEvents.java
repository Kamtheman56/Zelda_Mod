package com.kamth.zeldamod.event;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.entity.ZeldaBlockEntities;
import com.kamth.zeldamod.client.renderer.block_entity.*;
import com.kamth.zeldamod.client.renderer.entity.*;
import com.kamth.zeldamod.client.renderer.entity.thrown.BombEntityRenderer;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.client.*;
import com.kamth.zeldamod.entity.client.model.*;
import com.kamth.zeldamod.entity.client.render.*;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.item.armors.render.*;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerRenderLayers(EntityRenderersEvent.RegisterRenderers event) {

        // Seeds
        event.registerEntityRenderer(ModEntityTypes.SEED_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.KOROK_SEED.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.BOMB_SEED.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.DEKU_NUT.get(), ThrownItemRenderer::new);

        // Bombs
        event.registerEntityRenderer(ModEntityTypes.BOMB.get(), BombEntityRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.BOMB_FLOWER.get(), BombEntityRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.WATER_BOMB.get(), BombEntityRenderer::new);

        // Arrows
        event.registerEntityRenderer(ModEntityTypes.FIRE_ARROW.get(), FireArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.ICE_ARROW.get(), IceArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.LIGHT_ARROW.get(), LightArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.BOMB_ARROW.get(), BombArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.ANCIENT_ARROW.get(), AncientArrowRender::new);
        event.registerEntityRenderer(ModEntityTypes.SHOCK_ARROW.get(), LightningArrowRender::new);

        event.registerEntityRenderer(ModEntityTypes.SWORD_BEAM.get(), SwordBeamRender::new);
        event.registerEntityRenderer(ModEntityTypes.GUST_PROJECTILE.get(), GustRender::new);
        event.registerEntityRenderer(ModEntityTypes.FIRE_PROJECTILE.get(), FireRender::new);
        event.registerEntityRenderer(ModEntityTypes.ICE_PROJECTILE.get(), IceRender::new);
        event.registerEntityRenderer(ModEntityTypes.BOOMERANG.get(), BoomerangRender::new);
        event.registerEntityRenderer(ModEntityTypes.MAGIC_BOOMERANG.get(), MagicBoomerangRender::new);
        event.registerEntityRenderer(ModEntityTypes.SAND_PROJECTILE.get(), SandRender::new);
        event.registerEntityRenderer(ModEntityTypes.HOOKSHOT.get(), HookshotRender::new);
        event.registerEntityRenderer(ModEntityTypes.CLAWSHOT.get(), ClawshotRender::new);
        event.registerEntityRenderer(ModEntityTypes.SWORD_BEAM2.get(), SwordBeam2Render::new);
        event.registerEntityRenderer(ModEntityTypes.GALE_BOOMERANG.get(), GaleBoomerangRender::new);
        event.registerEntityRenderer(ModEntityTypes.BOMBCHU.get(), BombchuRenderer::new);

        event.registerBlockEntityRenderer(ZeldaBlockEntities.SWORD_PEDESTAL_ENTITY.get(), SwordPedestalEntityRenderer::new);
        event.registerBlockEntityRenderer(ZeldaBlockEntities.LOCKED_CHEST_ENTITY.get(), LockedChestEntityRenderer::new);
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
        event.registerLayerDefinition(ModModelLayers.TEST, DarknutHelmet::createBodyLayer);
        event.registerLayerDefinition(MobModelLayers.IRON_KNUCKLE_LAYER, IronKnuckleModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntityTypes.DEKU.get(), DekuScrubRenderer::new);
        EntityRenderers.register(ModEntityTypes.DEKU_MAD.get(), DekuMadScrubRenderer::new);
        EntityRenderers.register(ModEntityTypes.DARK_NUT.get(), DarknutRenderer::new);
        EntityRenderers.register(ModEntityTypes.DARK_KNIGHT.get(), DarkKnightRenderer::new);
        EntityRenderers.register(ModEntityTypes.KEESE.get(), KeeseRenderer::new);
        EntityRenderers.register(ModEntityTypes.CHUCHU.get(), ChuchuRenderer::new);
        EntityRenderers.register(ModEntityTypes.CHUCHU_FIRE.get(), FireChuchuRenderer::new);
        EntityRenderers.register(ModEntityTypes.CHUCHU_ICE.get(), IceChuchuRenderer::new);
        EntityRenderers.register(ModEntityTypes.CHUCHU_ELECTRIC.get(), ElectricChuchuRenderer::new);
        EntityRenderers.register(ModEntityTypes.KOROK.get(), KorokRenderer::new);
        EntityRenderers.register(ModEntityTypes.SKULLTULA.get(), SkulltulaRenderer::new);
        EntityRenderers.register(ModEntityTypes.IRON_KNUCKLE.get(), IronKnuckleRenderer::new);

        ItemProperties.register(ZeldaItems.MILK_BOTTLE.get(), new ResourceLocation("used"), (stack, world, entity, seed) -> {

            CompoundTag nbt = stack.getOrCreateTag();
            if (!nbt.contains("used")) {
                return 0.0f;
            }

            boolean used = nbt.getBoolean("used");

            return used ? 1.0f : 0.0f;
        });
    }
}
