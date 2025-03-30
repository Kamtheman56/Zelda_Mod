package com.kamth.zeldamod.client.rendering.block_entity;

import com.kamth.zeldamod.block.custom.pedastal.SwordPedestalBlock;
import com.kamth.zeldamod.block.entity.SwordPedestalEntity;
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


public class SwordPedestalEntityRenderer<T extends SwordPedestalEntity> implements BlockEntityRenderer<T> {

    public SwordPedestalEntityRenderer (BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render (T blockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemStack sword = blockEntity.getSword();

        if (!sword.isEmpty()) {
            this.renderItem2(blockEntity, sword, pPartialTick, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        }
    }

    private void renderItem2(T blockEntity, ItemStack sword, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, 0.6, 0.5);

        pPoseStack.mulPose(Axis.XP.rotationDegrees(180));

        switch (blockEntity.getBlockState().getValue(SwordPedestalBlock.FACING)) {
            case WEST, EAST -> pPoseStack.mulPose(Axis.YP.rotationDegrees(90));
            default -> pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
        }

        pPoseStack.mulPose(Axis.ZP.rotationDegrees(-45));

        renderer.renderStatic(sword, ItemDisplayContext.FIXED, getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, blockEntity.getLevel(), 1);

        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
