package com.kamth.zeldamod.entity.client.render.deku;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.model.DekuModel;
import com.kamth.zeldamod.entity.mobs.hostile.deku.DekuScrubEntity;
import com.kamth.zeldamod.item.armors.render.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class DekuScrubRenderer extends MobRenderer<DekuScrubEntity, DekuModel<DekuScrubEntity>> {
    public DekuScrubRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DekuModel<>(pContext.bakeLayer(ModModelLayers.DEKU_LAYER)), .2f);
    }

    @Override
    public ResourceLocation getTextureLocation(DekuScrubEntity pEntity) {

        ResourceLocation texture = new ResourceLocation
                (ZeldaMod.MOD_ID, "textures/entity/mob/"  + ForgeRegistries.ENTITY_TYPES.getKey(pEntity.getType()).getPath() + ".png");


        return texture;

    }
    @Override
    public void render(DekuScrubEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
