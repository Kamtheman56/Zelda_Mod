package com.kamth.zeldamod.painting;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, ZeldaMod.MOD_ID);

    public static final RegistryObject<PaintingVariant> FOREST_SECRET = PAINTING_VARIANTS.register("forest_secret",
            () -> new PaintingVariant(32,32));

    public static final RegistryObject<PaintingVariant> DREAMER = PAINTING_VARIANTS.register("dreamer",
            () -> new PaintingVariant(32,32));

    public static final RegistryObject<PaintingVariant> BOY_IN_THE_WALL = PAINTING_VARIANTS.register("boy_in_the_wall",
            () -> new PaintingVariant(64,32));

    public static final RegistryObject<PaintingVariant> CASTLE_OF_THE_DEMON_KING = PAINTING_VARIANTS.register("castle_of_the_demon_king",
            () -> new PaintingVariant(64,64));

    public static final RegistryObject<PaintingVariant> LOFT_IN_THE_SKY = PAINTING_VARIANTS.register("loft_in_the_sky",
            () -> new PaintingVariant(32,16));

    public static final RegistryObject<PaintingVariant> OCEAN_TOWER = PAINTING_VARIANTS.register("ocean_tower",
            () -> new PaintingVariant(32,32));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
