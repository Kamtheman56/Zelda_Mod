package com.kamth.zeldamod.item;

import com.kamth.zeldamod.custom.ModArmorMaterials;
import com.kamth.zeldamod.item.masks.TooltipMaskItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ItemRegistrationHelper {

    // Tooltip Masks

    public static RegistryObject<ArmorItem> regMask(String name, ArmorMaterial material, int damage, String style) {
        return ZeldaItems.ITEMS.register(name + "_mask", () -> new TooltipMaskItem(material, ArmorItem.Type.HELMET,
                new Item.Properties().defaultDurability(damage), style));
    }

    public static RegistryObject<ArmorItem> regMask(String name, ArmorMaterial material) {
        return regMask(name, material,165, "iyellow");
    }

    // Other Masks

    public static RegistryObject<ArmorItem> regMask(String name, ModArmorMaterials material, TriFunction<ArmorMaterial, ArmorItem.Type, Item.Properties, ArmorItem> constructor, int damage, Rarity rarity) {
        return ZeldaItems.ITEMS.register(name + "_mask", () -> constructor.apply(material, ArmorItem.Type.HELMET, new Item.Properties().defaultDurability(damage).rarity(rarity)));
    }

    public static RegistryObject<ArmorItem> regMask(String name, ModArmorMaterials material, TriFunction<ArmorMaterial, ArmorItem.Type, Item.Properties, ArmorItem> constructor, int damage) {
        return regMask(name, material, constructor, damage, Rarity.COMMON);
    }

    public static RegistryObject<ArmorItem> regMask(String name, ModArmorMaterials material, TriFunction<ArmorMaterial, ArmorItem.Type, Item.Properties, ArmorItem> constructor) {
        return regMask(name, material, constructor, 165, Rarity.COMMON);
    }

    // Spawn Eggs

    public static RegistryObject<Item> regSpawnEgg(String name, Supplier<? extends EntityType<? extends Mob>> entity, int colora, int colorb) {
        return ZeldaItems.ITEMS.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entity, colora, colorb, new Item.Properties()));
    }
}
