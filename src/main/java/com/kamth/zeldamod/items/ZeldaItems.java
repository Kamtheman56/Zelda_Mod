package com.kamth.zeldamod.items;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.food.ModFoods;
import com.kamth.zeldamod.items.MovementItems.TestItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.awt.*;
import java.util.function.Consumer;

public class ZeldaItems {

    public static final DeferredRegister.Items ZELDA_ITEMS = DeferredRegister.createItems(ZeldaMod.MOD_ID);


    // MISC ITEMS
    //emeralds
    public static final DeferredItem<Item> BLUE_EMERALD = ZELDA_ITEMS.registerItem("blue_emerald",
             properties -> new TestItem(properties.durability(10)));
    public static final DeferredItem<Item> RED_EMERALD = ZELDA_ITEMS.registerSimpleItem("red_emerald");
    public static final DeferredItem<Item> PURPLE_EMERALD = ZELDA_ITEMS.registerSimpleItem("purple_emerald");
    public static final DeferredItem<Item> SILVER_EMERALD = ZELDA_ITEMS.registerSimpleItem("silver_emerald");
    public static final DeferredItem<Item> GOLD_EMERALD = ZELDA_ITEMS.registerSimpleItem("gold_emerald");

    public static final DeferredItem<Item> MASTER_SHARD = ZELDA_ITEMS.registerSimpleItem("master_shard");
    public static final DeferredItem<Item> MASTER_ORE = ZELDA_ITEMS.registerSimpleItem("master_ore");
    public static final DeferredItem<Item> REFINED_MASTER_ORE = ZELDA_ITEMS.registerSimpleItem("refined_master_ore");

    public static final DeferredItem<Item> MASTER_UPGRADE_SMITHING_TEMPLATE = ZELDA_ITEMS.registerSimpleItem("master_upgrade_smithing_template");



    // Food
    public static final DeferredItem<Item> SUPER_MUSHROOM = ZELDA_ITEMS.registerItem("super_mushroom",
            properties -> new Item(properties.food(ModFoods.SUPER_MUSHROOM, ModFoods.SUPER_MUSHROOM_CONSUMABLE)){
                @Override
                public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                    if (Minecraft.getInstance().hasShiftDown()){
                        builder.accept(Component.translatable("tooltip.zeldamod.super_mushroom.tooltip"));
                    }
                    super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                }
            });


    // Melee Weapons
    public static final DeferredItem<Item> MASTER_SWORD= ZELDA_ITEMS.registerItem("master_sword",
            properties -> new Item(properties.sword(ModToolTiers.MASTER,3,-2.4f)));


    public static void register(IEventBus eventBus){
        ZELDA_ITEMS.register(eventBus);
    }



}
