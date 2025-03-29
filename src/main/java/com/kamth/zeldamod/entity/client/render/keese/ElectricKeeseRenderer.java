package com.kamth.zeldamod.entity.client.render.keese;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.MobModelLayers;
import com.kamth.zeldamod.entity.client.model.KeeseModel;
import com.kamth.zeldamod.entity.mobs.hostile.keese.ElectricKeeseEntity;
import com.kamth.zeldamod.entity.mobs.hostile.keese.IceKeeseEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ElectricKeeseRenderer extends MobRenderer<ElectricKeeseEntity, KeeseModel<ElectricKeeseEntity>> {
    public ElectricKeeseRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KeeseModel<>(pContext.bakeLayer(MobModelLayers.ELECTRIC_KEESE_LAYER)), .2f);
    }

    @Override
    public ResourceLocation getTextureLocation(ElectricKeeseEntity pEntity) {
        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/keese_electric.png");
    }

    @Override
    public void render(ElectricKeeseEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
