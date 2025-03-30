package com.kamth.zeldamod.client.rendering.entity.arrow;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.projectile.arrows.AbstractZeldaArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class ZeldaArrowRenderer<T extends AbstractZeldaArrowEntity> extends ArrowRenderer<T> {

    public final ResourceLocation texture;

    public ZeldaArrowRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/projectiles/" + ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).getPath() + ".png");
    }
}