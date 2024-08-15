package com.kamth.zeldamod.entity.client;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.kamth.zeldamod.entity.animations.ModAnimationDefinitions;
import com.kamth.zeldamod.entity.mobs.DarknutEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class DarknutModel<T extends Entity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    private final ModelPart darknut;
    private final ModelPart head;

    public DarknutModel(ModelPart root) {
        this.darknut = root.getChild("darknut");
      this.head = darknut.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition darknut = partdefinition.addOrReplaceChild("darknut", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -3.098F, 0.0F));

        PartDefinition body = darknut.addOrReplaceChild("body", CubeListBuilder.create().texOffs(44, 44).addBox(-4.002F, -0.4F, 2.052F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(34, 23).addBox(-4.002F, -0.4F, -2.048F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(48, 43).addBox(4.048F, -0.4F, -1.998F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(18, 0).addBox(-4.002F, -0.4F, -2.048F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(30, 28).addBox(-4.002F, -10.4F, -1.998F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.002F, -13.6F, 0.998F));

        PartDefinition arm_r = body.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.998F, -10.4F, -0.998F));

        PartDefinition pad_r = arm_r.addOrReplaceChild("pad_r", CubeListBuilder.create().texOffs(42, 0).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -0.5F, 1.0F));

        PartDefinition arm_l = body.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.002F, -10.4F, 0.002F));

        PartDefinition cube_r1 = arm_l.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(94, 0).addBox(0.0F, -16.0F, -1.0F, 0.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, -4.0F, -0.6545F, 0.0F, 0.0F));

        PartDefinition pad_l = arm_l.addOrReplaceChild("pad_l", CubeListBuilder.create().texOffs(34, 16).addBox(-11.0F, -13.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 12.0F, 0.0F));

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

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
        this.animateWalk(ModAnimationDefinitions.darknut_walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(((DarknutEntity) entity).idleAnimationState, ModAnimationDefinitions.darknut_idle, ageInTicks, 1f);
        this.animate(((DarknutEntity) entity).attackAnimationState, ModAnimationDefinitions.darknut_swing, ageInTicks, 1f);

    }
}