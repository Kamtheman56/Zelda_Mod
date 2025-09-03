package com.kamth.zeldamod.client.rendering.block_entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.custom.dungeon_blocks.LockedChestBlock;
import com.kamth.zeldamod.block.entity.LockedChestEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;


public class LockedChestEntityRenderer extends ChestRenderer<LockedChestEntity> {
    private static final String LID = "lid";
    private static final String LOCK = "lock";
    private final ModelPart lid;
    private final ModelPart bottom;
    private final ModelPart lock;


    private static final Material MATERIAL = chestMaterial("locked");
    public static final Material LOCKED = createCustomChestTextureId("locked_chest");
    public static final Material UNLOCKED = createCustomChestTextureId("locked_left");
;

    public LockedChestEntityRenderer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);

        ModelPart modelpart = pContext.bakeLayer(ModelLayers.CHEST);
        this.bottom = modelpart.getChild("bottom");
        this.lid = modelpart.getChild("lid");
        this.lock = modelpart.getChild("lock");
    }

    private static Material createCustomChestTextureId(String variant) {
        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(ZeldaMod.MOD_ID, "entity/chest/" + variant));
    }

    private static Material getCustomChestTextureId(ChestType type) {
        return LOCKED;
    }



    @Override
    protected Material getMaterial(LockedChestEntity blockEntity, ChestType chestType) {
        return MATERIAL;
    }

    public static Material chestMaterial(String chestName) {
        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(ZeldaMod.MOD_ID, "entity/chest/" + chestName));
    }


    @Override
    public void render(LockedChestEntity chest, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        Level level = chest.getLevel();
        boolean flag = level != null;
        BlockState blockstate = flag ? chest.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        ChestType chestType = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
        Block block = blockstate.getBlock();
        if (block instanceof AbstractChestBlock<?> abstractchestblock) {
            boolean flag1 = chestType != ChestType.SINGLE;
            pPoseStack.pushPose();
            float f = blockstate.getValue(ChestBlock.FACING).toYRot();
            pPoseStack.translate(0.5F, 0.5F, 0.5F);
            pPoseStack.mulPose(Axis.YP.rotationDegrees(-f));
            pPoseStack.translate(-0.5F, -0.5F, -0.5F);
            DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborcombineresult;
            if (flag) {
                neighborcombineresult = abstractchestblock.combine(blockstate, level, chest.getBlockPos(), true);
            } else {
                neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;
            }

            float f1 = neighborcombineresult.apply(ChestBlock.opennessCombiner(chest)).get(pPartialTick);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = neighborcombineresult.apply(new BrightnessCombiner<>()).applyAsInt(pPackedLight);
            Material material = getCustomChestTextureId(chestType);
            VertexConsumer vertexconsumer = material.buffer(pBuffer, RenderType::entityCutout);

                this.render(pPoseStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, i, pPackedOverlay);

        if (chest.getBlockState().getValue(LockedChestBlock.LOCKED)){

        }
            pPoseStack.popPose();
        }


    }

    private void render(PoseStack pPoseStack, VertexConsumer pConsumer, ModelPart pLidPart, ModelPart pLockPart, ModelPart pBottomPart, float pLidAngle, int pPackedLight, int pPackedOverlay) {
        pLidPart.xRot = -(pLidAngle * ((float)Math.PI / 2F));
        pLockPart.xRot = pLidPart.xRot;
        pLidPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
        pLockPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
        pBottomPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
    }
}