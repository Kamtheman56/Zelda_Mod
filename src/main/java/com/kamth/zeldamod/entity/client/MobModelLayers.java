package com.kamth.zeldamod.entity.client;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;


public class MobModelLayers {
    public static final ModelLayerLocation DEKU_LAYER = new ModelLayerLocation(
            new ResourceLocation(ZeldaMod.MOD_ID, "deku_layer"), "main");
    public static final ModelLayerLocation DEKU_MAD_LAYER = new ModelLayerLocation(
            new ResourceLocation(ZeldaMod.MOD_ID, "deku_mad_layer"), "main");
    
    public static final ModelLayerLocation DARK_NUT_LAYER = new ModelLayerLocation(
            new ResourceLocation(ZeldaMod.MOD_ID, "dark_nut_layer"), "main");
    public static final ModelLayerLocation DARK_KNIGHT_LAYER = new ModelLayerLocation(
            new ResourceLocation(ZeldaMod.MOD_ID, "dark_knight_layer"), "main");

    public static final ModelLayerLocation KEESE_LAYER = new ModelLayerLocation(
            new ResourceLocation(ZeldaMod.MOD_ID, "keese_layer"), "main");

}
