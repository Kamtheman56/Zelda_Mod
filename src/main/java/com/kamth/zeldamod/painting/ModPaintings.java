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

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
