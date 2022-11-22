package com.kamth.zeldamod.item;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.custom.HeavyBoots;
import com.kamth.zeldamod.item.custom.ModArmorMaterials;
import com.kamth.zeldamod.item.custom.armors.GoronTunic;
import com.kamth.zeldamod.item.custom.armors.ZoraTunic;
import com.kamth.zeldamod.item.custom.masks.*;
import com.kamth.zeldamod.item.custom.util.BoomerangItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZeldaMod.MOD_ID);


    public static final RegistryObject<Item> RED_EMERALD = ITEMS.register("red_emerald",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> BLUE_EMERALD = ITEMS.register("blue_emerald",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));

//This is where Tools start
    public static final RegistryObject<Item> KOKIRI_SWORD = ITEMS.register("kokiri_sword",
            () -> new SwordItem(ModTiers.ZELDA, 2, -2.4f,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));

    public static final RegistryObject<Item> RAZOR_SWORD = ITEMS.register("razor_sword",
            () -> new SwordItem(ModTiers.RAZOR, 2, -1.4f,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));

    public static final RegistryObject<Item> MASTER_SWORD = ITEMS.register("master_sword",
            () -> new SwordItem(ModTiers.ZELDAU, 4, -2.4f,
                    new Item.Properties().fireResistant().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> BIGGORON_SWORD = ITEMS.register("biggoron_sword",
            () -> new SwordItem(ModTiers.ZELDAU, 7, -3.0f,
                    new Item.Properties().fireResistant().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<Item> BIGGORON_KNIFE = ITEMS.register("biggoron_knife",
            () -> new SwordItem(ModTiers.ZELDAU, 7, -3.3f,
                    new Item.Properties().defaultDurability(10).tab(ModCreativeModeTab.ZELDA_TAB)));

public static final RegistryObject<Item> DEKU_SHIELD = ITEMS.register("deku_shield",
    () -> new ShieldItem(
         new Item.Properties().durability(80).tab(ModCreativeModeTab.ZELDA_TAB)));


    //This is where armors are located keep them together
    //Boots tier
    public static final RegistryObject<HeavyBoots> HEAVY_BOOTS = ITEMS.register("heavyboots",
            ()-> new HeavyBoots(ModArmorMaterials.ZELDAH, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));

   //Masks
    public static final RegistryObject<ArmorItem> ZORA_MASK = ITEMS.register("zoramask",
            ()-> new ZoraMask(ModArmorMaterials.ZORA, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_MASKS)));
    public static final RegistryObject<ArmorItem> DEKU_MASK = ITEMS.register("dekumask",
            ()-> new DekuMask(ModArmorMaterials.DEKU, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_MASKS)));
    public static final RegistryObject<ArmorItem> GORON_MASK = ITEMS.register("goronmask",
            ()-> new GoronMask(ModArmorMaterials.GORON, EquipmentSlot.HEAD,
                    new Item.Properties().fireResistant().tab(ModCreativeModeTab.ZELDA_MASKS)));
    public static final RegistryObject<ArmorItem> STONE_MASK = ITEMS.register("stonemask",
            ()-> new StoneMask(ModArmorMaterials.STONE, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_MASKS)));
    public static final RegistryObject<ArmorItem> BUNNY_MASK = ITEMS.register("bunnymask",
            ()-> new BunnyMask(ModArmorMaterials.BUNNY, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_MASKS)));

   //Tunics
    public static final RegistryObject<ArmorItem> KOKIRI_TUNIC = ITEMS.register("kokiritunic",
            ()-> new ArmorItem(ModArmorMaterials.Kokiri, EquipmentSlot.CHEST,
                    new Item.Properties().defaultDurability(85).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> GORON_TUNIC = ITEMS.register("gorontunic",
            ()-> new GoronTunic(ModArmorMaterials.GoronTunic, EquipmentSlot.CHEST,
                    new Item.Properties().defaultDurability(85).tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> ZORA_TUNIC = ITEMS.register("zoratunic",
            ()-> new ZoraTunic(ModArmorMaterials.ZoraTunic, EquipmentSlot.CHEST,
                    new Item.Properties().defaultDurability(85).tab(ModCreativeModeTab.ZELDA_TAB)));
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
