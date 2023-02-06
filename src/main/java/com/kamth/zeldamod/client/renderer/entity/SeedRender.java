package com.kamth.zeldamod.client.renderer.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.custom.projectile.SeedProjectile;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SeedRender extends ArrowRenderer<SeedProjectile> {

@OnlyIn(Dist.CLIENT)
    public SeedRender(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(SeedProjectile pEntity) {

        return new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/projectiles/arrow.png");
    }
}
