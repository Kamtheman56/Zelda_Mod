package com.kamth.zeldamod.entity.client.render.keese;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.client.MobModelLayers;
import com.kamth.zeldamod.entity.client.model.KeeseModel;
import com.kamth.zeldamod.entity.mobs.hostile.chus.ChuchuEntity;
import com.kamth.zeldamod.entity.mobs.hostile.keese.KeeseEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.registries.ForgeRegistries;

public class KeeseRenderer extends MobRenderer<KeeseEntity, KeeseModel<KeeseEntity>> {
    public KeeseRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KeeseModel<>(pContext.bakeLayer(MobModelLayers.KEESE_LAYER)), .2f);
    }

    @Override
    public ResourceLocation getTextureLocation(KeeseEntity pEntity) {
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();


        ResourceLocation texture = new ResourceLocation
                (ZeldaMod.MOD_ID, "textures/entity/mob/"  + ForgeRegistries.ENTITY_TYPES.getKey(pEntity.getType()).getPath() + ".png");


        return texture;

    }

    @Override
    public void render(KeeseEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}
