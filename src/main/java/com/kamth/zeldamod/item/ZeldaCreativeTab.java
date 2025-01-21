package com.kamth.zeldamod.item;

import com.kamth.zeldamod.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

public class ZeldaCreativeTab {
    public static void addCreativeTabItems(BuildCreativeModeTabContentsEvent event){
        if (event.getTab() == ModCreativeModeTab.ZELDA_BLOCKS.get()){
            event.accept(ZeldaItems.KOKIRI_HAT);
            event.accept(ZeldaItems.GORON_HAT);
            event.accept(ZeldaItems.ZORA_HAT);
            event.accept(ZeldaItems.DARK_HAT);
            event.accept(ZeldaItems.KOKIRI_TUNIC);
            event.accept(ZeldaItems.GORON_TUNIC);
            event.accept(ZeldaItems.ZORA_TUNIC);
            event.accept(ZeldaItems.DARK_TUNIC);
            event.accept(ZeldaItems.HYLIAN_HOOD);
            event.accept(ZeldaItems.CHAMPION_TUNIC);
            event.accept(ZeldaItems.CHAMPIONS_TUNIC);
            event.accept(ZeldaItems.ARCHAIC_TUNIC);
            event.accept(ZeldaItems.CLASSIC_HAT);
            event.accept(ZeldaItems.CLASSIC_TUNIC);
            event.accept(ZeldaItems.KOKIRI_PANTS);
            event.accept(ZeldaItems.HYLIAN_PANTS);
            event.accept(ZeldaItems.DARK_PANTS);
            event.accept(ZeldaItems.KOKIRI_BOOTS);
            event.accept(ZeldaItems.HEAVY_BOOTS);
            event.accept(ZeldaItems.HOVER_BOOTS);
            event.accept(ZeldaItems.FLIPPERS);
            event.accept(ZeldaItems.PEGASUS_BOOTS);
            event.accept(ZeldaItems.DARK_BOOTS);
            event.accept(ZeldaItems.KOKIRI_SWORD_OOT);
            event.accept(ZeldaItems.KOKIRI_SWORD_MM);
            event.accept(ZeldaItems.RAZOR_SWORD);
            event.accept(ZeldaItems.GILDED_SWORD);
            event.accept(ZeldaItems.BIGGORON_KNIFE);
            event.accept(ZeldaItems.BIGGORON_SWORD);
            event.accept(ZeldaItems.MASTER_SWORD);
            event.accept(ZeldaItems.MASTER_SWORD2);
            event.accept(ZeldaItems.MASTER_SWORD3);
            event.accept(ZeldaItems.MASTER_SWORD_TRUE);
            event.accept(ZeldaItems.FIERCE_SWORD);
            event.accept(ZeldaItems.HERO_SWORD);
            event.accept(ZeldaItems.WHITE_SWORD);
            event.accept(ZeldaItems.MAGIC_SWORD);
            event.accept(ZeldaItems.GLOOM_SWORD);
            event.accept(ZeldaItems.GLOOM_CLUB);
            event.accept(ZeldaItems.DEKU_SHIELD);
            event.accept(ZeldaItems.HYLIAN_SHIELD);
            event.accept(ZeldaItems.MIRROR_SHIELD);
            event.accept(ZeldaItems.BALANCED_MIRROR_SHIELD);
            event.accept(ZeldaItems.SLINGSHOT);
            event.accept(ZeldaItems.SCATTERSHOT);
            event.accept(ZeldaItems.HERO_BOW);
            event.accept(ZeldaItems.REINFORCED_BOW);
            event.accept(ZeldaItems.FIRE_ARROW);
            event.accept(ZeldaItems.ICE_ARROW);
            event.accept(ZeldaItems.LIGHT_ARROW);
            event.accept(ZeldaItems.BOMB_ARROW);
            event.accept(ZeldaItems.LIGHTNING_ARROW);
            event.accept(ZeldaItems.ANCIENT_ARROW);
            event.accept(ZeldaItems.FIRE_ROD);
            event.accept(ZeldaItems.ICE_ROD);
            event.accept(ZeldaItems.TORNADO_ROD);
            event.accept(ZeldaItems.SAND_ROD);
            event.accept(ZeldaItems.INFERNO_ROD);
            event.accept(ZeldaItems.BLIZZARD_ROD);
            event.accept(ZeldaItems.HURRICANE_ROD);
            event.accept(ZeldaItems.SANDSTORM_ROD);
            event.accept(ZeldaItems.ROC_FEATHER);
            event.accept(ZeldaItems.ROC_FEATHER_2);
            event.accept(ZeldaItems.ROC_CAPE);
            event.accept(ZeldaItems.BOOMERANG);
            event.accept(ZeldaItems.MAGIC_BOOMERANG);
            event.accept(ZeldaItems.GALE_BOOMERANG);
            event.accept(ZeldaItems.HAMMER);
            event.accept(ZeldaItems.MEGATON);
            event.accept(ZeldaItems.DEKU_LEAF);
            event.accept(ZeldaItems.GLIDER);
            event.accept(ZeldaItems.MITTS);
            event.accept(ZeldaItems.MOGMA_MITTS);
            event.accept(ZeldaItems.BOMB_BAG);
            event.accept(ZeldaItems.BOMB_BAG_MEDIUM);
            event.accept(ZeldaItems.BOMB_BAG_LARGE);
            event.accept(ZeldaItems.QUIVER_SMALL);
            event.accept(ZeldaItems.QUIVER_MEDIUM);
            event.accept(ZeldaItems.QUIVER_BIG);
            event.accept(ZeldaItems.WALLET);
            event.accept(ZeldaItems.WALLET_GIANT);
            event.accept(ZeldaItems.WALLET_TYCOON);
            event.accept(ZeldaItems.BOMB_FLOWER);
            event.accept(ZeldaItems.BOMB);
            event.accept(ZeldaItems.WATER_BOMB);
            event.accept(ZeldaItems.DEKU_NUT);
            event.accept(ZeldaItems.DEKU_STICK);
            event.accept(ZeldaItems.BLUE_RING);
            event.accept(ZeldaItems.RED_RING);
            event.accept(ZeldaItems.PURPLE_RING);
            event.accept(ZeldaItems.HOOKSHOT);
            event.accept(ZeldaItems.LONGSHOT);
            event.accept(ZeldaItems.CLAWSHOT);
            event.accept(ZeldaItems.CLAWSHOT_GODDESS);
            event.accept(ZeldaItems.LENS_OF_TRUTH);
            event.accept(ZeldaItems.FLUTE);
            event.accept(ZeldaItems.OCARINA);
            event.accept(ZeldaItems.SILVER_SCALE);
            event.accept(ZeldaItems.GOLDEN_SCALE);
            event.accept(ZeldaItems.DRAGON_SCALE);
            event.accept(ZeldaItems.FIRE_SHIELD);
        }
        if (event.getTab() == ModCreativeModeTab.ZELDA_MATERIAL.get()){
            event.accept(ZeldaItems.BLUE_EMERALD);
            event.accept(ZeldaItems.RED_EMERALD);
            event.accept(ZeldaItems.PURPLE_EMERALD);
            event.accept(ZeldaItems.SILVER_EMERALD);
            event.accept(ZeldaItems.GOLD_EMERALD);
            event.accept(ZeldaItems.MASTER_SHARD);
            event.accept(ZeldaItems.MASTER_ORE);
            event.accept(ZeldaItems.REFINED_MASTER_ORE);
            event.accept(ZeldaItems.GOLD_DUST);
            event.accept(ZeldaItems.MASTER_UPGRADE);
            event.accept(ZeldaItems.HEART_PIECE);
            event.accept(ZeldaItems.HEART_CONTAINER);
            event.accept(ZeldaItems.GOLD_CONTAINER);
            event.accept(ZeldaItems.GLOOM_CONTAINER);
            event.accept(ZeldaItems.SMALL_KEY);
            event.accept(ZeldaItems.BIG_KEY);
            event.accept(ZeldaItems.XTENDER);
            event.accept(ZeldaItems.CLAW);
            event.accept(ZeldaItems.DEITY_SHARD);
            event.accept(ZeldaItems.GLOOM_CLUMP);
            event.accept(ZeldaItems.KOROK_SEED);
            event.accept(ZeldaItems.COOKED_KOROK_SEED);
            event.accept(ZeldaItems.KOROK_SEED_GIFT);
            event.accept(ZeldaItems.BOMB_SEEDS);
            event.accept(ZeldaItems.CHU_JELLY);
            event.accept(ZeldaItems.FIRE_CHU_JELLY);
            event.accept(ZeldaItems.ICE_CHU_JELLY);
            event.accept(ZeldaItems.ELECTRIC_CHU_JELLY);
            event.accept(ZeldaItems.FARORE_PEARL);
            event.accept(ZeldaItems.DIN_PEARL);
            event.accept(ZeldaItems.NAYRU_PEARL);
            event.accept(ZeldaItems.PUMPKIN_SOUP);
            event.accept(ZeldaItems.STAMINA_POTION);
            event.accept(ZeldaItems.HEART_POTION);
            event.accept(ZeldaItems.BLUE_POTION);
            event.accept(ZeldaItems.GLOOM_RESIST_POTION);
            event.accept(ZeldaItems.MILK_BOTTLE);
            event.accept(ZeldaItems.MILK_MAGIC);
            event.accept(ZeldaItems.SHIELD_POTION);
            event.accept(ZeldaItems.SUPER_MUSHROOM);
            event.accept(ZeldaItems.LIFE_MUSHROOM);
            event.accept(ZeldaItems.MINI_MUSHROOM);
            event.accept(ZeldaItems.SUPER_LEAF);
            event.accept(ZeldaItems.BAKED_APPLE);
        }
        if (event.getTab() == ModCreativeModeTab.ZELDA_MASK.get()){
            event.accept(ZeldaItems.DEKU_MASK);
            event.accept(ZeldaItems.GORON_MASK);
            event.accept(ZeldaItems.ZORA_MASK);
            event.accept(ZeldaItems.FIERCE_MASK);
            event.accept(ZeldaItems.BLAST_MASK);
            event.accept(ZeldaItems.STONE_MASK);
            event.accept(ZeldaItems.NIGHT_MASK);
            event.accept(ZeldaItems.KEATON_MASK);
            event.accept(ZeldaItems.BREMEN_MASK);
            event.accept(ZeldaItems.BUNNY_MASK);
            event.accept(ZeldaItems.GERO_MASK);
            event.accept(ZeldaItems.SCENT_MASK);
            event.accept(ZeldaItems.TROUPE_MASK);
            event.accept(ZeldaItems.TRUTH_MASK);
            event.accept(ZeldaItems.ROMANI_MASK);
            event.accept(ZeldaItems.COUPLES_MASK);
            event.accept(ZeldaItems.GIANT_MASK);
            event.accept(ZeldaItems.GIBDO_MASK);
            event.accept(ZeldaItems.FAIRY_MASK);
            event.accept(ZeldaItems.KAFEI_MASK);
            event.accept(ZeldaItems.CAPTAIN_MASK);
            event.accept(ZeldaItems.KAMARO_MASK);
            event.accept(ZeldaItems.GARO_MASK);
            event.accept(ZeldaItems.POSTMAN_MASK);
            event.accept(ZeldaItems.HAWK_MASK);
            event.accept(ZeldaItems.SKULL_MASK);
            event.accept(ZeldaItems.SPOOKY_MASK);
            event.accept(ZeldaItems.GERUDO_MASK);
            event.accept(ZeldaItems.KOROK_MASK);
            event.accept(ZeldaItems.MAJORA_MASK);
            event.accept(ZeldaItems.MASK_ODOLWA);
            event.accept(ZeldaItems.MASK_GOHT);
            event.accept(ZeldaItems.MASK_GYORG);
            event.accept(ZeldaItems.MASK_TWINMOLD);

        }
        if (event.getTab() == ModCreativeModeTab.ZELDA_TAB.get()){
            event.accept(ModBlocks.SECRET_STONE);
            event.accept(ModBlocks.MASTER_ORE);
            event.accept(ModBlocks.DEEPSLATE_MASTER_ORE);
            event.accept(ModBlocks.MASK_BLOCK);
            event.accept(ModBlocks.COURAGE_BLOCK);
            event.accept(ModBlocks.WISDOM_BLOCK);
            event.accept(ModBlocks.POWER_BLOCK);
            event.accept(ModBlocks.BLUE_EMERALD_BLOCK);
            event.accept(ModBlocks.RED_EMERALD_BLOCK);
            event.accept(ModBlocks.PURPLE_EMERALD_BLOCK);
            event.accept(ModBlocks.SILVER_EMERALD_BLOCK);
            event.accept(ModBlocks.GOLD_EMERALD_BLOCK);
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
            event.accept(ModBlocks.JAR_WHITE);
            event.accept(ModBlocks.JAR_LIGHT_GRAY);
            event.accept(ModBlocks.JAR_GRAY);
            event.accept(ModBlocks.JAR_BLACK);
            event.accept(ModBlocks.JAR_BROWN);
            event.accept(ModBlocks.JAR_RED);
            event.accept(ModBlocks.JAR_ORANGE);
            event.accept(ModBlocks.JAR_YELLOW);
            event.accept(ModBlocks.JAR_LIME);
            event.accept(ModBlocks.JAR_GREEN);
            event.accept(ModBlocks.JAR_TEAL);
            event.accept(ModBlocks.JAR_LIGHT_BLUE);
            event.accept(ModBlocks.JAR_BLUE);
            event.accept(ModBlocks.JAR_PURPLE);
            event.accept(ModBlocks.JAR_MAGENTA);
            event.accept(ModBlocks.JAR_PINK);
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
            event.accept(ModBlocks.TIME_BRICKS_WALL);
            event.accept(ModBlocks.CRACKED_TIME_BRICKS_WALL);
            event.accept(ModBlocks.MOSSY_TIME_BRICKS_WALL);
            event.accept(ModBlocks.ZELDA_STONE);
            event.accept(ModBlocks.SUN_STONE);
            event.accept(ModBlocks.NOTE_STONE);
            event.accept(ModBlocks.DEKU_GRAVE);
            event.accept(ModBlocks.GORON_GRAVE);
            event.accept(ModBlocks.ZORA_GRAVE);
            event.accept(ModBlocks.SKY_STONE_CHISELED);
            event.accept(ModBlocks.SKY_STONE_MURAL);
            event.accept(ModBlocks.CHU_BLOCK_BLUE);
            event.accept(ModBlocks.CHU_BLOCK_FIRE);
            event.accept(ModBlocks.CHU_BLOCK_ICE);
            event.accept(ModBlocks.CHU_BLOCK_ELECTRIC);
            event.accept(ModBlocks.NIGHTSHADE);
            event.accept(ModBlocks.SUNDELION);
            event.accept(ModBlocks.DEKU_BLOCK);
            event.accept(ModBlocks.DEKU_BLOCK_BLUE);
            event.accept(ModBlocks.DEKU_BLOCK_GOLD);
            event.accept(ModBlocks.HEART_FLOWER);
            event.accept(ModBlocks.PRIMO_FLOWER);
            event.accept(ModBlocks.STAMINA_FRUIT);
            event.accept(ModBlocks.BombFlower2);
            event.accept(ModBlocks.CLAY_PUMPKIN);
            event.accept(ModBlocks.CARVED_CLAY_PUMPKIN);
            event.accept(ModBlocks.CLAY_JACK_O_LANTERN);
        }

        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
            event.accept(ZeldaItems.DEKU_SPAWN_EGG);
            event.accept(ZeldaItems.DEKU_MAD_SPAWN_EGG);
            event.accept(ZeldaItems.DARK_NUT_SPAWN_EGG);
            event.accept(ZeldaItems.DARK_KNIGHT_SPAWN_EGG);
            event.accept(ZeldaItems.KEESE_SPAWN_EGG);
            event.accept(ZeldaItems.CHUCHU_SPAWN_EGG);
            event.accept(ZeldaItems.FIRE_CHUCHU_SPAWN_EGG);
            event.accept(ZeldaItems.ICE_CHUCHU_SPAWN_EGG);
            event.accept(ZeldaItems.ELECTRIC_CHUCHU_SPAWN_EGG);
            event.accept(ZeldaItems.KOROK_SPAWN_EGG);
            event.accept(ZeldaItems.SKULLTULA_SPAWN_EGG);
        }
    }
}
