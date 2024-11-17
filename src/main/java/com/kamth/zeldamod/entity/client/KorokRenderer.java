package com.kamth.zeldamod.entity.client;

import com.google.common.collect.Maps;
import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.mobs.KorokEntity;
import com.kamth.zeldamod.entity.mobs.variants.KorokVariants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class KorokRenderer extends MobRenderer<KorokEntity, KorokModel<KorokEntity>> {
    public KorokRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KorokModel<>(pContext.bakeLayer(MobModelLayers.KOROK_LAYER)), .2f);
    }
    public static final Map<KorokVariants, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(KorokVariants.class), (p_114874_) -> {
                p_114874_.put(KorokVariants.DEFAULT,
                        new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/korok_oak.png"));
                p_114874_.put(KorokVariants.BIRCH,
                        new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/korok_birch.png"));
                p_114874_.put(KorokVariants.DARK,
                        new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/korok_dark_oak.png"));
            });
    @Override
    public ResourceLocation getTextureLocation(KorokEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(KorokEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
