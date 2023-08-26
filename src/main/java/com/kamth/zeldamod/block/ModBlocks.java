package com.kamth.zeldamod.block;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.custom.GloomBlock;
import com.kamth.zeldamod.block.custom.PorkBlock;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ZeldaMod.MOD_ID);
public static final RegistryObject<Block> SECRET_STONE = registerBlock("secret_stone", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(16f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MASTER_ORE = registerBlock("masters_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> PORK_BLOCK = registerBlock("pork_block", ()-> new PorkBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(.4f).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> GLOOM_BLOCK = registerBlock("gloom_block", ()-> new GloomBlock(BlockBehaviour.Properties.copy(Blocks.SCULK).strength(3f)));
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn);
        return toReturn;
    }
private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
}
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
