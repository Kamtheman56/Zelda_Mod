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

public class IronKnuckleModel<T extends Mob> extends HierarchicalModel<T> implements ArmedModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    private final ModelPart iron_knuckle;
    private final ModelPart body;
    private final ModelPart armor;
    private final ModelPart arm_r;
    private final ModelPart pad_r;
    private final ModelPart weapon;
    private final ModelPart arm_l;
    private final ModelPart pad_l;
    private final ModelPart head;
    private final ModelPart leg_r;
    private final ModelPart leg_l;

    public IronKnuckleModel(ModelPart root) {
        this.iron_knuckle = root.getChild("iron_knuckle");
        this.body = this.iron_knuckle.getChild("body");
        this.armor = this.body.getChild("armor");
        this.arm_r = this.armor.getChild("arm_r");
        this.pad_r = this.arm_r.getChild("pad_r");
        this.weapon = this.arm_r.getChild("weapon");
        this.arm_l = this.armor.getChild("arm_l");
        this.pad_l = this.arm_l.getChild("pad_l");
        this.head = this.body.getChild("head");
        this.leg_r = this.iron_knuckle.getChild("leg_r");
        this.leg_l = this.iron_knuckle.getChild("leg_l");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition iron_knuckle = partdefinition.addOrReplaceChild("iron_knuckle", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -3.098F, 0.0F));

        PartDefinition body = iron_knuckle.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.002F, -16.6F, 0.998F));

        PartDefinition armor = body.addOrReplaceChild("armor", CubeListBuilder.create().texOffs(5, 110).addBox(-7.0F, 5.0F, -3.0F, 14.0F, 5.0F, 6.0F, new CubeDeformation(0.5F))
                .texOffs(4, 84).addBox(-7.002F, -11.4F, -2.998F, 14.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition arm_r = armor.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(60, 0).addBox(-2.0F, 0.0F, -1.0F, 5.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(9.998F, -8.4F, -0.998F));

        PartDefinition pad_r = arm_r.addOrReplaceChild("pad_r", CubeListBuilder.create().texOffs(60, 20).addBox(-4.0F, -2.5F, -3.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(60, 20).addBox(-6.0F, -5.5F, -3.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -0.5F, 1.0F));

        PartDefinition weapon = arm_r.addOrReplaceChild("weapon", CubeListBuilder.create(), PartPose.offset(1.0F, 14.0F, 8.375F));

        PartDefinition arm_l = armor.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(22, 56).addBox(-2.0F, 0.0F, -2.0F, 5.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.002F, -8.4F, 0.002F));

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
        iron_knuckle.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return iron_knuckle;
    }
    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);


        this.arm_l.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F;
        this.arm_l.yRot = 0.0F;
        this.arm_l.zRot = 0.0F;
        this.arm_r.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.arm_r.yRot = 0.0F;
        this.arm_r.zRot = 0.0F;
        this.leg_r.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.leg_r.yRot = 0.0F;
        this.leg_r.zRot = 0.0F;
        this.leg_l.xRot = Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount * 0.5F;
        this.leg_l.yRot = 0.0F;
        this.leg_l.zRot = 0.0F;


        ItemStack itemstack = entity.getMainHandItem();

        if (entity.isAggressive() && (itemstack.isEmpty()  || !itemstack.is(Items.BOW))) {
            float f = Mth.sin(this.attackTime * (float)Math.PI);
            float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
            this.setAttacking(entity.getMainHandItem(), entity.getOffhandItem(), f, f1);
            AnimationUtils.bobArms(this.arm_r, this.arm_l, ageInTicks);
        }

    }




    private void setAttacking(ItemStack pRightHandItem, ItemStack pLeftHandItem, float limbswing, float attacktime) {
        if (pRightHandItem.isEmpty() && pLeftHandItem.isEmpty()) {
            this.arm_r.xRot = -1.2217305F;
            this.arm_r.yRot = 0.2617994F;
            this.arm_r.zRot = -0.47123888F - limbswing;
            this.arm_l.xRot = -1.2217305F;
            this.arm_l.yRot = -0.2617994F;
            this.arm_l.zRot = 0.47123888F + limbswing;
        } else {
            if (!pRightHandItem.isEmpty()) {
                this.arm_r.zRot = 0.0F;
                this.arm_r.yRot = -(0.1F - limbswing * 0.6F);
                this.arm_r.xRot = (-(float)Math.PI / 2F);
                this.arm_r.xRot -= limbswing * 1.2F - attacktime * 0.4F;

            }
            if (!pLeftHandItem.isEmpty()) {

                this.arm_l.zRot = 0.0F;
                this.arm_l.yRot = 0.1F - limbswing * 0.6F;
                this.arm_l.xRot = (-(float)Math.PI / 2F);
                this.arm_l.xRot -= limbswing * 1.2F - attacktime * 0.4F;
            }
        }
    }
    protected ModelPart getArm(HumanoidArm pSide) {
        return pSide == HumanoidArm.LEFT ? this.arm_l : this.arm_r;
    }

    @Override
    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        this.getArm(pSide).offsetPos(new Vector3f(0,0.5f,0));
        this.getArm(pSide).translateAndRotate(pPoseStack);


    }
}