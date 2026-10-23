package com.kamth.zeldamod.DataGen;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ZeldaMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ZeldaBlocks.getRK(ZeldaBlocks.BLUE_EMERALD_BLOCK.get()))
                .add(ZeldaBlocks.getRK(ZeldaBlocks.RED_EMERALD_BLOCK.get()))
                .add(ZeldaBlocks.getRK(ZeldaBlocks.MASTER_ORE.get()))
                .add(ZeldaBlocks.getRK(ZeldaBlocks.DEEPSLATE_MASTER_ORE.get()));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ZeldaBlocks.getRK(ZeldaBlocks.MASTER_ORE.get()))
                .add(ZeldaBlocks.getRK(ZeldaBlocks.DEEPSLATE_MASTER_ORE.get()));


        tag(ModTags.Blocks.TEST)
                .add(BlockItemIds.SAND.block());

        tag(ModTags.Blocks.NEEDS_MASTER_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_MASTER_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_MASTER_TOOL);


    }


}
