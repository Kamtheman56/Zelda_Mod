package com.kamth.zeldamod.item;

import com.google.common.collect.Maps;
import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public class ModArmorMaterials {

    public static final ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));


    // Armor Resource Keys
    public static final ResourceKey<EquipmentAsset> KOKIRI_ARMOR_KEY = ResourceKey.create(ROOT_ID, Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID, "kokiri_armor"));


    // Mask Resource Keys
    public static final ResourceKey<EquipmentAsset> DEKU_MASK_KEY = ResourceKey.create(ROOT_ID, Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID, "deku_mask"));
    public static final ResourceKey<EquipmentAsset> GIANTS_MASK_KEY = ResourceKey.create(ROOT_ID, Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID, "giants_mask"));





// Armors
    public static final ArmorMaterial KOKIRI_ARMOR_MATERIAL = new ArmorMaterial(1200,
            makeDefense(2,3,3,2, 4),10,  SoundEvents.ARMOR_EQUIP_LEATHER,0, 0, ItemTags.REPAIRS_LEATHER_ARMOR, KOKIRI_ARMOR_KEY);

// Masks

    // Transformation Masks
    public static final ArmorMaterial DEKU_MASK_MATERIAL = new ArmorMaterial(1200,
            makeDefense(2,3,3,0, 4),10,  SoundEvents.ARMOR_EQUIP_LEATHER,0, 0, ItemTags.REPAIRS_LEATHER_ARMOR, DEKU_MASK_KEY);

    public static final ArmorMaterial GIANTS_MASK_MATERIAL = new ArmorMaterial(1200,
            makeDefense(2,3,3,0, 4),10,  SoundEvents.ARMOR_EQUIP_NETHERITE,0, 0, ItemTags.REPAIRS_IRON_ARMOR, GIANTS_MASK_KEY);







    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(
                Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body)
        );
    }
}
