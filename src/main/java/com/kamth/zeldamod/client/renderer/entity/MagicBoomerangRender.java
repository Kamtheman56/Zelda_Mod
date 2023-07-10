package com.kamth.zeldamod.client.renderer.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.custom.projectile.BoomerangProjectile;
import com.kamth.zeldamod.entity.custom.projectile.MagicBoomerangProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class MagicBoomerangRender extends EntityRenderer<MagicBoomerangProjectile> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ZeldaMod.MOD_ID, "textures/item/magic_boomerang.png");
    private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(TEXTURE_LOCATION);

    public MagicBoomerangRender(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    protected int getBlockLightLevel(MagicBoomerangProjectile pEntity, BlockPos pPos) {
        return 15;
    }

    public void render(MagicBoomerangProjectile pEntity, float pEntityYaw, float pPartialTicks, PoseStack matrixStackIn, MultiBufferSource pBuffer, int pPackedLight) {
        matrixStackIn.pushPose();
        matrixStackIn.scale(1F, 1F, 1F);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-pEntityYaw + 90.0f));
        matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(90.0f));
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RENDER_TYPE);
        PoseStack.Pose posestack$pose = matrixStackIn.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 1, 0, 0);
        matrixStackIn.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, matrixStackIn, pBuffer, pPackedLight);
    }



    private static void vertex(VertexConsumer p_114090_, Matrix4f p_114091_, Matrix3f p_114092_, int p_114093_, float p_114094_, int p_114095_, int p_114096_, int p_114097_) {
        p_114090_.vertex(p_114091_, p_114094_ - 0.5F, (float)p_114095_ - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float)p_114096_, (float)p_114097_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_114093_).normal(p_114092_, 0.0F, 1.0F, 0.0F).endVertex();
    }


    @Override
    public ResourceLocation getTextureLocation(MagicBoomerangProjectile pEntity) {
        return TEXTURE_LOCATION;
    }




}
