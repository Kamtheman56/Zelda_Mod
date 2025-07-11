package com.kamth.zeldamod.item;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.custom.ModArmorMaterials;
import com.kamth.zeldamod.custom.ModFoods;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.armors.*;
import com.kamth.zeldamod.item.items.TooltipItem;
import com.kamth.zeldamod.item.items.arrows.*;
import com.kamth.zeldamod.item.items.bags.BombBagItem;
import com.kamth.zeldamod.item.items.bags.QuiverItem;
import com.kamth.zeldamod.item.items.bags.WalletItem;
import com.kamth.zeldamod.item.items.bombs.BombItem;
import com.kamth.zeldamod.item.items.bombs.BombchuItem;
import com.kamth.zeldamod.item.items.bombs.WaterBombItem;
import com.kamth.zeldamod.item.items.boomerangs.BoomerangItem;
import com.kamth.zeldamod.item.items.boomerangs.GaleBoomerangItem;
import com.kamth.zeldamod.item.items.boomerangs.MagicBoomerangItem;
import com.kamth.zeldamod.item.items.consumables.*;
import com.kamth.zeldamod.item.items.consumables.drink.BluePotionItem;
import com.kamth.zeldamod.item.items.consumables.drink.DrinkItem;
import com.kamth.zeldamod.item.items.consumables.drink.GloomResistPotionItem;
import com.kamth.zeldamod.item.items.consumables.hearts.GloomContainer;
import com.kamth.zeldamod.item.items.consumables.hearts.GoldHeartContainerItem;
import com.kamth.zeldamod.item.items.consumables.hearts.HeartContainerItem;
import com.kamth.zeldamod.item.items.consumables.jelly.ElectricJelly;
import com.kamth.zeldamod.item.items.consumables.jelly.FireJellyItem;
import com.kamth.zeldamod.item.items.consumables.jelly.IceJellyItem;
import com.kamth.zeldamod.item.items.consumables.jelly.JellyItem;
import com.kamth.zeldamod.item.items.grapples.ClawshotItem;
import com.kamth.zeldamod.item.items.grapples.HookshotItem;
import com.kamth.zeldamod.item.items.instruments.FluteItem;
import com.kamth.zeldamod.item.items.instruments.OcarinaItem;
import com.kamth.zeldamod.item.items.movement.*;
import com.kamth.zeldamod.item.items.tools.DiggingMittsItem;
import com.kamth.zeldamod.item.items.tools.KeyItem;
import com.kamth.zeldamod.item.items.tools.LensItem;
import com.kamth.zeldamod.item.items.tools.MogmaMittsItem;
import com.kamth.zeldamod.item.items.weapons.extra.HammerItem;
import com.kamth.zeldamod.item.items.weapons.extra.MegatonItem;
import com.kamth.zeldamod.item.items.weapons.extra.StickItem;
import com.kamth.zeldamod.item.items.weapons.projectiles.DekuNutItem;
import com.kamth.zeldamod.item.items.weapons.projectiles.HeroBowItem;
import com.kamth.zeldamod.item.items.rings.BlueRingItem;
import com.kamth.zeldamod.item.items.rings.PurpleRingItem;
import com.kamth.zeldamod.item.items.rings.RedRingItem;
import com.kamth.zeldamod.item.items.rods.*;
import com.kamth.zeldamod.item.items.shields.BalancedMirrorShieldItem;
import com.kamth.zeldamod.item.items.shields.MirrorShieldItem;
import com.kamth.zeldamod.item.items.shields.WoodShieldItem;
import com.kamth.zeldamod.item.items.bombs.BombFlowerItem;
import com.kamth.zeldamod.item.items.weapons.projectiles.ScattershotItem;
import com.kamth.zeldamod.item.items.weapons.projectiles.SlingshotItem;
import com.kamth.zeldamod.item.items.weapons.swords.*;
import com.kamth.zeldamod.item.items.weapons.swords.master.*;
import com.kamth.zeldamod.item.masks.regular.*;
import com.kamth.zeldamod.item.masks.transformation.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;

import static com.kamth.zeldamod.item.ItemRegistrationHelper.*;

