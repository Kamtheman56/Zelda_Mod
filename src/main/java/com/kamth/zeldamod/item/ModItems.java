package com.kamth.zeldamod.item;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.armors.HeavyBoots;
import com.kamth.zeldamod.item.armors.HoverBoots;
import com.kamth.zeldamod.item.custom.ModArmorMaterials;
import com.kamth.zeldamod.item.armors.GoronTunic;
import com.kamth.zeldamod.item.armors.ZoraTunic;
import com.kamth.zeldamod.item.custom.ModFoods;
import com.kamth.zeldamod.item.items.*;
import com.kamth.zeldamod.item.masks.*;
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
            () -> new SlingshotItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> MASTER_ORE = ITEMS.register("master_ore",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> GOLD_DUST = ITEMS.register("gold_dust",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.ZELDA_TAB)));


    //These are potions or consumables
    public static final RegistryObject<Item> PUMPKIN_SOUP = ITEMS.register("pumpkin_soup",
            () -> new DrinkItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB).stacksTo(16).food(ModFoods.PUMPKIN_SOUP)));
    public static final RegistryObject<Item> STAMINA = ITEMS.register("stamina_potion",
            () -> new DrinkItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB).stacksTo(1).food(ModFoods.STAMINA)));
    public static final RegistryObject<Item> HEART_POTION = ITEMS.register("heart_potion",
            () -> new DrinkItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB).stacksTo(1).food(ModFoods.HEART)));
    public static final RegistryObject<Item> MILK_BOTTLE1 = ITEMS.register("milk_bottle1",
            () -> new MilkBottleItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB).stacksTo(1).food(ModFoods.MILK_BOTTLE)));
    public static final RegistryObject<Item> MILK_BOTTLE2 = ITEMS.register("milk_bottle2",
            () -> new MilkHalfItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB).stacksTo(1).food(ModFoods.MILK_BOTTLE)));
    public static final RegistryObject<Item> SHIELD_POTION = ITEMS.register("shield_potion",
            () -> new DrinkItem(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB).stacksTo(1).food(ModFoods.SHIELD)));


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
                    new Item.Properties().fireResistant().tab(ModCreativeModeTab.ZELDA_TAB)));
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
            () -> new SwordItem(ModTiers.ZELDAU, 6, -2.4f,
                    new Item.Properties().fireResistant().tab(ModCreativeModeTab.ZELDA_TAB)));
public static final RegistryObject<Item> DEKU_SHIELD = ITEMS.register("deku_shield",
    () -> new ShieldItem(
         new Item.Properties().durability(168).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> HYLIAN_SHIELD = ITEMS.register("hylian_shield",
            () -> new ShieldItem(
                    new Item.Properties().durability(652).tab(ModCreativeModeTab.ZELDA_TAB)));

    //This is where armors are located keep them together
    //Boots tier
    public static final RegistryObject<HeavyBoots> HEAVY_BOOTS = ITEMS.register("heavyboots",
            ()-> new HeavyBoots(ModArmorMaterials.ZELDAH, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<HoverBoots> HOVER_BOOTS = ITEMS.register("hoverboots",
            ()-> new HoverBoots(ModArmorMaterials.ZELDAH, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));




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
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_MASKS).fireResistant()));

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



        public static void register (IEventBus eventbus) {
            ITEMS.register(eventbus);

        }

}
