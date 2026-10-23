package com.kamth.zeldamod.DataGen;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.items.ZeldaItems;
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

        // Tools and Weapons
        itemModels.generateFlatItem(ZeldaItems.MASTER_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // BLOCKS
        blockModels.createTrivialCube(ZeldaBlocks.BLUE_EMERALD_BLOCK.get());
        blockModels.createTrivialCube(ZeldaBlocks.RED_EMERALD_BLOCK.get());
        blockModels.createTrivialCube(ZeldaBlocks.MASTER_ORE.get());
        blockModels.createTrivialCube(ZeldaBlocks.DEEPSLATE_MASTER_ORE.get());
    }
}
