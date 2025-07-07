package com.kamth.zeldamod.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.joml.Vector3f;

public class BokoblinModel<T extends Mob> extends HierarchicalModel<T> implements ArmedModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    private final ModelPart Base;
    private final ModelPart Head;
    private final ModelPart Nose;
    private final ModelPart LeftEar;
    private final ModelPart RightEar;
    private final ModelPart Horn;
    private final ModelPart Body;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;
    public HumanoidModel.ArmPose leftArmPose = HumanoidModel.ArmPose.EMPTY;
    public HumanoidModel.ArmPose rightArmPose = HumanoidModel.ArmPose.EMPTY;

    public BokoblinModel(ModelPart root) {
        this.Base = root.getChild("Base");
        this.Head = this.Base.getChild("Head");
        this.Nose = this.Head.getChild("Nose");
        this.LeftEar = this.Head.getChild("LeftEar");
        this.RightEar = this.Head.getChild("RightEar");
        this.Horn = this.Head.getChild("Horn");
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

        PartDefinition Head = Base.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -7.0F, -4.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(2, 2).addBox(-5.0F, 0.0F, -4.01F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(2, 2).addBox(4.0F, 0.0F, -4.01F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition Nose = Head.addOrReplaceChild("Nose", CubeListBuilder.create().texOffs(18, 16).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 20).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -5.0F));

        PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create().texOffs(0, 16).addBox(5.0F, -6.0F, 0.0F, 9.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-14.0F, -6.0F, 0.0F, 9.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Horn = Head.addOrReplaceChild("Horn", CubeListBuilder.create().texOffs(36, 52).addBox(-7.0F, -19.0F, 0.0F, 14.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(30, 0).addBox(-2.0F, -20.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 17).addBox(-5.0F, -11.0F, -4.0F, 10.0F, 5.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Horn2_r1 = Horn.addOrReplaceChild("Horn2_r1", CubeListBuilder.create().texOffs(48, 8).mirror().addBox(-4.0F, -4.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -11.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition Horn1_r1 = Horn.addOrReplaceChild("Horn1_r1", CubeListBuilder.create().texOffs(48, 8).mirror().addBox(-4.0F, -4.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -11.5F, 0.0F, 0.0F, -2.5307F, 0.0F));

        PartDefinition Body = Base.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 22).addBox(-4.0F, 1.0F, -2.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 47).addBox(-5.0F, 7.0F, -3.0F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(8, 58).addBox(-3.0F, 11.0F, -3.01F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition RightArm = Base.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

        PartDefinition RightArm_r1 = RightArm.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(20, 30).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition LeftArm = Base.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

        PartDefinition LeftArm_r1 = LeftArm.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(20, 30).mirror().addBox(-1.5F, -1.0F, -2.0F, 3.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition RightLeg = Base.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 32).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

        PartDefinition LeftLeg = Base.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-1.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, -12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }




    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }


    @Override
    public ModelPart root() {
        return Base;
    }

    protected ModelPart getArm(HumanoidArm pSide) {
        return pSide == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
    }

    @Override
    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        this.getArm(pSide).offsetPos(new Vector3f(0,25.5f,0));
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