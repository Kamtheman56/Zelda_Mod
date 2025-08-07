package com.kamth.zeldamod.item.armors.render;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation ARCHAIC = create("archaic");
    public static final ModelLayerLocation LEATHERS = create("leathers");
    public static final ModelLayerLocation BUNNY = create("bunny_hood");
    public static final ModelLayerLocation PEGASUS = create("pegasus_boots");
    public static final ModelLayerLocation CAP = create("cap");
    public static final ModelLayerLocation DEKU_LAYER = new ModelLayerLocation(
            new ResourceLocation(ZeldaMod.MOD_ID, "deku_layer"), "main");
    public static final ModelLayerLocation DEKU_MAD_LAYER = new ModelLayerLocation(
            new ResourceLocation(ZeldaMod.MOD_ID, "deku_mad_layer"), "main");

    public static final ModelLayerLocation DARK_NUT_HELMET = create("darknut_helmet");
    public static final ModelLayerLocation BOKOBLIN_MASK = create("bokoblin_mask");


    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(ZeldaMod.MOD_ID, name), "main");
    }
}