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
        add("bomb_from_creeper", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/creeper")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.BOMB.get()));
        add("roc_feather_from_chicken", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/chicken")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.ROC_FEATHER.get()));

        add("herobow_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.HERO_BOW.get()));
        add("herobow_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_fletcher")).build(),
                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.HERO_BOW.get()));
        add("roc_feather_from_fletcher", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_fletcher")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.ROC_FEATHER.get()));
        add("megaton_from_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.MEGATON.get()));
        add("hookshot_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.HOOKSHOT.get()));
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

    }}
