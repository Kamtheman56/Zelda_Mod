package com.kamth.zeldamod.client.renderer.entity.thrown;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.client.renderer.MockItemRenderer;
import com.kamth.zeldamod.entity.projectile.bombs.AbstractBombEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.projectile.ItemSupplier;

import java.util.Optional;

public class BombEntityRenderer<T extends AbstractBombEntity & ItemSupplier> extends EntityRenderer<T> {

    public BombEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(AbstractBombEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        float time = pEntity.tickCount;
        float scale = (float) (0.75 + 0.05 * Math.sin(time * 0.2));

        int min = 160;
        int startRed = pEntity.getFuse() - 25;
        float speed = 0.5f;

        int color = (int) (minDip(min) + minDip(-min) * Math.sin((time - startRed) * speed + ((Math.PI / 2))));

        pMatrixStack.pushPose();

        pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pMatrixStack.mulPose(Axis.ZP.rotationDegrees(180));
        pMatrixStack.scale(scale, scale, scale);
        pMatrixStack.translate(-0.5, -0.75, -0.5);

        int green = 255;
        int blue = 255;

        if (time >= startRed) {
            green = color;
            blue = color;
        }

        MockItemRenderer.renderTintedItem(pMatrixStack, pBuffer, pPackedLight, getTextureLocation(pEntity), 255, green, blue);

        pMatrixStack.popPose();
    }

    private int minDip(int min) {
        return ((255 + min) / 2);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractBombEntity t) {
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        ResourceLocation itemKey = BuiltInRegistries.ITEM.getKey(t.getItem().getItem());

        ResourceLocation bombTex = new ResourceLocation(itemKey.getNamespace(),
                "textures/item/" + itemKey.getPath() + ".png");

        Optional<Resource> resource = resourceManager.getResource(bombTex);

        if (resource.isPresent()) {
            return bombTex;
        }

        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/item/bomb.png");
    }
}
