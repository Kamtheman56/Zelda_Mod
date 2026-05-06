package com.kamth.zeldamod.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.joml.Vector3f;

public class PhantonGanonModel <T extends Mob> extends HierarchicalModel<T> implements ArmedModel {
    private final ModelPart Ganon;
    private final ModelPart Head;
    private final ModelPart Body;
    private final ModelPart Right_Arm;
    private final ModelPart Left_Arm;
    private final ModelPart Right_Leg;
    private final ModelPart Left_Leg;

    public PhantonGanonModel(ModelPart root) {
        this.Ganon = root.getChild("Ganon");
        this.Head = this.Ganon.getChild("Head");
        this.Body = this.Ganon.getChild("Body");
        this.Right_Arm = this.Ganon.getChild("Right_Arm");
        this.Left_Arm = this.Ganon.getChild("Left_Arm");
        this.Right_Leg = this.Ganon.getChild("Right_Leg");
        this.Left_Leg = this.Ganon.getChild("Left_Leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Base = partdefinition.addOrReplaceChild("Ganon", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Head = Base.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-4.0F, -6.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -30.0F, 0.0F));

        PartDefinition Hair_Back_r1 = Head.addOrReplaceChild("Hair_Back_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-0.2556F, -3.2214F, -4.0F, 0.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2556F, -2.7786F, 5.0F, 1.4815F, -1.461F, -1.4894F));

        PartDefinition Hair_Left_r1 = Head.addOrReplaceChild("Hair_Left_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-0.2557F, -3.2214F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2557F, -2.7786F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition Hair_Right_r1 = Head.addOrReplaceChild("Hair_Right_r1", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-0.2557F, -3.2214F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.7443F, -2.7786F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition Body = Base.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -4.0F, -2.0F, 10.0F, 15.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 16).addBox(-5.0F, -4.0F, -2.0F, 10.0F, 15.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition RightArm = Base.addOrReplaceChild("Right_Arm", CubeListBuilder.create().texOffs(20, 35).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(18, 63).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-6.5F, -25.0F, 0.0F));

        PartDefinition LeftArm = Base.addOrReplaceChild("Left_Arm", CubeListBuilder.create().texOffs(20, 35).mirror().addBox(-1.5F, -1.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 63).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(6.5F, -25.0F, 0.0F));

        PartDefinition RightLeg = Base.addOrReplaceChild("Right_Leg", CubeListBuilder.create().texOffs(38, 37).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(71, 30).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

        PartDefinition LeftLeg = Base.addOrReplaceChild("Left_Leg", CubeListBuilder.create().texOffs(38, 37).mirror().addBox(-1.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(85, 30).addBox(-1.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, -12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    protected ModelPart getArm(HumanoidArm pSide) {
        return pSide == HumanoidArm.LEFT ? this.Left_Arm : this.Right_Arm;
    }
    @Override
    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        ModelPart modelpart = this.getArm(pSide);
        boolean flag = pSide == HumanoidArm.RIGHT;
        float f = pSide == HumanoidArm.RIGHT ? 27F : -27F;
        float z = pSide == HumanoidArm.RIGHT ? -0.7F : 0.7F;

        modelpart.y += f;
        modelpart.y -= f;
        modelpart.z += f;
        modelpart.z -= f;
        this.getArm(pSide).offsetPos(new Vector3f(1.2f,f, 0f));
        this.getArm(pSide).translateAndRotate(pPoseStack);
        pPoseStack.scale(1.2F, 1.2F, 1.2F);
        this.offsetStackPosition(pPoseStack, flag);
    }

    private void offsetStackPosition(PoseStack pPoseStack, boolean p_263414_) {
        if (p_263414_) {
            pPoseStack.translate(0, 0, -.1D);
        } else {
            pPoseStack.translate(0, 0, -.1D);
        }

    }
    @Override
    public ModelPart root() {
        return Ganon;
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        Ganon.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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


        this.Left_Arm.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F;
        this.Left_Arm.yRot = 0.0F;
        this.Left_Arm.zRot = 0.0F;
        this.Right_Arm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.Right_Arm.yRot = 0.0F;
        this.Right_Arm.zRot = 0.0F;
        this.Right_Leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.Right_Leg.yRot = 0.0F;
        this.Right_Leg.zRot = 0.0F;
        this.Left_Leg.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount * 0.5F;
        this.Left_Leg.yRot = 0.0F;
        this.Left_Leg.zRot = 0.0F;


        ItemStack itemstack = entity.getMainHandItem();

        if (entity.isAggressive() && (itemstack.isEmpty()  || !itemstack.is(Items.BOW))) {
            float f = Mth.sin(this.attackTime * (float)Math.PI);
            float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
            this.setAttacking(entity.getMainHandItem(), entity.getOffhandItem(), f, f1);
            AnimationUtils.bobArms(this.Right_Arm, this.Left_Arm, ageInTicks);
        }
    }
    private void setAttacking(ItemStack pRightHandItem, ItemStack pLeftHandItem, float limbswing, float attacktime) {
        if (pRightHandItem.isEmpty() && pLeftHandItem.isEmpty()) {
            this.Right_Arm.xRot = -1.2217305F;
            this.Right_Arm.yRot = 0.2617994F;
            this.Right_Arm.zRot = -0.47123888F - limbswing;
            this.Left_Arm.xRot = -1.2217305F;
            this.Left_Arm.yRot = -0.2617994F;
            this.Left_Arm.zRot = 0.47123888F + limbswing;
        } else {
            if (!pRightHandItem.isEmpty()) {
                this.Right_Arm.zRot = 0.0F;
                this.Right_Arm.yRot = -(0.1F - limbswing * 0.6F);
                this.Right_Arm.xRot = (-(float)Math.PI / 2F);
                this.Right_Arm.xRot -= limbswing * 1.2F - attacktime * 0.4F;

            }
            if (!pLeftHandItem.isEmpty()) {

                this.Left_Arm.zRot = 0.0F;
                this.Left_Arm.yRot = 0.1F - limbswing * 0.6F;
                this.Left_Arm.xRot = (-(float)Math.PI / 2F);
                this.Left_Arm.xRot -= limbswing * 1.2F - attacktime * 0.4F;
            }
        }
    }
}
