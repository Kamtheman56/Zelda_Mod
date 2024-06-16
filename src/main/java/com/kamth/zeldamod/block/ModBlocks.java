package com.kamth.zeldamod.block;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.custom.*;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ZeldaMod.MOD_ID);
public static final RegistryObject<Block> SECRET_STONE = registerBlock("secret_stone", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(18f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MASTER_ORE = registerBlock("masters_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
            .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> PORK_BLOCK = registerBlock("pork_block", ()-> new PorkBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).strength(.4f).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> GLOOM_BLOCK = registerBlock("gloom_block", ()-> new GloomBlock(BlockBehaviour.Properties.copy(Blocks.SCULK).strength(3f)));
    public static final RegistryObject<Block> GLOOM_VEIN = registerBlock("gloom_vein", ()-> new SculkVeinBlock(BlockBehaviour.Properties.copy(Blocks.SCULK_VEIN).noOcclusion().dynamicShape().strength(3f)));
    public static final RegistryObject<Block> HOOK_TARGET = registerBlock("hook_target", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> CLAW_TARGET = registerBlock("claw_target", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> WISDOM_BLOCK = registerBlock("wisdom_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> POWER_BLOCK = registerBlock("power_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> COURAGE_BLOCK = registerBlock("courage_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> BLUE_EMERALD_BLOCK = registerBlock("blue_emerald_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK)));
    public static final RegistryObject<Block> RED_EMERALD_BLOCK = registerBlock("red_emerald_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK)));
    public static final RegistryObject<Block> DEEPSLATE_MASTER_ORE = registerBlock("deepslate_masters_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
            .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> MASK_BLOCK = registerBlock("mask_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> CARMINE_FROGLIGHT = registerBlock("carmine_froglight", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OCHRE_FROGLIGHT)));
    public static final RegistryObject<Block> SUPERSHROOM_BLOCK = registerBlock("super_shroom_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)));
    public static final RegistryObject<Block> HAMMER_PEG = registerBlock("hammer_peg", ()-> new HammerPegBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).noOcclusion()));
    public static final RegistryObject<Block> COPPER_PEG = registerBlock("copper_peg", ()-> new CopperPegBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> EYE_SWITCH = registerBlock("eye_switch", ()-> new EyeSwitchBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    public static final RegistryObject<Block> TIMED_EYE_SWITCH = registerBlock("timed_eye_switch", ()-> new TimedEyeSwitchBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion(),60, false));
    public static final RegistryObject<Block> SHOCK_SWITCH = registerBlock("shock_switch", ()-> new ShockSwitchBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).lightLevel(state -> state.getValue(ShockSwitchBlock.POWERED) ? 12 : 8).noOcclusion()));
    public static final RegistryObject<Block> TIMED_SHOCK_SWITCH = registerBlock("timed_shock_switch", ()-> new TimedShockSwitchBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).noOcclusion().lightLevel(state -> state.getValue(ShockSwitchBlock.POWERED) ? 12 : 8),80, false));
    public static final RegistryObject<Block> LOCKED_DOOR = registerBlock("locked_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).noOcclusion(), BlockSetType.IRON));
    public static final RegistryObject<Block> LOCKED_BOSS_DOOR = registerBlock("locked_boss_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).sound(SoundType.METAL).noOcclusion(), BlockSetType.IRON));
    public static final RegistryObject<Block> LOCKED_CHEST = registerBlock("locked_chest", ()-> new LockedChestBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> SWORD_PEDESTAL = registerBlock("sword_pedestal", ()-> new SwordPedestalBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> MASTER_SWORD_PEDESTAL = registerBlock("master_sword_pedestal", ()-> new MasterSwordPedestalBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));
    public static final RegistryObject<Block> UNLOCKED_SWORD_PEDESTAL = registerBlock("unlocked_sword_pedestal", ()-> new UnlockedSwordPedestalBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> DEKU_BLOCK = registerBlock("deku_block", ()-> new DekuFlowerBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).noOcclusion().strength(0)));
    public static final RegistryObject<Block> DEKU_BLOCK_GOLD = registerBlock("deku_block_gold", ()-> new DekuFlowerBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).noOcclusion().strength(0).noOcclusion()));
    public static final RegistryObject<Block> DEKU_BLOCK_BLUE = registerBlock("deku_block_blue", ()-> new BlueDekuFlowerBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).noOcclusion().strength(0).noOcclusion()));
    public static final RegistryObject<Block> COURAGE_FLAME = registerBlock("courage_flame", ()-> new WisdomFlameBlock(BlockBehaviour.Properties.of().strength(8).sound(SoundType.EMPTY).noOcclusion().noCollission().lightLevel((p_50755_) -> 10).noLootTable()) );
    public static final RegistryObject<Block> POWER_FLAME = registerBlock("power_flame", ()-> new WisdomFlameBlock(BlockBehaviour.Properties.of().strength(8).sound(SoundType.EMPTY).noOcclusion().noCollission().lightLevel((p_50755_) -> 10).noLootTable()));
    public static final RegistryObject<Block> WISDOM_FLAME = registerBlock("wisdom_flame", ()-> new WisdomFlameBlock(BlockBehaviour.Properties.of().strength(8).sound(SoundType.EMPTY).noOcclusion().noCollission().lightLevel((p_50755_) -> 10).noLootTable()));
    public static final RegistryObject<Block> OWL_STATUE = registerBlock("owl_statue", ()-> new OwlBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
    public static final RegistryObject<Block> TIME_BLOCK = registerBlock("time_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).strength(16)));
    public static final RegistryObject<Block> TIME_BLOCK_GHOST = registerBlock("time_block_ghost", ()-> new TimeGhostBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).strength(18).noCollission().noOcclusion()));
    public static final RegistryObject<Block> TIME_BRICKS = registerBlock("time_bricks", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistryObject<Block> ZELDA_STONE = registerBlock("zelda_stone", ()-> new ZeldaStone(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

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
    public static final RegistryObject<Block> STAMINA_FRUIT = registerBlock("stamina_fruit",
            () -> new StaminaFruitBlock(() -> MobEffects.SATURATION, 1,
                    BlockBehaviour.Properties.of().sound(SoundType.FLOWERING_AZALEA).lightLevel((p_50755_) -> 10).noOcclusion().noCollission()));

    public static final RegistryObject<Block> BOMBFLOWER = BLOCKS.register("bomb_flower",
            () -> new BombFlowerBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().ignitedByLava().noCollission()));
    public static final RegistryObject<Block> BombFlower2 = registerBlock("wild_bomb_flower", ()-> new WildBombFlowerBlock(BlockBehaviour.Properties.of().ignitedByLava().instabreak().sound(SoundType.GRASS).noOcclusion().noCollission()));
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
