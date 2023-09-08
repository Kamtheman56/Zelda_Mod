package com.kamth.zeldamod;


import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ModCreativeModeTab;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.util.ModItemProperties;
import com.kamth.zeldamod.loot.ModLootModifiers;
import com.kamth.zeldamod.particle.ModParticles;
import com.kamth.zeldamod.villager.ModVillagers;
import com.mojang.logging.LogUtils;
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

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        ModVillagers.register(modEventBus);
        ModEffects.register(modEventBus);
        ModParticles.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }
private void clientSetup(final FMLClientSetupEvent event){
       ModItemProperties.addCustomItemProperties();



}


    private void commonSetup(final FMLCommonSetupEvent event){


    }
private void addCreative(BuildCreativeModeTabContentsEvent event){
    if (event.getTab() == ModCreativeModeTab.ZELDA_BLOCKS.get()){
        event.accept(ModItems.KOKIRI_HAT);
        event.accept(ModItems.GORON_HAT);
        event.accept(ModItems.ZORA_HAT);
        event.accept(ModItems.KOKIRI_TUNIC);
        event.accept(ModItems.GORON_TUNIC);
        event.accept(ModItems.ZORA_TUNIC);
        event.accept(ModItems.CHAMPION_TUNIC);
        event.accept(ModItems.CHAMPIONS_TUNIC);
        event.accept(ModItems.KOKIRI_PANTS);
        event.accept(ModItems.KOKIRI_BOOTS);
        event.accept(ModItems.HEAVY_BOOTS);
        event.accept(ModItems.HOVER_BOOTS);
        event.accept(ModItems.FLIPPERS);
        event.accept(ModItems.PEGASUS_BOOTS);
        event.accept(ModItems.KOKIRI_SWORD);
        event.accept(ModItems.RAZOR_SWORD);
        event.accept(ModItems.GILDED_SWORD);
        event.accept(ModItems.BIGGORON_KNIFE);
        event.accept(ModItems.BIGGORON_SWORD);
        event.accept(ModItems.MASTER_SWORD);
        event.accept(ModItems.MASTER_SWORD2);
        event.accept(ModItems.MASTER_SWORD3);
        event.accept(ModItems.MASTER_SWORD_TRUE);
        event.accept(ModItems.FIERCE_SWORD);
        event.accept(ModItems.DEKU_SHIELD);
        event.accept(ModItems.HYLIAN_SHIELD);
        event.accept(ModItems.MIRROR_SHIELD);
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
        event.accept(ModItems.ROC_CAPE);
        event.accept(ModItems.BOOMERANG);
        event.accept(ModItems.MAGIC_BOOMERANG);
        event.accept(ModItems.HAMMER);
        event.accept(ModItems.MEGATON);
        event.accept(ModItems.DEKU_LEAF);
        event.accept(ModItems.GLIDER);
        event.accept(ModItems.MITTS);
        event.accept(ModItems.MOGMA_MITTS);
        event.accept(ModItems.BOMB);
        event.accept(ModItems.WATER_BOMB);
        event.accept(ModItems.BOMB_FLOWER);
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
    }
    if (event.getTab() == ModCreativeModeTab.ZELDA_MATERIAL.get()){
        event.accept(ModItems.RED_EMERALD);
        event.accept(ModItems.BLUE_EMERALD);
        event.accept(ModItems.MASTER_SHARD);
        event.accept(ModItems.MASTER_ORE);
        event.accept(ModItems.MASTER_ORE2);
        event.accept(ModItems.GOLD_DUST);
        event.accept(ModItems.DEFAULT_UPGRADE);
        event.accept(ModItems.MASTER_UPGRADE);
        event.accept(ModItems.XTENDER);
        event.accept(ModItems.CLAW);
        event.accept(ModItems.BOMB_SEEDS);
        event.accept(ModItems.FARORE_PEARL);
        event.accept(ModItems.DIN_PEARL);
        event.accept(ModItems.NAYRU_PEARL);
        event.accept(ModItems.PUMPKIN_SOUP);
        event.accept(ModItems.STAMINA);
        event.accept(ModItems.HEART_POTION);
        event.accept(ModItems.MILK_BOTTLE1);
        event.accept(ModItems.MILK_MAGIC);
        event.accept(ModItems.SHIELD_POTION);
        event.accept(ModItems.BLUE_POTION);
        event.accept(ModItems.SUPER_MUSHROOM);
        event.accept(ModItems.LIFE_MUSHROOM);
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
        event.accept(ModItems.HAWK_MASK);
        event.accept(ModItems.MAJORA_MASK);
    }
        if (event.getTab() == ModCreativeModeTab.ZELDA_TAB.get()){
            event.accept(ModBlocks.SECRET_STONE);
            event.accept(ModBlocks.MASTER_ORE);
            event.accept(ModBlocks.PORK_BLOCK);
            event.accept(ModBlocks.GLOOM_BLOCK);
            event.accept(ModBlocks.HOOK_TARGET);
            event.accept(ModBlocks.CLAW_TARGET);
        }



}

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

    }
}
