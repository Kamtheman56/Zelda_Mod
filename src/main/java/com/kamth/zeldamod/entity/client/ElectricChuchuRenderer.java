package com.kamth.zeldamod.entity.client;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.mobs.ElectricChuchuEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ElectricChuchuRenderer extends MobRenderer<ElectricChuchuEntity, ChuchuModel<ElectricChuchuEntity>> {
    public ElectricChuchuRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ChuchuModel<>(pContext.bakeLayer(MobModelLayers.ELECTRIC_CHUCHU_LAYER)), .2f);
    }

    @Override
    public ResourceLocation getTextureLocation(ElectricChuchuEntity pEntity) {
        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/chuchu_yellow.png");
    }

    @Override
    public void render(ElectricChuchuEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
    protected void scale(ElectricChuchuEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        float f = 0.999F;
        pMatrixStack.scale(0.999F, 0.999F, 0.999F);
        pMatrixStack.translate(0.0F, 0.001F, 0.0F);
        float f1 = (float)pLivingEntity.getSize();
        float f2 = Mth.lerp(pPartialTickTime, pLivingEntity.oSquish, pLivingEntity.squish) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        pMatrixStack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

}
