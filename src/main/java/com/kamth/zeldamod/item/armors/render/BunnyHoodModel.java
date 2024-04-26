package com.kamth.zeldamod.item.armors.render;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class BunnyHoodModel extends HumanoidModel<LivingEntity> {
    public BunnyHoodModel(ModelPart root) {
        super((root));
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(5, 32).addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
        PartDefinition armorHead = head.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(27, 46).addBox(-5.0F, -8.99F, -4.0F, 10.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 56).addBox(-1.0F, -2.1F, -1.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -12.5F, -2.0F, 0.0F, 0.0F, 0.2618F));
        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(13, 56).addBox(-1.0F, -2.4F, -0.5F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -12.5F, -2.5F, 0.0F, 0.0F, -0.0873F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}