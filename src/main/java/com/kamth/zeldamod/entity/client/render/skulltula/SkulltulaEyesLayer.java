package com.kamth.zeldamod.entity.client.render.skulltula;

import com.kamth.zeldamod.entity.client.model.SkulltulaModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkulltulaEyesLayer <T extends Entity, M extends SkulltulaModel<T>> extends EyesLayer<T, M> {
    private static final RenderType SPIDER_EYES = RenderType.eyes(new ResourceLocation("textures/entity/spider_eyes.png"));

    public SkulltulaEyesLayer(RenderLayerParent<T, M> p_117507_) {
        super(p_117507_);
    }


    @Override
    public RenderType renderType() {
      return SPIDER_EYES;
    }
}
