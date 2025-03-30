package com.kamth.zeldamod.client.models;

// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BombchuModel<T extends Entity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "bombchu"), "main");
    private final ModelPart Bombchu;

    public BombchuModel(ModelPart root) {
        this.Bombchu = root.getChild("Bombchu");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Bombchu = partdefinition.addOrReplaceChild("Bombchu", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 0.0F));

        PartDefinition Body = Bombchu.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(1, 22).addBox(-1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

        PartDefinition Head = Bombchu.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(20, 0).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 6).addBox(-1.0F, 1.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 61).addBox(-4.0F, 0.0F, -5.02F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

        PartDefinition Fins = Head.addOrReplaceChild("Fins", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Left = Fins.addOrReplaceChild("Left", CubeListBuilder.create().texOffs(12, 10).addBox(2.0F, 1.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(31, 13).addBox(2.0F, 0.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(32, 13).addBox(2.0F, 5.0F, -4.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Right = Fins.addOrReplaceChild("Right", CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, 1.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(31, 13).addBox(-3.0F, 0.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(32, 13).mirror().addBox(-3.0F, 5.0F, -4.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Bombchu.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}