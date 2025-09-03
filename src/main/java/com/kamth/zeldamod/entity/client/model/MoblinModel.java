package com.kamth.zeldamod.entity.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.kamth.zeldamod.entity.animations.ModAnimationDefinitions;
import com.kamth.zeldamod.entity.mobs.hostile.bokoblin.BokoblinEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.joml.Vector3f;

public class MoblinModel<T extends Mob> extends HierarchicalModel<T> implements ArmedModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    private final ModelPart Moblin;
    private final ModelPart Head;
    private final ModelPart LeftEar;
    private final ModelPart RightEar;
    private final ModelPart Horn;
    private final ModelPart Nose;
    private final ModelPart Neck;
    private final ModelPart Body;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;

    public MoblinModel(ModelPart root) {
        this.Moblin = root.getChild("Moblin");
        this.Head = this.Moblin.getChild("Head");
        this.LeftEar = this.Head.getChild("LeftEar");
        this.RightEar = this.Head.getChild("RightEar");
        this.Horn = this.Head.getChild("Horn");
        this.Nose = this.Head.getChild("Nose");
        this.Neck = this.Head.getChild("Neck");
        this.Body = this.Moblin.getChild("Body");
        this.RightArm = this.Moblin.getChild("RightArm");
        this.LeftArm = this.Moblin.getChild("LeftArm");
        this.RightLeg = this.Moblin.getChild("RightLeg");
        this.LeftLeg = this.Moblin.getChild("LeftLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Moblin = partdefinition.addOrReplaceChild("Moblin", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Head = Moblin.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(82, 87).addBox(-6.0F, -10.3333F, -13.0F, 12.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -62.6667F, -13.0F));

        PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create().texOffs(124, 82).mirror().addBox(5.0F, -49.0F, -21.0F, 9.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, 38.6667F, 13.0F));

        PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create().texOffs(124, 82).addBox(-16.0F, -49.0F, -21.0F, 9.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 38.6667F, 13.0F));

        PartDefinition Horn = Head.addOrReplaceChild("Horn", CubeListBuilder.create().texOffs(100, 15).addBox(-1.0F, -65.0F, -30.0F, 0.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 38.6667F, 13.0F));

        PartDefinition Nose = Head.addOrReplaceChild("Nose", CubeListBuilder.create().texOffs(82, 44).addBox(-5.0F, -47.0F, -36.0F, 8.0F, 7.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(3, 177).addBox(-5.0F, -40.0F, -32.0F, 8.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 37.6667F, 8.0F));

        PartDefinition Neck = Head.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 92).addBox(-4.0F, -9.3333F, -6.0F, 8.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Body = Moblin.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(1, 1).addBox(-11.0F, -40.0F, -8.0F, 20.0F, 21.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 59).addBox(-9.0F, -19.0F, -6.0F, 16.0F, 14.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(100, 0).addBox(-7.0F, -5.0F, -4.0F, 12.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(64, 44).addBox(-6.0F, 1.0F, -4.01F, 10.0F, 13.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 36).addBox(-10.0F, -49.0F, -10.0F, 18.0F, 9.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition RightArm = Moblin.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(52, 59).addBox(-4.25F, 11.25F, -3.75F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 152).addBox(-5.25F, -1.75F, -5.75F, 12.0F, 13.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-17.75F, -66.25F, -0.25F));

        PartDefinition LeftArm = Moblin.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(52, 59).mirror().addBox(-2.75F, 11.25F, -3.75F, 7.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 152).addBox(-6.75F, -1.75F, -5.75F, 12.0F, 13.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(15.75F, -66.25F, -0.25F));

        PartDefinition RightLeg = Moblin.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(96, 106).addBox(-11.1F, -7.0F, -3.0F, 6.0F, 19.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 116).mirror().addBox(-11.1F, -13.0F, -5.0F, 6.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, -12.0F, 0.0F));

        PartDefinition LeftLeg = Moblin.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(96, 106).mirror().addBox(3.1F, -7.0F, -3.0F, 6.0F, 19.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 116).addBox(3.1F, -13.0F, -5.0F, 6.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }



    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Moblin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }


    @Override
    public ModelPart root() {
        return Moblin;
    }

    protected ModelPart getArm(HumanoidArm pSide) {
        return pSide == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
    }

    @Override
    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        this.getArm(pSide).offsetPos(new Vector3f(10,58.5f,0));
        pPoseStack.scale(2F, 2F, 2F);
        this.getArm(pSide).translateAndRotate(pPoseStack);


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


        this.LeftArm.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.0F * limbSwingAmount * 0.5F;
        this.LeftArm.yRot = 0.0F;
        this.LeftArm.zRot = 0.0F;
        this.RightArm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount * 0.5F;
        this.RightArm.yRot = 0.0F;
        this.RightArm.zRot = 0.0F;
        this.RightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount * 0.5F;
        this.RightLeg.yRot = 0.0F;
        this.RightLeg.zRot = 0.0F;
        this.LeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.2F * limbSwingAmount * 0.5F;
        this.LeftLeg.yRot = 0.0F;
        this.LeftLeg.zRot = 0.0F;

        ItemStack itemstack = entity.getMainHandItem();

        if (entity.isAggressive() && (itemstack.isEmpty()  || !itemstack.is(Items.BOW))) {
            float f = Mth.sin(this.attackTime * (float)Math.PI);
            float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
            this.setAttacking(entity.getMainHandItem(), entity.getOffhandItem(), f, f1);
            AnimationUtils.bobArms(this.RightArm, this.LeftArm, ageInTicks);
        }

        if (entity.isAggressive() && itemstack.is(Items.BOW)) {
            this.setBowAnimating(entity.getMainHandItem(), entity.getOffhandItem(), 1);
            AnimationUtils.bobArms(this.RightArm, this.LeftArm, ageInTicks);
        }

    }




    private void setAttacking(ItemStack pRightHandItem, ItemStack pLeftHandItem, float limbswing, float attacktime) {
        if (pRightHandItem.isEmpty() && pLeftHandItem.isEmpty()) {
            this.RightArm.xRot = -1.2217305F;
            this.RightArm.yRot = 0.2617994F;
            this.RightArm.zRot = -0.47123888F - limbswing;
            this.LeftArm.xRot = -1.2217305F;
            this.LeftArm.yRot = -0.2617994F;
            this.LeftArm.zRot = 0.47123888F + limbswing;
        } else {
            if (!pRightHandItem.isEmpty()) {
                this.RightArm.zRot = 0.0F;
                this.RightArm.yRot = -(0.1F - limbswing * 0.6F);
                this.RightArm.xRot = (-(float)Math.PI / 2F);
                this.RightArm.xRot -= limbswing * 1.2F - attacktime * 0.4F;

            }
            if (!pLeftHandItem.isEmpty()) {

                this.LeftArm.zRot = 0.0F;
                this.LeftArm.yRot = 0.1F - limbswing * 0.6F;
                this.LeftArm.xRot = (-(float)Math.PI / 2F);
                this.LeftArm.xRot -= limbswing * 1.2F - attacktime * 0.4F;
            }
        }
    }

    private void setBowAnimating(ItemStack pRightHandItem, ItemStack pLeftHandItem, float p_265125_) {
        if (!pRightHandItem.isEmpty()) {
            this.RightArm.yRot = -0.1F + this.Head.yRot;
            this.LeftArm.yRot = 0.1F + this.Head.yRot + 0.4F;
            this.RightArm.xRot = (-(float)Math.PI / 2F) + this.Head.xRot;
            this.LeftArm.xRot = (-(float)Math.PI / 2F) + this.Head.xRot;
        }
        if (!pLeftHandItem.isEmpty()) {
            this.LeftArm.yRot = -0.1F + this.Head.yRot;
            this.RightArm.yRot = 0.1F + this.Head.yRot + 0.4F;
            this.LeftArm.xRot = (-(float)Math.PI / 2F) + this.Head.xRot;
            this.RightArm.xRot = (-(float)Math.PI / 2F) + this.Head.xRot;
        }
    }
}