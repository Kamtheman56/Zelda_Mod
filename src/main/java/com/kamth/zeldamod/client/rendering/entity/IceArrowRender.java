package com.kamth.zeldamod.client.rendering.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.projectile.arrows.IceArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class IceArrowRender extends ArrowRenderer<IceArrow> {

        public static final ResourceLocation TEXTURE = new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/projectiles/ice_arrow.png");

        public IceArrowRender(EntityRendererProvider.Context manager) {
            super(manager);
        }



    /**
     * Returns the location of an entity's texture.
     *
     * @param pEntity
     */
    @Override
    public ResourceLocation getTextureLocation(IceArrow pEntity) {
        return TEXTURE;
    }
}

