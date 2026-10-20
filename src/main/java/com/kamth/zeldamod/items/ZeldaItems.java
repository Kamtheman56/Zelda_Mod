package com.kamth.zeldamod.items;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ZeldaItems {

    public static final DeferredRegister.Items ZELDA_ITEMS = DeferredRegister.createItems(ZeldaMod.MOD_ID);

    public static final DeferredItem<Item> BLUE_EMERALD = ZELDA_ITEMS.registerSimpleItem("blue_emerald");
    public static final DeferredItem<Item> RED_EMERALD = ZELDA_ITEMS.registerSimpleItem("red_emerald");

    public static void register(IEventBus eventBus){
        ZELDA_ITEMS.register(eventBus);
    }



}
