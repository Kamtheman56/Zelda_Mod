package com.kamth.zeldamod.entity.client.render.bokoblin;

import com.google.common.collect.Maps;
import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.MobModelLayers;
import com.kamth.zeldamod.entity.client.model.BokoblinModel;
import com.kamth.zeldamod.entity.mobs.friendly.KorokEntity;
import com.kamth.zeldamod.entity.mobs.hostile.bokoblin.BokoblinEntity;
import com.kamth.zeldamod.entity.mobs.variants.BokoblinVariants;
import com.kamth.zeldamod.entity.mobs.variants.KorokVariants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class BokoblinRenderer extends MobRenderer<BokoblinEntity, BokoblinModel<BokoblinEntity>> {
    public BokoblinRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BokoblinModel<>(pContext.bakeLayer(MobModelLayers.RED_BOKOBLIN_LAYER)), .2f);

        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
    }

    public static final Map<BokoblinVariants, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BokoblinVariants.class), (p_114874_) -> {
                p_114874_.put(BokoblinVariants.RED,
                        new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/red_bokoblin.png"));
                p_114874_.put(BokoblinVariants.BLUE,
                        new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/blue_bokoblin.png"));
                p_114874_.put(BokoblinVariants.BLACK,
                        new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/black_bokoblin.png"));
//                p_114874_.put(BokoblinVariants.GOLD,
//                        new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/gold_bokoblin.png"));
//                p_114874_.put(BokoblinVariants.WHITE,
//                        new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/white_bokoblin.png"));


            });
    @Override
    public ResourceLocation getTextureLocation(BokoblinEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }
    @Override
    public void render(BokoblinEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
