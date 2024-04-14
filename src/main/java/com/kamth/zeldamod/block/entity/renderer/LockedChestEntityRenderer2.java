package com.kamth.zeldamod.block.entity.renderer;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.entity.LockedChestEntity;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;


public class LockedChestEntityRenderer2 extends ChestRenderer<LockedChestEntity> {
    private static final Material MATERIAL = chestMaterial("locked_chest");

    public LockedChestEntityRenderer2(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    protected Material getMaterial(LockedChestEntity blockEntity, ChestType chestType) {
        return MATERIAL;
    }

    public static Material chestMaterial(String chestName) {
        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/chest/" + chestName));
    }
}