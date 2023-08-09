package com.kamth.zeldamod.item;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.armors.*;
import com.kamth.zeldamod.item.custom.ModArmorMaterials;
import com.kamth.zeldamod.item.custom.ModFoods;
import com.kamth.zeldamod.item.items.*;
import com.kamth.zeldamod.item.masks.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZeldaMod.MOD_ID);

    //These are generic items
    public static final RegistryObject<Item> RED_EMERALD = ITEMS.register("red_emerald",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> BLUE_EMERALD = ITEMS.register("blue_emerald",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> MASTER_ORE = ITEMS.register("master_ore",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> GOLD_DUST = ITEMS.register("gold_dust",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> MASTER_SHARD = ITEMS.register("master_shard",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> MASTER_ORE2 = ITEMS.register("refined_master_ore",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).tab(ModCreativeModeTab.ZELDA_TAB)));


    //These are potions or consumables
    public static final RegistryObject<Item> PUMPKIN_SOUP = ITEMS.register("pumpkin_soup",
            () -> new DrinkItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_FOODSTUFFS).stacksTo(16).food(ModFoods.PUMPKIN_SOUP)));
    public static final RegistryObject<Item> STAMINA = ITEMS.register("stamina_potion",
            () -> new DrinkItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_FOODSTUFFS).stacksTo(1).food(ModFoods.STAMINA)));
    public static final RegistryObject<Item> HEART_POTION = ITEMS.register("heart_potion",
            () -> new DrinkItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_FOODSTUFFS).stacksTo(1).food(ModFoods.HEART)));
    public static final RegistryObject<Item> MILK_BOTTLE1 = ITEMS.register("milk_bottle1",
            () -> new MilkBottleItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_FOODSTUFFS).stacksTo(1).food(ModFoods.MILK_BOTTLE)));
    public static final RegistryObject<Item> MILK_BOTTLE2 = ITEMS.register("milk_bottle2",
            () -> new MilkHalfItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_FOODSTUFFS).stacksTo(1).food(ModFoods.MILK_BOTTLE)));
    public static final RegistryObject<Item> SHIELD_POTION = ITEMS.register("shield_potion",
            () -> new DrinkItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_FOODSTUFFS).stacksTo(1).food(ModFoods.SHIELD)));
    public static final RegistryObject<Item> BLUE_POTION = ITEMS.register("blue_potion",
            () -> new BluePotionItem(new Item.Properties().rarity(Rarity.RARE).tab(ModCreativeModeTab.ZELDA_FOODSTUFFS).stacksTo(1).food(ModFoods.BLUE)));
    public static final RegistryObject<Item> MILK_MAGIC = ITEMS.register("magic_milk",
            () -> new DrinkItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_FOODSTUFFS).rarity(Rarity.RARE).stacksTo(1).food(ModFoods.MILK_MAGIC)));


    //This is where Tools start
    public static final RegistryObject<Item> KOKIRI_SWORD = ITEMS.register("kokiri_sword",
            () -> new SwordItem(ModTiers.ZELDA, 1, -2.4f,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> RAZOR_SWORD = ITEMS.register("razor_sword",
            () -> new SwordItem(ModTiers.RAZOR, 2, -1.4f,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> GILDED_SWORD = ITEMS.register("gilded_sword",
            () -> new SwordItem(ModTiers.GILDED, 3, -2.4f,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> MASTER_SWORD = ITEMS.register("master_sword",
            () -> new MasterSwordItem(ModTiers.ZELDAU, 4, -2.4f,
                    new Item.Properties().rarity(Rarity.RARE).fireResistant().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> BIGGORON_SWORD = ITEMS.register("biggoron_sword",
            () -> new SwordItem(ModTiers.ZELDAU, 7, -3.3f,
                    new Item.Properties().fireResistant().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> BIGGORON_KNIFE = ITEMS.register("biggoron_knife",
            () -> new SwordItem(ModTiers.ZELDAU, 7, -3.3f,
                    new Item.Properties().defaultDurability(10).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> MASTER_SWORD2 = ITEMS.register("master_sword2",
            () -> new MasterSwordItem(ModTiers.ZELDAU, 5, -2.4f,
                    new Item.Properties().fireResistant().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> MASTER_SWORD3 = ITEMS.register("master_sword3",
            () -> new MasterSwordItem(ModTiers.ZELDAU, 6, -2.4f,
                    new Item.Properties().fireResistant().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> SLINGSHOT = ITEMS.register("slingshot",
            () -> new SlingshotItem(new Item.Properties().defaultDurability(284).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> SCATTERSHOT = ITEMS.register("scattershot",
            () -> new ScattershotItem(new Item.Properties().defaultDurability(488).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> ROC_FEATHER = ITEMS.register("roc_feather",
            () -> new FeatherItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> SAND_ROD = ITEMS.register("sand_rod",
            () -> new SandWandItem(new Item.Properties().durability(96).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> BOMB = ITEMS.register("bomb",
            () -> new BombItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> WATER_BOMB = ITEMS.register("water_bomb",
            () -> new WaterBombItem(new Item.Properties().stacksTo(8).rarity(Rarity.UNCOMMON).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> HERO_BOW = ITEMS.register("hero_bow",
            () -> new HeroBowItem(new Item.Properties().defaultDurability(550).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> FIRE_ARROW = ITEMS.register("fire_arrow",
            () -> new FireArrowItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> ICE_ARROW = ITEMS.register("ice_arrow",
            () -> new IceArrowItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> LIGHT_ARROW = ITEMS.register("light_arrow",
            () -> new LightArrowItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> BOMB_ARROW = ITEMS.register("bomb_arrow",
            () -> new BombArrowItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> LIGHTNING_ARROW = ITEMS.register("lightning_arrow",
            () -> new LightningArrowItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> ANCIENT_ARROW = ITEMS.register("ancient_arrow",
            () -> new AncientArrowItem(new Item.Properties().rarity(Rarity.EPIC).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> DEKU_NUT = ITEMS.register("deku_nut",
            () -> new DekuNutItem(new Item.Properties().stacksTo(16).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> DEKU_LEAF = ITEMS.register("deku_leaf",
            () -> new LeafItem(new Item.Properties().durability(10).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> FIRE_ROD = ITEMS.register("fire_rod",
            () -> new FireRodItem(new Item.Properties().durability(218).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> ICE_ROD = ITEMS.register("ice_rod",
            () -> new IceRodItem(new Item.Properties().durability(218).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> TORNADO_ROD = ITEMS.register("tornado_rod",
            () -> new TornadoRodItem(new Item.Properties().durability(218).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> BOOMERANG = ITEMS.register("boomerang",
            () -> new BoomerangItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> MAGIC_BOOMERANG = ITEMS.register("magic_boomerang",
            () -> new MagicBoomerangItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> HAMMER = ITEMS.register("wooden_hammer",
            () -> new HammerItem(ModTiers.ZELDA, 6, -3f,
                    new Item.Properties().defaultDurability(260).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> MITTS = ITEMS.register("digging_mitts",
            () -> new MittsItem(1,-2f,Tiers.STONE, BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item.Properties().defaultDurability(390).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> MOGMA_MITTS = ITEMS.register("mogma_mitts",
            () -> new MittsItem2(2,-1.8f,Tiers.IRON, BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item.Properties().defaultDurability(780).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> GLIDER = ITEMS.register("glider",
            () -> new GliderItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.ZELDA_TAB)));
public static final RegistryObject<Item> DEKU_SHIELD = ITEMS.register("deku_shield",
    () -> new ShieldItem(new Item.Properties().durability(168).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> HYLIAN_SHIELD = ITEMS.register("hylian_shield",
            () -> new ShieldItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(652).tab(ModCreativeModeTab.ZELDA_TAB)));

    //This is where armors are located keep them together
    //Boots tier
    public static final RegistryObject<HeavyBoots> HEAVY_BOOTS = ITEMS.register("heavyboots",
            ()-> new HeavyBoots(ModArmorMaterials.ZELDAH, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<HoverBoots> HOVER_BOOTS = ITEMS.register("hoverboots",
            ()-> new HoverBoots(ModArmorMaterials.HOVER, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<PegasusBoots> PEGASUS_BOOTS = ITEMS.register("pegasusboots",
            ()-> new PegasusBoots(ModArmorMaterials.PEGASUS, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Flippers> FLIPPERS = ITEMS.register("flippers",
            ()-> new Flippers(ModArmorMaterials.ZoraTunic, EquipmentSlot.FEET,
                    new Item.Properties().defaultDurability(195).tab(ModCreativeModeTab.ZELDA_TAB)));





    //Masks

    public static final RegistryObject<ArmorItem> DEKU_MASK = ITEMS.register("dekumask",
            ()-> new DekuMask(ModArmorMaterials.DEKU, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_MASKS)));
    public static final RegistryObject<ArmorItem> GORON_MASK = ITEMS.register("goronmask",
            ()-> new GoronMask(ModArmorMaterials.GORON, EquipmentSlot.HEAD,
                    new Item.Properties().fireResistant().tab(ModCreativeModeTab.ZELDA_MASKS)));
    public static final RegistryObject<ArmorItem> ZORA_MASK = ITEMS.register("zoramask",
            ()-> new ZoraMask(ModArmorMaterials.ZORA, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_MASKS)));
    public static final RegistryObject<ArmorItem> STONE_MASK = ITEMS.register("stonemask",
            ()-> new StoneMask(ModArmorMaterials.STONE, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_MASKS)));
    public static final RegistryObject<ArmorItem> BUNNY_MASK = ITEMS.register("bunnymask",
            ()-> new BunnyMask(ModArmorMaterials.BUNNY, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_MASKS)));
    public static final RegistryObject<ArmorItem> NIGHT_MASK = ITEMS.register("nightmask",
            ()-> new NightMask(ModArmorMaterials.NIGHT, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_MASKS)));
    public static final RegistryObject<ArmorItem> BLAST_MASK = ITEMS.register("blastmask",
            ()-> new BlastMask(ModArmorMaterials.BLAST, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_MASKS)));
    public static final RegistryObject<ArmorItem> FIERCE_MASK = ITEMS.register("fiercemask",
            ()-> new FierceMask(ModArmorMaterials.FIERCE, EquipmentSlot.HEAD,
                    new Item.Properties().rarity(Rarity.EPIC).tab(ModCreativeModeTab.ZELDA_MASKS).fireResistant()));
    public static final RegistryObject<ArmorItem> MAJORA_MASK = ITEMS.register("majoramask",
            ()-> new MajoraMask(ModArmorMaterials.MAJORA, EquipmentSlot.HEAD,
                    new Item.Properties().rarity(Rarity.EPIC).tab(ModCreativeModeTab.ZELDA_MASKS).fireResistant()));

   //Tunics
    public static final RegistryObject<ArmorItem> KOKIRI_TUNIC = ITEMS.register("kokiritunic",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri, EquipmentSlot.CHEST,
                    new Item.Properties().defaultDurability(240).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> GORON_TUNIC = ITEMS.register("gorontunic",
            ()-> new GoronTunic(ModArmorMaterials.GoronTunic, EquipmentSlot.CHEST,
                    new Item.Properties().defaultDurability(240).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> ZORA_TUNIC = ITEMS.register("zoratunic",
            ()-> new ZoraTunic(ModArmorMaterials.ZoraTunic, EquipmentSlot.CHEST,
                    new Item.Properties().defaultDurability(240).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> KOKIRI_PANTS = ITEMS.register("kokiripants",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri, EquipmentSlot.LEGS,
                    new Item.Properties().defaultDurability(200).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> KOKIRI_HAT = ITEMS.register("kokirihat",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri, EquipmentSlot.HEAD,
                    new Item.Properties().defaultDurability(200).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> ZORA_HAT = ITEMS.register("zorahelmet",
            ()-> new ArmorItem(ModArmorMaterials.ZoraTunic, EquipmentSlot.HEAD,
                    new Item.Properties().defaultDurability(200).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> GORON_HAT = ITEMS.register("goronhelmet",
            ()-> new ArmorItem(ModArmorMaterials.GoronTunic, EquipmentSlot.HEAD,
                    new Item.Properties().defaultDurability(200).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> KOKIRI_BOOTS = ITEMS.register("kokiriboots",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri, EquipmentSlot.FEET,
                    new Item.Properties().defaultDurability(65).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> ROC_CAPE = ITEMS.register("roc_cape",
            ()-> new RocCape(ModArmorMaterials.Roc, EquipmentSlot.CHEST,
                    new Item.Properties().defaultDurability(380).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> CHAMPION_TUNIC = ITEMS.register("champion_tunic",
            ()-> new ArmorItem(ModArmorMaterials.Champion, EquipmentSlot.CHEST,
                    new Item.Properties().defaultDurability(340).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> CHAMPIONS_TUNIC = ITEMS.register("champions_tunic",
            ()-> new ArmorItem(ModArmorMaterials.Champions, EquipmentSlot.CHEST,
                    new Item.Properties().defaultDurability(560).tab(ModCreativeModeTab.ZELDA_TAB)));



        public static void register (IEventBus eventbus) {
            ITEMS.register(eventbus);

        }

}
