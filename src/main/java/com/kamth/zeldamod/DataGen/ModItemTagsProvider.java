package com.kamth.zeldamod.DataGen;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.items.ZeldaItems;
import com.kamth.zeldamod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ZeldaMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
            tag(ModTags.Items.MASTER_REPAIRABLE)
                    .add(ZeldaItems.MASTER_ORE.get());


            tag(ItemTags.SWORDS).add(ZeldaItems.MASTER_SWORD.get());
    }
}
