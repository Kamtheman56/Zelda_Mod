package com.kamth.zeldamod.DataGen;

import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.items.ZeldaItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }




    @Override
    protected void generate() {
        dropSelf(ZeldaBlocks.BLUE_EMERALD_BLOCK.get());
        dropSelf(ZeldaBlocks.RED_EMERALD_BLOCK.get());


        add(ZeldaBlocks.MASTER_ORE.get(),
                createOreDrop(ZeldaBlocks.MASTER_ORE.get(), ZeldaItems.MASTER_SHARD.get()));
        add(ZeldaBlocks.DEEPSLATE_MASTER_ORE.get(),
                createOreDrop(ZeldaBlocks.DEEPSLATE_MASTER_ORE.get(), ZeldaItems.MASTER_SHARD.get()));


    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ZeldaBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
