package com.kamth.zeldamod.client.rendering.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.client.models.BombchuModel;
import com.kamth.zeldamod.entity.projectile.bombs.bombchu.BombchuEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public class BombchuRenderer extends EntityRenderer<BombchuEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/projectiles/bombchu.png");

    private final BombchuModel<BombchuEntity> entityModel;

    public BombchuRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.entityModel = new BombchuModel<>(pContext.bakeLayer(BombchuModel.LAYER_LOCATION));
    }


    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BombchuEntity pEntity) {
        return TEXTURE;
    }

    public void render(BombchuEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();

        Direction floor = pEntity.getAttachedFaceClient();

        switch (floor) {
            case DOWN -> {
                pMatrixStack.mulPose(Axis.YN.rotationDegrees(pEntity.getYRot() + 180));
            }
            case UP -> {
                pMatrixStack.mulPose(Axis.XN.rotationDegrees(pEntity.getXRot()));
                pMatrixStack.mulPose(Axis.YP.rotationDegrees(pEntity.getYRot() + 180));
                pMatrixStack.translate(0, -0.4, 0);
            }
            case NORTH -> {
                pMatrixStack.mulPose(Axis.XP.rotationDegrees(90));
                pMatrixStack.mulPose(Axis.YN.rotationDegrees(pEntity.getYRot() + 180));
                pMatrixStack.translate(0, -0.2, -0.25);
            }
            case SOUTH -> {
                pMatrixStack.mulPose(Axis.XN.rotationDegrees(90));
                pMatrixStack.mulPose(Axis.YN.rotationDegrees(pEntity.getYRot() + 180));
                pMatrixStack.translate(0, -0.2, -0.25);
            }
            case EAST -> {
                pMatrixStack.mulPose(Axis.ZP.rotationDegrees(90));
                pMatrixStack.mulPose(Axis.YN.rotationDegrees(pEntity.getYRot() + 180));
                pMatrixStack.translate(0, -0.2, -0.25);
            }
            case WEST -> {
                pMatrixStack.mulPose(Axis.ZN.rotationDegrees(90));
                pMatrixStack.mulPose(Axis.YN.rotationDegrees(pEntity.getYRot() + 180));
                pMatrixStack.translate(0, -0.2, -0.25);
            }
        }

        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.entityModel.renderType(getTextureLocation(pEntity)));

        this.entityModel.setupAnim(pEntity, 0 ,0, pPartialTicks, 0, 0);

        float time = pEntity.tickCount;
        int min = 80;
        int startRed = pEntity.getFuse() - 5;
        float speed = 0.5f;

        float color = ( (float) (minDip(min) + minDip(-min) * Math.sin((time - startRed) * speed + ((Math.PI / 2))))) / 255.0f;

        float green = 1.0f;
        float blue = 1.0f;

        if (time >= startRed) {
            green = color;
            blue = color;
        }

        this.entityModel.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, green, blue, 1.0F);

        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    private int minDip(int min) {
        return ((255 + min) / 2);
    }

}
