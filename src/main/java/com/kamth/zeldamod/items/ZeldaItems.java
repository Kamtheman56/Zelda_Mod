package com.kamth.zeldamod.items;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ZeldaItems {

    public static final DeferredRegister.Items ZELDA_ITEMS = DeferredRegister.createItems(ZeldaMod.MOD_ID);


    // MISC ITEMS
    //emeralds
    public static final DeferredItem<Item> BLUE_EMERALD = ZELDA_ITEMS.registerSimpleItem("blue_emerald");
    public static final DeferredItem<Item> RED_EMERALD = ZELDA_ITEMS.registerSimpleItem("red_emerald");
    public static final DeferredItem<Item> PURPLE_EMERALD = ZELDA_ITEMS.registerSimpleItem("purple_emerald");
    public static final DeferredItem<Item> SILVER_EMERALD = ZELDA_ITEMS.registerSimpleItem("silver_emerald");
    public static final DeferredItem<Item> GOLD_EMERALD = ZELDA_ITEMS.registerSimpleItem("gold_emerald");

    public static final DeferredItem<Item> MASTER_SHARD = ZELDA_ITEMS.registerSimpleItem("master_shard");
    public static final DeferredItem<Item> MASTER_ORE = ZELDA_ITEMS.registerSimpleItem("master_ore");
    public static final DeferredItem<Item> REFINED_MASTER_ORE = ZELDA_ITEMS.registerSimpleItem("refined_master_ore");

    public static final DeferredItem<Item> MASTER_UPGRADE_SMITHING_TEMPLATE = ZELDA_ITEMS.registerSimpleItem("master_upgrade_smithing_template");







    public static void register(IEventBus eventBus){
        ZELDA_ITEMS.register(eventBus);
    }



}
