package com.kamth.zeldamod.entity.client.render.bosses;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.MobModelLayers;
import com.kamth.zeldamod.entity.client.model.PhantonGanonModel;
import com.kamth.zeldamod.entity.mobs.bosses.PhantomGanonEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PhantomGanonRenderer extends MobRenderer<PhantomGanonEntity, PhantonGanonModel<PhantomGanonEntity>> {
    public PhantomGanonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PhantonGanonModel<>(pContext.bakeLayer(MobModelLayers.PHANTOM_GANON_LAYER)), .2f);

        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));

    }



    @Override
    public @NotNull ResourceLocation getTextureLocation(PhantomGanonEntity pEntity) {
        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/phantom_ganondorf.png");
    }

    @Override
    public void render(PhantomGanonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
