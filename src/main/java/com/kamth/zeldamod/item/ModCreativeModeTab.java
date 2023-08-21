package com.kamth.zeldamod.item;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            ZeldaMod.MOD_ID);
    public static void register (IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
    public static RegistryObject<CreativeModeTab> ZELDA_BLOCKS = CREATIVE_MODE_TABS.register("zelda_blocks", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MASTER_SWORD_INJURED.get())).title(Component.translatable("zelda_blocks")).build());

    public static RegistryObject<CreativeModeTab> ZELDA_MATERIAL = CREATIVE_MODE_TABS.register("zelda_material", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MASTER_ORE.get())).title(Component.translatable("zelda_misc")).build());

    public static RegistryObject<CreativeModeTab> ZELDA_MASK = CREATIVE_MODE_TABS.register("zelda_mask", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DEKU_MASK.get())).title(Component.translatable("zelda_masks")).build());
    public static RegistryObject<CreativeModeTab> ZELDA_TAB = CREATIVE_MODE_TABS.register("zelda_tab", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.SECRET_STONE.get())).title(Component.translatable("zelda_tab")).build());




}




