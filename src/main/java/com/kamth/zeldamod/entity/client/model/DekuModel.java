package com.kamth.zeldamod.entity.client.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.kamth.zeldamod.entity.animations.ModAnimationDefinitions;
import com.kamth.zeldamod.entity.mobs.hostile.deku.DekuScrubEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class DekuModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart deku;
	private final ModelPart head;



	public DekuModel(ModelPart root) {
		this.deku = root.getChild("deku");
		this.head = deku.getChild("head");
	}



	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition deku = partdefinition.addOrReplaceChild("deku", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, 0.0F));

		PartDefinition head = deku.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition leaf = head.addOrReplaceChild("leaf", CubeListBuilder.create().texOffs(0, 52).addBox(-3.9366F, -0.5436F, -4.0404F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0634F, -9.3198F, 0.3977F));

		PartDefinition back_leaf_r1 = leaf.addOrReplaceChild("back_leaf_r1", CubeListBuilder.create().texOffs(32, 10).addBox(0.5F, -4.0F, -4.1F, 0.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0634F, -3.5436F, 4.9596F, 1.5708F, -1.0908F, 1.5708F));

		PartDefinition back_leaf_r2 = leaf.addOrReplaceChild("back_leaf_r2", CubeListBuilder.create().texOffs(32, 10).addBox(0.1F, -4.0F, -4.1F, 0.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0634F, 3.4564F, 4.9596F, 1.5708F, 1.3526F, 1.5708F));

		PartDefinition right_leaf_r1 = leaf.addOrReplaceChild("right_leaf_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-0.7546F, -3.0F, -4.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.582F, -2.4164F, -0.0404F, 0.0F, 0.0F, 2.7489F));

		PartDefinition right_leaf_r2 = leaf.addOrReplaceChild("right_leaf_r2", CubeListBuilder.create().texOffs(32, 0).addBox(-0.1934F, -3.0F, -4.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3932F, 2.6255F, -0.0404F, 0.0F, 0.0F, 0.2182F));

		PartDefinition left_leaf_r1 = leaf.addOrReplaceChild("left_leaf_r1", CubeListBuilder.create().texOffs(32, -8).addBox(-0.5642F, -3.0F, -4.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.2275F, -2.8702F, -0.0404F, 0.0F, 0.0F, -2.7925F));

		PartDefinition left_leaf_r2 = leaf.addOrReplaceChild("left_leaf_r2", CubeListBuilder.create().texOffs(32, -8).addBox(1.2F, -1.0F, -5.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0634F, 0.4564F, 0.9596F, 0.0F, 0.0F, -0.1745F));

		PartDefinition front_leaf_r1 = leaf.addOrReplaceChild("front_leaf_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, -3.0F, 0.2F, 8.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0634F, -2.5436F, -5.0404F, -2.6616F, 0.0F, 0.0F));

		PartDefinition front_leaf_r2 = leaf.addOrReplaceChild("front_leaf_r2", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, -3.0F, 0.2F, 8.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0634F, 2.4564F, -5.0404F, -0.2182F, 0.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(16, 22).addBox(-2.0F, -2.0F, -1.5F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -5.5F));

		PartDefinition left_leg = deku.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 40).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

		PartDefinition right_leg = deku.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(9, 38).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

		PartDefinition body = deku.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 30).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);
		this.animateWalk(ModAnimationDefinitions.deku_walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((DekuScrubEntity) entity).sitAnimationState, ModAnimationDefinitions.deku_hide, ageInTicks, 1.0F);
		this.animate(((DekuScrubEntity) entity).idleAnimationState, ModAnimationDefinitions.deku_idle, ageInTicks, 1f);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		deku.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return deku;
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

}