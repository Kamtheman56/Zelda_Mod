package com.kamth.zeldamod.item;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.food.ModFoods;
import com.kamth.zeldamod.item.items.MovementItems.TestItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;

public class ZeldaItems {

    public static final DeferredRegister.Items ZELDA_ITEMS = DeferredRegister.createItems(ZeldaMod.MOD_ID);


    // MISC ITEMS
    // Emeralds
    public static final DeferredItem<Item> BLUE_EMERALD = ZELDA_ITEMS.registerItem("blue_emerald",
             properties -> new TestItem(properties.durability(10)));
    public static final DeferredItem<Item> RED_EMERALD = ZELDA_ITEMS.registerSimpleItem("red_emerald");
    public static final DeferredItem<Item> PURPLE_EMERALD = ZELDA_ITEMS.registerSimpleItem("purple_emerald");
    public static final DeferredItem<Item> SILVER_EMERALD = ZELDA_ITEMS.registerSimpleItem("silver_emerald");
    public static final DeferredItem<Item> GOLD_EMERALD = ZELDA_ITEMS.registerSimpleItem("gold_emerald");

    public static final DeferredItem<Item> MASTER_SHARD = ZELDA_ITEMS.registerSimpleItem("master_shard");
    public static final DeferredItem<Item> MASTER_ORE = ZELDA_ITEMS.registerSimpleItem("master_ore");
    public static final DeferredItem<Item> REFINED_MASTER_ORE = ZELDA_ITEMS.registerSimpleItem("refined_master_ore");

    public static final DeferredItem<Item> MASTER_UPGRADE_SMITHING_TEMPLATE = ZELDA_ITEMS.registerSimpleItem("master_upgrade_smithing_template");



    // Food
    public static final DeferredItem<Item> SUPER_MUSHROOM = ZELDA_ITEMS.registerItem("super_mushroom",
            properties -> new Item(properties.food(ModFoods.SUPER_MUSHROOM, ModFoods.SUPER_MUSHROOM_CONSUMABLE)){
                @Override
                public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                    if (Minecraft.getInstance().hasShiftDown()){
                        builder.accept(Component.translatable("tooltip.zeldamod.super_mushroom.tooltip"));
                    }
                    super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> MINI_MUSHROOM = ZELDA_ITEMS.registerItem("mini_mushroom",
            properties -> new Item(properties.food(ModFoods.SUPER_MUSHROOM, ModFoods.MINI_MUSHROOM_CONSUMABLE)){});
    public static final DeferredItem<Item> MEGA_MUSHROOM = ZELDA_ITEMS.registerItem("mega_mushroom",
            properties -> new Item(properties.food(ModFoods.SUPER_MUSHROOM, ModFoods.MEGA_MUSHROOM_CONSUMABLE)){});


    // Melee Weapons

    // Regular Swords

    public static final DeferredItem<Item> KOKIRI_SWORD_OOT = ZELDA_ITEMS.registerItem("kokiri_sword_oot",
            properties -> new Item(properties.sword(ToolMaterial.STONE,3,-2.4f).durability(118).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> KOKIRI_SWORD_MM = ZELDA_ITEMS.registerItem("kokiri_sword_mm",
            properties -> new Item(properties.sword(ToolMaterial.STONE,3,-2.4f).durability(118).rarity(Rarity.UNCOMMON)));





    public static final DeferredItem<Item> MASTER_SWORD= ZELDA_ITEMS.registerItem("master_sword",
            properties -> new Item(properties.sword(ModToolTiers.MASTER,3,-2.4f).durability(1000)));






    // Armors

    // Tunics

    public static final DeferredItem<Item> KOKIRI_HAT = ZELDA_ITEMS.registerItem("kokiri_hat",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.KOKIRI_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final DeferredItem<Item> KOKIRI_TUNIC = ZELDA_ITEMS.registerItem("kokiri_tunic",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.KOKIRI_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> KOKIRI_LEGGINGS = ZELDA_ITEMS.registerItem("kokiri_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.KOKIRI_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> KOKIRI_BOOTS = ZELDA_ITEMS.registerItem("kokiri_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.KOKIRI_ARMOR_MATERIAL, ArmorType.BOOTS)));

    // Masks

    // Transformation Masks
    public static final DeferredItem<Item> DEKU_MASK = ZELDA_ITEMS.registerItem("deku_mask",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.DEKU_MASK_MATERIAL, ArmorType.HELMET)));

    public static final DeferredItem<Item> GIANTS_MASK = ZELDA_ITEMS.registerItem("giants_mask",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.GIANTS_MASK_MATERIAL, ArmorType.HELMET)));



    public static ResourceKey<Item> getRK (Item item){
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    public static void register(IEventBus eventBus){
        ZELDA_ITEMS.register(eventBus);
    }



}
