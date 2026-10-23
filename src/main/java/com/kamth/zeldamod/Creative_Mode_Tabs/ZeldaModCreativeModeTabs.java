package com.kamth.zeldamod.Creative_Mode_Tabs;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ZeldaModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ZeldaMod.MOD_ID);

    public static final Supplier<CreativeModeTab> ZELDA_ITEMS_TAB = CREATIVE_MODE_TABS.register("zeldamod_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ZeldaItems.MASTER_SWORD.get()))
                    .title(Component.translatable("creativetab.zeldamod.zelda_items"))
                    .withTabsAfter(Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID, "zeldamod_armor_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ZeldaItems.KOKIRI_SWORD_OOT);
                        output.accept(ZeldaItems.KOKIRI_SWORD_MM);
                        output.accept(ZeldaItems.RAZOR_SWORD);
                        output.accept(ZeldaItems.MASTER_SWORD);

                    }))
                    .build()

    );

    public static final Supplier<CreativeModeTab> ZELDA_ARMOR_TAB = CREATIVE_MODE_TABS.register("zeldamod_armor_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ZeldaItems.KOKIRI_HAT.get()))
                    .title(Component.translatable("creativetab.zeldamod.zelda_armor"))
                    .withTabsAfter(Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID, "zeldamod_masks_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ZeldaItems.KOKIRI_HAT);
                        output.accept(ZeldaItems.KOKIRI_TUNIC);
                        output.accept(ZeldaItems.KOKIRI_LEGGINGS);
                        output.accept(ZeldaItems.KOKIRI_BOOTS);
                    }))
                    .build()

    );



    public static final Supplier<CreativeModeTab> ZELDA_MASKS_TAB = CREATIVE_MODE_TABS.register("zeldamod_masks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ZeldaItems.DEKU_MASK.get()))
                    .title(Component.translatable("creativetab.zeldamod.zelda_masks"))
                    .withTabsAfter(Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID, "zeldamod_materials_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ZeldaItems.DEKU_MASK);
                        output.accept(ZeldaItems.GIANTS_MASK);

                    }))
                    .build()

    );

    public static final Supplier<CreativeModeTab> ZELDA_MATERIALS_TAB = CREATIVE_MODE_TABS.register("zeldamod_materials_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ZeldaItems.BLUE_EMERALD.get()))
                    .title(Component.translatable("creativetab.zeldamod.zelda_materials"))
                    .withTabsAfter(Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID, "zeldamod_blocks_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ZeldaItems.BLUE_EMERALD);
                        output.accept(ZeldaItems.RED_EMERALD);
                        output.accept(ZeldaItems.PURPLE_EMERALD);
                        output.accept(ZeldaItems.SILVER_EMERALD);
                        output.accept(ZeldaItems.GOLD_EMERALD);


                        output.accept(ZeldaItems.MASTER_SHARD);
                        output.accept(ZeldaItems.MASTER_ORE);
                        output.accept(ZeldaItems.REFINED_MASTER_ORE);
                        output.accept(ZeldaItems.MASTER_UPGRADE_SMITHING_TEMPLATE);
                        output.accept(ZeldaItems.SUPER_MUSHROOM);
                        output.accept(ZeldaItems.MINI_MUSHROOM);
                        output.accept(ZeldaItems.MEGA_MUSHROOM);

                    }))
                    .build()

    );
    public static final Supplier<CreativeModeTab> ZELDA_BLOCKS_TAB = CREATIVE_MODE_TABS.register("zeldamod_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ZeldaBlocks.BLUE_EMERALD_BLOCK.get()))
                    .title(Component.translatable("creativetab.zeldamod.zelda_blocks"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ZeldaBlocks.BLUE_EMERALD_BLOCK);
                        output.accept(ZeldaBlocks.RED_EMERALD_BLOCK);
                        output.accept(ZeldaBlocks.PURPLE_EMERALD_BLOCK);
                        output.accept(ZeldaBlocks.SILVER_EMERALD_BLOCK);
                        output.accept(ZeldaBlocks.GOLD_EMERALD_BLOCK);
                        output.accept(ZeldaBlocks.MASTER_ORE);
                        output.accept(ZeldaBlocks.DEEPSLATE_MASTER_ORE);

                    }))
                    .build()

    );

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
