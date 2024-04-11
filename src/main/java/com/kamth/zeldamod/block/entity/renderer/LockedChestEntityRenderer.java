package com.kamth.zeldamod.block.entity.renderer;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.block.entity.LockedChestEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractChestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class LockedChestEntityRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<LockedChestEntity> {
    private static final String BOTTOM = "bottom";
    private static final String LID = "lid";
    private static final String LOCK = "lock";
    private static final ResourceLocation TEXTURE = new ResourceLocation(ZeldaMod.MOD_ID, "textures/block/locked.png");
    private final ModelPart lid;
    private final ModelPart bottom;
    private final ModelPart lock;
    private final ModelPart doubleLeftLid;
    private final ModelPart doubleLeftBottom;
    private final ModelPart doubleLeftLock;
    private final ModelPart doubleRightLid;
    private final ModelPart doubleRightBottom;
    private final ModelPart doubleRightLock;
    private boolean xmasTextures;
    public static final Material LOCKED = createCustomChestTextureId("locked");
    public static final Material LOCKED_LEFT = createCustomChestTextureId("locked_left");
    public static final Material LOCKED_RIGHT = createCustomChestTextureId("locked_right");

    public LockedChestEntityRenderer(BlockEntityRendererProvider.Context pContext) {


        ModelPart modelpart = pContext.bakeLayer(ModelLayers.CHEST);
        this.bottom = modelpart.getChild("bottom");
        this.lid = modelpart.getChild("lid");
        this.lock = modelpart.getChild("lock");
        ModelPart modelpart1 = pContext.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT);
        this.doubleLeftBottom = modelpart1.getChild("bottom");
        this.doubleLeftLid = modelpart1.getChild("lid");
        this.doubleLeftLock = modelpart1.getChild("lock");
        ModelPart modelpart2 = pContext.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT);
        this.doubleRightBottom = modelpart2.getChild("bottom");
        this.doubleRightLid = modelpart2.getChild("lid");
        this.doubleRightLock = modelpart2.getChild("lock");
    }

    private void render(PoseStack pPoseStack, VertexConsumer pConsumer, ModelPart pLidPart, ModelPart pLockPart, ModelPart pBottomPart, float pLidAngle, int pPackedLight, int pPackedOverlay) {
        pLidPart.xRot = -(pLidAngle * ((float)Math.PI / 2F));
        pLockPart.xRot = pLidPart.xRot;
        pLidPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
        pLockPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
        pBottomPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
    }
    private static Material createCustomChestTextureId(String variant) {
        return new Material(Sheets.CHEST_SHEET , new ResourceLocation(ZeldaMod.MOD_ID, "textures/entity/chest" + variant));
    }


    private static Material getMaterial(ChestType type) {
        return switch (type) {
            case LEFT -> LOCKED_LEFT;
            case RIGHT -> LOCKED_RIGHT;
            default -> LOCKED;
        };
    }

    @Override
    public void render(LockedChestEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
            Level level = pBlockEntity.getLevel();
            boolean flag = level != null;
            BlockState blockstate = flag ? pBlockEntity.getBlockState() : ModBlocks.LOCKED_CHEST.get().defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
            ChestType chesttype = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
            Block block = blockstate.getBlock();
            if (block instanceof AbstractChestBlock<?> abstractchestblock) {
                boolean flag1 = chesttype != ChestType.SINGLE;
                pPoseStack.pushPose();
                float f = blockstate.getValue(ChestBlock.FACING).toYRot();
                pPoseStack.translate(0.5F, 0.5F, 0.5F);
                pPoseStack.mulPose(Axis.YP.rotationDegrees(-f));
                pPoseStack.translate(-0.5F, -0.5F, -0.5F);
                DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborcombineresult;
                if (flag) {
                    neighborcombineresult = abstractchestblock.combine(blockstate, level, pBlockEntity.getBlockPos(), true);
                } else {
                    neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;
                }

                float f1 = neighborcombineresult.apply(ChestBlock.opennessCombiner(pBlockEntity)).get(pPartialTick);
                f1 = 1.0F - f1;
                f1 = 1.0F - f1 * f1 * f1;
                int i = neighborcombineresult.apply(new BrightnessCombiner<>()).applyAsInt(pPackedLight);
                Material material = getMaterial(chesttype);
                VertexConsumer vertexconsumer = material.buffer(pBuffer, RenderType::entityCutout);
                if (flag1) {
                    if (chesttype == ChestType.LEFT) {
                        this.render(pPoseStack, vertexconsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, f1, i, pPackedOverlay);
                    } else {
                        this.render(pPoseStack, vertexconsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, f1, i, pPackedOverlay);
                    }
                } else {
                    this.render(pPoseStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, i, pPackedOverlay);
                }

                pPoseStack.popPose();
            }
        }


    @Override
    public boolean shouldRenderOffScreen(LockedChestEntity pBlockEntity) {
        return BlockEntityRenderer.super.shouldRenderOffScreen(pBlockEntity);
    }

    @Override
    public int getViewDistance() {
        return BlockEntityRenderer.super.getViewDistance();
    }

    @Override
    public boolean shouldRender(LockedChestEntity pBlockEntity, Vec3 pCameraPos) {
        return BlockEntityRenderer.super.shouldRender(pBlockEntity, pCameraPos);
    }

}
