package com.kamth.zeldamod.client.renderer.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.custom.projectile.LightArrow;
import com.kamth.zeldamod.entity.custom.projectile.LightningArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LightningArrowRender extends ArrowRenderer<LightningArrow> {

        public static final ResourceLocation TEXTURE = new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/projectiles/light_arrow.png");

        public LightningArrowRender(EntityRendererProvider.Context manager) {
            super(manager);
        }



    /**
     * Returns the location of an entity's texture.
     *
     * @param pEntity
     */
    @Override
    public ResourceLocation getTextureLocation(LightningArrow pEntity) {
        return TEXTURE;
    }
}

