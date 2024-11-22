package com.kamth.zeldamod.entity.client;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.mobs.SkulltulaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SkulltulaRenderer extends MobRenderer<SkulltulaEntity, SkulltulaModel<SkulltulaEntity>> {
    public SkulltulaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SkulltulaModel<>(pContext.bakeLayer(MobModelLayers.SKULLTULA_LAYER)), .2f);
    }

    @Override
    public ResourceLocation getTextureLocation(SkulltulaEntity pEntity) {
        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/skulltula.png");
    }
    @Override
    public void render(SkulltulaEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
