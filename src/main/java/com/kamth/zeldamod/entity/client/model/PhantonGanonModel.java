package com.kamth.zeldamod.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.joml.Vector3f;

public class PhantonGanonModel <T extends Mob> extends HierarchicalModel<T> implements ArmedModel {
    private final ModelPart Ganon;
    private final ModelPart Head;
    private final ModelPart Body;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;

    public PhantonGanonModel(ModelPart root) {
        this.Ganon = root.getChild("Ganon");
        this.Head = this.Ganon.getChild("Head");
        this.Body = this.Ganon.getChild("Body");
        this.RightArm = this.Ganon.getChild("RightArm");
        this.LeftArm = this.Ganon.getChild("LeftArm");
        this.RightLeg = this.Ganon.getChild("RightLeg");
        this.LeftLeg = this.Ganon.getChild("LeftLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Ganon = partdefinition.addOrReplaceChild("Ganon", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Head = Ganon.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition Hair_Back_r1 = Head.addOrReplaceChild("Hair_Back_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-0.2556F, -3.2214F, -4.0F, 0.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2556F, -8.7786F, 5.0F, 1.4815F, -1.461F, -1.4894F));

        PartDefinition Hair_Left_r1 = Head.addOrReplaceChild("Hair_Left_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-0.2557F, -3.2214F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2557F, -8.7786F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition Hair_Right_r1 = Head.addOrReplaceChild("Hair_Right_r1", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-0.2557F, -3.2214F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.7443F, -8.7786F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition Body = Ganon.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -4.0F, -2.0F, 10.0F, 15.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 16).addBox(-5.0F, -4.0F, -2.0F, 10.0F, 15.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition RightArm = Ganon.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(20, 35).addBox(-3.0F, -6.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(18, 63).addBox(-3.0F, -6.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

        PartDefinition LeftArm = Ganon.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(20, 35).mirror().addBox(0.0F, -6.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 63).addBox(0.0F, -6.0F, -2.0F, 3.0F, 16.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(5.0F, -22.0F, 0.0F));

        PartDefinition RightLeg = Ganon.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(38, 37).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(71, 30).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

        PartDefinition LeftLeg = Ganon.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(38, 37).mirror().addBox(-1.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(85, 30).addBox(-1.0F, -1.0F, -1.0F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, -12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {

    }

    @Override
    public ModelPart root() {
        return Ganon;
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        Ganon.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }
}
