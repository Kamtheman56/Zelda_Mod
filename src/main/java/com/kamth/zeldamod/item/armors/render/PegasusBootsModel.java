package com.kamth.zeldamod.item.armors.render;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class PegasusBootsModel extends HumanoidModel<LivingEntity> {
    public PegasusBootsModel(ModelPart root) {
        super((root));
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, LayerDefinitions.OUTER_ARMOR_DEFORMATION), PartPose.ZERO);
        PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, LayerDefinitions.OUTER_ARMOR_DEFORMATION), PartPose.ZERO);
        PartDefinition cube_r1 = rightLeg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-3, -3).addBox(-2.32F, 7.0F, 1.3F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1F, 0F, 0.0F));
        PartDefinition cube_r2 = leftLeg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-3, -3).addBox(2.32F, 7.0F, 1.3F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1F, 0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}