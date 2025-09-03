package com.kamth.zeldamod.entity.client.render.darknuts;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.MobModelLayers;
import com.kamth.zeldamod.entity.client.model.IronKnuckleModel;
import com.kamth.zeldamod.entity.mobs.hostile.darknuts.IronKnuckleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class IronKnuckleRenderer extends MobRenderer<IronKnuckleEntity, IronKnuckleModel<IronKnuckleEntity>> {
    public IronKnuckleRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new IronKnuckleModel<>(pContext.bakeLayer(MobModelLayers.IRON_KNUCKLE_LAYER)), .2f);
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));

    }

    @Override
    public ResourceLocation getTextureLocation(IronKnuckleEntity pEntity) {
        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/iron_knuckle.png");
    }

    @Override
    public void render(IronKnuckleEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
