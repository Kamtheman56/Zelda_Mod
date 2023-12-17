package com.kamth.zeldamod.block.entity.renderer;

import com.kamth.zeldamod.block.entity.SwordPedestalEntity;
import com.kamth.zeldamod.item.custom.ModTags;
import com.mojang.authlib.minecraft.client.MinecraftClient;
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
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.Level;


public class SwordPedestalEntityRenderer implements BlockEntityRenderer<SwordPedestalEntity> {
  public SwordPedestalEntityRenderer (BlockEntityRendererProvider.Context context){

  }

    @Override
    public void render(SwordPedestalEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemStack sword = pBlockEntity.getRenderStack();
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        pPoseStack.pushPose();
        if(sword.is(ModTags.Items.LEGENDARY_ITEMS));
        pPoseStack.translate(0.0D, -0.3D, 0.0D);
        pPoseStack.scale(1,1,1);
        renderer.renderStatic(sword, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();
        }



    public void renderItem(SwordPedestalEntity pBlockEntity, ItemStack sword, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        pPoseStack.pushPose();
        if(sword.is(ModTags.Items.LEGENDARY_ITEMS));
        pPoseStack.translate(0.0D, -0.3D, 0.0D);
        renderer.renderStatic(sword, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();
    }




    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
