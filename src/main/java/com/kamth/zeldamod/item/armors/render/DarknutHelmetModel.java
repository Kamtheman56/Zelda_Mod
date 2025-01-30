package com.kamth.zeldamod.item.armors.render;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class DarknutHelmetModel extends HumanoidModel<LivingEntity> {
    public DarknutHelmetModel(ModelPart root) {
        super((root));
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();


        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, LayerDefinitions.OUTER_ARMOR_DEFORMATION), PartPose.ZERO);

        PartDefinition armorhead = head.addOrReplaceChild("armorhead", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
        .texOffs(28, 50).addBox(-1.0F, -20.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition extra = head.addOrReplaceChild("extra", CubeListBuilder.create().texOffs(32, 0).addBox(-8.0F, -34.0F, 1.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(32, 4).addBox(4.0F, -34.0F, 1.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(0.0F, -38.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
