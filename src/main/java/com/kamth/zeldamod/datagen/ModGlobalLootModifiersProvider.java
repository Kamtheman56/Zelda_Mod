package com.kamth.zeldamod.datagen;


import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.loot.AddItemModifier;
import com.kamth.zeldamod.loot.AddSusSandItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, ZeldaMod.MOD_ID);
    }

    @Override
    protected void start() {
        //items from entities
        add("bomb_from_creeper", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/creeper")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.BOMB.get()));
        add("roc_feather_from_chicken", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/chicken")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.ROC_FEATHER.get()));
        add("bunny_hood_from_rabbit", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/rabbit")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.BUNNY_MASK.get()));
        add("garo_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/pillager")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.GARO_MASK.get()));
        add("hookshot_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/pillager")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.HOOKSHOT.get()));
        add("claw_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/pillager")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.CLAW.get()));
        add("extender_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/pillager")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.XTENDER.get()));
        add("fire_arrow_from_skeleton", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/skeleton")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.FIRE_ARROW.get()));
        add("ice_arrow_from_skeleton", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/skeleton")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.ICE_ARROW.get()));
        add("lightning_arrow_from_skeleton", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/skeleton")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.FIRE_ARROW.get()));
        add("fire_arrow_from_wither_skeleton", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/wither_skeleton")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.FIRE_ARROW.get()));
        add("ice_arrow_from_stray", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/stray")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.ICE_ARROW.get()));



//items from structures


        //from jungle temples
        add("herobow_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.HERO_BOW.get()));
        add("deku_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.DEKU_MASK.get()));
        add("boomerang_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.BOOMERANG.get()));
        add("hookshot_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.HOOKSHOT.get()));
        add("deku_shield_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ModItems.DEKU_SHIELD.get()));
        add("deku_mask_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.DEKU_MASK.get()));
        add("green_potion_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ModItems.STAMINA.get()));


        add("herobow_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_fletcher")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.HERO_BOW.get()));
        add("roc_feather_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_fletcher")).build(),
                LootItemRandomChanceCondition.randomChance(0.32f).build()}, ModItems.ROC_FEATHER.get()));
        add("megaton_from_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.MEGATON.get()));
        add("biggoron_from_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.BIGGORON_KNIFE.get()));
        add("hookshot_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.HOOKSHOT.get()));
        add("hookshot_from_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.13f).build()}, ModItems.HOOKSHOT.get()));
        add("mirror_shield_from_desert_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.27f).build()}, ModItems.MIRROR_SHIELD.get()));
        add("hylian_shield_from_strongholds", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.HYLIAN_SHIELD.get()));
        add("hylian_shield_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.18f).build()}, ModItems.HYLIAN_SHIELD.get()));
        add("hylian_shield_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.HYLIAN_SHIELD.get()));
        add("deku_shield_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.DEKU_SHIELD.get()));
        add("deku_shield_from_plains", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/plains_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ModItems.DEKU_SHIELD.get()));
        add("sandrod_from_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.SAND_ROD.get()));
        add("icerod_from_igloo", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ModItems.ICE_ROD.get()));
        add("firerod_from_bastion_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.FIRE_ROD.get()));
        add("firerod_from_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.FIRE_ROD.get()));
        add("firerod_from_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.16f).build()}, ModItems.FIRE_ROD.get()));

        //from ancient city
        add("lens_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.LENS_OF_TRUTH.get()));
        add("majora_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.MAJORA_MASK.get()));
        add("ancient_arrow_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ModItems.ANCIENT_ARROW.get()));
        add("icerod_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city_ice_box")).build(),
                LootItemRandomChanceCondition.randomChance(0.30f).build()}, ModItems.ICE_ROD.get()));
        add("deity_shard_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.DEITY_SHARD.get()));
        add("fire_arrow_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.FIRE_ARROW.get()));
        add("ice_arrow_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.ICE_ARROW.get()));
        add("lightning_arrow_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ModItems.LIGHTNING_ARROW.get()));
        add("light_arrow_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.LIGHT_ARROW.get()));
        add("blue_potion_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.BLUE_POTION.get()));
        add("red_potion_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.HEART_POTION.get()));
        add("green_potion_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.STAMINA.get()));



        //from woodland mansion
        add("hookshot_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.HOOKSHOT.get()));
        add("longshot_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.LONGSHOT.get()));
        add("hylian_shield_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.HYLIAN_SHIELD.get()));
        add("mirror_shield_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.MIRROR_SHIELD.get()));
        add("hero_bow_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.HERO_BOW.get()));
        add("shard_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.02f).build()}, ModItems.DEITY_SHARD.get()));

        //end city
        add("mirror_shield_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.MIRROR_SHIELD.get()));
        add("shard_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ModItems.DEITY_SHARD.get()));
        add("blue_potion_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.BLUE_POTION.get()));



       //from dungeons
        add("tornadorod_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.TORNADO_ROD.get()));
        add("firerod_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.FIRE_ROD.get()));
        add("icerod_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.ICE_ROD.get()));
        add("fire_arrow_from_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.FIRE_ARROW.get()));
        add("ice_arrow_from_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.ICE_ARROW.get()));
        add("lightning_arrow_from_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.LIGHTNING_ARROW.get()));
        add("light_arrow_from_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.LIGHT_ARROW.get()));
        add("hammer_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.40f).build()}, ModItems.HAMMER.get()));
        add("bomb_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()}, ModItems.BOMB.get()));
        add("lens_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.LENS_OF_TRUTH.get()));
        add("red_potion_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.HEART_POTION.get()));
        add("green_potion_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.30f).build()}, ModItems.STAMINA.get()));
        add("blue_potion_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.BLUE_POTION.get()));
        add("slingshot_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.42f).build()}, ModItems.SLINGSHOT.get()));
        add("scattershot_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.06f).build()}, ModItems.SCATTERSHOT.get()));
        add("boomerang_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.BOOMERANG.get()));
        add("deku_shield_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.40f).build()}, ModItems.DEKU_SHIELD.get()));
        add("hero_bow_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.HERO_BOW.get()));


        //from mineshafts
        add("bomb_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.BOMB.get()));
        add("bomb_seeds_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.11f).build()}, ModItems.BOMB_SEEDS.get()));
        add("blast_mask_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.BLAST_MASK.get()));
        add("stone_mask_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.STONE_MASK.get()));
        add("red_potion_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.HEART_POTION.get()));
        add("green_potion_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.STAMINA.get()));
        add("blue_potion_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.BLUE_POTION.get()));
        add("kokiri_sword_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.KOKIRI_SWORD.get()));
        add("red_ring_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.RED_RING.get()));
        add("blue_ring_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.BLUE_RING.get()));
        add("lens_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.2f).build()}, ModItems.LENS_OF_TRUTH.get()));
        add("mitts_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.MITTS.get()));
        add("fire_arrow_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.FIRE_ARROW.get()));
        add("ice_arrow_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.ICE_ARROW.get()));
        add("lightning_arrow_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.LIGHTNING_ARROW.get()));
        add("lightning_arrow_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.LIGHTNING_ARROW.get()));
        add("light_arrow_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.LIGHT_ARROW.get()));
        add("shard_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.02f).build()}, ModItems.DEITY_SHARD.get()));
        add("tornado_rod_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.2f).build()}, ModItems.TORNADO_ROD.get()));
        add("kokiri_tunic_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.KOKIRI_TUNIC.get()));
        add("deku_shield_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.DEKU_SHIELD.get()));
        add("hylian_shield_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.HYLIAN_SHIELD.get()));

//from strongholds
        add("all_night_from_strongholds", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.NIGHT_MASK.get()));
        add("blast_from_strongholds", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_library")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.BLAST_MASK.get()));
        add("hylian_from_strongholds", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.32f).build()}, ModItems.HYLIAN_SHIELD.get()));
        add("giant_from_strongholds", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.24f).build()}, ModItems.GIANT_MASK.get()));


        //pearl locations
        add("farore_pearl_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.FARORE_PEARL.get()));
        add("farore_pearl_from_desert_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.FARORE_PEARL.get()));
        add("nayru_pearl_from_elder_guardian", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/elder_guardian")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.NAYRU_PEARL.get()));
        add("nayru_pearl_from_shipwreck", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.NAYRU_PEARL.get()));
        add("din_pearl_from_strongholds", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ModItems.DIN_PEARL.get()));
        add("din_pearl_from_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.DIN_PEARL.get()));


        //armor from structures
        add("flippers_from_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.FLIPPERS.get()));
        add("zora_tunic_from_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.ZORA_TUNIC.get()));
        add("zora_hat_from_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.ZORA_TUNIC.get()));
        add("goron_tunic_from_bastion_other", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.GORON_TUNIC.get()));
        add("goron_tunic_from_bastion_other", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.GORON_HAT.get()));
        add("kokiri_tunic_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.75f).build()}, ModItems.KOKIRI_TUNIC.get()));
        add("kokiri_pants_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ModItems.KOKIRI_PANTS.get()));
        add("kokiri_boots_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ModItems.KOKIRI_BOOTS.get()));
        add("hover_boots_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.09f).build()}, ModItems.HOVER_BOOTS.get()));
        add("pegasus_boots_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.09f).build()}, ModItems.PEGASUS_BOOTS.get()));
        add("heavy_boots_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.09f).build()}, ModItems.HEAVY_BOOTS.get()));
        add("goron_tunic_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.GORON_TUNIC.get()));
        add("zora_tunic_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.16f).build()}, ModItems.ZORA_TUNIC.get()));
        add("champion_tunic_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.CHAMPION_TUNIC.get()));
        add("hover_boots_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.HOVER_BOOTS.get()));
        add("heavy_boots_from_igloo", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.HEAVY_BOOTS.get()));
        add("heavy_boots_from_shipwreck", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ModItems.HEAVY_BOOTS.get()));
        add("goron_tunic_from_ruined_portal", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.23f).build()}, ModItems.GORON_TUNIC.get()));
        add("goron_tunic_from_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.GORON_TUNIC.get()));

        //items from hero of village
        add("fire_arrow_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/fletcher_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.18f).build()}, ModItems.FIRE_ARROW.get()));
        add("ice_arrow_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/fletcher_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.18f).build()}, ModItems.ICE_ARROW.get()));
        add("lightning_arrow_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/fletcher_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.18f).build()}, ModItems.LIGHTNING_ARROW.get()));

        add("stamina_potion_from_cleric", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/cleric_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.STAMINA.get()));
        add("heart_potion_from_cleric", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/cleric_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.HEART_POTION.get()));

        add("deku_shield_from_weaponsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/weaponsmith_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.DEKU_SHIELD.get()));
        add("kokiri_sword_from_weaponsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/weaponsmith_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.KOKIRI_SWORD.get()));
        add("upgrade_from_weaponsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/weaponsmith_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.MASTER_UPGRADE.get()));


        //items from fishing
        add("water_bomb_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.WATER_BOMB.get()));
        add("claw_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.13f).build()}, ModItems.CLAW.get()));
        add("xtender_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.13f).build()}, ModItems.XTENDER.get()));
        add("bomb_seeds_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.13f).build()}, ModItems.BOMB_SEEDS.get()));
        add("upgrade_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ModItems.MASTER_UPGRADE.get()));

        //items from piglins
        add("scent_mask_from_piglins", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/piglin_bartering")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ModItems.SCENT_MASK.get()));

        //items from archaeology
        add("golddust_from_archaeology", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_rare")).build()}, ModItems.GOLD_DUST.get()));
        add("bomb_seed_from_archaeology", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()}, ModItems.BOMB_SEEDS.get()));
        add("boomerang_from_archaeology", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_rare")).build()}, ModItems.BOOMERANG.get()));
        add("claw_from_archaeology", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()}, ModItems.CLAW.get()));
        add("claw_from_archaeology_common", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_common")).build()}, ModItems.CLAW.get()));
        add("upgrade_from_well", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_well")).build()}, ModItems.MASTER_UPGRADE.get()));
        add("bomb_seeds_from_well", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_well")).build()}, ModItems.BOMB_SEEDS.get()));
        add("water_bomb_from_ocean_cold", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/ocean_ruins_cold")).build()}, ModItems.WATER_BOMB.get()));
        add("upgrade_from_archaeology", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_common")).build()}, ModItems.MASTER_UPGRADE.get()));
    }}
