package com.kamth.zeldamod;


import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.block.entity.ModBlockEntities;
import com.kamth.zeldamod.custom.ModItemProperties;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.client.*;
import com.kamth.zeldamod.item.ModCreativeModeTab;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.loot.ModLootModifiers;
import com.kamth.zeldamod.painting.ModPaintings;
import com.kamth.zeldamod.particle.ModParticles;
import com.kamth.zeldamod.sound.ModSounds;
import com.kamth.zeldamod.villager.ModVillagers;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ZeldaMod.MOD_ID)
public class ZeldaMod
{
    public static final String MOD_ID = "zeldamod";

    private static final Logger LOGGER = LogUtils.getLogger();


    public ZeldaMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTab.register(modEventBus);
        ModItems.register(modEventBus);
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
        modEventBus.addListener(this::addCreative);
        ModBlockEntities.register(modEventBus);

    }
private void clientSetup(final FMLClientSetupEvent event){
       ModItemProperties.addCustomItemProperties();

}


    private void commonSetup(final FMLCommonSetupEvent event){

event.enqueueWork(() -> {
    (( FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.NIGHTSHADE.getId(), ModBlocks.POTTED_NIGHTSHADE);
    (( FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.SUNDELION.getId(), ModBlocks.POTTED_SUNDELION);
    (( FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.HEART_FLOWER.getId(), ModBlocks.POTTED_HEART_FLOWER);
    (( FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PRIMO_FLOWER.getId(), ModBlocks.POTTED_PRIMO_FLOWER);
});
    }

private void addCreative(BuildCreativeModeTabContentsEvent event){
    if (event.getTab() == ModCreativeModeTab.ZELDA_BLOCKS.get()){
        event.accept(ModItems.KOKIRI_HAT);
        event.accept(ModItems.GORON_HAT);
        event.accept(ModItems.ZORA_HAT);
        event.accept(ModItems.DARK_HAT);
        event.accept(ModItems.KOKIRI_TUNIC);
        event.accept(ModItems.GORON_TUNIC);
        event.accept(ModItems.ZORA_TUNIC);
        event.accept(ModItems.DARK_TUNIC);
        event.accept(ModItems.HYLIAN_HOOD);
        event.accept(ModItems.CHAMPION_TUNIC);
        event.accept(ModItems.CHAMPIONS_TUNIC);
        event.accept(ModItems.ARCHAIC_TUNIC);
        event.accept(ModItems.CLASSIC_HAT);
        event.accept(ModItems.CLASSIC_TUNIC);
        event.accept(ModItems.KOKIRI_PANTS);
        event.accept(ModItems.DARK_PANTS);
        event.accept(ModItems.KOKIRI_BOOTS);
        event.accept(ModItems.HEAVY_BOOTS);
        event.accept(ModItems.HOVER_BOOTS);
        event.accept(ModItems.FLIPPERS);
        event.accept(ModItems.PEGASUS_BOOTS);
        event.accept(ModItems.DARK_BOOTS);
        event.accept(ModItems.KOKIRI_SWORD);
        event.accept(ModItems.KOKIRI_SWORD2);
        event.accept(ModItems.RAZOR_SWORD);
        event.accept(ModItems.GILDED_SWORD);
        event.accept(ModItems.BIGGORON_KNIFE);
        event.accept(ModItems.BIGGORON_SWORD);
        event.accept(ModItems.MASTER_SWORD);
        event.accept(ModItems.MASTER_SWORD2);
        event.accept(ModItems.MASTER_SWORD3);
        event.accept(ModItems.MASTER_SWORD_TRUE);
        event.accept(ModItems.FIERCE_SWORD);
        event.accept(ModItems.HERO_SWORD);
        event.accept(ModItems.WHITE_SWORD);
        event.accept(ModItems.MAGIC_SWORD);
        event.accept(ModItems.GLOOM_SWORD);
        event.accept(ModItems.GLOOM_CLUB);
        event.accept(ModItems.DEKU_SHIELD);
        event.accept(ModItems.HYLIAN_SHIELD);
        event.accept(ModItems.MIRROR_SHIELD);
        event.accept(ModItems.BALANCED_MIRROR_SHIELD);
        event.accept(ModItems.SLINGSHOT);
        event.accept(ModItems.SCATTERSHOT);
        event.accept(ModItems.HERO_BOW);
        event.accept(ModItems.FIRE_ARROW);
        event.accept(ModItems.ICE_ARROW);
        event.accept(ModItems.LIGHT_ARROW);
        event.accept(ModItems.BOMB_ARROW);
        event.accept(ModItems.LIGHTNING_ARROW);
        event.accept(ModItems.ANCIENT_ARROW);
        event.accept(ModItems.FIRE_ROD);
        event.accept(ModItems.ICE_ROD);
        event.accept(ModItems.TORNADO_ROD);
        event.accept(ModItems.SAND_ROD);
        event.accept(ModItems.ROC_FEATHER);
        event.accept(ModItems.ROC_FEATHER_2);
        event.accept(ModItems.ROC_CAPE);
        event.accept(ModItems.BOOMERANG);
        event.accept(ModItems.MAGIC_BOOMERANG);
        event.accept(ModItems.GALE_BOOMERANG);
        event.accept(ModItems.HAMMER);
        event.accept(ModItems.MEGATON);
        event.accept(ModItems.DEKU_LEAF);
        event.accept(ModItems.GLIDER);
        event.accept(ModItems.MITTS);
        event.accept(ModItems.MOGMA_MITTS);
        event.accept(ModItems.BOMB_FLOWER);
        event.accept(ModItems.BOMB);
        event.accept(ModItems.WATER_BOMB);
        event.accept(ModItems.DEKU_NUT);
        event.accept(ModItems.DEKU_STICK);
        event.accept(ModItems.BLUE_RING);
        event.accept(ModItems.RED_RING);
        event.accept(ModItems.PURPLE_RING);
        event.accept(ModItems.HOOKSHOT);
        event.accept(ModItems.LONGSHOT);
        event.accept(ModItems.CLAWSHOT);
        event.accept(ModItems.CLAWSHOT_GODDESS);
        event.accept(ModItems.LENS_OF_TRUTH);
        event.accept(ModItems.FLUTE);
        event.accept(ModItems.OCARINA);
        event.accept(ModItems.SILVER_SCALE);
        event.accept(ModItems.GOLDEN_SCALE);
        event.accept(ModItems.DRAGON_SCALE);
        event.accept(ModItems.FIRE_SHIELD);
    }
    if (event.getTab() == ModCreativeModeTab.ZELDA_MATERIAL.get()){
        event.accept(ModItems.RED_EMERALD);
        event.accept(ModItems.BLUE_EMERALD);
        event.accept(ModItems.MASTER_SHARD);
        event.accept(ModItems.MASTER_ORE);
        event.accept(ModItems.MASTER_ORE2);
        event.accept(ModItems.GOLD_DUST);
        event.accept(ModItems.MASTER_UPGRADE);
        event.accept(ModItems.HEART_PIECE);
        event.accept(ModItems.HEART_CONTAINER);
        event.accept(ModItems.GOLD_CONTAINER);
        event.accept(ModItems.GLOOM_CONTAINER);
        event.accept(ModItems.SMALL_KEY);
        event.accept(ModItems.BIG_KEY);
        event.accept(ModItems.XTENDER);
        event.accept(ModItems.CLAW);
        event.accept(ModItems.DEITY_SHARD);
        event.accept(ModItems.GLOOM_CLUMP);
        event.accept(ModItems.BOMB_SEEDS);
        event.accept(ModItems.FARORE_PEARL);
        event.accept(ModItems.DIN_PEARL);
        event.accept(ModItems.NAYRU_PEARL);
        event.accept(ModItems.PUMPKIN_SOUP);
        event.accept(ModItems.STAMINA);
        event.accept(ModItems.HEART_POTION);
        event.accept(ModItems.BLUE_POTION);
        event.accept(ModItems.GLOOM_RESIST_POTION);
        event.accept(ModItems.MILK_BOTTLE1);
        event.accept(ModItems.MILK_MAGIC);
        event.accept(ModItems.SHIELD_POTION);
        event.accept(ModItems.SUPER_MUSHROOM);
        event.accept(ModItems.LIFE_MUSHROOM);
        event.accept(ModItems.MINI_MUSHROOM);
        event.accept(ModItems.SUPER_LEAF);
        event.accept(ModItems.BAKED_APPLE);
        event.accept(ModItems.DEKU_SPAWN_EGG);
        event.accept(ModItems.DEKU_MAD_SPAWN_EGG);
        event.accept(ModItems.DARK_NUT_SPAWN_EGG);
        event.accept(ModItems.DARK_KNIGHT_SPAWN_EGG);
        event.accept(ModItems.KEESE_SPAWN_EGG);
    }
    if (event.getTab() == ModCreativeModeTab.ZELDA_MASK.get()){
        event.accept(ModItems.DEKU_MASK);
        event.accept(ModItems.GORON_MASK);
        event.accept(ModItems.ZORA_MASK);
        event.accept(ModItems.FIERCE_MASK);
        event.accept(ModItems.BLAST_MASK);
        event.accept(ModItems.STONE_MASK);
        event.accept(ModItems.NIGHT_MASK);
        event.accept(ModItems.KEATON_MASK);
        event.accept(ModItems.BREMEN_MASK);
        event.accept(ModItems.BUNNY_MASK);
        event.accept(ModItems.GERO_MASK);
        event.accept(ModItems.SCENT_MASK);
        event.accept(ModItems.TROUPE_MASK);
        event.accept(ModItems.TRUTH_MASK);
        event.accept(ModItems.ROMANI_MASK);
        event.accept(ModItems.COUPLES_MASK);
        event.accept(ModItems.GIANT_MASK);
        event.accept(ModItems.GIBDO_MASK);
        event.accept(ModItems.FAIRY_MASK);
        event.accept(ModItems.KAFEI_MASK);
        event.accept(ModItems.CAPTAIN_MASK);
        event.accept(ModItems.KAMARO_MASK);
        event.accept(ModItems.GARO_MASK);
        event.accept(ModItems.POSTMAN_MASK);
        event.accept(ModItems.HAWK_MASK);
        event.accept(ModItems.SKULL_MASK);
        event.accept(ModItems.SPOOKY_MASK);
        event.accept(ModItems.GERUDO_MASK);
        event.accept(ModItems.MAJORA_MASK);
        event.accept(ModItems.MASK_ODOLWA);
        event.accept(ModItems.MASK_GOHT);
        event.accept(ModItems.MASK_GYORG);
        event.accept(ModItems.MASK_TWINMOLD);

    }
        if (event.getTab() == ModCreativeModeTab.ZELDA_TAB.get()){
            event.accept(ModBlocks.SECRET_STONE);
            event.accept(ModBlocks.MASTER_ORE);
            event.accept(ModBlocks.DEEPSLATE_MASTER_ORE);
            event.accept(ModBlocks.MASK_BLOCK);
            event.accept(ModBlocks.COURAGE_BLOCK);
            event.accept(ModBlocks.WISDOM_BLOCK);
            event.accept(ModBlocks.POWER_BLOCK);
            event.accept(ModBlocks.RED_EMERALD_BLOCK);
            event.accept(ModBlocks.BLUE_EMERALD_BLOCK);
            event.accept(ModBlocks.SWORD_PEDESTAL);
            event.accept(ModBlocks.ANCIENT_SWORD_PEDESTAL);
            event.accept(ModBlocks.MASTER_SWORD_PEDESTAL);
            event.accept(ModBlocks.UNLOCKED_SWORD_PEDESTAL);
            event.accept(ModBlocks.COURAGE_FLAME);
            event.accept(ModBlocks.WISDOM_FLAME);
            event.accept(ModBlocks.POWER_FLAME);
            event.accept(ModBlocks.PORK_BLOCK);
            event.accept(ModBlocks.CARMINE_FROGLIGHT);
            event.accept(ModBlocks.GLOOM_BLOCK);
            event.accept(ModBlocks.GLOOM_VEIN);
            event.accept(ModBlocks.MALICE_BLOCK);
            event.accept(ModBlocks.HOOK_TARGET);
            event.accept(ModBlocks.CLAW_TARGET);
            event.accept(ModBlocks.HAMMER_PEG);
            event.accept(ModBlocks.COPPER_PEG);
            event.accept(ModBlocks.TIMED_EYE_SWITCH);
            event.accept(ModBlocks.EYE_SWITCH);
            event.accept(ModBlocks.LOCKED_DOOR);
            event.accept(ModBlocks.LOCKED_BOSS_DOOR);
            event.accept(ModBlocks.SHOCK_SWITCH);
            event.accept(ModBlocks.TIMED_SHOCK_SWITCH);
            event.accept(ModBlocks.PRESSURE_SWITCH);
            event.accept(ModBlocks.TIMED_PRESSURE_SWITCH);
            event.accept(ModBlocks.RUSTED_PRESSURE_SWITCH);
            event.accept(ModBlocks.RUSTED_TIMED_PRESSURE_SWITCH);
            event.accept(ModBlocks.OWL_STATUE);
            event.accept(ModBlocks.JAR_GREEN);
            event.accept(ModBlocks.JAR_BLUE);
            event.accept(ModBlocks.JAR_RED);
            event.accept(ModBlocks.JAR_BROWN);
            event.accept(ModBlocks.TIME_BLOCK);
            event.accept(ModBlocks.TIME_BRICKS);
            event.accept(ModBlocks.CRACKED_TIME_BRICKS);
            event.accept(ModBlocks.MOSSY_TIME_BRICKS);
            event.accept(ModBlocks.TIME_BRICKS_SLAB);
            event.accept(ModBlocks.CRACKED_TIME_BRICKS_SLAB);
            event.accept(ModBlocks.MOSSY_TIME_BRICKS_SLAB);
            event.accept(ModBlocks.TIME_BRICKS_STAIRS);
            event.accept(ModBlocks.CRACKED_TIME_BRICKS_STAIRS);
            event.accept(ModBlocks.MOSSY_TIME_BRICKS_STAIRS);
            event.accept(ModBlocks.ZELDA_STONE);
            event.accept(ModBlocks.SUN_STONE);
            event.accept(ModBlocks.NOTE_STONE);
            event.accept(ModBlocks.DEKU_GRAVE);
            event.accept(ModBlocks.GORON_GRAVE);
            event.accept(ModBlocks.ZORA_GRAVE);
            event.accept(ModBlocks.SKY_STONE_CHISELED);
            event.accept(ModBlocks.SKY_STONE_MURAL);
            event.accept(ModBlocks.NIGHTSHADE);
            event.accept(ModBlocks.SUNDELION);
            event.accept(ModBlocks.DEKU_BLOCK);
            event.accept(ModBlocks.DEKU_BLOCK_BLUE);
            event.accept(ModBlocks.DEKU_BLOCK_GOLD);
            event.accept(ModBlocks.HEART_FLOWER);
            event.accept(ModBlocks.PRIMO_FLOWER);
            event.accept(ModBlocks.STAMINA_FRUIT);
            event.accept(ModBlocks.BombFlower2);
        }



}

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntityTypes.DEKU.get(), DekuScrubRenderer::new);
            EntityRenderers.register(ModEntityTypes.DEKU_MAD.get(), DekuMadScrubRenderer::new);
            EntityRenderers.register(ModEntityTypes.DARK_NUT.get(), DarknutRenderer::new);
            EntityRenderers.register(ModEntityTypes.DARK_KNIGHT.get(), DarkKnightRenderer::new);
            EntityRenderers.register(ModEntityTypes.KEESE.get(), KeeseRenderer::new);
        }

    }
}
