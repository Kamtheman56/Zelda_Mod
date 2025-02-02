package com.kamth.zeldamod.item.armors.render;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class HatModel extends HumanoidModel<LivingEntity> {
    public HatModel(ModelPart root) {
        super((root));
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, LayerDefinitions.INNER_ARMOR_DEFORMATION), PartPose.ZERO);

        PartDefinition armorhead = head.addOrReplaceChild("armorhead", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, LayerDefinitions.OUTER_ARMOR_DEFORMATION), PartPose.ZERO);


        PartDefinition bb_main = head.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -31.0F, 5.0F, 0.7418F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
