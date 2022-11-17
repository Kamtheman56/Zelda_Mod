package com.kamth.zeldamod.item;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.custom.HeavyBoots;
import com.kamth.zeldamod.item.custom.ModArmorMaterials;
import com.kamth.zeldamod.item.custom.masks.ZoraMask;
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
public static final RegistryObject<Item> DEKU_SHIELD = ITEMS.register("deku_shield",
    () -> new ShieldItem(
         new Item.Properties().durability(80).tab(ModCreativeModeTab.ZELDA_TAB)));


    //This is where armors are located keep them together
    public static final RegistryObject<HeavyBoots> HEAVY_BOOTS = ITEMS.register("heavyboots",
            ()-> new HeavyBoots(ModArmorMaterials.ZELDAH, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
    public static final RegistryObject<ArmorItem> ZORA_MASK = ITEMS.register("zoramask",
            ()-> new ZoraMask(ModArmorMaterials.ZORA, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ZELDA_TAB)));
        public static void register (IEventBus eventbus) {
            ITEMS.register(eventbus);

        }

}
