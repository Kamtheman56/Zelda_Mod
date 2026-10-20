package com.kamth.zeldamod.Creative_Mode_Tabs;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.items.ZeldaItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ZeldaModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ZeldaMod.MOD_ID);

    public static final Supplier<CreativeModeTab> ZELDA_ITEMS_TAB = CREATIVE_MODE_TABS.register("zeldamod_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ZeldaItems.BLUE_EMERALD.get()))
                    .title(Component.translatable("creativetab.zeldamod.zelda_items"))
                    .withTabsAfter(Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID, "zeldamod_blocks_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ZeldaItems.BLUE_EMERALD);
                        output.accept(ZeldaItems.RED_EMERALD);

                    }))
                    .build()

    );

    public static final Supplier<CreativeModeTab> ZELDA_BLOCKS_TAB = CREATIVE_MODE_TABS.register("zeldamod_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ZeldaBlocks.BLUE_EMERALD_BLOCK.get()))
                    .title(Component.translatable("creativetab.zeldamod.zelda_blocks"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ZeldaBlocks.BLUE_EMERALD_BLOCK);
                        output.accept(ZeldaBlocks.RED_EMERALD_BLOCK);
                        output.accept(ZeldaBlocks.MASTER_ORE);
                        output.accept(ZeldaBlocks.DEEPSLATE_MASTER_ORE);

                    }))
                    .build()

    );

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
