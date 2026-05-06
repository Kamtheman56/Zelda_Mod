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
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import org.jetbrains.annotations.NotNull;

public class PhantomGanonRenderer extends MobRenderer<PhantomGanonEntity, PhantonGanonModel<PhantomGanonEntity>> {
    public PhantomGanonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PhantonGanonModel<>(pContext.bakeLayer(MobModelLayers.PHANTOM_GANON_LAYER)), .2f);
        this.addLayer(new PhantomGanonEyesLayer<>(this));
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));

    }
    private static final ResourceLocation  PHANTOM_GANONDORF = new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/phantom_ganondorf_2.png");
    private static final ResourceLocation  PHANTOM_GANONDORF_INVULNERABLE = new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/mob/phantom_ganondorf_invulnerable.png");


    @Override
    public void render(PhantomGanonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    protected int getBlockLightLevel(PhantomGanonEntity pEntity, BlockPos pPos) {
        return 15;
    }


    @Override
    public ResourceLocation getTextureLocation(PhantomGanonEntity pEntity) {
        int i = pEntity.getInvulnerableTicks();
        return i > 0 && (i > 80 || i / 5 % 2 != 1) ? PHANTOM_GANONDORF_INVULNERABLE : PHANTOM_GANONDORF;
    }

    protected void scale(PhantomGanonEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(1.5F, 1.5F, 1.5F);
    }
}
