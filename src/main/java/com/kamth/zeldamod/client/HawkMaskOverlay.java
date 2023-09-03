package com.kamth.zeldamod.client;

import com.kamth.zeldamod.ZeldaMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.extensions.IForgeGuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HawkMaskOverlay  {
    private static final ResourceLocation HAWK = new ResourceLocation(ZeldaMod.MOD_ID,
            "textures/overlay/hawk.png");


    public static final IGuiOverlay HAWK_MASK = ((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, HAWK);
        for (int i = 0; i < 10; i++) {
            poseStack.blit(HAWK, x - 94 + (i * 9), y - 54, 0, 0, 12, 12,
                    12, 12);
        }});
}
