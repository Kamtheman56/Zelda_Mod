package com.kamth.zeldamod.datagen;


import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.loot.AddItemModifier;
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
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.BOMB.get()));
        add("roc_feather_from_chicken", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/chicken")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.ROC_FEATHER.get()));
//items from structures
        add("herobow_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.HERO_BOW.get()));
        add("herobow_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_fletcher")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.HERO_BOW.get()));
        add("roc_feather_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_fletcher")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.ROC_FEATHER.get()));
        add("megaton_from_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.MEGATON.get()));
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
                LootItemRandomChanceCondition.randomChance(0.42f).build()}, ModItems.HYLIAN_SHIELD.get()));
        add("hylian_shield_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.18f).build()}, ModItems.HYLIAN_SHIELD.get()));
        add("hylian_shield_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.HYLIAN_SHIELD.get()));
        add("deku_shield_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.DEKU_SHIELD.get()));
        add("deku_shield_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.40f).build()}, ModItems.DEKU_SHIELD.get()));
        add("deku_shield_from_plains", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/plains_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ModItems.DEKU_SHIELD.get()));
        add("hammer_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.40f).build()}, ModItems.HAMMER.get()));
        add("sandrod_from_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.SAND_ROD.get()));
        add("lens_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.LENS_OF_TRUTH.get()));
        add("majora_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ModItems.MAJORA_MASK.get()));
        add("icerod_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city_ice_box")).build(),
                LootItemRandomChanceCondition.randomChance(0.30f).build()}, ModItems.ICE_ROD.get()));
        add("icerod_from_igloo", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ModItems.ICE_ROD.get()));
        add("firerod_from_bastion_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.30f).build()}, ModItems.FIRE_ROD.get()));
        add("firerod_from_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ModItems.FIRE_ROD.get()));
        add("firerod_from_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.16f).build()}, ModItems.FIRE_ROD.get()));
        add("tornadorod_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.TORNADO_ROD.get()));
        add("firerod_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ModItems.FIRE_ROD.get()));
        add("icerod_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ModItems.ICE_ROD.get()));
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
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.DIN_PEARL.get()));
        add("din_pearl_from_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ModItems.DIN_PEARL.get()));

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

        //items from fishing
        add("water_bomb_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.WATER_BOMB.get()));
        add("claw_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.13f).build()}, ModItems.CLAW.get()));
        add("xtender_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.13f).build()}, ModItems.XTENDER.get()));

        //items from archaeology
        add("golddust_from_archaeology", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_rare")).build(),
                LootItemRandomChanceCondition.randomChance(0.14f).build()}, ModItems.GOLD_DUST.get()));
        add("boomerang_from_archaeology", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_rare")).build(),
                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ModItems.BOOMERANG.get()));

    }}
