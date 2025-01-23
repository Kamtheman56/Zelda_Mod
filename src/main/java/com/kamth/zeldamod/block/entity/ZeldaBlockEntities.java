package com.kamth.zeldamod.block.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.function.Supplier;

public class ZeldaBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ZeldaMod.MOD_ID);



    public static final RegistryObject<BlockEntityType<SwordPedestalEntity>> SWORD_PEDESTAL_ENTITY =
            register("sword_pedestal_be", SwordPedestalEntity::new,
                    ModBlocks.SWORD_PEDESTAL,
                    ModBlocks.ANCIENT_SWORD_PEDESTAL,
                    ModBlocks.UNLOCKED_SWORD_PEDESTAL,
                    ModBlocks.MASTER_SWORD_PEDESTAL
            );




    // Containers

    public static final RegistryObject<BlockEntityType<LockedChestEntity>> LOCKED_CHEST_ENTITY =
            register("locked_chest_be", LockedChestEntity::new, ModBlocks.LOCKED_CHEST);

    public static final RegistryObject<BlockEntityType<JarEntity>> JAR_ENTITY =
            register("jar_be", JarEntity::new, ModBlocks.JAR_BLUE);




    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(
            String id, BlockEntityType.BlockEntitySupplier<T> supplier, Supplier<Block>... blockSuppliers) {
        return BLOCK_ENTITIES.register(id, () -> BlockEntityType.Builder.of(supplier,
                Arrays.stream(blockSuppliers).map(Supplier::get).toArray(Block[]::new)).build(null));
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
