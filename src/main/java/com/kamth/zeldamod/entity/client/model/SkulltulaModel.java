package com.kamth.zeldamod.entity.client.model;

import com.kamth.zeldamod.entity.animations.ModAnimationDefinitions;
import com.kamth.zeldamod.entity.mobs.SkulltulaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class SkulltulaModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body0;
    private final ModelPart body1;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightMiddleHindLeg;
    private final ModelPart leftMiddleHindLeg;
    private final ModelPart rightMiddleFrontLeg;
    private final ModelPart leftMiddleFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    public SkulltulaModel(ModelPart root) {
        this.root = root.getChild("root");
        this.head = this.root.getChild("head");
        this.body0 = this.root.getChild("body0");
        this.body1 = this.root.getChild("body1");
        this.rightHindLeg = this.root.getChild("right_hind_leg");
        this.leftHindLeg = this.root.getChild("left_hind_leg");
        this.rightMiddleHindLeg = this.root.getChild("right_middle_hind_leg");
        this.leftMiddleHindLeg = this.root.getChild("left_middle_hind_leg");
        this.rightMiddleFrontLeg = this.root.getChild("right_middle_front_leg");
        this.leftMiddleFrontLeg = this.root.getChild("left_middle_front_leg");
        this.rightFrontLeg = this.root.getChild("right_front_leg");
        this.leftFrontLeg = this.root.getChild("left_front_leg");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, -3.0F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body0 = root.addOrReplaceChild("body0", CubeListBuilder.create().texOffs(32, 28).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

        PartDefinition body1 = root.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -5.0F, -6.0F, 14.0F, 9.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(-3, 59).addBox(-7.0F, -5.0F, -9.0F, 14.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

        PartDefinition right_hind_leg = root.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(32, 24).addBox(-18.0F, -3.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 5.0F, 0.0F, 0.7854F, -0.7854F));

        PartDefinition left_hind_leg = root.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(32, 24).addBox(2.0F, -3.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 5.0F, 0.0F, -0.7854F, 0.7854F));

        PartDefinition right_middle_hind_leg = root.addOrReplaceChild("right_middle_hind_leg", CubeListBuilder.create().texOffs(32, 24).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 4.0F, 0.0F, 0.2618F, -0.6109F));

        PartDefinition left_middle_hind_leg = root.addOrReplaceChild("left_middle_hind_leg", CubeListBuilder.create().texOffs(32, 24).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 4.0F, 0.0F, -0.2618F, 0.6109F));

        PartDefinition right_middle_front_leg = root.addOrReplaceChild("right_middle_front_leg", CubeListBuilder.create().texOffs(32, 24).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 3.0F, 0.0F, -0.2618F, -0.6109F));

        PartDefinition left_middle_front_leg = root.addOrReplaceChild("left_middle_front_leg", CubeListBuilder.create().texOffs(32, 24).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 3.0F, 0.0F, 0.2618F, 0.6109F));

        PartDefinition right_front_leg = root.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(32, 24).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 2.0F, 0.0F, -0.7854F, -0.7854F));

        PartDefinition left_front_leg = root.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(32, 24).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 2.0F, 0.0F, 0.7854F, 0.7854F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void setupAnim(Entity entity, float pLimbSwing, float pLimbSwingAmount, float ageInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(((SkulltulaEntity) entity).idleAnimationState, ModAnimationDefinitions.skulltula_idle, ageInTicks, 1f);
        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
        float f = ((float)Math.PI / 4F);
        this.rightHindLeg.zRot = (-(float)Math.PI / 4F);
        this.leftHindLeg.zRot = ((float)Math.PI / 4F);
        this.rightMiddleHindLeg.zRot = -0.58119464F;
        this.leftMiddleHindLeg.zRot = 0.58119464F;
        this.rightMiddleFrontLeg.zRot = -0.58119464F;
        this.leftMiddleFrontLeg.zRot = 0.58119464F;
        this.rightFrontLeg.zRot = (-(float)Math.PI / 4F);
        this.leftFrontLeg.zRot = ((float)Math.PI / 4F);
        float f1 = -0.0F;
        float f2 = ((float)Math.PI / 8F);
        this.rightHindLeg.yRot = ((float)Math.PI / 4F);
        this.leftHindLeg.yRot = (-(float)Math.PI / 4F);
        this.rightMiddleHindLeg.yRot = ((float)Math.PI / 8F);
        this.leftMiddleHindLeg.yRot = (-(float)Math.PI / 8F);
        this.rightMiddleFrontLeg.yRot = (-(float)Math.PI / 8F);
        this.leftMiddleFrontLeg.yRot = ((float)Math.PI / 8F);
        this.rightFrontLeg.yRot = (-(float)Math.PI / 4F);
        this.leftFrontLeg.yRot = ((float)Math.PI / 4F);
        float f3 = -(Mth.cos(pLimbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * pLimbSwingAmount;
        float f4 = -(Mth.cos(pLimbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * pLimbSwingAmount;
        float f5 = -(Mth.cos(pLimbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * pLimbSwingAmount;
        float f6 = -(Mth.cos(pLimbSwing * 0.6662F * 2.0F + ((float)Math.PI * 1.5F)) * 0.4F) * pLimbSwingAmount;
        float f7 = Math.abs(Mth.sin(pLimbSwing * 0.6662F + 0.0F) * 0.4F) * pLimbSwingAmount;
        float f8 = Math.abs(Mth.sin(pLimbSwing * 0.6662F + (float)Math.PI) * 0.4F) * pLimbSwingAmount;
        float f9 = Math.abs(Mth.sin(pLimbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * pLimbSwingAmount;
        float f10 = Math.abs(Mth.sin(pLimbSwing * 0.6662F + ((float)Math.PI * 1.5F)) * 0.4F) * pLimbSwingAmount;
        this.rightHindLeg.yRot += f3;
        this.leftHindLeg.yRot += -f3;
        this.rightMiddleHindLeg.yRot += f4;
        this.leftMiddleHindLeg.yRot += -f4;
        this.rightMiddleFrontLeg.yRot += f5;
        this.leftMiddleFrontLeg.yRot += -f5;
        this.rightFrontLeg.yRot += f6;
        this.leftFrontLeg.yRot += -f6;
        this.rightHindLeg.zRot += f7;
        this.leftHindLeg.zRot += -f7;
        this.rightMiddleHindLeg.zRot += f8;
        this.leftMiddleHindLeg.zRot += -f8;
        this.rightMiddleFrontLeg.zRot += f9;
        this.leftMiddleFrontLeg.zRot += -f9;
        this.rightFrontLeg.zRot += f10;
        this.leftFrontLeg.zRot += -f10;

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }
}