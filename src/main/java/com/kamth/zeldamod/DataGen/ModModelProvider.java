package com.kamth.zeldamod.DataGen;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.item.ModArmorMaterials;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, ZeldaMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ZeldaItems.BLUE_EMERALD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ZeldaItems.RED_EMERALD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ZeldaItems.PURPLE_EMERALD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ZeldaItems.SILVER_EMERALD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ZeldaItems.GOLD_EMERALD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ZeldaItems.MASTER_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ZeldaItems.MASTER_ORE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ZeldaItems.REFINED_MASTER_ORE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ZeldaItems.MASTER_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ZeldaItems.SUPER_MUSHROOM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ZeldaItems.MINI_MUSHROOM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ZeldaItems.MEGA_MUSHROOM.get(), ModelTemplates.FLAT_ITEM);

        // Tools and Weapons

        itemModels.generateFlatItem(ZeldaItems.KOKIRI_SWORD_OOT.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ZeldaItems.KOKIRI_SWORD_MM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ZeldaItems.RAZOR_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ZeldaItems.MASTER_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);


        // Armor

        itemModels.generateTrimmableItem(ZeldaItems.KOKIRI_HAT.get(), ModArmorMaterials.KOKIRI_ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ZeldaItems.KOKIRI_TUNIC.get(), ModArmorMaterials.KOKIRI_ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(ZeldaItems.KOKIRI_LEGGINGS.get(), ModArmorMaterials.KOKIRI_ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(ZeldaItems.KOKIRI_BOOTS.get(), ModArmorMaterials.KOKIRI_ARMOR_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        // Masks

        itemModels.generateTrimmableItem(ZeldaItems.DEKU_MASK.get(), ModArmorMaterials.DEKU_MASK_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ZeldaItems.GIANTS_MASK.get(), ModArmorMaterials.GIANTS_MASK_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);

        // Blocks
        blockModels.createTrivialCube(ZeldaBlocks.BLUE_EMERALD_BLOCK.get());
        blockModels.createTrivialCube(ZeldaBlocks.RED_EMERALD_BLOCK.get());
        blockModels.createTrivialCube(ZeldaBlocks.PURPLE_EMERALD_BLOCK.get());
        blockModels.createTrivialCube(ZeldaBlocks.SILVER_EMERALD_BLOCK.get());
        blockModels.createTrivialCube(ZeldaBlocks.GOLD_EMERALD_BLOCK.get());


        blockModels.createTrivialCube(ZeldaBlocks.MASTER_ORE.get());
        blockModels.createTrivialCube(ZeldaBlocks.DEEPSLATE_MASTER_ORE.get());
    }
}
