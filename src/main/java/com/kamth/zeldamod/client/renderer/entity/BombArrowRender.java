package com.kamth.zeldamod.client.renderer.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.projectile.arrows.BombArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BombArrowRender extends ArrowRenderer<BombArrow> {

        public static final ResourceLocation TEXTURE = new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/projectiles/fire_arrow.png");

        public BombArrowRender(EntityRendererProvider.Context manager) {
            super(manager);
        }



    /**
     * Returns the location of an entity's texture.
     *
     * @param pEntity
     */
    @Override
    public ResourceLocation getTextureLocation(BombArrow pEntity) {
        return TEXTURE;
    }
}

