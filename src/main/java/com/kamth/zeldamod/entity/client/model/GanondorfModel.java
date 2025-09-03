package com.kamth.zeldamod.entity.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.kamth.zeldamod.entity.animations.ModAnimationDefinitions;
import com.kamth.zeldamod.entity.mobs.hostile.bokoblin.BokoblinEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;

public class GanondorfModel<T extends Mob> extends HierarchicalModel<T> implements ArmedModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    private final ModelPart Base;
    private final ModelPart Head;
    private final ModelPart Body;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;

    public GanondorfModel(ModelPart root) {
        this.Base = root.getChild("Base");
        this.Head = this.Base.getChild("Head");
        this.Body = this.Base.getChild("Body");
        this.RightArm = this.Base.getChild("RightArm");
        this.LeftArm = this.Base.getChild("LeftArm");
        this.RightLeg = this.Base.getChild("RightLeg");
        this.LeftLeg = this.Base.getChild("LeftLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Base = partdefinition.addOrReplaceChild("Base", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Head = Base.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition Hair_Back_r1 = Head.addOrReplaceChild("Hair_Back_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-0.2556F, -3.2214F, -4.0F, 0.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2556F, -8.7786F, 5.0F, 1.4815F, -1.461F, -1.4894F));

        PartDefinition Hair_Left_r1 = Head.addOrReplaceChild("Hair_Left_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-0.2557F, -3.2214F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2557F, -8.7786F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition Hair_Right_r1 = Head.addOrReplaceChild("Hair_Right_r1", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-0.2557F, -3.2214F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.7443F, -8.7786F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition Body = Base.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -4.0F, -2.0F, 10.0F, 15.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 16).addBox(-5.0F, -4.0F, -2.0F, 10.0F, 15.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition RightArm = Base.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(20, 35).addBox(-3.0F, -6.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(18, 63).addBox(-3.0F, -6.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

        PartDefinition LeftArm = Base.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(20, 35).mirror().addBox(0.0F, -6.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 63).addBox(0.0F, -6.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(5.0F, -22.0F, 0.0F));

        PartDefinition RightLeg = Base.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(38, 37).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(71, 30).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

        PartDefinition LeftLeg = Base.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(38, 37).mirror().addBox(-1.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(85, 30).addBox(-1.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, -12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }



    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {

    }

    @Override
    public ModelPart root() {
        return Base;
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.Head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.Head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);




        this.LeftArm.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F;
        this.LeftArm.yRot = 0.0F;
        this.LeftArm.zRot = 0.0F;
        this.RightArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.RightArm.yRot = 0.0F;
        this.RightArm.zRot = 0.0F;
        this.RightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.RightLeg.yRot = 0.0F;
        this.RightLeg.zRot = 0.0F;
        this.LeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount * 0.5F;
        this.LeftLeg.yRot = 0.0F;
        this.LeftLeg.zRot = 0.0F;
    }
}