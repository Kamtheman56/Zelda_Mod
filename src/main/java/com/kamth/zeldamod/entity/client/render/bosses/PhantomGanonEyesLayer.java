package com.kamth.zeldamod.entity.client.render.bosses;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.model.PhantonGanonModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

public class PhantomGanonEyesLayer <T extends Mob, M extends PhantonGanonModel <T>>  extends EyesLayer<T, M> {
    public PhantomGanonEyesLayer(RenderLayerParent<T, PhantonGanonModel<T>> pRenderer) {
        super((RenderLayerParent<T, M>) pRenderer);
    }
    private static final RenderType SPIDER_EYES = RenderType.eyes(new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/phantom_ganondorf_eyes.png"));





    @Override
    public RenderType renderType() {
        return SPIDER_EYES;
    }
}
