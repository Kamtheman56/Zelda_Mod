package com.kamth.zeldamod.client.renderer.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.projectile.boomerangs.MagicBoomerangProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

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
        if (pEntity.tickCount >= 2) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0, 0.2, 0);
            matrixStackIn.mulPose(Axis.XP.rotationDegrees(90F));

            Minecraft mc = Minecraft.getInstance();
            float time = pEntity.tickCount + (mc.isPaused() ? 0 : pPartialTicks);
            matrixStackIn.mulPose(Axis.ZP.rotationDegrees(time * 20F));

            mc.getItemRenderer().renderStatic(pEntity.getStack(), ItemDisplayContext.FIXED, pPackedLight, OverlayTexture.WHITE_OVERLAY_V, matrixStackIn, pBuffer, pEntity.level(), 2);

            matrixStackIn.popPose();
        }
    }




    private static void vertex(VertexConsumer p_114090_, Matrix4f p_114091_, Matrix3f p_114092_, int p_114093_, float p_114094_, int p_114095_, int p_114096_, int p_114097_) {
        p_114090_.vertex(p_114091_, p_114094_ - 0.5F, (float)p_114095_ - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float)p_114096_, (float)p_114097_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_114093_).normal(p_114092_, 0.0F, 1.0F, 0.0F).endVertex();
    }


    @Override
    public ResourceLocation getTextureLocation(MagicBoomerangProjectile pEntity) {
        return TEXTURE_LOCATION;
    }




}
