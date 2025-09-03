package com.kamth.zeldamod.entity.client.render.bokoblin;

import com.google.common.collect.Maps;
import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.MobModelLayers;
import com.kamth.zeldamod.entity.client.model.BokoblinModel;
import com.kamth.zeldamod.entity.client.model.MoblinModel;
import com.kamth.zeldamod.entity.mobs.hostile.bokoblin.BokoblinEntity;
import com.kamth.zeldamod.entity.mobs.hostile.bokoblin.MoblinEntity;
import com.kamth.zeldamod.entity.mobs.variants.BokoblinVariants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class MoblinRenderer extends MobRenderer<MoblinEntity, MoblinModel<MoblinEntity>> {
    public MoblinRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MoblinModel<>(pContext.bakeLayer(MobModelLayers.MOBLIN_LAYER)), .2f);

        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
    }

    public static final Map<BokoblinVariants, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BokoblinVariants.class), (p_114874_) -> {
                p_114874_.put(BokoblinVariants.RED,
                        new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/red_moblin.png"));
                p_114874_.put(BokoblinVariants.BLUE,
                        new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/blue_moblin.png"));
                p_114874_.put(BokoblinVariants.BLACK,
                        new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/black_moblin.png"));



            });
    @Override
    public ResourceLocation getTextureLocation(MoblinEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }
    @Override
    public void render(MoblinEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
