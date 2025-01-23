package com.kamth.zeldamod.datagen;


import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.loot.AddItemModifier;
import com.kamth.zeldamod.loot.AddSusSandItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ZeldaItems.BOMB.get()));
        add("roc_feather_from_chicken", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/chicken")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ZeldaItems.ROC_FEATHER.get()));
        add("bunny_hood_from_rabbit", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/rabbit")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ZeldaItems.BUNNY_MASK.get()));
        add("garo_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/pillager")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.GARO_MASK.get()));
        add("hookshot_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/pillager")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ZeldaItems.HOOKSHOT.get()));
        add("claw_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/pillager")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.CLAW.get()));
        add("extender_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/pillager")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.XTENDER.get()));
        add("fire_arrow_from_skeleton", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/skeleton")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.FIRE_ARROW.get()));
        add("ice_arrow_from_skeleton", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/skeleton")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.ICE_ARROW.get()));
        add("lightning_arrow_from_skeleton", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/skeleton")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.FIRE_ARROW.get()));
        add("fire_arrow_from_wither_skeleton", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/wither_skeleton")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ZeldaItems.FIRE_ARROW.get()));
        add("ice_arrow_from_stray", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/stray")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ZeldaItems.ICE_ARROW.get()));



//items from structures


        //from jungle temples
        add("herobow_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ZeldaItems.HERO_BOW.get()));
        add("boomerang_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.BOOMERANG.get()));
        add("hookshot_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ZeldaItems.HOOKSHOT.get()));
        add("deku_shield_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ZeldaItems.DEKU_SHIELD.get()));
        add("deku_mask_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ZeldaItems.DEKU_MASK.get()));
        add("green_potion_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ZeldaItems.STAMINA_POTION.get()));
        add("deku_leaf_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ZeldaItems.DEKU_LEAF.get()));


        add("herobow_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_fletcher")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ZeldaItems.HERO_BOW.get()));
        add("roc_feather_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_fletcher")).build(),
                LootItemRandomChanceCondition.randomChance(0.32f).build()}, ZeldaItems.ROC_FEATHER.get()));
        add("megaton_from_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.MEGATON.get()));
        add("biggoron_from_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.BIGGORON_KNIFE.get()));
        add("hookshot_from_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.HOOKSHOT.get()));


        add("hylian_shield_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ZeldaItems.HYLIAN_SHIELD.get()));
        add("deku_shield_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ZeldaItems.DEKU_SHIELD.get()));
        add("deku_stick_from_plains", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/plains_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ZeldaItems.DEKU_STICK.get()));
        add("deku_nut_from_plains", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/plains_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ZeldaItems.DEKU_NUT.get()));

        add("icerod_from_igloo", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ZeldaItems.ICE_ROD.get()));
        add("firerod_from_bastion_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ZeldaItems.FIRE_ROD.get()));
        add("firerod_from_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.FIRE_ROD.get()));
        add("firerod_from_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.16f).build()}, ZeldaItems.FIRE_ROD.get()));

        //from desert temple
        add("sandrod_from_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ZeldaItems.SAND_ROD.get()));
        add("mirror_shield_from_desert_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.27f).build()}, ZeldaItems.MIRROR_SHIELD.get()));

        //from ancient city
        add("lens_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.LENS_OF_TRUTH.get()));
        add("majora_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ZeldaItems.MAJORA_MASK.get()));
        add("ancient_arrow_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ZeldaItems.ANCIENT_ARROW.get()));
        add("icerod_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city_ice_box")).build(),
                LootItemRandomChanceCondition.randomChance(0.30f).build()}, ZeldaItems.ICE_ROD.get()));
        add("deity_shard_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ZeldaItems.DEITY_SHARD.get()));
        add("fire_arrow_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.FIRE_ARROW.get()));
        add("ice_arrow_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.ICE_ARROW.get()));
        add("lightning_arrow_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ZeldaItems.SHOCK_ARROW.get()));
        add("light_arrow_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ZeldaItems.LIGHT_ARROW.get()));
        add("blue_potion_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.BLUE_POTION.get()));
        add("red_potion_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.HEART_POTION.get()));
        add("green_potion_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.STAMINA_POTION.get()));
        add("ocarina_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ZeldaItems.OCARINA.get()));

        add("dark_hat_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ZeldaItems.DARK_HAT.get()));
        add("dark_boots_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ZeldaItems.DARK_BOOTS.get()));
        add("dark_pants_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ZeldaItems.DARK_PANTS.get()));
        add("dark_tunic_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.DARK_TUNIC.get()));
        add("gloom_clump_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ZeldaItems.GLOOM_CLUMP.get()));
        add("gloom_container_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.01f).build()}, ZeldaItems.GLOOM_CONTAINER.get()));

        //from woodland mansion
        add("hookshot_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ZeldaItems.HOOKSHOT.get()));
        add("longshot_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ZeldaItems.LONGSHOT.get()));
        add("hylian_shield_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.HYLIAN_SHIELD.get()));
        add("mirror_shield_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ZeldaItems.MIRROR_SHIELD.get()));
        add("hero_bow_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.HERO_BOW.get()));
        add("shard_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.02f).build()}, ZeldaItems.DEITY_SHARD.get()));

        //end city
        add("mirror_shield_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.26f).build()}, ZeldaItems.MIRROR_SHIELD.get()));
        add("shard_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.03f).build()}, ZeldaItems.DEITY_SHARD.get()));
        add("blue_potion_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ZeldaItems.BLUE_POTION.get()));


       //from dungeons
        add("fire_arrow_from_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.FIRE_ARROW.get()));
        add("ice_arrow_from_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.ICE_ARROW.get()));
        add("lightning_arrow_from_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.SHOCK_ARROW.get()));
        add("light_arrow_from_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.LIGHT_ARROW.get()));
        add("hammer_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.40f).build()}, ZeldaItems.HAMMER.get()));
        add("bomb_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()}, ZeldaItems.BOMB.get()));
        add("lens_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.10f).build()}, ZeldaItems.LENS_OF_TRUTH.get()));
        add("red_potion_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.HEART_POTION.get()));
        add("green_potion_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.30f).build()}, ZeldaItems.STAMINA_POTION.get()));
        add("blue_potion_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ZeldaItems.BLUE_POTION.get()));
        add("slingshot_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.42f).build()}, ZeldaItems.SLINGSHOT.get()));
        add("boomerang_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.BOOMERANG.get()));
        add("deku_shield_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.40f).build()}, ZeldaItems.DEKU_SHIELD.get()));
        add("hero_bow_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ZeldaItems.HERO_BOW.get()));
        add("hylian_shield_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.09f).build()}, ZeldaItems.HYLIAN_SHIELD.get()));
        add("hero_sword_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ZeldaItems.HERO_SWORD.get()));

        //from Pillager Outposts
        add("hookshot_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.24f).build()}, ZeldaItems.HOOKSHOT.get()));
        add("blue_ring_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ZeldaItems.BLUE_RING.get()));
        add("garo_mask_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ZeldaItems.GARO_MASK.get()));
        add("hylian_shield_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.18f).build()}, ZeldaItems.HYLIAN_SHIELD.get()));
        add("kokiri_sword_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.26f).build()}, ZeldaItems.KOKIRI_SWORD_MM.get()));
        add("tornado_rod_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.TORNADO_ROD.get()));


        //from mineshafts
        add("bomb_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ZeldaItems.BOMB.get()));
        add("bomb_seeds_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.11f).build()}, ZeldaItems.BOMB_SEEDS.get()));
        add("blast_mask_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.BLAST_MASK.get()));
        add("stone_mask_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.STONE_MASK.get()));
        add("red_potion_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ZeldaItems.HEART_POTION.get()));
        add("green_potion_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ZeldaItems.STAMINA_POTION.get()));
        add("blue_potion_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ZeldaItems.BLUE_POTION.get()));
        add("kokiri_sword_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ZeldaItems.KOKIRI_SWORD_OOT.get()));
        add("red_ring_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.RED_RING.get()));
        add("mitts_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.MITTS.get()));
        add("fire_arrow_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.FIRE_ARROW.get()));
        add("ice_arrow_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.ICE_ARROW.get()));
        add("lightning_arrow_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.SHOCK_ARROW.get()));
        add("lightning_arrow_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.SHOCK_ARROW.get()));
        add("light_arrow_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.LIGHT_ARROW.get()));
        add("tornado_rod_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.TORNADO_ROD.get()));
        add("kokiri_tunic_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ZeldaItems.KOKIRI_TUNIC.get()));
        add("deku_shield_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.DEKU_SHIELD.get()));
        add("hylian_shield_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ZeldaItems.HYLIAN_SHIELD.get()));

        //from shipwrecks
        add("flippers_from_shipwreck", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ZeldaItems.FLIPPERS.get()));
        add("zora_mask_from_shipwreck", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.02f).build()}, ZeldaItems.ZORA_MASK.get()));

        //from underwater ruins
        add("zora_mask_from_ruins", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.06f).build()}, ZeldaItems.ZORA_MASK.get()));
        add("ice_arrow_from_ruins", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.18f).build()}, ZeldaItems.ICE_ARROW.get()));
        add("water_bomb_from_big_ruins", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.34f).build()}, ZeldaItems.WATER_BOMB.get()));
        add("water_bomb_from_small_ruins", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(0.28f).build()}, ZeldaItems.WATER_BOMB.get()));
        add("flippers_from_small_ruins", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.FLIPPERS.get()));
        add("flippers_from_big_ruins", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.FLIPPERS.get()));
        add("heavy_boots_from_small_ruins", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.HEAVY_BOOTS.get()));


//from strongholds
        add("all_night_from_strongholds", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.NIGHT_MASK.get()));
        add("blast_from_strongholds", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_library")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.BLAST_MASK.get()));
        add("hylian_from_strongholds", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.32f).build()}, ZeldaItems.HYLIAN_SHIELD.get()));
        add("giant_from_strongholds", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.24f).build()}, ZeldaItems.GIANT_MASK.get()));


        //pearl locations
        add("farore_pearl_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.FARORE_PEARL.get()));
        add("farore_pearl_from_desert_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.FARORE_PEARL.get()));
        add("nayru_pearl_from_elder_guardian", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/elder_guardian")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.NAYRU_PEARL.get()));
        add("nayru_pearl_from_shipwreck", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.NAYRU_PEARL.get()));
        add("din_pearl_from_strongholds", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ZeldaItems.DIN_PEARL.get()));
        add("din_pearl_from_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.DIN_PEARL.get()));


        //armor from structures
        add("flippers_from_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ZeldaItems.FLIPPERS.get()));
        add("zora_tunic_from_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.ZORA_TUNIC.get()));
        add("zora_tunic_from_shipwreck_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ZeldaItems.ZORA_TUNIC.get()));
        add("zora_hat_from_shipwreck_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ZeldaItems.ZORA_HAT.get()));
        add("zora_hat_from_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ZeldaItems.ZORA_HAT.get()));
        add("goron_tunic_from_bastion_other", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.GORON_TUNIC.get()));
        add("goron_hat_from_bastion_other", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ZeldaItems.GORON_HAT.get()));
        add("kokiri_tunic_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.75f).build()}, ZeldaItems.KOKIRI_TUNIC.get()));
        add("kokiri_hat_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ZeldaItems.KOKIRI_HAT.get()));
        add("kokiri_pants_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ZeldaItems.KOKIRI_PANTS.get()));
        add("kokiri_boots_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.55f).build()}, ZeldaItems.KOKIRI_BOOTS.get()));
        add("hylian_pants_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ZeldaItems.HYLIAN_PANTS.get()));
        add("hylian_hood_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.34f).build()}, ZeldaItems.HYLIAN_HOOD.get()));
        add("hover_boots_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.09f).build()}, ZeldaItems.HOVER_BOOTS.get()));
        add("pegasus_boots_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.09f).build()}, ZeldaItems.PEGASUS_BOOTS.get()));
        add("heavy_boots_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.09f).build()}, ZeldaItems.HEAVY_BOOTS.get()));
        add("goron_tunic_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.GORON_TUNIC.get()));
        add("zora_tunic_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.16f).build()}, ZeldaItems.ZORA_TUNIC.get()));
        add("champion_tunic_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ZeldaItems.CHAMPION_TUNIC.get()));
        add("hover_boots_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ZeldaItems.HOVER_BOOTS.get()));
        add("heavy_boots_from_igloo", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ZeldaItems.HEAVY_BOOTS.get()));
        add("heavy_boots_from_shipwreck", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.65f).build()}, ZeldaItems.HEAVY_BOOTS.get()));
        add("goron_tunic_from_ruined_portal", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.23f).build()}, ZeldaItems.GORON_TUNIC.get()));
        add("goron_hat_from_ruined_portal", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.16f).build()}, ZeldaItems.GORON_HAT.get()));
        add("goron_tunic_from_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ZeldaItems.GORON_TUNIC.get()));

        //items from hero of village
        add("fire_arrow_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/fletcher_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.18f).build()}, ZeldaItems.FIRE_ARROW.get()));
        add("ice_arrow_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/fletcher_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.18f).build()}, ZeldaItems.ICE_ARROW.get()));
        add("lightning_arrow_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/fletcher_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.18f).build()}, ZeldaItems.SHOCK_ARROW.get()));

        add("stamina_potion_from_cleric", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/cleric_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.STAMINA_POTION.get()));
        add("heart_potion_from_cleric", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/cleric_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.08f).build()}, ZeldaItems.HEART_POTION.get()));

        add("deku_shield_from_weaponsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/weaponsmith_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ZeldaItems.DEKU_SHIELD.get()));
        add("kokiri_sword_from_weaponsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/weaponsmith_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ZeldaItems.KOKIRI_SWORD_OOT.get()));
        add("upgrade_from_weaponsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/weaponsmith_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.09f).build()}, ZeldaItems.MASTER_UPGRADE.get()));
        add("hero_sword_from_weaponsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/hero_of_the_village/weaponsmith_gift")).build(),
                LootItemRandomChanceCondition.randomChance(0.07f).build()}, ZeldaItems.HERO_SWORD.get()));


        //items from fishing
        add("water_bomb_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ZeldaItems.WATER_BOMB.get()));
        add("claw_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.13f).build()}, ZeldaItems.CLAW.get()));
        add("xtender_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.13f).build()}, ZeldaItems.XTENDER.get()));
        add("upgrade_from_fishing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.MASTER_UPGRADE.get()));

        //items from piglins
        add("scent_mask_from_piglins", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/piglin_bartering")).build(),
                LootItemRandomChanceCondition.randomChance(0.15f).build()}, ZeldaItems.SCENT_MASK.get()));

        //items from archaeology
        add("golddust_from_archaeology", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_rare")).build()}, ZeldaItems.GOLD_DUST.get()));
        add("master_upgrade_from_archaeology", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()}, ZeldaItems.MASTER_UPGRADE.get()));
        add("golddust_from_archaeology", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()}, ZeldaItems.GOLD_DUST.get()));
        add("boomerang_from_archaeology", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_rare")).build()}, ZeldaItems.BOOMERANG.get()));
        add("xtender_from_archaeology", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()}, ZeldaItems.XTENDER.get()));
        add("claw_from_archaeology_common", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_common")).build()}, ZeldaItems.CLAW.get()));
        add("upgrade_from_well", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_well")).build()}, ZeldaItems.MASTER_UPGRADE.get()));
        add("bomb_seeds_from_well", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_well")).build()}, ZeldaItems.BOMB_SEEDS.get()));
        add("water_bomb_from_ocean_cold", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/ocean_ruins_cold")).build()}, ZeldaItems.WATER_BOMB.get()));
        add("upgrade_from_archaeology", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_common")).build()}, ZeldaItems.MASTER_UPGRADE.get()));
    }}
