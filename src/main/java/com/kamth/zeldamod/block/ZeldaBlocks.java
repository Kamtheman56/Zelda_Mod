package com.kamth.zeldamod.block;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ZeldaBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ZeldaMod.MOD_ID);

// EMERALD BLOCKS
    public static final DeferredBlock<Block>  BLUE_EMERALD_BLOCK = registerBlock("blue_emerald_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block>  RED_EMERALD_BLOCK = registerBlock("red_emerald_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block>  PURPLE_EMERALD_BLOCK = registerBlock("purple_emerald_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block>  SILVER_EMERALD_BLOCK = registerBlock("silver_emerald_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block>  GOLD_EMERALD_BLOCK = registerBlock("gold_emerald_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));


    public static final DeferredBlock<Block>  MASTER_ORE = registerBlock("master_ore_block",
            properties -> new DropExperienceBlock(UniformInt.of(2,4),(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE))));
    public static final DeferredBlock<Block>  DEEPSLATE_MASTER_ORE = registerBlock("deepslate_master_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2,4),(properties.strength(6f)
                    .requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE))));









    // Helper Methods for registering blocks

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ZeldaItems.ZELDA_ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function, Component... components) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block, Component... components) {
        ZeldaItems.ZELDA_ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }


    public static ResourceKey<Block> getRK (Block block){
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}

