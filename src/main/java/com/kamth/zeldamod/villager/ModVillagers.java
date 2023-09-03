package com.kamth.zeldamod.villager;

import com.google.common.collect.ImmutableSet;
import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES=
            DeferredRegister.create(ForgeRegistries.POI_TYPES, ZeldaMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, ZeldaMod.MOD_ID);

public static final RegistryObject<PoiType> MASK_BLOCK_POI = POI_TYPES.register("mask_block_poi",
        () -> new PoiType(ImmutableSet.copyOf(ModBlocks.MASTER_ORE.get().getStateDefinition().getPossibleStates()),
                1,2));
public static final RegistryObject<VillagerProfession> MASK_TRADER = VILLAGER_PROFESSIONS.register("mask_trader",
        () -> new VillagerProfession("mask_trader", x -> x.get() == MASK_BLOCK_POI.get(),
                x -> x.get() == MASK_BLOCK_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                SoundEvents.VILLAGER_WORK_LEATHERWORKER));


    public static void register (IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
