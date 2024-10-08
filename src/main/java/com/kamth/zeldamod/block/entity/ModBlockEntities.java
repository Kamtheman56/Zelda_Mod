package com.kamth.zeldamod.block.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ZeldaMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<SwordPedestalEntity>> SWORD_PEDESTAL_BE =
            BLOCK_ENTITIES.register("sword_pedestal_be", () ->
                    BlockEntityType.Builder.of(SwordPedestalEntity::new,
                            ModBlocks.SWORD_PEDESTAL.get()).build(null));
    public static final RegistryObject<BlockEntityType<MasterSwordPedestalEntity>> MASTER_SWORD_PEDESTAL_BE =
            BLOCK_ENTITIES.register("master_sword_pedestal_be", () ->
                    BlockEntityType.Builder.of(MasterSwordPedestalEntity::new,
                            ModBlocks.MASTER_SWORD_PEDESTAL.get()).build(null));

    public static final RegistryObject<BlockEntityType<UnlockedSwordPedestalEntity>> UNLOCKED_SWORD_PEDESTAL_BE =
            BLOCK_ENTITIES.register("unlocked_sword_pedestal_be", () ->
                    BlockEntityType.Builder.of(UnlockedSwordPedestalEntity::new,
                            ModBlocks.UNLOCKED_SWORD_PEDESTAL.get()).build(null));
    public static final RegistryObject<BlockEntityType<AncientSwordPedestalEntity>> ANCIENT_SWORD_PEDESTAL_BE =
            BLOCK_ENTITIES.register("ancient_sword_pedestal_be", () -> BlockEntityType.Builder.of(AncientSwordPedestalEntity::new, ModBlocks.ANCIENT_SWORD_PEDESTAL.get()).build(null));

    public static final RegistryObject<BlockEntityType<LockedChestEntity>> LOCKED_CHEST_BE =
            BLOCK_ENTITIES.register("locked_chest_be", () ->
                    BlockEntityType.Builder.of(LockedChestEntity::new,
                            ModBlocks.LOCKED_CHEST.get()).build(null));
    public static final RegistryObject<BlockEntityType<JarEntity>> JAR_BE =
            BLOCK_ENTITIES.register("jar_be", () ->
                    BlockEntityType.Builder.of(JarEntity::new,
                            ModBlocks.JAR_BLUE.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
