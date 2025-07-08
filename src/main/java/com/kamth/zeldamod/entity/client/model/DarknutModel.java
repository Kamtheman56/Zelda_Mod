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

        private final ModelPart darknut;
        private final ModelPart head;
        private final ModelPart body;
        private final ModelPart rightArm;
        private final ModelPart leftArm;
        private final ModelPart rightLeg;
        private final ModelPart leftLeg;



        public DarknutModel(ModelPart root) {
            this.darknut = root.getChild("darknut");
            this.head = darknut.getChild("head");
            this.body = darknut.getChild("body");
            this.rightArm = body.getChild("arm_r");
            this.leftArm = body.getChild("arm_l");
            this.rightLeg = darknut.getChild("leg_r");
            this.leftLeg = darknut.getChild("leg_l");

        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition darknut = partdefinition.addOrReplaceChild("darknut", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -3.098F, 0.0F));

            PartDefinition body = darknut.addOrReplaceChild("body", CubeListBuilder.create().texOffs(30, 28).addBox(-4.002F, -10.4F, -1.998F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.002F, -13.6F, 0.998F));

            PartDefinition arm_l = body.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.998F, -10.4F, -0.998F));

            PartDefinition pad_l = arm_l.addOrReplaceChild("pad_l", CubeListBuilder.create().texOffs(42, 0).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -0.5F, 1.0F));

            PartDefinition arm_r = body.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.002F, -10.4F, 0.002F));

            PartDefinition pad_r = arm_r.addOrReplaceChild("pad_r", CubeListBuilder.create().texOffs(34, 16).addBox(-11.0F, -13.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 12.0F, 0.0F));

            PartDefinition head = darknut.addOrReplaceChild("head", CubeListBuilder.create().texOffs(16, 25).addBox(0.0F, -9.0F, -4.702F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                    .texOffs(18, 0).addBox(-8.0F, -5.0F, -0.702F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(50, 7).addBox(4.0F, -5.0F, -0.702F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                    .texOffs(18, 0).addBox(-4.0F, -3.0F, -4.702F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 48).addBox(-4.0F, -3.0F, 3.308F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -29.0F, 1.702F));

            PartDefinition leg_r = darknut.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(16, 40).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -11.0F, 1.0F));

            PartDefinition leg_l = darknut.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(32, 44).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -11.0F, 1.0F));

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





    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        this.getArm(pSide).offsetPos(new Vector3f(0,10.5f,0));
        this.getArm(pSide).translateAndRotate(pPoseStack);
    }

    private ModelPart getArm(HumanoidArm pArm) {
        return pArm == HumanoidArm.RIGHT ? this.leftArm : this.rightArm;
    }


    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);


        this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightArm.yRot = 0.0F;
        this.rightArm.zRot = 0.0F;
        this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.leftArm.yRot = 0.0F;
        this.leftArm.zRot = 0.0F;
        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.rightLeg.yRot = 0.0F;
        this.rightLeg.zRot = 0.0F;
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount * 0.5F;
        this.leftLeg.yRot = 0.0F;
        this.leftLeg.zRot = 0.0F;


        ItemStack itemstack = entity.getMainHandItem();
        if (entity.isAggressive() && (itemstack.isEmpty() || !itemstack.is(Items.BOW))) {

            float f = Mth.sin(this.attackTime * (float)Math.PI);
            float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);

            this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;

            AnimationUtils.bobArms(this.rightArm, this.leftArm, ageInTicks);
        }
    }
}