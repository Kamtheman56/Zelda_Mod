package com.kamth.zeldamod.item;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.custom.ModArmorMaterials;
import com.kamth.zeldamod.custom.ModFoods;
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

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZeldaMod.MOD_ID);

    //These are generic items
    public static final RegistryObject<Item> RED_EMERALD = ITEMS.register("red_emerald",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_EMERALD = ITEMS.register("blue_emerald",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MASTER_ORE = ITEMS.register("master_ore",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> GOLD_DUST = ITEMS.register("gold_dust",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> MASTER_SHARD = ITEMS.register("master_shard",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MASTER_ORE2 = ITEMS.register("refined_master_ore",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MASTER_UPGRADE = ITEMS.register("master_upgrade",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIN_PEARL = ITEMS.register("pearl_power",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FARORE_PEARL = ITEMS.register("pearl_courage",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NAYRU_PEARL = ITEMS.register("pearl_wisdom",
            () -> new Item(new Item.Properties()));
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
            () -> new HeartContainerItem(new Item.Properties()));
    public static final RegistryObject<Item> GLOOM_CONTAINER = ITEMS.register("gloom_container",
            () -> new GloomContainer(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_CONTAINER = ITEMS.register("gold_heart_container",
            () -> new GoldHeartContainerItem(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> HEART_PIECE = ITEMS.register("heart_piece",
            () -> new Item(new Item.Properties()));

    //These are potions or consumables
    public static final RegistryObject<Item> PUMPKIN_SOUP = ITEMS.register("pumpkin_soup",
            () -> new DrinkItem(new Item.Properties().stacksTo(16).food(ModFoods.PUMPKIN_SOUP)));
    public static final RegistryObject<Item> STAMINA = ITEMS.register("stamina_potion",
            () -> new DrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.STAMINA)));
    public static final RegistryObject<Item> HEART_POTION = ITEMS.register("heart_potion",
            () -> new DrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.HEART)));
    public static final RegistryObject<Item> MILK_BOTTLE1 = ITEMS.register("milk_bottle1",
            () -> new MilkBottleItem(new Item.Properties().stacksTo(1).food(ModFoods.MILK_BOTTLE)));
    public static final RegistryObject<Item> MILK_BOTTLE2 = ITEMS.register("milk_bottle2",
            () -> new MilkHalfItem(new Item.Properties().stacksTo(1).food(ModFoods.MILK_BOTTLE)));
    public static final RegistryObject<Item> SHIELD_POTION = ITEMS.register("shield_potion",
            () -> new DrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.SHIELD)));
    public static final RegistryObject<Item> BLUE_POTION = ITEMS.register("blue_potion",
            () -> new BluePotionItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).food(ModFoods.BLUE)));
    public static final RegistryObject<Item> MILK_MAGIC = ITEMS.register("magic_milk",
            () -> new DrinkItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).food(ModFoods.MILK_MAGIC)));
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



    //This is where Tools start
    public static final RegistryObject<Item> KOKIRI_SWORD = ITEMS.register("kokiri_sword",
            () -> new SwordItem(ModTiers.ZELDA, 1, -2.4f,
                    new Item.Properties()));
    public static final RegistryObject<Item> KOKIRI_SWORD2 = ITEMS.register("sword_kokiri",
            () -> new SwordItem(ModTiers.ZELDA, 1, -2.4f,
                    new Item.Properties().defaultDurability(118)));
    public static final RegistryObject<Item> RAZOR_SWORD = ITEMS.register("razor_sword",
            () -> new RazorSwordItem(ModTiers.RAZOR, 2, -1.4f,
                    new Item.Properties().defaultDurability(260)));
    public static final RegistryObject<Item> GILDED_SWORD = ITEMS.register("gilded_sword",
            () -> new SwordItem(ModTiers.GILDED, 3, -2.4f,
                    new Item.Properties().defaultDurability(2000)));
    public static final RegistryObject<Item> MASTER_SWORD = ITEMS.register("master_sword",
            () -> new MasterSwordItem(ModTiers.ZELDAU, 4, -2.4f,
                    new Item.Properties().defaultDurability(1800).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> BIGGORON_SWORD = ITEMS.register("biggoron_sword",
            () -> new SwordItem(ModTiers.GORON, 7, -3.3f,
                    new Item.Properties().fireResistant().defaultDurability(3500)));
    public static final RegistryObject<Item> BIGGORON_KNIFE = ITEMS.register("biggoron_knife",
            () -> new SwordItem(ModTiers.GORON, 7, -3.3f,
                    new Item.Properties().defaultDurability(10)));
    public static final RegistryObject<Item> MASTER_SWORD2 = ITEMS.register("master_sword2",
            () -> new MasterSwordItem(ModTiers.MASTER, 5, -2.4f,
                    new Item.Properties().defaultDurability(2700).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> MASTER_SWORD3 = ITEMS.register("master_sword3",
            () -> new MasterSwordGoldItem(ModTiers.MASTER, 6, -2.4f,
                    new Item.Properties().defaultDurability(3600).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> MASTER_SWORD_TRUE = ITEMS.register("master_sword_true",
            () -> new TrueMasterSwordItem(ModTiers.MASTER_TRUE, 9, -2.2f,
                    new Item.Properties().fireResistant().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MASTER_SWORD_REFORGED = ITEMS.register("master_sword_reforged",
            () -> new ReforgedSword(ModTiers.MASTER_TRUE, 11, -2f,
                    new Item.Properties().fireResistant().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MASTER_SWORD_INJURED = ITEMS.register("master_sword_injured",
            () -> new InjuredSwordItem(ModTiers.MASTER, 1, -2.5f,
                    new Item.Properties().defaultDurability(131).fireResistant().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MASTER_SWORD_DAGGER = ITEMS.register("master_sword_dagger",
            () -> new DaggerSwordItem(ModTiers.MASTER, -1, -2.5f,
                    new Item.Properties().rarity(Rarity.RARE)));
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
            () -> new FierceSwordItem(ModTiers.DEITY, 7, -1.4f,
                    new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> HERO_SWORD = ITEMS.register("hero_sword",
            () -> new MagicSwordItem(Tiers.WOOD, 3, -2.4f,
                    new Item.Properties()));
    public static final RegistryObject<Item> WHITE_SWORD = ITEMS.register("white_sword",
            () -> new MagicSwordItem(Tiers.STONE, 3, -2.4f,
                    new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_SWORD = ITEMS.register("magic_sword",
            () -> new MagicSwordItem(Tiers.IRON, 4, -2.4f,
                    new Item.Properties()));
    public static final RegistryObject<Item> GLOOM_SWORD = ITEMS.register("gloom_sword",
            () -> new GloomWeaponItem(Tiers.IRON, 4, -2.4f,
                    new Item.Properties().defaultDurability(60)));
    public static final RegistryObject<Item> GLOOM_CLUB = ITEMS.register("gloom_club",
            () -> new GloomWeaponItem(Tiers.IRON, 8, -3.4f,
                    new Item.Properties().defaultDurability(120)));


    public static final RegistryObject<Item> SLINGSHOT = ITEMS.register("slingshot",
            () -> new SlingshotItem(new Item.Properties().defaultDurability(284)));
    public static final RegistryObject<Item> SCATTERSHOT = ITEMS.register("scattershot",
            () -> new ScattershotItem(new Item.Properties().defaultDurability(488)));
    public static final RegistryObject<Item> ROC_FEATHER = ITEMS.register("roc_feather",
            () -> new FeatherItem(new Item.Properties().defaultDurability(90)));
    public static final RegistryObject<Item> ROC_FEATHER_2 = ITEMS.register("roc_feather_2",
            () -> new FeatherItem(new Item.Properties().defaultDurability(180)));
    public static final RegistryObject<Item> SAND_ROD = ITEMS.register("sand_rod",
            () -> new SandWandItem(new Item.Properties().durability(96)));
    public static final RegistryObject<Item> BOMB = ITEMS.register("bomb",
            () -> new BombItem(new Item.Properties().stacksTo(32).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> WATER_BOMB = ITEMS.register("water_bomb",
            () -> new WaterBombItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BOMB_FLOWER = ITEMS.register("bomb_flower",
            () -> new BombFlowerItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> HERO_BOW = ITEMS.register("hero_bow",
            () -> new HeroBowItem(new Item.Properties().defaultDurability(550)));
    public static final RegistryObject<Item> FIRE_ARROW = ITEMS.register("fire_arrow",
            () -> new FireArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> ICE_ARROW = ITEMS.register("ice_arrow",
            () -> new IceArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_ARROW = ITEMS.register("light_arrow",
            () -> new LightArrowItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BOMB_ARROW = ITEMS.register("bomb_arrow",
            () -> new BombArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> LIGHTNING_ARROW = ITEMS.register("lightning_arrow",
            () -> new LightningArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> ANCIENT_ARROW = ITEMS.register("ancient_arrow",
            () -> new AncientArrowItem(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> DEKU_NUT = ITEMS.register("deku_nut",
            () -> new DekuNutItem(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> DEKU_LEAF = ITEMS.register("deku_leaf",
            () -> new LeafItem(new Item.Properties().durability(10)));
    public static final RegistryObject<Item> FIRE_ROD = ITEMS.register("fire_rod",
            () -> new FireRodItem(new Item.Properties().durability(218)));
    public static final RegistryObject<Item> ICE_ROD = ITEMS.register("ice_rod",
            () -> new IceRodItem(new Item.Properties().durability(218)));
    public static final RegistryObject<Item> TORNADO_ROD = ITEMS.register("tornado_rod",
            () -> new TornadoRodItem(new Item.Properties().durability(218)));
    public static final RegistryObject<Item> BOOMERANG = ITEMS.register("boomerang",
            () -> new BoomerangItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MAGIC_BOOMERANG = ITEMS.register("magic_boomerang",
            () -> new MagicBoomerangItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GALE_BOOMERANG = ITEMS.register("boomerang_gale",
            () -> new GaleBoomerangItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> HAMMER = ITEMS.register("wooden_hammer",
            () -> new HammerItem(ModTiers.ZELDA, 6, -3f,
                    new Item.Properties().defaultDurability(260)));
    public static final RegistryObject<Item> MEGATON = ITEMS.register("megaton_hammer",
            () -> new MegatonItem(ModTiers.ZELDA, 8, -3f,
                    new Item.Properties().defaultDurability(520)));
    public static final RegistryObject<Item> DEKU_STICK = ITEMS.register("deku_stick",
            () -> new StickItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> DEKU_STICK_LIT = ITEMS.register("deku_stick_lit",
            () -> new StickItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DEKU_STICK_LIT2 = ITEMS.register("deku_stick_lit2",
            () -> new StickItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MITTS = ITEMS.register("digging_mitts",
            () -> new MittsItem(1,-2f,Tiers.STONE, BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item.Properties().defaultDurability(390)));
    public static final RegistryObject<Item> MOGMA_MITTS = ITEMS.register("mogma_mitts",
            () -> new MittsItem2(2,-1.8f,Tiers.IRON, BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item.Properties().defaultDurability(780)));
    public static final RegistryObject<Item> GLIDER = ITEMS.register("glider",
            () -> new GliderItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BLUE_RING = ITEMS.register("blue_ring",
            () -> new BlueRingItem(new Item.Properties().defaultDurability(80)));
    public static final RegistryObject<Item> RED_RING = ITEMS.register("red_ring",
            () -> new RedRingItem(new Item.Properties().defaultDurability(80)));
    public static final RegistryObject<Item> PURPLE_RING = ITEMS.register("purple_ring",
            () -> new PurpleRingItem(new Item.Properties().defaultDurability(160)));
    public static final RegistryObject<Item> HOOKSHOT = ITEMS.register("hookshot",
            () -> new HookshotItem(new Item.Properties().defaultDurability(360)));
    public static final RegistryObject<Item> LONGSHOT = ITEMS.register("longshot",
            () -> new HookshotItem(new Item.Properties().defaultDurability(720)));
    public static final RegistryObject<Item> CLAWSHOT = ITEMS.register("clawshot",
            () -> new ClawshotItem(new Item.Properties().defaultDurability(360)));
    public static final RegistryObject<Item> CLAWSHOT_GODDESS = ITEMS.register("clawshot_goddess",
            () -> new ClawshotItem(new Item.Properties().defaultDurability(720)));
    public static final RegistryObject<Item> LENS_OF_TRUTH = ITEMS.register("lens",
            () -> new LensItem(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SCALE = ITEMS.register("scale_silver",
            () -> new ScaleItem(new Item.Properties().defaultDurability(124)));
    public static final RegistryObject<Item> GOLDEN_SCALE = ITEMS.register("scale_gold",
            () -> new ScaleItem(new Item.Properties().defaultDurability(248)));
    public static final RegistryObject<Item> OCARINA = ITEMS.register("ocarina",
            () -> new OcarinaItem(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> ASCEND = ITEMS.register("ascend",
            () -> new AscendItem(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> FLUTE = ITEMS.register("flute",
            () -> new FluteItem(new Item.Properties().rarity(Rarity.RARE)));

public static final RegistryObject<Item> DEKU_SHIELD = ITEMS.register("deku_shield",
    () -> new WoodShieldItem(new Item.Properties().durability(168)));
    public static final RegistryObject<Item> HYLIAN_SHIELD = ITEMS.register("hylian_shield",
            () -> new ShieldItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(652)));
    public static final RegistryObject<Item> MIRROR_SHIELD = ITEMS.register("mirror_shield",
            () -> new MirrorShieldItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(800)));

    //This is where armors are located keep them together
    //Boots tier
    public static final RegistryObject<HeavyBoots> HEAVY_BOOTS = ITEMS.register("heavyboots",
            ()-> new HeavyBoots(ModArmorMaterials.ZELDAH, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(250)));
    public static final RegistryObject<HoverBoots> HOVER_BOOTS = ITEMS.register("hoverboots",
            ()-> new HoverBoots(ModArmorMaterials.HOVER, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(250)));
    public static final RegistryObject<PegasusBoots> PEGASUS_BOOTS = ITEMS.register("pegasusboots",
            ()-> new PegasusBoots(ModArmorMaterials.PEGASUS, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(195)));
    public static final RegistryObject<Flippers> FLIPPERS = ITEMS.register("flippers",
            ()-> new Flippers(ModArmorMaterials.ZoraTunic, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(195)));
    public static final RegistryObject<DragonScale> DRAGON_SCALE = ITEMS.register("scale_dragon",
            ()-> new DragonScale(ModArmorMaterials.Dragon, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(600)));
    public static final RegistryObject<FireShield> FIRE_SHIELD = ITEMS.register("fire_shield",
            ()-> new FireShield(ModArmorMaterials.Fire, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(600)));



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
            ()-> new RomaniMask(ModArmorMaterials.TROUPE, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(455)));
    public static final RegistryObject<ArmorItem> ROMANI_MASK = ITEMS.register("romanimask",
            ()-> new RomaniMask(ModArmorMaterials.ROMANI, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> GERO_MASK = ITEMS.register("geromask",
            ()-> new RomaniMask(ModArmorMaterials.GERO, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> HAWK_MASK = ITEMS.register("hawkmask",
            ()-> new RomaniMask(ModArmorMaterials.HAWK, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> GIANT_MASK = ITEMS.register("giantmask",
            ()-> new GiantMask(ModArmorMaterials.GIANT, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> COUPLES_MASK = ITEMS.register("couplesmask",
            ()-> new CouplesMask(ModArmorMaterials.COUPLES, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> GIBDO_MASK = ITEMS.register("gibdomask",
            ()-> new RomaniMask(ModArmorMaterials.GIBDO, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> FAIRY_MASK = ITEMS.register("fairymask",
            ()-> new FairyMask(ModArmorMaterials.FAIRY, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> KAFEI_MASK = ITEMS.register("kafeimask",
            ()-> new RomaniMask(ModArmorMaterials.KAFEI, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> BREMEN_MASK = ITEMS.register("bremenmask",
            ()-> new BremenMask(ModArmorMaterials.BREMEN, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> CAPTAIN_MASK = ITEMS.register("captainmask",
            ()-> new RomaniMask(ModArmorMaterials.CAPTAIN, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> KAMARO_MASK = ITEMS.register("kamaromask",
            ()-> new KamaroMask(ModArmorMaterials.KAMARO, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> GARO_MASK = ITEMS.register("garomask",
            ()-> new GaroMask(ModArmorMaterials.GARO, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> POSTMAN_MASK = ITEMS.register("postmanmask",
            ()-> new RomaniMask(ModArmorMaterials.POSTMAN, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> SPOOKY_MASK = ITEMS.register("spookymask",
            ()-> new RomaniMask(ModArmorMaterials.SPOOKY, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> SKULL_MASK = ITEMS.register("skullmask",
            ()-> new RomaniMask(ModArmorMaterials.SKULL, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));
    public static final RegistryObject<ArmorItem> GERUDO_MASK = ITEMS.register("gerudomask",
            ()-> new RomaniMask(ModArmorMaterials.GERUDO, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(165)));


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
                    new Item.Properties().defaultDurability(380)));
    public static final RegistryObject<ArmorItem> CHAMPION_TUNIC = ITEMS.register("champion_tunic",
            ()-> new ArmorItem(ModArmorMaterials.Champion, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(340)));
    public static final RegistryObject<ArmorItem> CHAMPIONS_TUNIC = ITEMS.register("champions_tunic",
            ()-> new ChampionLeathers(ModArmorMaterials.Champions, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(560)));
    public static final RegistryObject<ArmorItem> CLASSIC_TUNIC = ITEMS.register("classic_tunic",
            ()-> new ArmorItem(ModArmorMaterials.Classic, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(440)));
    public static final RegistryObject<ArmorItem> CLASSIC_HAT = ITEMS.register("classic_hat",
            ()-> new ArmorItem(ModArmorMaterials.Classic, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(200)));
    public static final RegistryObject<ArmorItem> ARCHAIC_TUNIC = ITEMS.register("archaic_tunic",
            ()-> new ArchaicTunic(ModArmorMaterials.Archaic, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(240)));
    public static final RegistryObject<ArmorItem> HYLIAN_HOOD = ITEMS.register("hylian_hood",
            ()-> new ArmorItem(ModArmorMaterials.Hylian, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(200)));


    public static final RegistryObject<Item> DEKU_SPAWN_EGG = ITEMS.register("deku_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DEKU, 917142, 43520,
                    new Item.Properties()));
    public static final RegistryObject<Item> DARK_NUT_SPAWN_EGG = ITEMS.register("dark_nut_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DARK_NUT, 12566463, 14637912,
                    new Item.Properties()));
    public static final RegistryObject<Item> KEESE_SPAWN_EGG = ITEMS.register("keese_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.KEESE, 10000, 00000,
                    new Item.Properties()));

        public static void register (IEventBus eventbus) {
            ITEMS.register(eventbus);

        }

}
