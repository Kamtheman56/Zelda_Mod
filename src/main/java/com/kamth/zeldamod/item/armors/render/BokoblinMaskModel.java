package com.kamth.zeldamod.item.armors.render;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class BokoblinMaskModel extends HumanoidModel<LivingEntity> {
    public BokoblinMaskModel(ModelPart root) {
        super((root));
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();


        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, LayerDefinitions.INNER_ARMOR_DEFORMATION), PartPose.ZERO);


        PartDefinition armorhead = partdefinition.addOrReplaceChild("armorhead", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bb_main = head.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 25).addBox(-3.0F, -33.0F, -5.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 28).addBox(-2.0F, -34.0F, -5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(4.0F, -31.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.5F))
                .texOffs(56, 4).addBox(-5.0F, -31.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Horn2_r1 = head.addOrReplaceChild("Horn2_r1", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-4.0F, -4.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -12F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition Horn1_r1 = head.addOrReplaceChild("Horn1_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -4.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12F, 0.0F, 0.0F, -2.5307F, 0.0F));

        PartDefinition EarRight_r1 = head.addOrReplaceChild("EarRight_r1", CubeListBuilder.create().texOffs(16, 22).addBox(-6.0F, -3.0F, 0.0F, 9.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -5.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition EarLeft_r1 = head.addOrReplaceChild("EarLeft_r1", CubeListBuilder.create().texOffs(16, 16).addBox(-3.0F, -3.0F, 0.0F, 9.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -5.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
