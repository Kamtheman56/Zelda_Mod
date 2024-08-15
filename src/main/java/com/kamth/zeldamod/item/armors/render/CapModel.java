package com.kamth.zeldamod.item.armors.render;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class CapModel extends HumanoidModel<LivingEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor


	public CapModel(ModelPart root) {
		super((root));}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0.0F);

		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
				PartDefinition Head2 = partdefinition.addOrReplaceChild("Head2", CubeListBuilder.create()	.texOffs(0, 14).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),PartPose.offset(0.0F, 0.0F, 0.0F));
						PartDefinition Head3 = partdefinition.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(32, 33).addBox(-4.0F, -11.0F, -4.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.4F)), PartPose.offset(0.0F, 0.0F, 0.0F));
								PartDefinition Head4 = partdefinition.addOrReplaceChild("Head4", CubeListBuilder.create().texOffs(-2, 34).addBox(-4.0F, -13.4F, -3.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail_r1 = Head.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(36, 0).addBox(-3.0F, -1.0F, 0.0F, 6.0F, 2.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -12.2158F, 4.0478F, -1.0036F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}