package com.kamth.zeldamod;


import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.block.entity.ModBlockEntities;
import com.kamth.zeldamod.custom.ModItemProperties;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.client.*;
import com.kamth.zeldamod.entity.mobs.KeeseEntity;
import com.kamth.zeldamod.item.ZeldaCreativeTab;
import com.kamth.zeldamod.item.ModCreativeModeTab;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.loot.ModLootModifiers;
import com.kamth.zeldamod.painting.ModPaintings;
import com.kamth.zeldamod.particle.ModParticles;
import com.kamth.zeldamod.sound.ModSounds;
import com.kamth.zeldamod.villager.ModVillagers;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ZeldaMod.MOD_ID)
public class ZeldaMod {
    public static final String MOD_ID = "zeldamod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ZeldaMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTab.register(modEventBus);
        ZeldaItems.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        ModVillagers.register(modEventBus);
        ModEffects.register(modEventBus);
        ModParticles.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModPaintings.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(ZeldaCreativeTab::addCreativeTabItems);
        ModBlockEntities.register(modEventBus);

    }
    private void clientSetup(final FMLClientSetupEvent event){
        ModItemProperties.addCustomItemProperties();
    }

    private void commonSetup(final FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.KOROK.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,   Animal::checkAnimalSpawnRules);
            SpawnPlacements.register(ModEntityTypes.CHUCHU.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.CHUCHU_FIRE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.CHUCHU_ICE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.CHUCHU_ELECTRIC.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.SKULLTULA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.DEKU.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.DEKU_MAD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.KEESE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, KeeseEntity::checkKeeseSpawnRules);



            (( FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.NIGHTSHADE.getId(), ModBlocks.POTTED_NIGHTSHADE);
            (( FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.SUNDELION.getId(), ModBlocks.POTTED_SUNDELION);
            (( FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.HEART_FLOWER.getId(), ModBlocks.POTTED_HEART_FLOWER);
            (( FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PRIMO_FLOWER.getId(), ModBlocks.POTTED_PRIMO_FLOWER);
        });
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
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
}
