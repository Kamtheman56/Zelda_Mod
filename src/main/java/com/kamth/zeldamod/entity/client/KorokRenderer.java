package com.kamth.zeldamod.entity.client;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.mobs.KorokEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class KorokRenderer extends MobRenderer<KorokEntity, KorokModel<KorokEntity>> {
    public KorokRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KorokModel<>(pContext.bakeLayer(MobModelLayers.KOROK_LAYER)), .2f);
    }

    @Override
    public ResourceLocation getTextureLocation(KorokEntity pEntity) {
        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/korok.png");
    }

    @Override
    public void render(KorokEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