public class ZeldaItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZeldaMod.MOD_ID);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // REGULAR ITEMS

    // EMERALDS
    public static final RegistryObject<Item> BLUE_EMERALD = ITEMS.register("blue_emerald",
            () -> new TooltipItem(new Item.Properties(), "iaqua"));

    public static final RegistryObject<Item> RED_EMERALD = ITEMS.register("red_emerald",
            () -> new TooltipItem(new Item.Properties(), "ired"));

    public static final RegistryObject<Item> PURPLE_EMERALD = ITEMS.register("purple_emerald",
            () -> new TooltipItem(new Item.Properties(), "ipurple"));

    public static final RegistryObject<Item> SILVER_EMERALD = ITEMS.register("silver_emerald",
            () -> new TooltipItem(new Item.Properties(), "ibwhite"));

    public static final RegistryObject<Item> GOLD_EMERALD = ITEMS.register("gold_emerald",
            () -> new TooltipItem(new Item.Properties(), "ib-yellow"));

    // KOROK SEED
    public static final RegistryObject<Item> KOROK_SEED = ITEMS.register("korok_seed",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> KOROK_SEED_GIFT = ITEMS.register("korok_seed_gift",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));

    // MASTER ORE
    public static final RegistryObject<Item> MASTER_ORE = ITEMS.register("master_ore",
            () -> new WarpItem(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> GOLD_DUST = ITEMS.register("gold_dust",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> MASTER_SHARD = ITEMS.register("master_shard",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> REFINED_MASTER_ORE = ITEMS.register("refined_master_ore",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MASTER_UPGRADE = ITEMS.register("master_upgrade",
            () -> new TooltipItem(new Item.Properties().rarity(Rarity.UNCOMMON), "iyellow", true));

    // PEARLS
    public static final RegistryObject<Item> DIN_PEARL = ITEMS.register("pearl_power",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> FARORE_PEARL = ITEMS.register("pearl_courage",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> NAYRU_PEARL = ITEMS.register("pearl_wisdom",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    // CHU JELLY
    public static final RegistryObject<Item> CHU_JELLY = ITEMS.register("chu_jelly",
            () -> new JellyItem(new Item.Properties()));

    public static final RegistryObject<Item> FIRE_CHU_JELLY = ITEMS.register("fire_chu_jelly",
            () -> new FireJellyItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ICE_CHU_JELLY = ITEMS.register("ice_chu_jelly",
            () -> new IceJellyItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ELECTRIC_CHU_JELLY = ITEMS.register("electric_chu_jelly",
            () -> new ElectricJelly(new Item.Properties().rarity(Rarity.UNCOMMON)));

    // KEY
    public static final RegistryObject<Item> SMALL_KEY = ITEMS.register("key_small",
            () -> new KeyItem(new Item.Properties(), ZeldaBlocks.LOCKED_DOOR.get()));

    public static final RegistryObject<Item> BIG_KEY = ITEMS.register("key_big",
            () -> new KeyItem(new Item.Properties(), ZeldaBlocks.LOCKED_BOSS_DOOR.get()));

    // HEARTS
    public static final RegistryObject<Item> HEART_CONTAINER = ITEMS.register("heart_container",
            () -> new HeartContainerItem(new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> GLOOM_CONTAINER = ITEMS.register("gloom_container",
            () -> new GloomContainer(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> GOLD_HEART_CONTAINER = ITEMS.register("gold_heart_container",
            () -> new GoldHeartContainerItem(new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> HEART_PIECE = ITEMS.register("heart_piece",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    // OTHER
    public static final RegistryObject<Item> GLOOM_CLUMP = ITEMS.register("gloom_clump",
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSUMABLE ITEMS

    // POTIONS
    public static final RegistryObject<Item> STAMINA_POTION = ITEMS.register("stamina_potion",
            () -> new DrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.STAMINA).rarity(Rarity.UNCOMMON),
                    "igreen"));

    public static final RegistryObject<Item> HEART_POTION = ITEMS.register("heart_potion",
            () -> new DrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.HEART).rarity(Rarity.UNCOMMON),
                    "ired"));

    public static final RegistryObject<Item> SHIELD_POTION = ITEMS.register("shield_potion",
            () -> new DrinkItem(new Item.Properties().stacksTo(1).food(ModFoods.SHIELD),
                    "ipurple"));

    public static final RegistryObject<Item> BLUE_POTION = ITEMS.register("blue_potion",
            () -> new BluePotionItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).food(ModFoods.BLUE).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> GLOOM_RESIST_POTION = ITEMS.register("gloom_resist_potion",
            () -> new GloomResistPotionItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).food(ModFoods.GLOOM_RESIST)));

    // FOODS
    public static final RegistryObject<Item> PUMPKIN_SOUP = ITEMS.register("pumpkin_soup",
            () -> new DrinkItem(new Item.Properties().stacksTo(16).food(ModFoods.PUMPKIN_SOUP), "iyellow"));

    public static final RegistryObject<Item> BAKED_APPLE = ITEMS.register("baked_apple",
            () -> new Item(new Item.Properties().food(Foods.BAKED_POTATO)));

    public static final RegistryObject<Item> MILK_BOTTLE = ITEMS.register("milk_bottle",
            () -> new MilkBottleItem(new Item.Properties().stacksTo(1).food(ModFoods.MILK_BOTTLE)));

    public static final RegistryObject<Item> MILK_MAGIC = ITEMS.register("magic_milk",
            () -> new DrinkItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).food(ModFoods.MILK_MAGIC), "ipurple"));

    // SEEDS
    public static final RegistryObject<Item> BOMB_SEEDS = ITEMS.register("bomb_seeds",
            () -> new ItemNameBlockItem(ZeldaBlocks.BOMBFLOWER.get(),new  Item.Properties()));

    public static final RegistryObject<Item> COOKED_KOROK_SEED = ITEMS.register("korok_seed_cooked",
            () -> new Item(new Item.Properties().food(ModFoods.KOROK)));

    // MARIO CONSUMABLES
    public static final RegistryObject<Item> SUPER_MUSHROOM = ITEMS.register("mushroom_super",
            () -> new Item(new Item.Properties().food(ModFoods.SUPER_MUSHROOM)));

    public static final RegistryObject<Item> LIFE_MUSHROOM = ITEMS.register("mushroom_life",
            () -> new Item(new Item.Properties().food(ModFoods.LIFE_MUSHROOM)));

    public static final RegistryObject<Item> MINI_MUSHROOM = ITEMS.register("mushroom_mini",
            () -> new Item(new Item.Properties().food(ModFoods.MINI_MUSHROOM)));

    public static final RegistryObject<Item> SUPER_LEAF = ITEMS.register("super_leaf",
            () -> new SuperLeafItem(new Item.Properties().stacksTo(1).defaultDurability(3)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SWORDS



    // REGULAR

    public static final RegistryObject<Item> KOKIRI_SWORD_OOT = ITEMS.register("kokiri_sword_oot",
            () -> new SwordItem(ModTiers.ZELDA, 1, -2.4f,
                    new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> KOKIRI_SWORD_MM = ITEMS.register("kokiri_sword_mm",
            () -> new SwordItem(ModTiers.ZELDA, 1, -2.4f,
                    new Item.Properties().defaultDurability(118).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> GILDED_SWORD = ITEMS.register("gilded_sword",
            () -> new SwordItem(ModTiers.GILDED, 3, -2.4f,
                    new Item.Properties().defaultDurability(2000).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> BIGGORON_SWORD = ITEMS.register("biggoron_sword",
            () -> new SwordItem(ModTiers.GORON, 7, -3.3f,
                    new Item.Properties().fireResistant().defaultDurability(3500).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> BIGGORON_KNIFE = ITEMS.register("biggoron_knife",
            () -> new SwordItem(ModTiers.GORON, 7, -3.3f,
                    new Item.Properties().defaultDurability(10).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> GLOOM_SWORD = ITEMS.register("gloom_sword",
            () -> new GloomWeaponItem(ModTiers.GLOOM, 4, -2.4f,
                    new Item.Properties().defaultDurability(60).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> GLOOM_CLUB = ITEMS.register("gloom_club",
            () -> new GloomWeaponItem(ModTiers.GLOOM, 8, -3.4f,
                    new Item.Properties().defaultDurability(120).rarity(Rarity.RARE)));


    // BREAK INTO NEW SWORD

    public static final RegistryObject<Item> RAZOR_SWORD = ITEMS.register("razor_sword",
            () -> new RazorSwordItem(ModTiers.RAZOR, 2, -1.4f,
                    new Item.Properties().defaultDurability(260).rarity(Rarity.UNCOMMON),
                    KOKIRI_SWORD_MM.get()));

    // SWORD BEAM SWORDS

    public static final RegistryObject<Item> MASTER_SWORD = ITEMS.register("master_sword",
            () -> new MasterSwordItem(ModTiers.ZELDAU, 4, -2.4f,
                    new Item.Properties().defaultDurability(1800).rarity(Rarity.RARE).fireResistant()));

    public static final RegistryObject<Item> MASTER_SWORD_TEMPERED = ITEMS.register("master_sword_tempered",
            () -> new MasterSwordItem(ModTiers.MASTER, 5, -2.4f,
                    new Item.Properties().defaultDurability(2700).rarity(Rarity.RARE).fireResistant()));

    public static final RegistryObject<Item> MASTER_SWORD_GOLDEN = ITEMS.register("master_sword_golden",
            () -> new GoldenMasterSwordItem(ModTiers.MASTER, 6, -2.4f,
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
            () -> new FierceDeitySwordItem(ModTiers.DEITY, 10, -1.4f,
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




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SPECIAL ITEMS

   // PROJECTILE ITEMS

    public static final RegistryObject<Item> SLINGSHOT = ITEMS.register("slingshot",
            () -> new SlingshotItem(new Item.Properties().defaultDurability(284).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> SCATTERSHOT = ITEMS.register("scattershot",
            () -> new ScattershotItem(new Item.Properties().defaultDurability(488).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> HERO_BOW = ITEMS.register("hero_bow",
            () -> new HeroBowItem(new Item.Properties().defaultDurability(550).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> REINFORCED_BOW = ITEMS.register("reinforced_bow",
            () -> new HeroBowItem(new Item.Properties().defaultDurability(1180).rarity(Rarity.UNCOMMON)));

    // THROWABLES

    public static final RegistryObject<Item> BOMB = ITEMS.register("bomb",
            () -> new BombItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> WATER_BOMB = ITEMS.register("water_bomb",
            () -> new WaterBombItem(new Item.Properties().stacksTo(12).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> BOMB_FLOWER = ITEMS.register("bomb_flower",
            () -> new BombFlowerItem(new Item.Properties().stacksTo(8).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> BOMBCHU = ITEMS.register("bombchu",
            () -> new BombchuItem(new Item.Properties().stacksTo(8).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> DEKU_NUT = ITEMS.register("deku_nut",
            () -> new DekuNutItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> BOOMERANG = ITEMS.register("boomerang",
            () -> new BoomerangItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> MAGIC_BOOMERANG = ITEMS.register("boomerang_magic",
            () -> new MagicBoomerangItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> GALE_BOOMERANG = ITEMS.register("boomerang_gale",
            () -> new GaleBoomerangItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));


    // ARROWS

    public static final RegistryObject<Item> FIRE_ARROW = ITEMS.register("fire_arrow",
            () -> new FireArrowItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ICE_ARROW = ITEMS.register("ice_arrow",
            () -> new IceArrowItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> LIGHT_ARROW = ITEMS.register("light_arrow",
            () -> new LightArrowItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> BOMB_ARROW = ITEMS.register("bomb_arrow",
            () -> new BombArrowItem(new Item.Properties()));

    public static final RegistryObject<Item> SHOCK_ARROW = ITEMS.register("shock_arrow",
            () -> new ShockArrowItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ANCIENT_ARROW = ITEMS.register("ancient_arrow",
            () -> new AncientArrowItem(new Item.Properties().rarity(Rarity.EPIC)));

    // RODS

    public static final RegistryObject<Item> FIRE_ROD = ITEMS.register("fire_rod",
            () -> new FireRodItem(new Item.Properties().durability(218).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ICE_ROD = ITEMS.register("ice_rod",
            () -> new IceRodItem(new Item.Properties().durability(218).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> TORNADO_ROD = ITEMS.register("tornado_rod",
            () -> new TornadoRodItem(new Item.Properties().durability(218).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> SAND_ROD = ITEMS.register("sand_rod",
            () -> new SandWandItem(new Item.Properties().durability(96).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> INFERNO_ROD = ITEMS.register("inferno_rod",
            () -> new InfernoRodItem(new Item.Properties().durability(670).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BLIZZARD_ROD = ITEMS.register("blizzard_rod",
            () -> new BlizzardRodItem(new Item.Properties().durability(512).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> SANDSTORM_ROD = ITEMS.register("sandstorm_rod",
            () -> new SandStormRodItem(new Item.Properties().durability(318).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> HURRICANE_ROD = ITEMS.register("hurricane_rod",
            () -> new HurricaneRodItem(new Item.Properties().durability(480).rarity(Rarity.EPIC)));


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MOVEMENT ITEMS


    public static final RegistryObject<Item> ROC_FEATHER = ITEMS.register("roc_feather",
            () -> new FeatherItem(new Item.Properties().defaultDurability(90).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ROC_FEATHER_2 = ITEMS.register("roc_feather_2",
            () -> new FeatherItem(new Item.Properties().defaultDurability(180).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> DEKU_LEAF = ITEMS.register("deku_leaf",
            () -> new DekuLeafItem(new Item.Properties().durability(10).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> PARAGLIDER = ITEMS.register("glider",
            () -> new GliderItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> SILVER_SCALE = ITEMS.register("scale_silver",
            () -> new ScaleItem(new Item.Properties().defaultDurability(124).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> GOLDEN_SCALE = ITEMS.register("scale_gold",
            () -> new ScaleItem(new Item.Properties().defaultDurability(248).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ASCEND = ITEMS.register("ascend",
            () -> new AscendItem(new Item.Properties().rarity(Rarity.RARE)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // COMBAT RELATED

    public static final RegistryObject<Item> HAMMER = ITEMS.register("wooden_hammer",
            () -> new HammerItem(ModTiers.ZELDA, 6, -3f,
                    new Item.Properties().defaultDurability(260).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> MEGATON = ITEMS.register("megaton_hammer",
            () -> new MegatonItem(ModTiers.ZELDA, 8, -3f,
                    new Item.Properties().defaultDurability(520).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> DEKU_STICK = ITEMS.register("deku_stick",
            () -> new StickItem(new Item.Properties().stacksTo(64)));

    // TODO: DELETE THIS ONE
    public static final RegistryObject<Item> DEKU_STICK_LIT = ITEMS.register("deku_stick_lit",
            () -> new StickItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> BLUE_RING = ITEMS.register("blue_ring",
            () -> new BlueRingItem(new Item.Properties().defaultDurability(80).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> RED_RING = ITEMS.register("red_ring",
            () -> new RedRingItem(new Item.Properties().defaultDurability(80).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> PURPLE_RING = ITEMS.register("purple_ring",
            () -> new PurpleRingItem(new Item.Properties().defaultDurability(160).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> MITTS = ITEMS.register("digging_mitts",
            () -> new DiggingMittsItem(1,-2f,Tiers.STONE, BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item.Properties().defaultDurability(390).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> MOGMA_MITTS = ITEMS.register("mogma_mitts",
            () -> new MogmaMittsItem(2,-1.8f,Tiers.IRON, BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item.Properties().defaultDurability(780).rarity(Rarity.RARE)));


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // INSTRUMENTS

    public static final RegistryObject<Item> OCARINA = ITEMS.register("ocarina",
        () -> new OcarinaItem(new Item.Properties().rarity(Rarity.RARE).defaultDurability(250)));

    public static final RegistryObject<Item> FLUTE = ITEMS.register("flute",
            () -> new FluteItem(new Item.Properties().rarity(Rarity.RARE).defaultDurability(5)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GRAPPLING HOOKS

    public static final RegistryObject<Item> HOOKSHOT = ITEMS.register("hookshot",
            () -> new HookshotItem(new Item.Properties().defaultDurability(360).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> LONGSHOT = ITEMS.register("longshot",
            () -> new HookshotItem(new Item.Properties().defaultDurability(720).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> CLAWSHOT = ITEMS.register("clawshot",
            () -> new ClawshotItem(new Item.Properties().defaultDurability(360).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> CLAWSHOT_GODDESS = ITEMS.register("clawshot_goddess",
            () -> new ClawshotItem(new Item.Properties().defaultDurability(720).rarity(Rarity.RARE)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MISC ITEMS

    public static final RegistryObject<Item> LENS_OF_TRUTH = ITEMS.register("lens",
            () -> new LensItem(new Item.Properties().rarity(Rarity.UNCOMMON)));



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // BAGS
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


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SHIELDS

    public static final RegistryObject<Item> DEKU_SHIELD = ITEMS.register("deku_shield",
    () -> new WoodShieldItem(new Item.Properties().durability(168)));

    public static final RegistryObject<Item> HYLIAN_SHIELD = ITEMS.register("hylian_shield",
            () -> new ShieldItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(652)));

    public static final RegistryObject<Item> MIRROR_SHIELD = ITEMS.register("mirror_shield",
            () -> new MirrorShieldItem(new Item.Properties().rarity(Rarity.RARE).durability(800)));

    public static final RegistryObject<Item> BALANCED_MIRROR_SHIELD = ITEMS.register("balanced_mirror_shield",
            () -> new BalancedMirrorShieldItem(new Item.Properties().rarity(Rarity.EPIC).durability(1200)));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ARMOR

    // HATS
    public static final RegistryObject<ArmorItem> KOKIRI_HAT = ITEMS.register("kokiri_hat",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(200)));

    public static final RegistryObject<ArmorItem> ZORA_HAT = ITEMS.register("zora_hat",
            ()-> new ArmorItem(ModArmorMaterials.ZoraTunic, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(200)));

    public static final RegistryObject<ArmorItem> GORON_HAT = ITEMS.register("goron_hat",
            ()-> new ArmorItem(ModArmorMaterials.GoronTunic, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(200)));

    public static final RegistryObject<ArmorItem> CLASSIC_HAT = ITEMS.register("classic_hat",
            ()-> new ArmorItem(ModArmorMaterials.Classic, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(390)));

    public static final RegistryObject<ArmorItem> HYLIAN_HOOD = ITEMS.register("hylian_hood",
            ()-> new ArmorItem(ModArmorMaterials.Hylian, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(200)));

    public static final RegistryObject<ArmorItem> DARK_HAT = ITEMS.register("dark_hat",
            ()-> new ArmorItem(ModArmorMaterials.Dark, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(450)));

    public static final RegistryObject<ArmorItem> DARK_NUT_HELMET = ITEMS.register("darknut_helmet",
            ()-> new DarknutHelmet(ModArmorMaterials.DARK_NUT, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(450)));

    public static final RegistryObject<ArmorItem> DARK_KNIGHT_HELMET = ITEMS.register("dark_knight_helmet",
            ()-> new DarkKnightHelmet(ModArmorMaterials.DARK_KNIGHT, ArmorItem.Type.HELMET,
                    new Item.Properties().defaultDurability(450)));

    // TUNICS

    public static final RegistryObject<ArmorItem> KOKIRI_TUNIC = ITEMS.register("kokiri_tunic",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(240)));

    public static final RegistryObject<ArmorItem> GORON_TUNIC = ITEMS.register("goron_tunic",
            ()-> new GoronTunic(ModArmorMaterials.GoronTunic, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(240)));

    public static final RegistryObject<ArmorItem> ZORA_TUNIC = ITEMS.register("zora_tunic",
            ()-> new ZoraTunic(ModArmorMaterials.ZoraTunic, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(240)));

    public static final RegistryObject<ArmorItem> DARK_TUNIC = ITEMS.register("dark_tunic",
            ()-> new DarkArmor(ModArmorMaterials.Dark, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(666)));

    public static final RegistryObject<ArmorItem> CLASSIC_TUNIC = ITEMS.register("classic_tunic",
            ()-> new ArmorItem(ModArmorMaterials.Classic, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(650)));

    public static final RegistryObject<ArmorItem> ROC_CAPE = ITEMS.register("roc_cape",
            ()-> new RocCape(ModArmorMaterials.Roc,ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(380).rarity(Rarity.RARE)));

    // BOTW TUNICS

    public static final RegistryObject<ArmorItem> CHAMPION_TUNIC = ITEMS.register("champion_tunic",
            ()-> new ArmorItem(ModArmorMaterials.Champion, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(340)));

    public static final RegistryObject<ArmorItem> CHAMPIONS_LEATHERS = ITEMS.register("champions_leathers",
            ()-> new ChampionLeathers(ModArmorMaterials.LEATHERS, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(560).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<ArmorItem> ARCHAIC_TUNIC = ITEMS.register("archaic_tunic",
            ()-> new ArchaicTunic(ModArmorMaterials.Archaic, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().defaultDurability(240)));



    // PANTS

    public static final RegistryObject<ArmorItem> KOKIRI_PANTS = ITEMS.register("kokiri_pants",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().defaultDurability(200)));

    public static final RegistryObject<ArmorItem> HYLIAN_PANTS = ITEMS.register("hylian_pants",
            ()-> new ArmorItem(ModArmorMaterials.Champion, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().defaultDurability(300)));

    public static final RegistryObject<ArmorItem> DARK_PANTS = ITEMS.register("dark_pants",
            ()-> new ArmorItem(ModArmorMaterials.Dark, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().defaultDurability(580)));

    // BOOTS

    public static final RegistryObject<ArmorItem> KOKIRI_BOOTS = ITEMS.register("kokiri_boots",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri,ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(180)));

    public static final RegistryObject<ArmorItem> DARK_BOOTS = ITEMS.register("dark_boots",
            ()-> new ArmorItem(ModArmorMaterials.Dark, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(450)));

    public static final RegistryObject<HeavyBoots> HEAVY_BOOTS = ITEMS.register("heavy_boots",
            ()-> new HeavyBoots(ModArmorMaterials.HEAVY, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(250).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<HoverBoots> HOVER_BOOTS = ITEMS.register("hover_boots",
            ()-> new HoverBoots(ModArmorMaterials.HOVER, ArmorItem.Type.BOOTS,
                    new Item.Properties().defaultDurability(250).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<PegasusBoots> PEGASUS_BOOTS = ITEMS.register("pegasus_boots",
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // MASKS

    // TRANSFORMATION MASKS

    public static final RegistryObject<ArmorItem> FIERCE_DEITY_MASK = ITEMS.register("fierce_deity_mask",
            ()-> new FierceDeityMask(ModArmorMaterials.FIERCE, ArmorItem.Type.HELMET,
                    new Item.Properties().rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<ArmorItem> MAJORA_MASK = ITEMS.register("majoras_mask",
            ()-> new MajorasMask(ModArmorMaterials.MAJORA, ArmorItem.Type.HELMET,
                    new Item.Properties().rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<ArmorItem> DEKU_MASK = regMask("deku", ModArmorMaterials.DEKU, DekuMask::new, 250, Rarity.UNCOMMON);

    public static final RegistryObject<ArmorItem> GORON_MASK = regMask("goron", ModArmorMaterials.GORON, GoronMask::new, 250, Rarity.UNCOMMON);

    public static final RegistryObject<ArmorItem> ZORA_MASK = regMask("zora", ModArmorMaterials.ZORA, ZoraMask::new, 250, Rarity.UNCOMMON);

    public static final RegistryObject<ArmorItem> GIANT_MASK = regMask("giant", ModArmorMaterials.GIANT, GiantMask::new);

    // REGULAR MASKS

    public static final RegistryObject<ArmorItem> STONE_MASK = regMask("stone", ModArmorMaterials.STONE, StoneMask::new);

    public static final RegistryObject<ArmorItem> BUNNY_HOOD = regMask("bunny", ModArmorMaterials.BUNNY, BunnyHood::new);

    public static final RegistryObject<ArmorItem> NIGHT_MASK = regMask("night", ModArmorMaterials.NIGHT, NightMask::new);

    public static final RegistryObject<ArmorItem> BLAST_MASK = regMask("blast", ModArmorMaterials.BLAST, BlastMask::new);

    public static final RegistryObject<ArmorItem> SCENT_MASK = regMask("scent", ModArmorMaterials.SCENT, ScentMask::new, 145);

    public static final RegistryObject<ArmorItem> TRUTH_MASK = regMask("truth", ModArmorMaterials.TRUTH, TruthMask::new);

    public static final RegistryObject<ArmorItem> KEATON_MASK = regMask("keaton", ModArmorMaterials.KEATON, KeatonMask::new);

    public static final RegistryObject<ArmorItem> TROUPE_MASK = regMask("troupe", ModArmorMaterials.TROUPE, TroupeMask::new, 455);

    public static final RegistryObject<ArmorItem> ROMANI_MASK = regMask("romani", ModArmorMaterials.ROMANI, RomaniMask::new);

    public static final RegistryObject<ArmorItem> GERO_MASK = regMask("gero", ModArmorMaterials.GERO, GeroMask::new);

    public static final RegistryObject<ArmorItem> HAWKEYE_MASK = regMask("hawkeye", ModArmorMaterials.HAWK);

    public static final RegistryObject<ArmorItem> COUPLES_MASK = regMask("couples", ModArmorMaterials.COUPLES, CouplesMask::new);

    public static final RegistryObject<ArmorItem> GIBDO_MASK = regMask("gibdo", ModArmorMaterials.GIBDO);

    public static final RegistryObject<ArmorItem> FAIRY_MASK = regMask("fairy", ModArmorMaterials.FAIRY, FairyMask::new);

    public static final RegistryObject<ArmorItem> KAFEI_MASK = regMask("kafei", ModArmorMaterials.KAFEI);

    public static final RegistryObject<ArmorItem> BREMEN_MASK = regMask("bremen", ModArmorMaterials.BREMEN, BremenMask::new);

    public static final RegistryObject<ArmorItem> CAPTAIN_MASK = regMask("captain", ModArmorMaterials.CAPTAIN);

    public static final RegistryObject<ArmorItem> KAMARO_MASK = regMask("kamaro", ModArmorMaterials.KAMARO, KamaroMask::new);

    public static final RegistryObject<ArmorItem> GARO_MASK = regMask("garo", ModArmorMaterials.GARO, GaroMask::new);

    public static final RegistryObject<ArmorItem> POSTMAN_MASK = regMask("postman", ModArmorMaterials.POSTMAN);

    public static final RegistryObject<ArmorItem> SPOOKY_MASK = regMask("spooky", ModArmorMaterials.SPOOKY);

    public static final RegistryObject<ArmorItem> SKULL_MASK = regMask("skull", ModArmorMaterials.SKULL);

    public static final RegistryObject<ArmorItem> GERUDO_MASK = regMask("gerudo", ModArmorMaterials.GERUDO);

    public static final RegistryObject<ArmorItem> KOROK_MASK = regMask("korok", ModArmorMaterials.KOROK, 382, "igreen");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // SPAWN EGGS

    // HOSTILE MOBS

    public static final RegistryObject<Item> DEKU_SPAWN_EGG = ITEMS.register("deku_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DEKU, 8408862, 778264, new Item.Properties()));

    public static final RegistryObject<Item> DEKU_MAD_SPAWN_EGG = ITEMS.register("deku_mad_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DEKU_MAD, 8408862, 16273174, new Item.Properties()));

    public static final RegistryObject<Item> DARK_NUT_SPAWN_EGG = ITEMS.register("dark_nut_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DARK_NUT, 12566463, 14637912, new Item.Properties()));

    public static final RegistryObject<Item> DARK_KNIGHT_SPAWN_EGG = ITEMS.register("dark_knight_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DARK_KNIGHT, 6176839, 2561279, new Item.Properties()));

    public static final RegistryObject<Item> SKULLTULA_SPAWN_EGG = ITEMS.register("skulltula_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SKULLTULA, 16774707, 16777215, new Item.Properties()));

    public static final RegistryObject<Item> IRON_KNUCKLE_SPAWN_EGG = ITEMS.register("iron_knuckle_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.IRON_KNUCKLE, 6176839, 2561279, new Item.Properties()));

    // KEESE

    public static final RegistryObject<Item> KEESE_SPAWN_EGG = ITEMS.register("keese_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.KEESE, 7493436, 16252718, new Item.Properties()));

    public static final RegistryObject<Item> FIRE_KEESE_SPAWN_EGG = ITEMS.register("fire_keese_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.FIRE_KEESE, 16081152, 16067072, new Item.Properties()));

    public static final RegistryObject<Item> ICE_KEESE_SPAWN_EGG = ITEMS.register("ice_keese_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.ICE_KEESE, 1765631, 14364415, new Item.Properties()));

    public static final RegistryObject<Item> ELECTRIC_KEESE_SPAWN_EGG = ITEMS.register("electric_keese_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.ELECTRIC_KEESE, 16769577, 4718565, new Item.Properties()));

    // CHUS

    public static final RegistryObject<Item> CHU_SPAWN_EGG =  regSpawnEgg("chuchu", ModEntityTypes.CHUCHU, 0x00dcf6, 0x00faf3);
    public static final RegistryObject<Item> FIRE_CHU_SPAWN_EGG = regSpawnEgg("fire_chuchu", ModEntityTypes.CHUCHU_FIRE, 0xff3e33, 0xe31c33);
    public static final RegistryObject<Item> ICE_CHU_SPAWN_EGG = regSpawnEgg("ice_chuchu", ModEntityTypes.CHUCHU_ICE, 0xC7feff, 0xffffff);
    public static final RegistryObject<Item> ELECTRIC_CHU_SPAWN_EGG = regSpawnEgg("electric_chuchu", ModEntityTypes.CHUCHU_ELECTRIC, 0xcfff1a, 0x60f514);

    // BOKOBLINS
    public static final RegistryObject<Item> BOKOBLIN_SPAWN_EGG = ITEMS.register("red_bokoblin_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.RED_BOKOBLIN, 7493436, 16252718, new Item.Properties()));

    // FRIENDLY MOBS

    public static final RegistryObject<Item> KOROK_SPAWN_EGG = regSpawnEgg("korok", ModEntityTypes.KOROK, 0xd79a42, 0x44c723);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void register (IEventBus eventbus) {
        ITEMS.register(eventbus);
    }
}
