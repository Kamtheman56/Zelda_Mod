package com.kamth.zeldamod.block.entity.renderer;

import com.kamth.zeldamod.block.custom.SwordPedestalBlock;
import com.kamth.zeldamod.block.entity.AncientSwordPedestalEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;


public class AncientSwordPedestalEntityRenderer implements BlockEntityRenderer<AncientSwordPedestalEntity> {
  public AncientSwordPedestalEntityRenderer(BlockEntityRendererProvider.Context context){

  }




    @Override
    public void render (AncientSwordPedestalEntity te, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemStack sword = te.getSword();
        if (!sword.isEmpty())
        {
            this.renderItem2(te, sword, pPartialTick, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        }
    }

    private void renderItem2(AncientSwordPedestalEntity te, ItemStack sword, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
    {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5D, 0.6D, 0.5D);
//this.rotateItem(matrixStack, 180f, 90f, -45f);
       // pPoseStack.translate(0.0D, -0.3D, 0.0D);

        switch (te.getBlockState().getValue(SwordPedestalBlock.FACING))
        {
            case WEST:
            case EAST:
                pPoseStack.mulPose(Axis.XP.rotationDegrees(180f));
                pPoseStack.mulPose(Axis.YP.rotationDegrees(90f));
                pPoseStack.mulPose(Axis.ZP.rotationDegrees(-45f));
                break;
            case NORTH:
            case SOUTH:
                pPoseStack.mulPose(Axis.XP.rotationDegrees(180f));
                pPoseStack.mulPose(Axis.YP.rotationDegrees(180f));
                pPoseStack.mulPose(Axis.ZP.rotationDegrees(-45f));
                break;
            default:
                break;
        }
        renderer.renderStatic(sword, ItemDisplayContext.FIXED, getLightLevel(te.getLevel(), te.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, te.getLevel(), 1);
        pPoseStack.popPose();
    }}




    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
