package com.kamth.zeldamod.DataGen;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.ZeldaItems;
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
                    .add(ZeldaItems.getRK(ZeldaItems.MASTER_ORE.get()));


            // Weapons
        tag(ItemTags.SWORDS).add(ZeldaItems.getRK(ZeldaItems.KOKIRI_SWORD_OOT.get()));
        tag(ItemTags.SWORDS).add(ZeldaItems.getRK(ZeldaItems.KOKIRI_SWORD_MM.get()));
        tag(ItemTags.SWORDS).add(ZeldaItems.getRK(ZeldaItems.RAZOR_SWORD.get()));
        tag(ItemTags.SWORDS).add(ZeldaItems.getRK(ZeldaItems.MASTER_SWORD.get()));

            // Armor

        tag(ItemTags.HEAD_ARMOR).add(ZeldaItems.getRK(ZeldaItems.KOKIRI_HAT.get()));
        tag(ItemTags.CHEST_ARMOR).add(ZeldaItems.getRK(ZeldaItems.KOKIRI_TUNIC.get()));
        tag(ItemTags.LEG_ARMOR).add(ZeldaItems.getRK(ZeldaItems.KOKIRI_LEGGINGS.get()));
        tag(ItemTags.FOOT_ARMOR).add(ZeldaItems.getRK(ZeldaItems.KOKIRI_BOOTS.get()));

    }
}
