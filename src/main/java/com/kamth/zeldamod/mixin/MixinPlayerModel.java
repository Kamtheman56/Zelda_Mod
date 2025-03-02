package com.kamth.zeldamod.mixin;



import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.item.items.grapples.ClawshotItem;
import com.kamth.zeldamod.item.items.movement.AscendItem;
import com.kamth.zeldamod.item.items.movement.DekuLeafItem;
import com.kamth.zeldamod.item.items.movement.GliderItem;
import com.kamth.zeldamod.item.masks.transformation.ZoraMask;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerModel.class)
public abstract class MixinPlayerModel extends HumanoidModel<LivingEntity> {
    @Unique
    private static final float ARM_ROTATION = (float)(Math.PI*2-2.9);

    public MixinPlayerModel(ModelPart part){
        super(part);
    }

    @Inject(
            method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V",
            at = {@At(shift = At.Shift.AFTER, value = "INVOKE", target = "Lnet/minecraft/client/model/HumanoidModel;setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V")
            }
    )
    public void onSetRotationAngles(LivingEntity entity,
                                    float limbSwing,
                                    float limbSwingAmount,
                                    float ageInTicks,
                                    float netHeadYaw,
                                    float headPitch,
                                    CallbackInfo ci) {
        ItemStack stack = entity.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack main_item = entity.getItemBySlot(EquipmentSlot.MAINHAND);
        ItemStack secondary_item = entity.getItemBySlot(EquipmentSlot.OFFHAND);

        if (stack.getItem() instanceof ZoraMask && entity.isSwimming()) {
            leftArm.xRot = ARM_ROTATION;
            leftArm.zRot = 34.5f;
            rightArm.xRot = ARM_ROTATION;
            rightArm.zRot = -34.5f;
        }
        if (main_item.getItem() instanceof AscendItem && entity.isUsingItem()) {
            rightArm.xRot = ARM_ROTATION;
            rightArm.zRot = 0f;
        }
        if (main_item.is(ModTags.Items.GLIDERS) && entity.isUsingItem() || secondary_item.is(ModTags.Items.GLIDERS) && entity.isUsingItem()) {
            leftArm.xRot = ARM_ROTATION;
            leftArm.zRot = 0f;
            rightArm.xRot = ARM_ROTATION;
            rightArm.zRot = 0f;
        }

        if (!entity.isVisuallySwimming() && main_item.getItem() instanceof ClawshotItem) {
            this.rightArm.yRot = -0.1F + this.head.yRot;
            this.rightArm.xRot = (-(float) Math.PI / 2F) + this.head.xRot;
        }
        if (!entity.isVisuallySwimming() && secondary_item.getItem() instanceof ClawshotItem) {
            this.leftArm.yRot = 0.1F + this.head.yRot;
            this.leftArm.xRot = (-(float) Math.PI / 2F) + this.head.xRot;
        }
    }


}