package com.kamth.zeldamod.entity.client.model;

import com.kamth.zeldamod.entity.animations.ModAnimationDefinitions;
import com.kamth.zeldamod.entity.mobs.friendly.KorokEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;

public class KorokModel<T extends Entity> extends HierarchicalModel<T> implements ArmedModel {
    private final ModelPart korok;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public KorokModel(ModelPart root) {
        this.korok = root.getChild("korok");
        this.right_arm = this.korok.getChild("right_arm");
        this.left_arm = this.korok.getChild("left_arm");
        this.body = this.korok.getChild("body");
        this.head = this.body.getChild("head");
        this.left_leg = this.body.getChild("left_leg");
        this.right_leg = this.body.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition korok = partdefinition.addOrReplaceChild("korok", CubeListBuilder.create(), PartPose.offset(-2.0F, 22.5F, -1.0F));

        PartDefinition right_arm = korok.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-2.5F, -5.0F, 0.0F));

        PartDefinition right_arm_r1 = right_arm.addOrReplaceChild("right_arm_r1", CubeListBuilder.create().texOffs(16, 21).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition left_arm = korok.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(6.5F, -5.0F, 0.0F));

        PartDefinition left_arm_r1 = left_arm.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(16, 14).addBox(-1.0F, -1.5F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition body = korok.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(2.0F, -0.5F, 5.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 48).addBox(-8.0F, -8.0F, -1.525F, 16.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(-2.5F, -9.0F, -0.495F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 0).addBox(0.5F, -7.0F, 0.505F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.5F, -4.0F, -1.495F, 7.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -6.505F));

        PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 38).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.5F, -5.0F));

        PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(11, 38).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.5F, -5.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animateWalk(ModAnimationDefinitions.korok_walk, limbSwing, limbSwingAmount, 2f, 1f);
        this.animate(((KorokEntity) entity).idleAnimationState, ModAnimationDefinitions.korok_idle, ageInTicks, 1f);
        if (entity instanceof KorokEntity korok) {
            if (korok.isPartyKorok()){
                this.animate(((KorokEntity) entity).danceAnimationState, ModAnimationDefinitions.korok_dance, ageInTicks, 1f);
            }
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        korok.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return korok;
    }

    @Override
    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        boolean flag = pSide == HumanoidArm.RIGHT;
        ModelPart modelpart = flag ? this.right_arm : this.left_arm;
        this.root().translateAndRotate(pPoseStack);
        this.body.translateAndRotate(pPoseStack);
        modelpart.translateAndRotate(pPoseStack);
        pPoseStack.scale(0.55F, 0.55F, 0.55F);
        this.offsetStackPosition(pPoseStack, flag);
    }

    private void offsetStackPosition(PoseStack pPoseStack, boolean p_263414_) {
        if (p_263414_) {
            pPoseStack.translate(0.046875D, -0.15625D, 0.078125D);
        } else {
            pPoseStack.translate(-0.046875D, -0.15625D, 0.078125D);
        }

    }
}