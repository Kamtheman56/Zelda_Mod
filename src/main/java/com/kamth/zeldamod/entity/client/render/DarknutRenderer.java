package com.kamth.zeldamod.entity.client.render;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.MobModelLayers;
import com.kamth.zeldamod.entity.client.model.DarknutModel;
import com.kamth.zeldamod.entity.mobs.DarknutEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DarknutRenderer extends MobRenderer<DarknutEntity, DarknutModel<DarknutEntity>> {
    public DarknutRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DarknutModel<>(pContext.bakeLayer(MobModelLayers.DARK_NUT_LAYER)), .2f);

    }

    @Override
    public ResourceLocation getTextureLocation(DarknutEntity pEntity) {
        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/darknut.png");
    }

    @Override
    public void render(DarknutEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
