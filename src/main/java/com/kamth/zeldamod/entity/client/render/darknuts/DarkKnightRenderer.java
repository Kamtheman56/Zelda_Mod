package com.kamth.zeldamod.entity.client.render.darknuts;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.MobModelLayers;
import com.kamth.zeldamod.entity.client.model.DarknutModel;
import com.kamth.zeldamod.entity.mobs.hostile.darknuts.DarkKnightEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class DarkKnightRenderer extends MobRenderer<DarkKnightEntity, DarknutModel<DarkKnightEntity>> {
    public DarkKnightRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DarknutModel<>(pContext.bakeLayer(MobModelLayers.DARK_KNIGHT_LAYER)), .2f);
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));

    }

    @Override
    public ResourceLocation getTextureLocation(DarkKnightEntity pEntity) {
        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/dark_knight.png");
    }

    @Override
    public void render(DarkKnightEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
