package com.kamth.zeldamod.entity.client.render;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.MobModelLayers;
import com.kamth.zeldamod.entity.client.model.KeeseModel;
import com.kamth.zeldamod.entity.mobs.KeeseEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class KeeseRenderer extends MobRenderer<KeeseEntity, KeeseModel<KeeseEntity>> {
    public KeeseRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KeeseModel<>(pContext.bakeLayer(MobModelLayers.KEESE_LAYER)), .2f);
    }

    @Override
    public ResourceLocation getTextureLocation(KeeseEntity pEntity) {
        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/keese.png");
    }

    @Override
    public void render(KeeseEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
