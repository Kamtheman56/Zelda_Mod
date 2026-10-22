package com.kamth.zeldamod.DataGen;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ZeldaBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ZeldaMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ZeldaBlocks.BLUE_EMERALD_BLOCK.get())
                .add(ZeldaBlocks.RED_EMERALD_BLOCK.get())
                .add(ZeldaBlocks.MASTER_ORE.get())
                .add(ZeldaBlocks.DEEPSLATE_MASTER_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ZeldaBlocks.MASTER_ORE.get())
                .add(ZeldaBlocks.DEEPSLATE_MASTER_ORE.get());
    }
}
