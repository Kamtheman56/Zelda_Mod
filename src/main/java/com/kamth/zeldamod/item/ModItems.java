package com.kamth.zeldamod.item;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.custom.ModArmorMaterials;
import com.kamth.zeldamod.custom.ModFoods;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.armors.*;
import com.kamth.zeldamod.item.items.*;
import com.kamth.zeldamod.item.masks.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZeldaMod.MOD_ID);

    //These are generic items
    public static final RegistryObject<Item> RED_EMERALD = ITEMS.register("red_emerald",
            () -> new EmeraldItem(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_EMERALD = ITEMS.register("blue_emerald",
            () -> new EmeraldItem(new Item.Properties()));
    public static final RegistryObject<Item> MASTER_ORE = ITEMS.register("master_ore",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> GOLD_DUST = ITEMS.register("gold_dust",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> MASTER_SHARD = ITEMS.register("master_shard",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MASTER_ORE2 = ITEMS.register("refined_master_ore",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MASTER_UPGRADE = ITEMS.register("master_upgrade",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> GLOOM_CLUMP = ITEMS.register("gloom_clump",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> DIN_PEARL = ITEMS.register("pearl_power",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> FARORE_PEARL = ITEMS.register("pearl_courage",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> NAYRU_PEARL = ITEMS.register("pearl_wisdom",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> XTENDER = ITEMS.register("extender",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CLAW= ITEMS.register("claw",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DEITY_SHARD = ITEMS.register("sword_fragment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MASK_GOHT = ITEMS.register("mask_goht",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MASK_GYORG = ITEMS.register("mask_gyorg",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MASK_TWINMOLD = ITEMS.register("mask_twinmold",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MASK_ODOLWA = ITEMS.register("mask_odolwa",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SMALL_KEY = ITEMS.register("key_small",
            () -> new KeyItem(new Item.Properties()));
    public static final RegistryObject<Item> BIG_KEY = ITEMS.register("key_big",
            () -> new BigKeyItem(new Item.Properties()));
    public static final RegistryObject<Item> HEART_CONTAINER = ITEMS.register("heart_container",
            () -> new HeartContainerItem(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> GLOOM_CONTAINER = ITEMS.register("gloom_container",
            () -> new GloomContainer(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> GOLD_CONTAINER = ITEMS.register("gold_heart_container",
            () -> new GoldHeartContainerItem(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> HEART_PIECE = ITEMS.register("heart_piece",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> KOROK_SEED = ITEMS.register("korok_seed",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> KOROK_SEED_GIFT = ITEMS.register("korok_seed_gift",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CHU_JELLY = ITEMS.register("chu_jelly",
            () -> new JellyItem(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_CHU_JELLY = ITEMS.register("fire_chu_jelly",
            () -> new FireJellyItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ICE_CHU_JELLY = ITEMS.register("ice_chu_jelly",
            () -> new JellyItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ELECTRIC_CHU_JELLY = ITEMS.register("electric_chu_jelly",
            () -> new Item(new Item.Properties().food(ModFoods.CHU_ELECTRIC).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PURPLE_EMERALD = ITEMS.register("purple_emerald",
            () -> new EmeraldItem(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_EMERALD = ITEMS.register("silver_emerald",
            () -> new EmeraldItem(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_EMERALD = ITEMS.register("gold_emerald",
            () -> new EmeraldItem(new Item.Properties()));



    //These are potions or consumables
    public static final RegistryObject<Item> PUMPKIN_SOUP = ITEMS.register("pumpkin_soup",
            () -> new DrinkItem(new Item.Properties().stacksTo(16).food(ModFoods.PUMPKIN_SOUP)));
    public static final RegistryObject<Item> STAMINA = ITEMS.register("stamina_potion",
            () -> new DrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.STAMINA).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HEART_POTION = ITEMS.register("heart_potion",
            () -> new DrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.HEART).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MILK_BOTTLE1 = ITEMS.register("milk_bottle1",
            () -> new MilkBottleItem(new Item.Properties().stacksTo(1).food(ModFoods.MILK_BOTTLE)));
    public static final RegistryObject<Item> MILK_BOTTLE2 = ITEMS.register("milk_bottle2",
            () -> new MilkHalfItem(new Item.Properties().stacksTo(1).food(ModFoods.MILK_BOTTLE)));
    public static final RegistryObject<Item> SHIELD_POTION = ITEMS.register("shield_potion",
            () -> new DrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.SHIELD)));
    public static final RegistryObject<Item> BLUE_POTION = ITEMS.register("blue_potion",
            () -> new BluePotionItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).food(ModFoods.BLUE).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MILK_MAGIC = ITEMS.register("magic_milk",
            () -> new DrinkItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).food(ModFoods.MILK_MAGIC)));
    public static final RegistryObject<Item> GLOOM_RESIST_POTION = ITEMS.register("gloom_resist_potion",
            () -> new GloomResistPotionItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> SUPER_MUSHROOM = ITEMS.register("mushroom_super",
            () -> new Item(new Item.Properties().food(ModFoods.SUPER_MUSHROOM)));
    public static final RegistryObject<Item> LIFE_MUSHROOM = ITEMS.register("mushroom_life",
            () -> new Item(new Item.Properties().food(ModFoods.LIFE_MUSHROOM)));
    public static final RegistryObject<Item> MINI_MUSHROOM = ITEMS.register("mushroom_mini",
            () -> new Item(new Item.Properties().food(ModFoods.MINI_MUSHROOM)));
    public static final RegistryObject<Item> BAKED_APPLE = ITEMS.register("baked_apple",
            () -> new Item(new Item.Properties().food(Foods.BAKED_POTATO)));
    public static final RegistryObject<Item> SUPER_LEAF = ITEMS.register("super_leaf",
            () -> new SuperLeafItem(new Item.Properties().stacksTo(1).defaultDurability(3)));
    public static final RegistryObject<Item> BOMB_SEEDS = ITEMS.register("bomb_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BOMBFLOWER.get(),new  Item.Properties()));
    public static final RegistryObject<Item> COOKED_KOROK_SEED = ITEMS.register("korok_seed_cooked",
            () -> new Item(new Item.Properties().food(ModFoods.KOROK)));



    //This is where Tools start
    public static final RegistryObject<Item> KOKIRI_SWORD = ITEMS.register("kokiri_sword",
            () -> new SwordItem(ModTiers.ZELDA, 1, -2.4f,
                    new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> KOKIRI_SWORD2 = ITEMS.register("sword_kokiri",
            () -> new SwordItem(ModTiers.ZELDA, 1, -2.4f,
                    new Item.Properties().defaultDurability(118).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> RAZOR_SWORD = ITEMS.register("razor_sword",
            () -> new RazorSwordItem(ModTiers.RAZOR, 2, -1.4f,
                    new Item.Properties().defaultDurability(260).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> GILDED_SWORD = ITEMS.register("gilded_sword",
            () -> new SwordItem(ModTiers.GILDED, 3, -2.4f,
                    new Item.Properties().defaultDurability(2000).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MASTER_SWORD = ITEMS.register("master_sword",
            () -> new MasterSwordItem(ModTiers.ZELDAU, 4, -2.4f,
                    new Item.Properties().defaultDurability(1800).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> BIGGORON_SWORD = ITEMS.register("biggoron_sword",
            () -> new SwordItem(ModTiers.GORON, 7, -3.3f,
                    new Item.Properties().fireResistant().defaultDurability(3500).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> BIGGORON_KNIFE = ITEMS.register("biggoron_knife",
            () -> new SwordItem(ModTiers.GORON, 7, -3.3f,
                    new Item.Properties().defaultDurability(10).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MASTER_SWORD2 = ITEMS.register("master_sword2",
            () -> new MasterSwordItem(ModTiers.MASTER, 5, -2.4f,
                    new Item.Properties().defaultDurability(2700).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> MASTER_SWORD3 = ITEMS.register("master_sword3",
            () -> new MasterSwordGoldItem(ModTiers.MASTER, 6, -2.4f,
                    new Item.Properties().defaultDurability(3600).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> MASTER_SWORD_TRUE = ITEMS.register("master_sword_true",
            () -> new TrueMasterSwordItem(ModTiers.MASTER_TRUE, 9, -2.2f,
                    new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MASTER_SWORD_REFORGED = ITEMS.register("master_sword_reforged",
            () -> new ReforgedSword(ModTiers.MASTER_TRUE, 11, -2f,
                    new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MASTER_SWORD_INJURED = ITEMS.register("master_sword_injured",
            () -> new InjuredSwordItem(ModTiers.MASTER, 1, -2.5f,
                    new Item.Properties().defaultDurability(131).fireResistant().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MASTER_SWORD_DAGGER = ITEMS.register("master_sword_dagger",
            () -> new DaggerSwordItem(ModTiers.MASTER, -1, -2.5f,
                    new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> REFORGED_MASTER = ITEMS.register("reforged_sword",
            () -> new InjuredSwordItem(ModTiers.MASTER, 3, -2.4f,
                    new Item.Properties().defaultDurability(680).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> REFORGED_MASTER1 = ITEMS.register("reforged_sword_1",
            () -> new InjuredSwordItem(ModTiers.MASTER, 6, -2.3f,
                    new Item.Properties().defaultDurability(1260).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> REFORGED_MASTER2 = ITEMS.register("reforged_sword_2",
            () -> new InjuredSwordItem(ModTiers.MASTER, 8, -2.2f,
                    new Item.Properties().defaultDurability(1850).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> FIERCE_SWORD = ITEMS.register("fierce_sword",
            () -> new FierceSwordItem(ModTiers.DEITY, 10, -1.4f,
                    new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> HERO_SWORD = ITEMS.register("hero_sword",
            () -> new MagicSwordItem(Tiers.WOOD, 3, -2.4f,
                    new Item.Properties().rarity(Rarity.UNCOMMON).defaultDurability(600)));
    public static final RegistryObject<Item> WHITE_SWORD = ITEMS.register("white_sword",
            () -> new MagicSwordItem(Tiers.STONE, 3, -2.4f,
                    new Item.Properties().rarity(Rarity.RARE).defaultDurability(1800)));
    public static final RegistryObject<Item> MAGIC_SWORD = ITEMS.register("magic_sword",
            () -> new MagicSwordItem(Tiers.NETHERITE, 5, -2.2f,
                    new Item.Properties().rarity(Rarity.EPIC).defaultDurability(0)));
    public static final RegistryObject<Item> GLOOM_SWORD = ITEMS.register("gloom_sword",
            () -> new GloomWeaponItem(ModTiers.GLOOM, 4, -2.4f,
                    new Item.Properties().defaultDurability(60).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> GLOOM_CLUB = ITEMS.register("gloom_club",
            () -> new GloomWeaponItem(ModTiers.GLOOM, 8, -3.4f,
                    new Item.Properties().defaultDurability(120).rarity(Rarity.RARE)));

// this is where items go
    public static final RegistryObject<Item> SLINGSHOT = ITEMS.register("slingshot",
            () -> new SlingshotItem(new Item.Properties().defaultDurability(284).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> SCATTERSHOT = ITEMS.register("scattershot",
            () -> new ScattershotItem(new Item.Properties().defaultDurability(488).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ROC_FEATHER = ITEMS.register("roc_feather",
            () -> new FeatherItem(new Item.Properties().defaultDurability(90).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ROC_FEATHER_2 = ITEMS.register("roc_feather_2",
            () -> new FeatherItem(new Item.Properties().defaultDurability(180).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> SAND_ROD = ITEMS.register("sand_rod",
            () -> new SandWandItem(new Item.Properties().durability(96).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> BOMB = ITEMS.register("bomb",
            () -> new BombItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> WATER_BOMB = ITEMS.register("water_bomb",
            () -> new WaterBombItem(new Item.Properties().stacksTo(12).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> BOMB_FLOWER = ITEMS.register("bomb_flower",
            () -> new BombFlowerItem(new Item.Properties().stacksTo(8).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BOMBCHU = ITEMS.register("bombchu",
            () -> new BombchuItem(new Item.Properties().stacksTo(8).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HERO_BOW = ITEMS.register("hero_bow",
            () -> new HeroBowItem(new Item.Properties().defaultDurability(550).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> REINFORCED_BOW = ITEMS.register("reinforced_bow",
            () -> new HeroBowItem(new Item.Properties().defaultDurability(1180).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> FIRE_ARROW = ITEMS.register("fire_arrow",
            () -> new FireArrowItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ICE_ARROW = ITEMS.register("ice_arrow",
            () -> new IceArrowItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> LIGHT_ARROW = ITEMS.register("light_arrow",
            () -> new LightArrowItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BOMB_ARROW = ITEMS.register("bomb_arrow",
            () -> new BombArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHTNING_ARROW = ITEMS.register("lightning_arrow",
            () -> new LightningArrowItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ANCIENT_ARROW = ITEMS.register("ancient_arrow",
            () -> new AncientArrowItem(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> DEKU_NUT = ITEMS.register("deku_nut",
            () -> new DekuNutItem(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> DEKU_LEAF = ITEMS.register("deku_leaf",
            () -> new DekuLeafItem(new Item.Properties().durability(10).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> FIRE_ROD = ITEMS.register("fire_rod",
            () -> new InfernoRodItem(new Item.Properties().durability(218).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> ICE_ROD = ITEMS.register("ice_rod",
            () -> new IceRodItem(new Item.Properties().durability(218).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> TORNADO_ROD = ITEMS.register("tornado_rod",
            () -> new TornadoRodItem(new Item.Properties().durability(218).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> BOOMERANG = ITEMS.register("boomerang",
            () -> new BoomerangItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MAGIC_BOOMERANG = ITEMS.register("magic_boomerang",
            () -> new MagicBoomerangItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> GALE_BOOMERANG = ITEMS.register("boomerang_gale",
            () -> new GaleBoomerangItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> HAMMER = ITEMS.register("wooden_hammer",
            () -> new HammerItem(ModTiers.ZELDA, 6, -3f,
                    new Item.Properties().defaultDurability(260).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MEGATON = ITEMS.register("megaton_hammer",
            () -> new MegatonItem(ModTiers.ZELDA, 8, -3f,
                    new Item.Properties().defaultDurability(520).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> INFERNO_ROD = ITEMS.register("inferno_rod",
            () -> new InfernoRodItem(new Item.Properties().durability(670).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> BLIZZARD_ROD = ITEMS.register("blizzard_rod",
            () -> new BlizzardRodItem(new Item.Properties().durability(512).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> SANDSTORM_ROD = ITEMS.register("sandstorm_rod",
            () -> new SandStormRodItem(new Item.Properties().durability(318).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> HURRICANE_ROD = ITEMS.register("hurricane_rod",
            () -> new HurricaneRodItem(new Item.Properties().durability(480).rarity(Rarity.EPIC)));



    public static final RegistryObject<Item> DEKU_STICK = ITEMS.register("deku_stick",
            () -> new StickItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> DEKU_STICK_LIT = ITEMS.register("deku_stick_lit",
            () -> new StickItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MITTS = ITEMS.register("digging_mitts",
            () -> new MittsItem(1,-2f,Tiers.STONE, BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item.Properties().defaultDurability(390).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MOGMA_MITTS = ITEMS.register("mogma_mitts",
            () -> new MittsItem2(2,-1.8f,Tiers.IRON, BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item.Properties().defaultDurability(780).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> GLIDER = ITEMS.register("glider",
            () -> new GliderItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BLUE_RING = ITEMS.register("blue_ring",
            () -> new BlueRingItem(new Item.Properties().defaultDurability(80).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> RED_RING = ITEMS.register("red_ring",
            () -> new RedRingItem(new Item.Properties().defaultDurability(80).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PURPLE_RING = ITEMS.register("purple_ring",
            () -> new PurpleRingItem(new Item.Properties().defaultDurability(160).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> HOOKSHOT = ITEMS.register("hookshot",
            () -> new HookshotItem(new Item.Properties().defaultDurability(360).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> LONGSHOT = ITEMS.register("longshot",
            () -> new HookshotItem(new Item.Properties().defaultDurability(720).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLAWSHOT = ITEMS.register("clawshot",
            () -> new ClawshotItem(new Item.Properties().defaultDurability(360).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> CLAWSHOT_GODDESS = ITEMS.register("clawshot_goddess",
            () -> new ClawshotItem(new Item.Properties().defaultDurability(720).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> LENS_OF_TRUTH = ITEMS.register("lens",
            () -> new LensItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> SILVER_SCALE = ITEMS.register("scale_silver",
            () -> new ScaleItem(new Item.Properties().defaultDurability(124).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> GOLDEN_SCALE = ITEMS.register("scale_gold",
            () -> new ScaleItem(new Item.Properties().defaultDurability(248).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> OCARINA = ITEMS.register("ocarina",
            () -> new OcarinaItem(new Item.Properties().rarity(Rarity.RARE).defaultDurability(250)));
    public static final RegistryObject<Item> ASCEND = ITEMS.register("ascend",
            () -> new AscendItem(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> FLUTE = ITEMS.register("flute",
            () -> new FluteItem(new Item.Properties().rarity(Rarity.RARE).defaultDurability(5)));

    // bag items go here
    public static final RegistryObject<Item> BOMB_BAG = ITEMS.register("bomb_bag",
            () -> new BombBagItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), 64, Collections.singletonList(ModTags.Items.BOMBS)));
    public static final RegistryObject<Item> BOMB_BAG_MEDIUM = ITEMS.register("bomb_bag_medium",
            () -> new BombBagItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 96, Collections.singletonList(ModTags.Items.BOMBS)));
    public static final RegistryObject<Item> BOMB_BAG_LARGE = ITEMS.register("bomb_bag_large",
            () -> new BombBagItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 128, Collections.singletonList(ModTags.Items.BOMBS)));
    public static final RegistryObject<Item> QUIVER_SMALL = ITEMS.register("quiver",
            () -> new QuiverItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), 144, Collections.singletonList(ModTags.Items.BOW_AMMO)));
    public static final RegistryObject<Item> QUIVER_MEDIUM = ITEMS.register("quiver_medium",
            () -> new QuiverItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 208, Collections.singletonList(ModTags.Items.BOW_AMMO)));
    public static final RegistryObject<Item> QUIVER_BIG = ITEMS.register("quiver_large",
            () -> new QuiverItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 448, Collections.singletonList(ModTags.Items.BOW_AMMO)));
    public static final RegistryObject<Item> WALLET = ITEMS.register("wallet",
            () -> new WalletItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), 144, Collections.singletonList(ModTags.Items.GEMS)));
    public static final RegistryObject<Item> WALLET_GIANT = ITEMS.register("wallet_medium",
            () -> new WalletItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 272, Collections.singletonList(ModTags.Items.GEMS)));
    public static final RegistryObject<Item> WALLET_TYCOON = ITEMS.register("wallet_large",
            () -> new WalletItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 512, Collections.singletonList(ModTags.Items.GEMS)));
    public static final RegistryObject<Item> ADVENTURE_POUCH = ITEMS.register("item_pouch",
            () -> new CustomBundleItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 8, Collections.singletonList(ModTags.Items.POUCH_ITEMS)));
    public static final RegistryObject<Item> ADVENTURE_POUCH_LARGE = ITEMS.register("item_pouch_large",
            () -> new CustomBundleItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 16, Collections.singletonList(ModTags.Items.POUCH_ITEMS)));
    public static final RegistryObject<Item> MASK_POUCH = ITEMS.register("mask_pouch",
            () -> new CustomBundleItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 29, Collections.singletonList(ModTags.Items.MASKS)));

    // shields go here
public static final RegistryObject<Item> DEKU_SHIELD = ITEMS.register("deku_shield",
    () -> new WoodShieldItem(new Item.Properties().durability(168)));
    public static final RegistryObject<Item> HYLIAN_SHIELD = ITEMS.register("hylian_shield",
            () -> new ShieldItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(652)));
    public static final RegistryObject<Item> MIRROR_SHIELD = ITEMS.register("mirror_shield",
            () -> new MirrorShieldItem(new Item.Properties().rarity(Rarity.RARE).durability(800)));
    public static final RegistryObject<Item> BALANCED_MIRROR_SHIELD = ITEMS.register("balanced_mirror_shield",
            () -> new BalancedMirrorShieldItem(new Item.Properties().rarity(Rarity.EPIC).durability(1200)));

    // This is where armors are located keep them together
    // Boots tier
    public static final RegistryObject<HeavyBoots> HEAVY_BOOTS = ITEMS.register("heavyboots",
            ()-> new HeavyBoots(ModArmorMaterials.ZELDAH, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(250).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<HoverBoots> HOVER_BOOTS = ITEMS.register("hoverboots",
            ()-> new HoverBoots(ModArmorMaterials.HOVER, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(250).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<PegasusBoots> PEGASUS_BOOTS = ITEMS.register("pegasusboots",
            ()-> new PegasusBoots(ModArmorMaterials.PEGASUS, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(195).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Flippers> FLIPPERS = ITEMS.register("flippers",
            ()-> new Flippers(ModArmorMaterials.ZoraTunic, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(195).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<DragonScale> DRAGON_SCALE = ITEMS.register("scale_dragon",
            ()-> new DragonScale(ModArmorMaterials.Dragon, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(600).rarity(Rarity.RARE)));
    public static final RegistryObject<FireShield> FIRE_SHIELD = ITEMS.register("fire_shield",
            ()-> new FireShield(ModArmorMaterials.Fire, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(600).rarity(Rarity.RARE)));



    //Masks

    public static final RegistryObject<ArmorItem> DEKU_MASK = ITEMS.register("dekumask",
            ()-> new DekuMask(ModArmorMaterials.DEKU, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(250).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<ArmorItem> GORON_MASK = ITEMS.register("goronmask",
            ()-> new GoronMask(ModArmorMaterials.GORON, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(250).rarity(Rarity.UNCOMMON).fireResistant()));
    public static final RegistryObject<ArmorItem> ZORA_MASK = ITEMS.register("zoramask",
            ()-> new ZoraMask(ModArmorMaterials.ZORA, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(250).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<ArmorItem> STONE_MASK = ITEMS.register("stonemask",
            ()-> new StoneMask(ModArmorMaterials.STONE, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> BUNNY_MASK = ITEMS.register("bunnymask",
            ()-> new BunnyMask(ModArmorMaterials.BUNNY, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> NIGHT_MASK = ITEMS.register("nightmask",
            ()-> new NightMask(ModArmorMaterials.NIGHT, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> BLAST_MASK = ITEMS.register("blastmask",
            ()-> new BlastMask(ModArmorMaterials.BLAST, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> FIERCE_MASK = ITEMS.register("fiercemask",
            ()-> new FierceMask(ModArmorMaterials.FIERCE, ArmorItem.Type.HELMET,
                    new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<ArmorItem> MAJORA_MASK = ITEMS.register("majoramask",
            ()-> new MajoraMask(ModArmorMaterials.MAJORA, ArmorItem.Type.HELMET,
                    new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<ArmorItem> SCENT_MASK = ITEMS.register("scentmask",
            ()-> new ScentMask(ModArmorMaterials.SCENT, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(145)));
    public static final RegistryObject<ArmorItem> TRUTH_MASK = ITEMS.register("truthmask",
            ()-> new TruthMask(ModArmorMaterials.TRUTH, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> KEATON_MASK = ITEMS.register("keatonmask",
            ()-> new KeatonMask(ModArmorMaterials.KEATON, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> TROUPE_MASK = ITEMS.register("troupemask",
            ()-> new TroupeMask(ModArmorMaterials.TROUPE, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(455)));
    public static final RegistryObject<ArmorItem> ROMANI_MASK = ITEMS.register("romanimask",
            ()-> new RomaniMask(ModArmorMaterials.ROMANI, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> GERO_MASK = ITEMS.register("geromask",
            ()-> new GeroMask(ModArmorMaterials.GERO, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> HAWK_MASK = ITEMS.register("hawkmask",
            ()-> new HawkeyeMask(ModArmorMaterials.HAWK, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> GIANT_MASK = ITEMS.register("giantmask",
            ()-> new GiantMask(ModArmorMaterials.GIANT, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> COUPLES_MASK = ITEMS.register("couplesmask",
            ()-> new CouplesMask(ModArmorMaterials.COUPLES, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> GIBDO_MASK = ITEMS.register("gibdomask",
            ()-> new GibdoMask(ModArmorMaterials.GIBDO, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> FAIRY_MASK = ITEMS.register("fairymask",
            ()-> new FairyMask(ModArmorMaterials.FAIRY, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> KAFEI_MASK = ITEMS.register("kafeimask",
            ()-> new KafeiMask(ModArmorMaterials.KAFEI, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> BREMEN_MASK = ITEMS.register("bremenmask",
            ()-> new BremenMask(ModArmorMaterials.BREMEN, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> CAPTAIN_MASK = ITEMS.register("captainmask",
            ()-> new CaptainMask(ModArmorMaterials.CAPTAIN, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> KAMARO_MASK = ITEMS.register("kamaromask",
            ()-> new KamaroMask(ModArmorMaterials.KAMARO, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> GARO_MASK = ITEMS.register("garomask",
            ()-> new GaroMask(ModArmorMaterials.GARO, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> POSTMAN_MASK = ITEMS.register("postmanmask",
            ()-> new PostmanHat(ModArmorMaterials.POSTMAN, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> SPOOKY_MASK = ITEMS.register("spookymask",
            ()-> new SpookyMask(ModArmorMaterials.SPOOKY, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> SKULL_MASK = ITEMS.register("skullmask",
            ()-> new SkullMask(ModArmorMaterials.SKULL, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> GERUDO_MASK = ITEMS.register("gerudomask",
            ()-> new GerudoMask(ModArmorMaterials.GERUDO, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> KOROK_MASK = ITEMS.register("korok_mask",
            ()-> new KorokMask(ModArmorMaterials.KOROK, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(382)));

    public static final RegistryObject<ArmorItem> STEVE_MASK = ITEMS.register("steve_mask",
            ()-> new ArmorItem(ModArmorMaterials.STEVE, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(2007)));


   //Tunics
    public static final RegistryObject<ArmorItem> KOKIRI_TUNIC = ITEMS.register("kokiritunic",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(240)));
    public static final RegistryObject<ArmorItem> GORON_TUNIC = ITEMS.register("gorontunic",
            ()-> new GoronTunic(ModArmorMaterials.GoronTunic, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(240)));
    public static final RegistryObject<ArmorItem> ZORA_TUNIC = ITEMS.register("zoratunic",
            ()-> new ZoraTunic(ModArmorMaterials.ZoraTunic, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(240)));
    public static final RegistryObject<ArmorItem> KOKIRI_PANTS = ITEMS.register("kokiripants",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().defaultDurability(200)));
    public static final RegistryObject<ArmorItem> KOKIRI_HAT = ITEMS.register("kokirihat",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(200)));
    public static final RegistryObject<ArmorItem> ZORA_HAT = ITEMS.register("zorahat",
            ()-> new ArmorItem(ModArmorMaterials.ZoraTunic, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(200)));
    public static final RegistryObject<ArmorItem> GORON_HAT = ITEMS.register("goronhat",
            ()-> new ArmorItem(ModArmorMaterials.GoronTunic, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(200)));
    public static final RegistryObject<ArmorItem> KOKIRI_BOOTS = ITEMS.register("kokiriboots",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri,ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(180)));
    public static final RegistryObject<ArmorItem> ROC_CAPE = ITEMS.register("roc_cape",
            ()-> new RocCape(ModArmorMaterials.Roc,ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(380).rarity(Rarity.RARE)));
    public static final RegistryObject<ArmorItem> CHAMPION_TUNIC = ITEMS.register("champion_tunic",
            ()-> new ArmorItem(ModArmorMaterials.Champion, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(340)));
    public static final RegistryObject<ArmorItem> CHAMPIONS_TUNIC = ITEMS.register("champions_tunic",
            ()-> new ChampionLeathers(ModArmorMaterials.Champions, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(560).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<ArmorItem> CLASSIC_TUNIC = ITEMS.register("classic_tunic",
            ()-> new ArmorItem(ModArmorMaterials.Classic, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(650)));
    public static final RegistryObject<ArmorItem> CLASSIC_HAT = ITEMS.register("classic_hat",
            ()-> new ArmorItem(ModArmorMaterials.Classic, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(390)));
    public static final RegistryObject<ArmorItem> ARCHAIC_TUNIC = ITEMS.register("archaic_tunic",
            ()-> new ArchaicTunic(ModArmorMaterials.Archaic, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(240)));
    public static final RegistryObject<ArmorItem> HYLIAN_HOOD = ITEMS.register("hylian_hood",
            ()-> new ArmorItem(ModArmorMaterials.Hylian, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(200)));
    public static final RegistryObject<ArmorItem> DARK_TUNIC = ITEMS.register("dark_tunic",
            ()-> new DarkArmor(ModArmorMaterials.Dark, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(666)));
    public static final RegistryObject<ArmorItem> DARK_HAT = ITEMS.register("dark_hat",
            ()-> new ArmorItem(ModArmorMaterials.Dark, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(450)));
    public static final RegistryObject<ArmorItem> DARK_PANTS = ITEMS.register("dark_pants",
            ()-> new ArmorItem(ModArmorMaterials.Dark, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().defaultDurability(580)));
    public static final RegistryObject<ArmorItem> DARK_BOOTS = ITEMS.register("dark_boots",
            ()-> new ArmorItem(ModArmorMaterials.Dark, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(450)));
    public static final RegistryObject<ArmorItem> HYLIAN_PANTS = ITEMS.register("hylian_pants",
            ()-> new ArmorItem(ModArmorMaterials.Champion, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().defaultDurability(300)));


    public static final RegistryObject<Item> DEKU_SPAWN_EGG = ITEMS.register("deku_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DEKU, 8408862, 778264,
                    new Item.Properties()));
    public static final RegistryObject<Item> DEKU_MAD_SPAWN_EGG = ITEMS.register("deku_mad_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DEKU_MAD, 8408862, 16273174,
                    new Item.Properties()));
    public static final RegistryObject<Item> DARK_NUT_SPAWN_EGG = ITEMS.register("dark_nut_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DARK_NUT, 12566463, 14637912,
                    new Item.Properties()));
    public static final RegistryObject<Item> DARK_KNIGHT_SPAWN_EGG = ITEMS.register("dark_knight_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DARK_KNIGHT, 6176839, 2561279,
                    new Item.Properties()));
    public static final RegistryObject<Item> KEESE_SPAWN_EGG = ITEMS.register("keese_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.KEESE, 6176839, 16252718,
                    new Item.Properties()));
    public static final RegistryObject<Item> CHUCHU_SPAWN_EGG = ITEMS.register("chuchu_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CHUCHU, 56566, 64243,
                    new Item.Properties()));
    public static final RegistryObject<Item> FIRE_CHUCHU_SPAWN_EGG = ITEMS.register("fire_chuchu_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CHUCHU_FIRE, 16727603, 14883891,
                    new Item.Properties()));
    public static final RegistryObject<Item> ICE_CHUCHU_SPAWN_EGG = ITEMS.register("ice_chuchu_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CHUCHU_ICE, 13106943, 16777215,
                    new Item.Properties()));
    public static final RegistryObject<Item> ELECTRIC_CHUCHU_SPAWN_EGG = ITEMS.register("electric_chuchu_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CHUCHU_ELECTRIC, 13631258, 6354196,
                    new Item.Properties()));
    public static final RegistryObject<Item> KOROK_SPAWN_EGG = ITEMS.register("korok_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.KOROK, 14129730, 4507427,
                    new Item.Properties()));
    public static final RegistryObject<Item> SKULLTULA_SPAWN_EGG = ITEMS.register("skulltula_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SKULLTULA, 5062189, 16252708,
                    new Item.Properties()));

        public static void register (IEventBus eventbus) {
            ITEMS.register(eventbus);

        }

}
