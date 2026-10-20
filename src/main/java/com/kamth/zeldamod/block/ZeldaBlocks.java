package com.kamth.zeldamod.block;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.items.ZeldaItems;
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


    public static final DeferredBlock<Block>  BLUE_EMERALD_BLOCK = registerBlock("blue_emerald_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block>  RED_EMERALD_BLOCK = registerBlock("red_emerald_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block>  MASTER_ORE = registerBlock("master_ore",
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

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}

