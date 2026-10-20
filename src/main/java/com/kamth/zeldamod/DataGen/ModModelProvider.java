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




        // BLOCKS
        blockModels.createTrivialCube(ZeldaBlocks.BLUE_EMERALD_BLOCK.get());
        blockModels.createTrivialCube(ZeldaBlocks.RED_EMERALD_BLOCK.get());
        blockModels.createTrivialCube(ZeldaBlocks.MASTER_ORE.get());
        blockModels.createTrivialCube(ZeldaBlocks.DEEPSLATE_MASTER_ORE.get());
    }
}
