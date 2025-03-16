package com.kamth.zeldamod.client.renderer.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.projectile.arrows.AncientArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class AncientArrowRender extends ArrowRenderer<AncientArrow> {

        public static final ResourceLocation TEXTURE = new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/projectiles/ancient_arrow.png");

        public AncientArrowRender(EntityRendererProvider.Context manager) {
            super(manager);
        }



    /**
     * Returns the location of an entity's texture.
     *
     * @param pEntity
     */
    @Override
    public ResourceLocation getTextureLocation(AncientArrow pEntity) {
        return TEXTURE;
    }
}

