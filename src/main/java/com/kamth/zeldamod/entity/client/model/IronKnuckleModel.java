package com.kamth.zeldamod.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class IronKnuckleModel<T extends Entity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    private final ModelPart darknut;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public IronKnuckleModel(ModelPart root) {
        this.darknut = root.getChild("darknut");
        this.head = darknut.getChild("head");
        this.body = darknut.getChild("body");
        this.rightArm = body.getChild("arm_l");
        this.leftArm = body.getChild("arm_r");
        this.rightLeg = darknut.getChild("leg_r");
        this.leftLeg = darknut.getChild("leg_l");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition iron_knuckle = partdefinition.addOrReplaceChild("darknut", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -3.098F, 0.0F));

        PartDefinition body = iron_knuckle.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.002F, -16.6F, 0.998F));

        PartDefinition armor = body.addOrReplaceChild("armor", CubeListBuilder.create().texOffs(5, 110).addBox(-7.0F, 5.0F, -3.0F, 14.0F, 5.0F, 6.0F, new CubeDeformation(0.5F))
                .texOffs(4, 84).addBox(-7.002F, -11.4F, -2.998F, 14.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition arm_r = body.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(60, 0).addBox(-2.0F, 0.0F, -1.0F, 5.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(9.998F, -13.4F, -0.998F));

        PartDefinition pad_r = arm_r.addOrReplaceChild("pad_r", CubeListBuilder.create().texOffs(60, 20).addBox(-4.0F, -2.5F, -3.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(60, 20).addBox(-6.0F, -5.5F, -3.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -0.5F, 1.0F));

        PartDefinition weapon = arm_r.addOrReplaceChild("weapon", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 11.0F, -6.0F, 2.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
                .texOffs(22, 76).addBox(0.0F, 11.0F, 13.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.5F))
                .texOffs(56, 71).addBox(0.0F, 11.0F, -6.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.5F))
                .texOffs(0, 56).addBox(1.0F, 4.0F, 10.0F, 0.0F, 16.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

        PartDefinition arm_l = body.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(22, 56).addBox(-2.0F, 0.0F, -2.0F, 5.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.002F, -13.4F, 0.002F));

        PartDefinition pad_l = arm_l.addOrReplaceChild("pad_l", CubeListBuilder.create().texOffs(40, 62).addBox(-10.0F, -15.0F, -3.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(40, 62).addBox(-8.0F, -18.0F, -3.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 12.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(76, 29).addBox(-5.0F, -7.0F, -4.702F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(40, 30).addBox(-5.0F, -3.0F, -4.702F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(40, 46).addBox(-5.0F, -3.0F, -4.702F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(-0.002F, -21.4F, 0.704F));

        PartDefinition leg_r = iron_knuckle.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(64, 80).addBox(-3.0F, -3.0F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -11.0F, 1.0F));

        PartDefinition leg_l = iron_knuckle.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(66, 62).addBox(-4.0F, -3.0F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -11.0F, 1.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }




    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        darknut.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return darknut;
    }
    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        this.body.yRot = 0.0F;
        this.rightArm.z = 0.0F;
        this.rightArm.x = -5.0F;
        this.leftArm.z = 0.0F;
        this.leftArm.x = 5.0F;
        float f = 1.0F;


        if (f < 1.0F) {
            f = 1.0F;
        }

        this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.rightArm.zRot = 0.0F;
        this.leftArm.zRot = 0.0F;
        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.rightLeg.yRot = 0.005F;
        this.leftLeg.yRot = -0.005F;
        this.rightLeg.zRot = 0.005F;
        this.leftLeg.zRot = -0.005F;
    }
}