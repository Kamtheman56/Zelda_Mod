package com.kamth.zeldamod.entity.client.render;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.MobModelLayers;
import com.kamth.zeldamod.entity.client.model.BokoblinModel;
import com.kamth.zeldamod.entity.mobs.hostile.bokoblin.BokoblinEntity;
import com.kamth.zeldamod.item.armors.render.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class BokoblinRenderer extends MobRenderer<BokoblinEntity, BokoblinModel<BokoblinEntity>> {
    public BokoblinRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BokoblinModel<>(pContext.bakeLayer(MobModelLayers.RED_BOKOBLIN_LAYER)), .2f);

        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(BokoblinEntity pEntity) {

        ResourceLocation texture = new ResourceLocation
                (ZeldaMod.MOD_ID, "textures/entity/mob/"  + ForgeRegistries.ENTITY_TYPES.getKey(pEntity.getType()).getPath() + ".png");


        return texture;

    }
    @Override
    public void render(BokoblinEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
