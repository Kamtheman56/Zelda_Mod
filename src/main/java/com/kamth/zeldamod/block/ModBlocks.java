package com.kamth.zeldamod.block;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.custom.*;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ZeldaMod.MOD_ID);
public static final RegistryObject<Block> SECRET_STONE = registerBlock("secret_stone", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(16f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MASTER_ORE = registerBlock("masters_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
            .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> PORK_BLOCK = registerBlock("pork_block", ()-> new PorkBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).strength(.4f).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> GLOOM_BLOCK = registerBlock("gloom_block", ()-> new GloomBlock(BlockBehaviour.Properties.copy(Blocks.SCULK).strength(3f)));
    public static final RegistryObject<Block> HOOK_TARGET = registerBlock("hook_target", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> CLAW_TARGET = registerBlock("claw_target", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> WISDOM_BLOCK = registerBlock("wisdom_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> POWER_BLOCK = registerBlock("power_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> COURAGE_BLOCK = registerBlock("courage_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> DEEPSLATE_MASTER_ORE = registerBlock("deepslate_masters_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
            .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> MASK_BLOCK = registerBlock("mask_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));

    public static final RegistryObject<Block> NIGHTSHADE = registerBlock("nightshade",
            () -> new FlowerBlock(() -> MobEffects.DAMAGE_RESISTANCE, 5,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().lightLevel((p_50755_) -> 3).noCollission()));
    public static final RegistryObject<Block> POTTED_NIGHTSHADE = BLOCKS.register("potted_nightshade",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.NIGHTSHADE,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final RegistryObject<Block> SUNDELION = registerBlock("sundelion",
            () -> new FlowerBlock(() -> ModEffects.GLOOM_RESIST.get(), 5,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().lightLevel((p_50755_) -> 3).noCollission()));
    public static final RegistryObject<Block> POTTED_SUNDELION = BLOCKS.register("potted_sundelion",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.SUNDELION,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final RegistryObject<Block> HEART_FLOWER = registerBlock("heart_flower",
            () -> new HeartFlowerBlock(() -> MobEffects.HEAL, 1,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_HEART_FLOWER = BLOCKS.register("potted_heart_flower",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.HEART_FLOWER,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    public static final RegistryObject<Block> PRIMO_FLOWER = registerBlock("primo_flower",
            () -> new PrimoHeartFlowerBlock(() -> MobEffects.HEAL, 1,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_PRIMO_FLOWER = BLOCKS.register("potted_primo_flower",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.PRIMO_FLOWER,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final RegistryObject<Block> BOMBFLOWER = BLOCKS.register("bomb_flower",
            () -> new BombFlowerBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
  //  public static final RegistryObject<Block> SAND_WAND = registerBlock("sand_wand_block", ()-> new SandWandBlock(BlockBehaviour.Properties.copy(Blocks.SAND).sound(SoundType.SAND)));
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
