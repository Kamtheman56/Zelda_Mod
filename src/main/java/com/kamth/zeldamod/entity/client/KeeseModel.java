package com.kamth.zeldamod.entity.client;

import com.kamth.zeldamod.entity.animations.ModAnimationDefinitions;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class KeeseModel<T extends Entity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    private final ModelPart keese;
    private final ModelPart head;


    public KeeseModel(ModelPart root) {
        this.keese = root.getChild("keese");
    this.head = keese.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition keese = partdefinition.addOrReplaceChild("keese", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = keese.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 22).addBox(-3.0F, -1.0F, -1.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition feet = head.addOrReplaceChild("feet", CubeListBuilder.create().texOffs(1, 30).addBox(-3.0F, 0.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(4, 30).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 30).addBox(1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition rightEar = head.addOrReplaceChild("rightEar", CubeListBuilder.create().texOffs(1, 15).addBox(-2.5F, -2.0F, 1.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(1, 10).addBox(-2.5F, -2.0F, 1.01F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -2.0F, 0.0F));

        PartDefinition leftEar = head.addOrReplaceChild("leftEar", CubeListBuilder.create().texOffs(8, 15).addBox(-0.1F, -1.0F, 1.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(19, 25).addBox(-0.1F, -1.0F, 1.01F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.1F, -3.0F, 0.0F));

        PartDefinition body = keese.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(12, 0).addBox(-3.0F, -2.0F, 0.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

        PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(16, 0).addBox(-7.0F, -2.0F, 0.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

        PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(12, 7).addBox(1.0F, -2.0F, 0.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, 0.0F));

        PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(16, 8).addBox(1.0F, -2.0F, 0.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }
    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
        this.animateWalk(ModAnimationDefinitions.keese_walk, limbSwing, limbSwingAmount, 2f, 2.5f);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        keese.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return keese;
    }
    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }
}