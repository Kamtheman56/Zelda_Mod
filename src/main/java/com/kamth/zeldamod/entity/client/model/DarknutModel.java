package com.kamth.zeldamod.entity.client.model;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


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

public class DarknutModel<T extends Mob> extends HierarchicalModel<T> implements ArmedModel {
        // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    private final ModelPart Darknut;
    private final ModelPart Head;
    private final ModelPart Body;
    private final ModelPart Right_Arm;
    private final ModelPart Left_Arm;
    private final ModelPart Right_Leg;
    private final ModelPart Left_Leg;

    public DarknutModel(ModelPart root) {
        this.Darknut = root.getChild("Darknut");
        this.Head = this.Darknut.getChild("Head");
        this.Body = this.Darknut.getChild("Body");
        this.Right_Arm = this.Darknut.getChild("Right Arm");
        this.Left_Arm = this.Darknut.getChild("Left Arm");
        this.Right_Leg = this.Darknut.getChild("Right Leg");
        this.Left_Leg = this.Darknut.getChild("Left Leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Darknut = partdefinition.addOrReplaceChild("Darknut", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Head = Darknut.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
                .texOffs(40, 16).addBox(-8.0F, -10.0F, 1.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(40, 20).addBox(4.0F, -10.0F, 1.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(32, 39).addBox(0.0F, -14.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Body = Darknut.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition RightArm = Darknut.addOrReplaceChild("Right Arm", CubeListBuilder.create().texOffs(24, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(32, 32).addBox(-5.0F, -3.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition LeftArm = Darknut.addOrReplaceChild("Left Arm", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(32, 32).addBox(-1.0F, -3.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition RightLeg = Darknut.addOrReplaceChild("Right Leg", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition LeftLeg = Darknut.addOrReplaceChild("Left Leg", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }



    @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        Darknut.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        }

        @Override
        public ModelPart root() {
            return Darknut;
        }


        private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
            pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
            pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

            this.Head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
            this.Head.xRot = pHeadPitch * ((float)Math.PI / 180F);
        }





    protected ModelPart getArm(HumanoidArm pSide) {
        return pSide == HumanoidArm.LEFT ? this.Left_Arm : this.Right_Arm;
    }

    @Override
    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        this.getArm(pSide).offsetPos(new Vector3f(0,0.5f,0));
        this.getArm(pSide).translateAndRotate(pPoseStack);


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